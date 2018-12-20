#!/usr/bin/env python

import json
import yaml
import numpy as np
import rospy
import rospkg
import tf
import actionlib
from move_base_msgs.msg import MoveBaseAction, MoveBaseGoal
import moveit_msgs.msg
import geometry_msgs.msg
from moveit_commander import PlanningSceneInterface
from moveit_commander import roscpp_initialize, RobotCommander
from tf.transformations import quaternion_from_euler
import time
# TODO: refactor classes

class Plan:
    def __init__(self):
        self.id = 0
        self.init = ''
        self.goal = ''
        self.task_list = list()


class Task:
    def __init__(self):
        self.action = ''
        self.robot = ''
        self.target = ''
        self.product = ''


class PlanInterpreter:
    def __init__(self, robot, scene):
        self.shelves = dict()
        self.products = dict()
        self.listener = tf.TransformListener()
        self.robot = robot
        self.scene = scene
        rospy.loginfo('Load moveit_commander')
        rospy.sleep(1)

        rospack = rospkg.RosPack()
        self.path = rospack.get_path('turtlebot2i_warehouse')
        print(self.path)

        self.waypoints = self.load_waypoint(self.path + '/scene.yaml')
        json_plan = self.load_json(self.path + '/plan.json')
        self.plan = self.parse_plan(json_plan)
        self.load_objects(self.path + '/scene.yaml')

        # TODO, check these positions
        self.storage = list(([[0.00, 0.1, 0.22], 0],
                             [[0.00, 0.0, 0.22], 0],
                             [[0.00, -0.1, 0.22], 0]))

    def load_json(self, path):
        return json.load(open(path))

    def load_yaml(self, path):
        stream = file(path, 'r')
        return yaml.load(stream)

    def load_waypoint(self, path):
        yaml = self.load_yaml(path)

        waypoints = {}

        for line in yaml:
            waypoints[line['node']['name']] = line['node']['waypoint']

        return waypoints

    def to_pose(self, waypoint, frame="/map"):
        p = geometry_msgs.msg.PoseStamped()
        p.header.frame_id = frame
        p.header.stamp = rospy.Time.now()
        p.pose.position.x = waypoint[0]
        p.pose.position.y = waypoint[1]
        p.pose.position.z = waypoint[2]
        if len(waypoint) == 7:
            p.pose.orientation.x = waypoint[3]
            p.pose.orientation.y = waypoint[4]
            p.pose.orientation.z = waypoint[5]
            p.pose.orientation.w = waypoint[6]
        elif len(waypoint) == 6:
            q = quaternion_from_euler(waypoint[3]/180.0*np.pi,
                                      waypoint[4]/180.0*np.pi,
                                      waypoint[5]/180.0*np.pi)
            p.pose.orientation.x = q[0]
            p.pose.orientation.y = q[1]
            p.pose.orientation.z = q[2]
            p.pose.orientation.w = q[3]
        return p

    def add_mesh(self, node):
        try:
            self.scene.add_mesh(node['name'],
                                self.to_pose(node['obj_position']),
                                self.path + '/' + node['mesh'])
            rospy.loginfo('New object added: %s', node['name'])
        except:
            rospy.logwarn('Failed to add object: %s %s',
                          node['name'],
                          self.path + '/' + node['mesh'])

    def add_box(self, node, size=[0.035, 0.035, 0.030]):
        try:
            self.scene.add_box(node['name'],
                               self.to_pose(node['obj_position']),
                               size)
            rospy.loginfo('New object added: %s', node['name'])
        except:
            rospy.logwarn('Failed to add product: %s',
                          node['name'])

    def add_product(self, shelf, product, idx=''):
        product = dict(self.products[product])
        product['name'] += idx
        pose = self.shelves[shelf]['obj_position']
        pose = np.asarray(pose[:3]) + np.asarray(product['obj_position'][:3])
        product['obj_position'] = np.hstack(
            (pose, product['obj_position'][3:]))
        self.add_box(product)
        return product['name']

    def obj_position(self, waypoint, offset):
        waypoint = np.asarray(waypoint)
        offset = np.asarray(offset)
        if waypoint.shape[0] != offset.shape[0]:
            rospy.logwarn('Waypoint: [%s] and Offset: [%s] shapes don\'t match',
                          waypoint,
                          offset)
            return waypoint
        return waypoint - offset
    
    def load_objects(self, path):
        shelf_item = list()
        yaml = self.load_yaml(path)
        for line in yaml:
            if 'offset' in line['node'].keys():
                line['node']['obj_position'] = self.obj_position(line['node']['waypoint'],
                                                                 line['node']['offset'])
            else:
                line['node']['obj_position'] = line['node']['waypoint']
            if 'mesh' in line['node'].keys():
                self.add_mesh(line['node'])
            if 'position' in line['node']['name']:
                shelf_item.append(line['node'])
            if 'Shelf' in line['node']['name']:
                self.shelves[
                    line['node']['name']] = line['node']
            if ('product' in line['node']['name']) | ('position' in line['node']['name']):
                self.products[
                    line['node']['name']] = line['node']
#        for i in shelf_item:
#            for j in self.shelves:
#                self.add_product(j, i['name'], idx=j)

    def parse_plan(self, json_plan):
        p_plan = Plan()
        p_plan.id = json_plan['id']
        p_plan.init = json_plan['init']
        p_plan.goal = json_plan['goal']

        for task in json_plan['plan']:
            if (len(task)) >= 3:

                p_task = Task()
                p_task.action = task[0]
                p_task.robot = task[1]
                p_task.target = task[2]
                if (len(task)) == 4:
                    p_task.product = task[3]

                p_plan.task_list.append(p_task)

        return p_plan

    def task_manager(self):
        lastWp = []
        for task in self.plan.task_list:
            if task.action == 'move':
                rospy.loginfo('Move to [%s]', task.target)
                self.__move_task(task.robot, task.target)
                lastWP = self.waypoints[task.target]
            elif task.action == 'pick':
                rospy.loginfo('Moving to [%s] at [%s]',
                              task.product,
                              task.target)
                rospy.loginfo('From [%s] \n to [%s]',
                              lastWP,
                              self.waypoint2pick(lastWP, task.product)
                              )            
                self.__move_pose(
                    self.waypoint2pick(lastWP, task.product)
                )
                product_name = self.add_product(task.target, task.product)
                rospy.loginfo('Picking [%s] at [%s]',
                              task.product,
                              task.target)
                input()
                result = self.__pick_task(product_name,
                                 rpy=[0, np.pi/4.0, 0])
                rospy.loginfo(result)
                if result is None:
                    rospy.loginfo("Pick has failed")
                    #retreat arm
                    continue
                store = self.__available_storage()
                if not store:
                    rospy.loginfo("No available Store")
                else:
                    rospy.loginfo("Storing product on [%s]",
                                  store[0])
                    rospy.loginfo("Storing product on [%s] MAP_FRAME",
                                  self.target_to_frame(store[0]))
                    result = self.__place_task(self.target_to_frame(store[0]),
                                      rpy=[0, 0, 0])
                    if result is None:
                        rospy.loginfo("Place has failed")
                        #drop object or try again
                        continue
                    store[1] = 1
            elif task.action == 'drop':
                rospy.loginfo('Dropping [%s] at [%s]',
                              task.product,
                              task.target)
                self.scene.remove_attached_object('base_footprint',
                                                  task.product)
                self.__pick_task(task.product)
                rospy.loginfo('Dropping at position: [%s]',
                              self.drop_position())
                self.__place_task(self.drop_position())

            # self.__check_task_status()

    def drop_position(self, offset=[0.42, 0, 0.20]):
        return self.target_to_frame(np.asarray(offset))

    def target_to_frame(self, target,
                        frame_to="/map",
                        frame_from="/base_footprint"):
        pose = geometry_msgs.msg.PoseStamped()
        pose.pose.position.x = target[0]
        pose.pose.position.y = target[1]
        pose.pose.position.z = target[2]
        pose.pose.orientation.w = 1
        pose.header.frame_id = frame_from
        self.listener.waitForTransform(frame_from,
                                       frame_to,
                                       rospy.Time.now(),
                                       rospy.Duration(4))
        self.listener.getLatestCommonTime(frame_from, frame_to)
        pose = self.listener.transformPose(frame_to, pose)
        return [pose.pose.position.x,
                pose.pose.position.y,
                pose.pose.position.z]

    def get_attached_product(self, product_name):
        return 'productRed'

    def waypoint2pick(self, waypoint, product):
        ''' waypoint information is always infront of productRed
        '''
        waypoint = np.asarray(waypoint)
        productRed_position = np.asarray(
            self.products['productRed']['obj_position'])
        product_position = np.asarray(
            self.products[product]['obj_position'])
        delta = (productRed_position - product_position)[:3]
        waypoint[:3] -= delta[:3]
        return waypoint

    def __move_pose(self, pose):
        # TODO: The client must be retrieved by the robot name
        client = actionlib.SimpleActionClient('/move_base', MoveBaseAction)
        client.wait_for_server()

        goal = MoveBaseGoal()
        goal.target_pose = self.to_pose(pose)
        client.send_goal(goal)

        wait = client.wait_for_result()
        if not wait:
            rospy.logerr("Action server not available!")
            rospy.signal_shutdown("Action server not available!")
        else:
            state = client.get_state()
        if state == 3:
            rospy.loginfo("Goal succeeded!")    
        
    def __move_task(self, robot, target):
        # TODO: The client must be retrieved by the robot name
        client = actionlib.SimpleActionClient('/move_base', MoveBaseAction)
        client.wait_for_server()

        goal = MoveBaseGoal()
        goal.target_pose = self.to_pose(self.waypoints[target])
        client.send_goal(goal)

        wait = client.wait_for_result()
        if not wait:
            rospy.logerr("Action server not available!")
            rospy.signal_shutdown("Action server not available!")
        else:
            state = client.get_state()
        if state == 3:
            rospy.loginfo("Goal succeeded!")

    def __pick_task(self, target_obj="box", rpy=[]):
        client = actionlib.SimpleActionClient('/pick_custom',
                                              moveit_msgs.msg.PickupAction)
        client.wait_for_server()
        goal = moveit_msgs.msg.PickupActionGoal().goal
        goal.target_name = target_obj
        if len(rpy) > 0:
            goal.possible_grasps = [moveit_msgs.msg.Grasp()]
            q = quaternion_from_euler(rpy[0], rpy[1], rpy[2])
            goal.possible_grasps[0].grasp_pose = geometry_msgs.msg.PoseStamped()
            goal.possible_grasps[0].grasp_pose.pose.orientation.x = q[0]
            goal.possible_grasps[0].grasp_pose.pose.orientation.y = q[1]
            goal.possible_grasps[0].grasp_pose.pose.orientation.z = q[2]
            goal.possible_grasps[0].grasp_pose.pose.orientation.w = q[3]
        client.send_goal(goal)
        client.wait_for_result()
        return client.get_result()

    def __place_task(self, target, rpy=[]):
        client = actionlib.SimpleActionClient('place_custom',
                                              moveit_msgs.msg.PlaceAction)
        client.wait_for_server()
        goal = moveit_msgs.msg.PlaceActionGoal().goal
        goal.place_locations.append(moveit_msgs.msg.PlaceLocation())
        goal.place_locations[0].place_pose = geometry_msgs.msg.PoseStamped()
        goal.place_locations[0].place_pose.pose.position.x = target[0]
        goal.place_locations[0].place_pose.pose.position.y = target[1]
        goal.place_locations[0].place_pose.pose.position.z = target[2]
        if len(rpy) > 0:
            q = quaternion_from_euler(rpy[0], rpy[1], rpy[2])
            goal.place_locations[0].place_pose.pose.orientation.x = q[0]
            goal.place_locations[0].place_pose.pose.orientation.y = q[1]
            goal.place_locations[0].place_pose.pose.orientation.z = q[2]
            goal.place_locations[0].place_pose.pose.orientation.w = q[3]
        print goal
        client.send_goal(goal)
        
        client.wait_for_result()
        return client.get_result()

    def __available_storage(self):
        for store in self.storage:
            if store[1] == 0:
                return store
        return False

    def __check_task_status(self, robot):
        client = actionlib.SimpleActionClient(
            robot + '/move_base', MoveBaseAction)
        client.wait_for_server()

        wait = client.wait_for_result()

        if wait:
            state = client.get_state()
            # succeeded
            if state == GoalStatus.SUCCEEDED:
                return 1
            # not completed
            else:
                return 0
        # without task
        else:
            return -1


if __name__ == "__main__":
    # rospy.sleep(30)
    roscpp_initialize([])
    rospy.init_node('plan_interpreter_py')
    robot = RobotCommander()
    scene = PlanningSceneInterface()
    # rospy.sleep(15)
    plan_int = PlanInterpreter(robot, scene)
    # print(plan_int.plan)
    # print(plan_int.waypoints)
    # print(plan_int.waypoints[""])
    plan_int.task_manager()
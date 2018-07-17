# 1. Overview

This *ROS workspace* provides all the libraries for turtlebot2i robot. This robot is intended to be used inside the automated warehouse and it will be mainly resposible for picking products from shelves and placing in the conveyor belts to get delivered. The warehouse scenario also expects the presence of other robots and humans, which corresponds to a collaborative scenario. Here, the turtlebot2i workspace is comprised of three [metapackages](http://wiki.ros.org/Metapackages): 

1. **base_robot:** Contains all functionality related to the turtlebot2i base, this means the Kobuki robot and its acessories (plates, support, sensors). The base_robot is responsible for enabling the robot navigation and the environment perception.
2. **phantomx_pincher_arm:** Formed by the methods that enable the robot arm manipulation. This includes the arm motion planning through Moveit! library.
3. **turtlebot2i:** Integrates the *base_robot* and *phantomx_pincher_arm* functionalities. Includes codes related to the interaction of the robot with the warehouse.

**Note:** Each metapackage contains other ROS packages with their respective documentation.

# 2. Turtlebot2i Robot

The turtlebot is a family of robots generally used for educational and research purposes. The [turtlebot2i](http://www.trossenrobotics.com/interbotix-turtlebot-2i-mobile-ros-platform.aspx) is one of these robots and it is manufactured by Trossen Robotics. This robot is equipped with these following main parts:

<img style="float: right;" src="http://www.trossenrobotics.com/Shared/Images/Product/Interbotix-Turtlebot-2i-Mobile-ROS-Platform/Img3077_isolated.jpg" width="200">

- Kobuki Mobile Base
- Pincher MK3 Robo Arm
- Intel RealSense 3D Camera SR300-Series
- Orbbec Astra Cam
- Accelerometer/Gyro/Compass

Besides that, the turtlebot2i was equipped with the [Scanse LiDAR](http://scanse.io/), which is a low cost sensor that returns range measurements of 360 degrees of the environment.

# 3. Simulated Warehouse

Before running experiments with real robots, it is interesting to validate the code in a simulated environment. In this project, the [V-REP simulator](http://www.coppeliarobotics.com) is used to reproduce the warehouse scenario, the robot behavior and other elements that exist in this environment (e.g. trucks, workers). In this sense, the V-REP is used just for visualization purpose.
The main reason for choosing V-REP is the presence of many ready to use models, possibility to draw new models and demands relatively less computational power (compared to Gazebo).

**Note:** As V-REP does not have a native support to ROS, the [ROS Interface](http://www.coppeliarobotics.com/helpFiles/en/rosInterf.htm) plugin should be installed. 
**Note 2:** Most of the lua scripts of the scenario are stored outside V-REP. This means that the lua scripts contained in the scenario just load these files (they are inside *vrep* folder). This was necessary for version control of the scripts and better reusability.

# 4. ROS

[Robot Operating System (ROS)](http://www.ros.org/) is framework that contains several tools and libraries for robot development. ROS is maintained by the community and many important universities collaborates with it. ROS is also used by the majority of the researchers, thus, ROS methods and codes are almost a standard among them.
The main advatage of ROS is that (in most of the cases) it dispenses the necessity of developing low level algorithms for the robot by reusing the the code avaible in its repository. 

**Note:** If you are not familiar with ROS, it is recommended the following materials: [ROS Concepts](http://wiki.ros.org/ROS/Concepts), [ROS wiki](http://wiki.ros.org/), [ROS techincal overview](http://wiki.ros.org/ROS/Technical%20Overview) and [ROS architecture](www.willowgarage.com/sites/default/files/icraoss09-ROS.pdf).

# 5. Installation

<<<<<<< HEAD
<<<<<<< HEAD
All the instructions below were tested in Ubuntu 16.04. 

=======
>>>>>>> 277a3ff... Update README.md
## 1. Install V-Rep
=======
## 1. Install V-REP
>>>>>>> 4ea115e... Updates on README

If using simulated environment, install V-REP from: http://www.coppeliarobotics.com/downloads.html
Download the latest version of **V-REP Pro** or **V-REP Pro Edu**. The **V-REP Player** version was not tested here.

In the next section you will install ROS and V-REP ROS Interface.

<<<<<<< HEAD
Set the following **VREP_ROOT** environment variables by running the following lines in the terminal (replace the <path_to_vrep> by the full path to the V-REP folder):

```
echo "export VREP_ROOT_DIR=/<path_to_vrep>/V-REP_PRO_EDU_V3_5_0_Linux" >> ~/.bashrc
echo "export VREP_ROOT=/<path_to_vrep>/V-REP_PRO_EDU_V3_5_0_Linux" >> ~/.bashrc
source ~/.bashrc
```


## 2. Install ROS Kinect
=======
Set the following VREP_ROOT environment variables by adding these lines to .bashrc:

```
export VREP_ROOT_DIR=/<path_to_vrep>/V-REP_PRO_EDU_V3_5_0_Linux
export VREP_ROOT=/<path_to_vrep>/V-REP_PRO_EDU_V3_5_0_Linux
```

**Note:** There are some V-REP versions that have problems with Remote API and ROS Interface, so it is recommended to download the latest version.

## 2. Install ROS Kinect. 
>>>>>>> 277a3ff... Update README.md
Instructions can be found in this link: http://wiki.ros.org/kinetic/Installation

* When running "sudo rosdep init", ignore the following error if it appears: "ERROR: default sources list file already exists:
"
* Follow the "recommended" installation for ease of use.

<<<<<<< HEAD
1. Install Turttlebot2i packages from ROS
    ```
    $ sudo apt install ros-kinetic-turtlebot* libudev-dev ros-kinetic-find-object-2d ros-kinetic-rtabmap-ros
    ros-kinetic-moveit ros-kinetic-octomap-ros ros-kinetic-manipulation-msgs ros-kinetic-controller-manager python-wxgtk3.0
    ```

2. Create the Turtlebot2i workspace by clonning the SCOTT repository
    ```
    $ git clone https://github.com/EricssonResearch/scott-eu.git
    ```
    *As of 7/Mar/2018 you need to change to a subbranch by doing (in the future it might not be necessary): 
    ```
    $ git checkout simulation-ros 
    ```
    
    Add to .bashrc (replace <path_to_repository> by the path to the scott folder) :
    ```
    $ echo "export ROS_PACKAGE_PATH=$ROS_PACKAGE_PATH:/<path_to_repository>/scott-eu/simulation-ros" >> ~/.bashrc
    $ source ~/.bashrc
    ```

3. Setup the catkin workspace and set ROS environment variables
    Follow these instructions: http://wiki.ros.org/catkin/Tutorials/create_a_workspace

    *Be careful to always select the "kinetic" version of ROS

    Install catkin python tools:
    
    ```
    $ sudo apt-get install python-catkin-tools
    ```
=======


1. Install Turttlebot2i packages from ROS
  ```
  $ sudo apt install ros-kinetic-turtlebot* libudev-dev ros-kinetic-find-object-2d ros-kinetic-rtabmap-ros
  ros-kinetic-moveit ros-kinetic-octomap-ros ros-kinetic-manipulation-msgs ros-kinetic-controller-manager python-wxgtk3.0
  ```

2. Create the Turtlebot2i workspace by clonning the SCOTT repository
  ```
  $ git clone https://github.com/EricssonResearch/scott-eu.git
  ```
*As of 7/Mar/2018 you need to change to a subbranch by doing: 
```
$ git checkout simulation-ros 
```
In the future it might not be necessary

3. Setup the catkin workspace and set ROS environment variables
  Follow these instructions: http://wiki.ros.org/catkin/Tutorials/create_a_workspace

* Be careful to always select the "kinetic" version of ROS

Install catkin python tools:

```
$ sudo apt-get install python-catkin-tools
```
>>>>>>> 277a3ff... Update README.md

4. Install xsltproc (required by the vrep_ros_interface)

```
$ sudo apt-get install xsltproc
```

5. Compile the repository from the simulation-ros workspace root

<<<<<<< HEAD
    Go to /scott-eu/simulation-ros and run:
    ```
    $ catkin build
    ```
=======
Go to /scott-eu/simulation-ros and run:

  ```
  $ catkin build

  ```


6. Install V-REP ROS Interface
Add to .bashrc :
```
$ export ROS_PACKAGE_PATH=$ROS_PACKAGE_PATH:/<path_to_repository>/scott-eu/simulation-ros
```

  ```
  $ source ~/.bashrc
  $ roscd vrep_ros_interface
  $ ./install.sh
  
  ```
  If having problems to compile the vrep_ros_interface, check the README.md contained in the package folder.
  
[TODO]: Until here it's ok. Check the next steps 

## 3. Follow instructions in each package's there is a README.md.
>>>>>>> 277a3ff... Update README.md


<<<<<<< HEAD
5. Install V-REP ROS Interface

    ```
    $ roscd vrep_ros_interface
    $ ./install.sh
    ```
    If having problems to compile the vrep_ros_interface, check the README.md contained in the vrep_ros_interface package folder.
  
## 3. Running the Simulated Environment

1. Start ROS CORE
    ```
    $ roscore
    ```

2. Open Vrep and load a scene
    ```
    cd /<path_to_vrep>
    ./vrep.sh
    ```
    - All the scenes are stored in the *turtlebot2i_description/v-rep_model* folder of ROS workspace. V-REP scenes have .ttt extension.
    - Try opening the "warehouse_turtlebot2i.ttt" file from V-REP (File -> Open Scene...).
    - Press play button to start the simulation.

3. Run ROS programs
    All the ROS programs are stored in the ROS package. The instructions to run the programs can be found in the README.md files located in each package.
    Example to run the keyboard teleoperation:
    ```
    $ roslaunch turtlebot2i_navigation turtlebot2i_keyop.launch
    ```

## 4. Using Python VREP Remote API (Optional)

To use the python remote API provided by VREP, some adjustments are necessary:

1. Copy the remoteApi.so (.dll in Windows and .dylib in Mac) to the V-REP python programming folder
    ```
    cp /<path_to_vrep>/programming/remoteApiBindings/lib/lib/64Bit/remoteApi.so /<path_to_vrep>/programming/remoteApiBindings/python/python/
    ```
    - It may be necessary to change the source folder to copy the remoteApi library depending on the OS (Win/Mac/Linux) and processor (32/64bit). All the library versions are located on /<path_to_vrep>/programming/remoteApiBindings/lib/lib/ folder

2. Add python remote API library to python path

    ```
    echo "export PYTHONPATH=$PYTHONPATH:$VREP_ROOT/programming/remoteApiBindings/python/python" >> ~/.bashrc
    source ~/.bashrc
    ```
=======
# Running

## 1. Start ROS CORE
roscore

## 2. Open V-REP Pro or V-REP Pro Edu and then the Scene
 
 /path_to_vrep_folder/vrep.sh /path_to_scott_project_folder/scott-eu/simulation-ros/src/turtlebot2i/turtlebot2i_description/v-rep_model/warehouse_turtlebot2i.ttt 
 
Press play button

A number of errors will pop up at initialization, but they should fade and you can ignore them. The simulation should now be running, but the robots are not doing anything interesting.

**Note:** In the terminal that vrep.sh was executed, make sure that the message "Plugin 'RosInterface': load succeeded." appears. If not, recheck the installation instructions or try a different version of V-REP.

## Make one robot move

Update build (might take a while):

/path_to_scott_project_folder/scott-eu/simulation-ros$ catkin build

Run navigation functionality:

roslaunch turtlebot2i_navigation turtlebot2i_keyop.launch 

At the terminal, press 'e' for enabling the robot. Then 'up arrow' for the robot to move ahead.



>>>>>>> 277a3ff... Update README.md
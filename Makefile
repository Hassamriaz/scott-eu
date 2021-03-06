.PHONY: build up

build:
	(cd planner_reasoner 		&&	make build)
	(cd deployment/maven-base 	&&	make build)
	(cd lyo-services 		&&	make build)
	(cd robot-emulator 		&&	make build)

push:
	(cd planner_reasoner		&&	make push)
	(cd deployment/maven-base 	&&	make push)
	(cd lyo-services		&&	make push)
	(cd robot-emulator		&&	make push)

up: build
	(cd deployment	&& docker-compose up)

up-quick:
	(cd deployment	&& docker-compose build)
	(cd deployment	&& docker-compose up)

swarm-restart:
	# (cd deployment	&& docker-compose build)
	(cd deployment	&& docker swarm init) | true
	# (cd deployment	&& docker service rm `docker service ls --filter name=scott -q`; sleep 3) | true
	(cd deployment	&& docker stack rm scott) | true
	(cd deployment	&& docker stack deploy -c docker-compose.yml --prune scott)

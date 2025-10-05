FROM bellsoft/liberica-openjdk-alpine:21

Run apk add curl jq

WORKDIR /home/selenium-docker

ADD target/docker-resources ./
ADD Runner.sh Runner.sh

Run dos2unix Runner.sh

ENTRYPOINT sh Runner.sh
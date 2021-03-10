#! /bin/bash

# 기존 프로세스 kill
echo "> Kill Current Application"
CURRENT_PID=$(ps -ef | grep issuetracker | grep java | awk '{print $2}')
sudo kill -15 ${CURRENT_PID}

# 기존의 nohup.out 삭제
sudo rm /home/ubuntu/nohup.out
echo "> Remove nohup.out"

# 새로 어플리케이션 실행
nohup java -jar -Dspring.profiles.active=deploy /home/ubuntu/issuetracker/build/libs/* > /home/ubuntu/nohup.out 2>&1 &
echo "> Now new WAS runs."

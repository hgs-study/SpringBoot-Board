#!/usr/bin/env bash

#현재 stop.sh가 속해 있는경로
#하단의 profile.sh의 경로를 찾기 위해 사용
ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)

#자바로 보면 일종의 import
#해당 코드로 인해 stop.sh에서도 profile.sh의 여러 function을 사용
source ${ABSDIR}/profile.sh

REPOSITORY=/home/ec2-user/app/step4
PROJECT_NAME=SpringBoot-Board

echo "> Build 파일 복사"
echo "> cp $REPOSITORY/zip/*.jar $REPOSITORY/"

cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> 새 어플리케이션 배포"
JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR NAME : $JAR_NAME" 

echo "> $JAR_NAME 에 실행 권한 추가"

chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"

IDLE_PROOFILE=$(find_idle_profile)

echo "> $JAR_NAME 를 profile=$IDLE_PROOFILE 로 실행합니다."

nohup java -jar \
    -Dspring.config.location=classpath:/application.properties,classpath:/application-$IDLE_PROFILE.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties \
    -Dspring.profiles.active=$IDLE_PROFILE \
    $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &
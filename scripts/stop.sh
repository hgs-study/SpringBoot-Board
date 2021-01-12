#!/usr/bin/env bash

#현재 stop.sh가 속해 있는경로
#하단의 profile.sh의 경로를 찾기 위해 사용
ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)

#자바로 보면 일종의 import
#해당 코드로 인해 stop.sh에서도 profile.sh의 여러 function을 사용
source ${ABSDIR}/profile.sh

IDLE_PORT=$(find_idle_port)

echo "> $IDLE_PORT 에서 구동중인 애플리 케이셔 pid 확인"
IDLE_PID = $(lsof -ti tcp:${IDLE_PORT})

if [ -z ${IDLE_PID}]
then
  echo "> 현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다."
else
  echo "> kill -15 $IDLE_PID"
  kill -15 ${IDLE_PID}
  sleep 5
fi
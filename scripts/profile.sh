#!/usr/bin/env bash

# 쉬고 있는 profile 찾기 : real1이 사용중이면 real2가 쉬고 있고, 반대면 real1이 쉬고 있음
function find_idle_profile(){
  #현재 엔진엑스가 바라보고 있는 스프링 부트가 정상적으로 수행 중인지 확인
  #응답값을 HttpStatus로 받는다
  #정상이면 200, 오류가 발생한다면 400~503 사이로 발생하니 400이상은 모두 예외로 보고 real2를 현재 profile로 사용한다.
  RESPONSE_CODE = $(curl -s -o /dev/null -w "%{http_code}" http://localhost/profile)

  if[ ${RESPONSE_CODE} -ge 400] #400보다 크면 (즉, 40x/ 50x 에러 모두 포함)
  then
    CURRENT_PROFILE=real2
  else
    CURRENT_PROFILE=${curl -s http://localhost/profile}
  fi

  if[${CURRENT_PROFILE} ==real1]
  then
    IDLE_PROFILE=real2
  else
    IDLE_PROFILE=real1
  fi

  echo "${IDLE_PROFILE}"
}

#쉬고 있는 profile port 찾기
function find_idle_port() {
  #엔진엑스와 연결되지 않은 profile
  #스프링 부트 프로젝트를 이 profile로 연결하기 위해 반환한다.
  IDLE_PROFILE=$(find_idle_profile)

  if [ ${IDLE_PROFILE} == real1]
  then
    echo "8081"
  else
    echo "8082"
  fi
}
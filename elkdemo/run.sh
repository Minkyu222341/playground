#!/bin/bash

# 필요한 디렉토리 생성
mkdir -p logs
mkdir -p logstash/config
mkdir -p logstash/pipeline

# 설정 파일 복사
cp -f docker-compose.yml ./
cp -f logstash/config/logstash.yml ./logstash/config/
cp -f logstash/pipeline/logstash.conf ./logstash/pipeline/

# ELK 스택 실행
echo "ELK 스택 실행 중..."
docker-compose up -d

# 서비스가 준비될 때까지 잠시 대기
echo "서비스 준비 중... (30초 대기)"
sleep 30


# Spring Boot 애플리케이션 빌드 및 실행
echo "Spring Boot 애플리케이션 빌드 및 실행 중..."
cd spring-boot-app
./gradlew -Dorg.gradle.java.home="C:\Program Files\Java\jdk-21" clean build
java -jar build/libs/app.jar

# 종료 시 컨테이너 정리
function cleanup {
  echo "애플리케이션 종료 중..."
  cd ..
  docker-compose down
}

# 스크립트 종료 시 cleanup 함수 실행
trap cleanup EXIT
# Spring Boot ELK 스택 데모 프로젝트

이 프로젝트는 Spring Boot 애플리케이션과 ELK(Elasticsearch, Logstash, Kibana) 스택을 통합하여 로그를 효과적으로 수집, 분석 및 시각화하는 방법을 보여줍니다.

## 기술 스택

- Java 21
- Spring Boot 3.2.3
- Spring Data JPA
- H2 Database
- Gradle
- Elasticsearch 8.12.0
- Logstash 8.12.0
- Kibana 8.12.0
- Docker & Docker Compose

## 프로젝트 구조

```
├── docker-compose.yml               # ELK 스택 Docker 컴포즈 설정
├── logstash/
│   ├── config/
│   │   └── logstash.yml             # Logstash 기본 설정
│   └── pipeline/
│       └── logstash.conf            # Logstash 파이프라인 설정
├── spring-boot-app/
│   ├── build.gradle                 # Gradle 빌드 설정
│   └── src/
│       └── main/
│           ├── java/com/example/elkdemo/
│           │   ├── ElkDemoApplication.java        # 메인 애플리케이션
│           │   ├── controller/                    # REST 컨트롤러
│           │   ├── entity/                        # JPA 엔티티
│           │   ├── repository/                    # 데이터 레포지토리
│           │   ├── service/                       # 비즈니스 로직
│           │   └── scheduler/                     # 로그 생성 스케줄러
│           └── resources/
│               ├── application.yml                # 애플리케이션 설정
│               └── logback-spring.xml             # Logback 설정
├── logs/                            # 로그 파일 디렉토리
└── run.sh                           # 실행 스크립트
```

## 설정 및 실행 방법

### 사전 요구사항

- Docker 및 Docker Compose 설치
- Java 21 설치
- Gradle 설치

### 실행 방법

1. 저장소 클론:
   ```bash
   git clone <repository-url>
   cd elk-demo
   ```

2. 실행 스크립트에 실행 권한 부여:
   ```bash
   chmod +x run.sh
   ```

3. 애플리케이션 실행:
   ```bash
   ./run.sh
   ```
   이 스크립트는 다음을 수행합니다:
    - 필요한 디렉토리 생성
    - Docker Compose를 통해 ELK 스택 실행
    - Spring Boot 애플리케이션 빌드 및 실행

4. 각 서비스 접근:
    - Kibana: http://localhost:5601
    - Elasticsearch: http://localhost:9200
    - Spring Boot 애플리케이션: http://localhost:8080
    - H2 콘솔: http://localhost:8080/h2-console

## Kibana에서 로그 확인하기

1. Kibana에 접속합니다 (http://localhost:5601)
2. 메뉴에서 "Stack Management" > "Index Patterns" 선택
3. "Create index pattern" 버튼 클릭
4. 인덱스 패턴 필드에 "spring-logs-*" 입력하고 생성
5. 메뉴에서 "Analytics" > "Discover" 선택하여 로그 확인

## REST API 엔드포인트

애플리케이션에서는 다음과 같은 사용자 관리 API를 제공합니다:

- `GET /api/users`: 모든 사용자 조회
- `GET /api/users/{id}`: ID로 사용자 조회
- `POST /api/users`: 새 사용자 생성
- `PUT /api/users/{id}`: 사용자 정보 업데이트
- `DELETE /api/users/{id}`: 사용자 삭제
- `GET /api/users/role/{role}`: 특정 역할의 사용자 조회

## 로그 생성

애플리케이션은 다음 방법으로 로그를 생성합니다:

1. API 호출시 자동 로깅
2. `LogGeneratorScheduler` 클래스를 통한 주기적인 테스트 로그 생성 (5초마다)

## 커스터마이징

- `logback-spring.xml`: 로깅 설정 변경
- `logstash/pipeline/logstash.conf`: 로그 수집 및 필터링 규칙 변경
- `application.yml`: 애플리케이션 설정 변경

## ELK 스택 활용

1. 로그 분석
    - Kibana의 검색 기능을 사용하여 로그 분석
    - 필터 및 쿼리로 특정 로그 찾기

2. 대시보드 생성
    - Kibana에서 로그 시각화 생성
    - 시각화를 조합하여 대시보드 생성

3. 알림 설정
    - Elasticsearch의 알림 기능을 통해 특정 이벤트 발생 시 알림 설정 (유료 기능)

## 참고사항

- 이 데모는 학습 및 테스트 목적이므로 프로덕션 환경에 적용할 경우 보안 설정을 추가하세요.
- 최신 ELK 스택 버전을 사용하고 있으므로 메모리 사용량에 주의하세요.
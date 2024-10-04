# 📅 Schedule Project
- Spring Boot를 사용하여 일정 게시판을 구현하는 기본적인 웹 프로젝트입니다.
- 개발기간 - 2024년 09월 26일 - 2024년 10월 04일

## ⚙️ 개발 환경
- **프로그래밍 언어**: Java 17
- **IDE**: IntelliJ IDEA
- **Database**: mysql 
- **운영 체제**: Windows

## 📌 주요 기능
- **일정 등록**
: 사용자가 새로운 일정을 등록할 수 있습니다. 일정 내용과 작성자 정보 입력을 지원합니다.

- **일정 목록 조회**
: 검색창에 입력된 작성자의 등록된 모든 일정을 조회할 수 있습니다.

- **일정 상세 조회**
: 선택한 일정의 상세 정보를 확인할 수 있습니다. 작성자, 작성일, 내용 등이 포함됩니다.

- **일정 수정**
: 기존 일정을 수정할 수 있으며, 작성자와 내용을 변경할 수 있습니다. 비밀번호 확인 후 수정이 가능합니다.

- **일정 삭제**
: 등록된 일정을 삭제할 수 있습니다. 비밀번호 확인 후 삭제가 가능합니다.

## 🔗 API 설계
![image](https://github.com/user-attachments/assets/a3379155-e1cc-463b-b4c5-a3cdf1da0366)





## 📊  ERD
![image](https://github.com/user-attachments/assets/056df7ae-a9d7-48da-8549-e89575b55fd8)





## 📁 파일 구조
```
my-spring-boot-app
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── schedule
│   │   │               ├── ScheduletApplication.java
│   │   │               ├── controller
│   │   │               │   └── ScheduleController.java
│   │   │               ├── dto
│   │   │               │   └── ScheduleRequestDto.java
│   │   │               │   └── ScheduleResponseDto.java
│   │   │               ├── entity
│   │   │               │   └── Schedule.java
│   │   │               ├── service
│   │   │               │   └── ScheduleService.java
│   │   │               └── repository
│   │   │                   └── ScheduleRepository.java
│   │   │               
│   │   ├── resources
│   │   │   ├── static
│   │   │   │   └── index.html
│   │   │   ├── templates
│   │   │   └── application.properties
│   │   │    
│   ├── test
├── .gitignore
├── bulid.gradle
├── gradlew
├── gradlew.bat
├── schedule.sql
└── README.md

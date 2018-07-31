# mentor-tree

## Dependencies
This project depends on [employee-tree](https://github.com/nppatel4/employee-tree) to display employee information. Mentor-tree only manages the relationships between employees. It does not manage any employee information.

### Eureka
[employee-tree](https://github.com/nppatel4/employee-tree) should be registered with Eureka as *employee-tree*.

## Run Locally
### Run Dependencies Using Docker Compose
This runs Eureka and the employee-tree service:
```bash
docker-compose up
```

Stop and teardown containers:
```bash
docker-compose down
```

### Run From Shell
The `local` spring profile should be used when running locally. The gradle `bootRun` task has been configured to use the local profile by default.
```bash
./gradlew bootRun
```
### IntelliJ
Include `local` in the *Active profiles* field in the *Run Configuration* for the Spring Boot application.

## Swagger UI (API Docs)
[swagger on localhost](http://localhost:8081/swagger-ui.html)

## Deploy to PCF
Store your CF username and password in environment variables:
```bash
export CF_CCUSER="<pcf-username>"
export CF_CCPASSWORD="<pcf-password>"
```
Use the CF gradle plugin to push:
```bash
./gradlew cf-push -Pcf.space=<your-space-name-here>
```

## Run Integration Tests Using Docker Compose
[Docker](https://docs.docker.com/) and [Docker Compose](https://docs.docker.com/compose/) can be used to provide dependent services necessary to run integration tests. Projects should be arranged on disk as shown below:
```
├── employee-tree
└── mentor-tree
```
Get the latest `employee-tree` and build the jar:
```bash
cd employee-tree
git pull
./gradlew clean assemble
```

| :warning: Ensure nothing is running on ports 8080 and 8761 before starting the build. |
|----------------------------------------------------------------------------------------------|

Build `mentor-tree` jar, run docker-compose, and run the integration tests:
```bash
cd mentor-tree
./gradlew integrationTest
```

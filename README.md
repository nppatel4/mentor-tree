# mentor-tree
This project depends on [employee-tree](https://github.com/nppatel4/employee-tree) to display employee information.

## Eureka
[employee-tree](https://github.com/nppatel4/employee-tree) should be registered with Eureka as *employee-tree*.

## Build
```bash
./gradlew clean assemble
```

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

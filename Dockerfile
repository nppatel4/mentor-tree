FROM openjdk:8-jdk-alpine
RUN apk update && apk add curl && rm -rf /var/cache/apk/*
VOLUME /tmp
COPY build/libs/mentor-tree-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 8081

# filepath: /Users/administrator/Desktop/git/event-register/my-event-registration-app/Dockerfile
FROM openjdk:11-jre-slim
VOLUME /tmp
COPY target/my-event-registration-app-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
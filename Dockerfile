#FROM openjdk:11-jdk-slim
FROM openjdk

WORKDIR /app

COPY target/pessoasRH-0.0.1-SNAPSHOT.jar /app/spring-app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Dspring.devtools.restart.enabled=true", "-jar", "/app/spring-app.jar"]

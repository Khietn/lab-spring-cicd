# Docker Build Maven Stage
#FROM maven:3-jdk-8-alpine AS build
#WORKDIR /app
#COPY pom.xml .
#COPY src ./src
#RUN --mount=type=cache,target=/root/.m2  mvn clean package -Dmaven.test.skip

# Run spring boot in Docker
FROM openjdk:17-oracle
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]


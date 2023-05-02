# Docker Build Maven Stage
FROM maven:3-jdk-8-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Run spring boot in Docker
FROM openjdk:17-oracle
WORKDIR /app
ARG JAR_FILE=target/springboot-0.1.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","app.jar"]

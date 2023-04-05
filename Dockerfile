# Docker Build Maven Stage
FROM maven:3-jdk-8-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN --mount=type=cache,target=/root/.m2  mvn clean package -Dmaven.test.skip

# Run spring boot in Docker
FROM openjdk:17-oracle
WORKDIR /app
ARG JAR_FILE=target/springboot-0.1.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]


# Docker Build Maven Stage
FROM maven:3-jdk-8-alpine AS build
# Copy folder in docker
WORKDIR /opt/app
COPY ./ /opt/app
RUN apk add
#RUN mvn clean install -DskipTests
# Run spring boot in Docker
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

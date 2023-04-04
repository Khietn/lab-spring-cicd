# Docker Build Maven Stage
#FROM maven:3-jdk-8-alpine AS build
# Copy folder in docker
#WORKDIR /opt/app
#COPY ./ /opt/app
#RUN mvn clean install
# Run spring boot in Docker
FROM openjdk:17-oracle
VOLUME /tmp
ARG JAR_FILE=target/springboot-0.1.jar
COPY ${JAR_FILE} /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

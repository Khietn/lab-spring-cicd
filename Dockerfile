# Docker Build Maven Stage
FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -f ./pom.xml clean package -Dmaven.test.skip

# Run spring boot in Docker
# FROM openjdk:17-oracle
# WORKDIR /app
# ARG JAR_FILE=target/springboot-0.1.jar
# COPY ${JAR_FILE} app.jar
# EXPOSE 8081
# ENTRYPOINT ["java","-jar","app.jar"]

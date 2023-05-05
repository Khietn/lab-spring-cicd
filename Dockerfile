# Docker Build Maven Stage
FROM maven:3.8.3-openjdk-17 AS build
COPY src /usr/src
COPY pom.xml /usr
RUN mvn -f /usr/pom.xml clean package -Dmaven.test.skip

# Run spring boot in Docker
FROM openjdk:17-oracle
COPY --from=build /usr/target/springboot-0.1.jar /usr/app/app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/usr/app/app.jar"]

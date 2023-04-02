FROM openjdk:17-oracle
VOLUME /tmp
ARG JAR_FILE=target/springboot-0.1.jar
ADD ${JAR_FILE} /app.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","/app.jar"]

#FROM openjdk:11-jre-slim

#COPY springboot-0.1.jar /app.jar

#EXPOSE 8080

#CMD ["java", "-jar", "/app.jar"]


#Maven Build
FROM maven:3-openjdk-17 AS builder
COPY pom.xml .
COPY src /src
RUN   mvn -f pom.xml clean package -DskipTests

#Run
FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY --from=builder ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

#FROM openjdk:17
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

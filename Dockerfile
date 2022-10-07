FROM openjdk:11-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} facturation.jar
ENTRYPOINT ["java","-jar","/facturation.jar"]
EXPOSE 9090
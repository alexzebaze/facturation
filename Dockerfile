FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} facturation.jar
ENTRYPOINT ["java","-jar","facturation.jar"]

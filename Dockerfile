FROM openjdk:8-jdk-alpine
ADD target/netflix-streaming-user.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
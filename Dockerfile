FROM openjdk:17-alpine
ADD target/Example.jar Example.jar
ENTRYPOINT ["java" , "-jar", "Example.jar"]
EXPOSE 8080
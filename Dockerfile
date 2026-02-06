FROM openjdk:27-ea-jdk
COPY target/hiring-app.jar hiring-app.jar
ENTRYPOINT ["java", "-jar", "/hiring-app.jar"]
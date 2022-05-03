FROM openjdk:17
ADD backend/target/Employees-0.0.1-SNAPSHOT.jar Employees-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "Employees-0.0.1-SNAPSHOT.jar"]
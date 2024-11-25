FROM maven:3.9.0-openjdk-23 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:23-jdk-slim AS final

WORKDIR /app

COPY --from=build /app/target/my-banking-app-0.0.1-SNAPSHOT.jar app.jar

# اجرای برنامه
ENTRYPOINT ["java", "-jar", "app.jar"]
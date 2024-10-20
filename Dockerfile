# Build stage
FROM maven:3.9.2-eclipse-temurin AS build
WORKDIR /app
COPY . .
RUN mvn -B clean package -DskipTests

# Package stage
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

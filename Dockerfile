# Use a base image with Java runtime
FROM eclipse-temurin:17-jdk as builder

# Set working directory
WORKDIR /app

# Copy backend JAR
COPY weather-app/target/weather-app-0.0.1-SNAPSHOT.jar app.jar

# Copy frontend static build files
COPY weather-app-react/build ./static

# Serve frontend from Spring Boot's static folder
RUN mkdir -p /app/public && cp -r ./static/* /app/public/

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
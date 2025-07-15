# ----------- Build React Frontend -----------
FROM node:18 as frontend-builder
WORKDIR /app
COPY weather-app-react/package*.json ./
RUN npm install
COPY weather-app-react/ ./
RUN npm run build

# ----------- Build Spring Boot Backend JAR -----------
FROM maven:3.9.6-eclipse-temurin-17 as backend-builder
WORKDIR /app
COPY weather-app/. .    
RUN mvn clean package -DskipTests

# ----------- Final Image -----------
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy built JAR
COPY --from=backend-builder /app/target/*.jar app.jar

# Copy frontend build output into Spring Boot's default static directory
COPY --from=frontend-builder /app/build /app/public

ENTRYPOINT ["java", "-jar", "app.jar"]
# ----------- Build React frontend -----------
FROM node:20 AS frontend
WORKDIR /app
COPY weather-app-react/ .
RUN npm install && npm run build

# ----------- Build Spring Boot backend -----------
FROM eclipse-temurin:17-jdk AS backend
WORKDIR /app
COPY weather-app/ .
RUN ./mvnw package -DskipTests

# ----------- Final image -----------
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy built Spring Boot jar from backend stage
COPY --from=backend /app/target/*.jar app.jar

# Copy built React static files into Spring Boot's static folder
RUN mkdir -p /app/public
COPY --from=frontend /app/build /app/public

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
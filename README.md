# Weather App

A full-stack weather web application built with React + TypeScript for the frontend and Spring Boot for the backend.

---

## Features

- Search cities with live autocomplete suggestions
- Fetch and display current, hourly, and daily weather forecasts
- Responsive UI with animated weather icons and visuals
- Loading indicators during data fetch
- Modular and type-safe code with TypeScript
- Backend REST API built with Spring Boot to serve weather data

---

## Technologies

### Frontend

- React (with TypeScript)
- Tailwind CSS for styling
- Fetch API for communication with backend
- Debounce utility for optimized search requests

### Backend

- Spring Boot (Java)
- REST API endpoints for weather data fetching and caching
- Integration with external weather services (e.g., Open-Meteo API)

---

## Installation

### Prerequisites

- Node.js and npm installed
- Java JDK 11+ installed
- Maven installed (for backend)

### Setup Frontend

```bash
cd weather-app-react
npm install
npm start
```

### Setup Backend

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

## Environment Variables
Frontend expects API URL in `.env` file:
```env
REACT_APP_API_BASE_URL=http://localhost:8080
```
Ensure the backend URL matches this in your `.env`.

## Running the Application

1. Start backend with mvn spring-boot:run (default port 8080)
2. Start frontend with npm start (default port 3000)
3. Open http://localhost:3000 in your browser
4. Use the search bar to find cities and view weather data

## Build & Deployment
- Build frontend for production:

```bash
npm run build
```

- Package and run backend JAR for deployment.
- Host frontend build files on any static server or integrate with backend.

## Demonstration

<p align="center">
![weather-app-demo](https://github.com/user-attachments/assets/180df64b-fbbe-40b1-b5ca-2ef2db861ddb)
</p>

## Notes

- API URL and keys should be managed via environment variables for security.
- CORS configuration in backend may be required for local development.

## License

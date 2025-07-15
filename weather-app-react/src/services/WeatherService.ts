import { City } from "../types/City";

export const fetchWeather = async (city: City) => {
    try {
      const response = await fetch(
        `${process.env.REACT_APP_API_BASE_URL}/api/weather/fetch`,
        {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(city)
        }
      );

      if (!response.ok) {
        throw new Error("Failed to fetch weather data.");
      }
    } catch (error) {
      throw new Error("Error in POST request");
    }
  };

export  const getWeather = async (city: City) => {
    try {
      const response = await fetch(
        `${process.env.REACT_APP_API_BASE_URL}/api/weather/all`,
        {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(city)
        }
      );

      if (!response.ok) throw new Error("Failed to get weather");
      const data = await response.json();
      return data;
    } catch (error) {
      throw new Error("Error fetching weather");
    }
  };
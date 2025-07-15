export type Weather = {
    currentWeather: {
        weatherCode: number;
        description: string;
        temperature: number;
        apparentTemperature: number;
        isDay: boolean;
        humidity: number;
        precipitation: number;
        windSpeed: number;
        windDirection: number;
    };
    hourlyWeatherList: Array<{
        weatherCode: number;
        description: string;
        temperature: number;
        apparentTemperature: number;
        isDay: boolean;
        humidity: number;
        precipitationAmount: number;
        precipitationProbability: number;
        windSpeed: number;
        windDirection: number;
        uvIndex: number;
        time: string;
    }>;
    dailyWeatherList: Array<{
        weatherCode: number;
        description: string;
        minTemperature: number;
        maxTemperature: number;
        precipitationAmount: number;
        sunrise: string;
        sunset: string;
        time: string;
    }>;
}
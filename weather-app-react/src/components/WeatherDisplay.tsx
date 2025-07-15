import React, { useRef, useState } from 'react';
import { Weather } from '../types/Weather';
import images from '../utils/images';

interface WeatherDisplayProps {
    weather: Weather;
    imageWidth: number;
    imageHeight: number;
    onCloseClick: () => void;
}

const WeatherDisplay: React.FC<WeatherDisplayProps> = ({ weather, imageWidth, imageHeight, onCloseClick }) => {

    const clockRef = useRef<HTMLImageElement>(null);
    const [angle, setAngle] = useState<number>(0);
    const [am, setAm] = useState<boolean>(true);
    const [hour, setHour] = useState<number>(0);
    const [hour_24, setHour_24] = useState<number>(0);
    
    const handleMouseMove = (e: MouseEvent) => {
        if (!clockRef.current) return;
        const rect = clockRef.current.getBoundingClientRect();
        const centerX = rect.left + rect.width / 2;
        const centerY = rect.top + rect.height / 2;
        const dx = e.clientX - centerX;
        const dy = centerY - e.clientY;
        const radians = Math.atan2(dy, dx);
        const degrees = (radians * 180) / Math.PI;
        const normalized = -(degrees - 90) % 360;
        setAngle(normalized);
        updateHour(normalized);
    };

    const updateHour = (angle: number, isAm: boolean = am) => {
        const shifted = (angle + 360) % 360;
        const time = Math.round((shifted > 345 ? 0 : shifted)/30);
        const time_24 = time + (isAm ? 0 : 1) * 12;
        setHour(time);
        setHour_24(time_24);
    }
    
    const handleMouseDown = () => {
        window.addEventListener("mousemove", handleMouseMove);
        window.addEventListener("mouseup", handleMouseUp);
    };
    
    const handleMouseUp = () => {
        window.removeEventListener("mousemove", handleMouseMove);
        window.removeEventListener("mouseup", handleMouseUp);
    };

    const handleTimeClick = () => {
        setAm(prevAm => {
            const newAm = !prevAm;
            updateHour(angle, newAm);
            return newAm;
        })
    }

    const convertDate = (date: string) => {
        const dateParts = date.split("-");
        const months = {
            "01": "January",
            "02": "February",
            "03": ""
        }
    }

    const showTime = (isAm: boolean = am, time: number = hour) => {
        if (isAm && hour != 0) return hour + " am";
        if (!isAm && hour != 0) return hour + " pm";
        if (hour == 0) return (isAm ? "12 am" : "12 pm");
    }

    return (
        <div className="absolute bottom-0" style={{width: `${imageWidth}px`, height: `${imageHeight}px`}}>
            {/* <h3>Current Weather</h3>
            <p>Weather Code: {weather.currentWeather.weatherCode}</p>
            <p>Description: {weather.currentWeather.description}</p>
            <p>Temperature: {weather.currentWeather.temperature}</p>
            <p>Apparent Temperature: {weather.currentWeather.apparentTemperature}</p>
            <p>Day: {weather.currentWeather.isDay}</p>
            <p>Humidity: {weather.currentWeather.humidity}</p>
            <p>Precipitation: {weather.currentWeather.precipitation}</p>
            <p>Wind Speed: {weather.currentWeather.windSpeed}</p>
            <p>Wind Direction: {weather.currentWeather.windDirection}</p>

            <h3>Hourly Forecast</h3>
            <ul>
                {weather.hourlyWeatherList.map((hour, index) => (
                    <li key={index}>{hour.time}: {hour.temperature} °C, {hour.description}</li>
                ))}
            </ul>
            
            <h3>Daily Forecast</h3>
            <ul>
                {weather.dailyWeatherList.map((day, index) => (
                    <li key={index}>{day.time}: High {day.maxTemperature} °C, Low {day.minTemperature} °C, {day.description}</li>
                ))}
            </ul> */}
            <img src={images.paper} alt="" className="absolute bottom-0"/>
            <img src={images.closeIcon} alt="" onClick={() => onCloseClick()} className="absolute right-[35%] top-[18%] w-[2vw] hover:scale-150 hover:cursor-pointer"/> 
            <img src={images.sunIconAnimated} alt="" className="absolute left-[35%] top-[18%] w-[8vw]  drop-shadow-[-10px_0px_10px_rgba(0,0,0,0.5)]"/> 
            <img src={images.cloudIconAnimated} alt="" className="absolute left-[35%] top-[23%] w-[13.5vw]  drop-shadow-[-10px_0px_10px_rgba(0,0,0,0.5)]"/>  
            <p className="absolute right-[40.5%] top-[18%] text-[2vw] font-[crayon] drop-shadow-[-5px_0px_5px_rgba(0,0,0,0.4)]">Current</p>
            <p className="absolute right-[38%] top-[21%] text-[5vw] font-[crayon] drop-shadow-[-5px_0px_5px_rgba(0,0,0,0.4)]">{weather.currentWeather.temperature.toPrecision(2)}°C</p>
            <p className="absolute right-[35%] top-[33.5%] text-[1.3vw] font-[crayon]">Feels like</p>
            <p className="absolute right-[35%] top-[37%] text-[1.3vw] font-[crayon]">{weather.currentWeather.apparentTemperature.toPrecision(2)}°C</p>
            <p className="absolute left-[35.5%] top-[33.5%] font-[crayon] text-[1.3vw] h-[5vw] w-[17.5vw] font-bold">{weather.currentWeather.description}</p>
            {/* <p className="absolute left-[35.5%] top-[33.5%] font-[crayon] text-[1.3vw] h-[5vw] w-[17.5vw] font-bold">Slight or Moderate Thunderstorm</p> */}
            <img src={images.humidityIcon} alt="" className="absolute left-[45%] top-[40.5%] w-[1.3vw] hover:scale-150"/> 
            <p className="absolute left-[35.5%] top-[41.5%] font-[crayon] text-[1.3vw]">Humidity {weather.currentWeather.humidity}</p>            
            <p className="absolute left-[35.5%] top-[45%] font-[crayon] text-[1.3vw]">Precipitation {weather.currentWeather.precipitation}</p>
            <img src={images.arrow} alt="" className="absolute right-[45%] top-[41%] w-[1.5vw] hover:scale-150"/> 
            <img src={images.arrow} alt="" className="absolute right-[44%] top-[41%] w-[1.5vw] rotate-180 hover:scale-150"/>
            <p className="absolute right-[35%] top-[41.5%] font-[crayon] text-[1.3vw]">Min {weather.dailyWeatherList.at(0)?.minTemperature}°C</p> 
            <p className="absolute right-[35%] top-[45%] font-[crayon] text-[1.3vw]">Max {weather.dailyWeatherList.at(0)?.maxTemperature}°C</p> 
            <img src={images.windCircleIcon} alt="" className="absolute right-[34.5%] top-[48%] w-[9vw]"/> 
            <img src={images.windArrowIcon} alt="" className="absolute right-[34.5%] top-[48%] w-[9vw] hover:scale-150" style={{ transform: `rotate(${weather.currentWeather.windDirection}deg)` }}/> 
            <p className="absolute right-[44.5%] top-[51.5%] font-[crayon] text-[1.5vw]">Wind</p> 
            <p className="absolute right-[44.5%] top-[55%] font-[crayon] text-[1.3vw]">{weather.currentWeather.windDirection}°</p>
            <p className="absolute right-[44.5%] top-[58.5%] font-[crayon] text-[1.3vw]">{weather.currentWeather.windSpeed} km/h</p>
            <p className="absolute left-[36%] top-[49.5%] font-[crayon] text-[1.3vw]">Sunrise</p>
            <p className="absolute left-[42.5%] top-[49.5%] font-[crayon] text-[1.3vw]">Sunset</p>
            <img src={images.sunriseIcon} alt="" className="absolute left-[35%] top-[53%] w-[7vw] hover:scale-150"/> 
            <img src={images.sunsetIcon} alt="" className="absolute left-[42%] top-[53%] w-[7vw] hover:scale-150"/> 
            <p className="absolute left-[36.5%] top-[60%] font-[crayon] text-[1.3vw]">{weather.dailyWeatherList.at(0)?.sunrise.split("T")[1]}</p>
            <p className="absolute left-[43.5%] top-[60%] font-[crayon] text-[1.3vw]">{weather.dailyWeatherList.at(0)?.sunset.split("T")[1]}</p>
            {/* <div className="flex flex-col items-center mt-10">
            <div
                ref={clockRef}
                className="relative w-64 h-64 rounded-full border-4 border-gray-800 bg-gray-100"
                onMouseDown={handleMouseDown}
            >
                <div
                className="absolute w-1 h-28 bg-red-500 origin-bottom left-1/2 top-4"
                style={{ transform: `rotate(${angle}deg)` }}
                ></div>
            </div>
            <p className="mt-4 text-xl font-semibold">Angle: {Math.round(angle)}&deg;</p>
            </div> */}

            <img ref={clockRef} onMouseDown={handleMouseDown} src={images.clockIcon} alt="" className="absolute left-[35%] top-[64%] w-[12vw]" draggable={false}/> 
            <img src={images.clockHand} onMouseDown={handleMouseDown} alt="" className="absolute left-[40.5%] top-[64.5%] w-[0.75vw] hover:cursor-pointer origin-[50%_100%]" draggable={false} style={{ transform: `rotate(${angle}deg)` }}/> 
            {am && (<img src={images.am} onClick={handleTimeClick} alt="" className="absolute left-[39.5%] top-[78%] w-[3vw] hover:cursor-pointer hover:scale-150" draggable={false}/>)}
            {!am && (<img src={images.pm} onClick={handleTimeClick} alt="" className="absolute left-[39.5%] top-[78%] w-[3vw] hover:cursor-pointer hover:scale-150" draggable={false}/>)}
            <p className="absolute right-[35%] top-[82%] font-[crayon] text-[1.5vw]">{new Date(weather.hourlyWeatherList.at(hour_24)?.time ?? "").toLocaleDateString("en-US", {
                month: "long",
                day: "numeric",
                year: "numeric"
                })}
            </p>
            <p className="absolute left-[48%] top-[66%] font-[crayon] text-[1.3vw]">{weather.hourlyWeatherList.at(hour_24)?.temperature}°C at {showTime()}</p>
            <p className="absolute left-[48%] top-[70%] font-[crayon] text-[1.3vw]">UV {weather.hourlyWeatherList.at(hour_24)?.uvIndex}</p>
            <p className="absolute left-[48%] top-[74%] font-[crayon] text-[1.3vw] h-[4vw] w-[17.5vw]">{weather.hourlyWeatherList.at(hour_24)?.description}</p>
            <p className="absolute left-[46%] top-[82%] font-[crayon] text-[1.3vw] text-[#a61e1e] hover:cursor-pointer hover:scale-120">see more...</p>
            {/* <p className="absolute left-[48%] top-[74%] font-[crayon] text-[1.3vw] h-[4vw] w-[17.5vw]">Slight or Moderate Thunderstorm</p> */}
        </div>
    );
};

export default WeatherDisplay;
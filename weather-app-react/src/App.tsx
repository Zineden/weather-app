import React, { ChangeEvent, use, useEffect, useRef, useState } from 'react';
import { City } from './types/City';
import { fetchWeather, getWeather } from './services/WeatherService';
import CitySearch from './components/CitySearch';
import { Weather } from './types/Weather';
import WeatherDisplay from './components/WeatherDisplay';
import images from './utils/images';
import './output.css';

const App: React.FC = () => {
  const [selectedCity, setSelectedCity] = useState<City | null> (null);
  const [weather, setWeather] = useState<Weather | null> (null);
  const [isLoading, setIsLoading] = useState<boolean>(false);
  const imageRef = useRef<HTMLImageElement>(null);
  const [imageSize, setImageSize] = useState<{ width: number; height: number }>({
    width: 0,
    height: 0,
  });
  
  const handleCitySelect = async (city: City) => {
    setIsLoading(true);
    await fetchWeather(city);
    const weatherData = await getWeather(city);

    if (weatherData) {
      setSelectedCity(city);
      setWeather(weatherData);
      setIsLoading(false);
    }
  };

  const handleWindowClose = (): void => {
    setWeather(null);
  };

  const updateSize = () => {
    if (imageRef.current) {
      const rect = imageRef.current.getBoundingClientRect();
      setImageSize({ width: rect.width, height: rect.height });
    }
  };

  useEffect(() => {
    updateSize();
    window.addEventListener('resize', updateSize);
    return () => window.removeEventListener('resize', updateSize);
  }, []);

  return (
    <div style={{backgroundImage: `url(${images.bgDay})`}} className="bg-cover bg-center w-screen min-h-screen p-0 m-0 relative overflow-hidden">
      <img src={images.sunAnimated} alt="Sun" className="absolute block bottom-0 drop-shadow-[0_15px_15px_rgba(0,0,0,0.3)]"/>
      <img src={images.cloudAnimated} alt="Cloud" className="absolute bottom-0 drop-shadow-[0_15px_15px_rgba(0,0,0,0.3)]"/>
      <div className="relative w-screen min-h-screen aspect-auto">
        <img src={images.movingScene} alt="Bushes and trees" ref={imageRef} onLoad={updateSize} className="absolute bottom-0 drop-shadow-[-10px_-10px_10px_rgba(0,0,0,0.4)]"/>
        <img src={images.plank1} alt="Plank" className="absolute bottom-0 drop-shadow-[-10px_-10px_10px_rgba(0,0,0,0.4)]"/>
        <img src={images.plank2} alt="Plank" className="absolute bottom-0 drop-shadow-[-10px_-10px_10px_rgba(0,0,0,0.4)]"/>
        <img src={images.plank3} alt="Plank" className="absolute bottom-0 drop-shadow-[-10px_-10px_10px_rgba(0,0,0,0.4)]"/>
        <img src={images.signpost} alt="Signpost" className="absolute bottom-0"/>
        <img src={images.hills} alt="Hills" className="absolute bottom-0"/>
        <CitySearch imageWidth={imageSize.width} imageHeight={imageSize.height} onCitySelect={handleCitySelect} setIsLoading={setIsLoading}/>
        {weather && (
          <>
            <div className="bg-black/50 absolute w-full h-full"></div>
            <WeatherDisplay weather={weather} imageWidth={imageSize.width} imageHeight={imageSize.height} onCloseClick={handleWindowClose}/>
          </>
        )}
        {isLoading && (
          <>
            <div className="bg-black/50 fixed inset-0 z-40"></div>
            <div className="absolute z-50 left-[47%] top-[43%] w-[5vw] h-[5vw] border-4 border-gray-300 border-t-[#197a1e] rounded-full animate-spin"></div>
          </>
        )}
      </div>
    </div>
  );
};

export default App;
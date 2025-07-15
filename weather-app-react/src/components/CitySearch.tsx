import { ChangeEvent, useCallback, useEffect, useMemo, useState } from 'react';
import { debounce } from '../utils/debounce';
import { City } from '../types/City';
import images from '../utils/images';
import React from 'react';

type CitySearchProps = {
    onCitySelect: (city: City) => void;
    imageWidth: number;
    imageHeight: number;
    setIsLoading: (state: boolean) => void;
};

const CitySearch: React.FC<CitySearchProps> = ({ onCitySelect, imageWidth, imageHeight, setIsLoading}) => {
    const [query, setQuery] = useState<string>('');
    const [results, setResults] = useState<City[]>([]);
    const [focused, setFocused] = useState<boolean>(false);

    const searchCities = useCallback(async (query: string): Promise<City[]> => {
        try {
            if (query === "") return [];
            const response = await fetch(`${process.env.REACT_APP_API_BASE_URL}/api/cities/search?prefix=${query}`);
            const data = await response.json();
            return Array.isArray(data.results) ? data.results : [];
        } catch (error) {
            console.error("Failed to fetch cities", error);
            return [];
        }
      }, []);

    const debouncedSearch = useCallback(
        debounce(async (value: string) => {
            if (value.trim() === "") {
                setIsLoading(false);
                return [];
            }
          
            setIsLoading(true);

            const cities = await searchCities(value);
            setResults(cities);
            setIsLoading(false);
        }, 500),
        [searchCities]
    );

    const handleFocus = (e: React.FocusEvent<HTMLInputElement>) => {
        setFocused(true);
    };

    const handleBlur = (e: React.FocusEvent<HTMLInputElement>) => {
        if (query === '') {
            setFocused(false);
        }
    };

    const handleInputChange = async (e: ChangeEvent<HTMLInputElement>) => {
        const value = e.target.value;
        setQuery(value);

        if (value === '') {
            setFocused(false);
        }

        debouncedSearch(value);
    };

    const handleCityClick = (city: City): void => {
        onCitySelect(city);
        setQuery(city.name);
    };

    const positions: {bottom: string, left: string}[] = [
        {bottom: '36%', left: '52%'},
        {bottom: '28.5%', left: '35%'},
        {bottom: '20.5%', left: '52%'}
    ];

    const planks: string[] = [images.plank1, images.plank2, images.plank3];

    return (
        <div className="absolute bottom-0" style={{width: `${imageWidth}px`, height: `${imageHeight}px`}}>
            <input
                type="text"
                placeholder={focused ? '' : "Search..."}
                value={query}
                onChange={handleInputChange}
                onFocus={handleFocus}
                onBlur={handleBlur}
                className="outline-none text-[#6b4f3b] placeholder-[#6b4f3b] absolute bottom-[49%] left-[32%] px-4 py-2 w-[34%] h-[7.5%] text-[5vw] font-[carved-wood] text-center placeholder:text-center"
            />
            {/* <p className="absolute left-[47.5%] top-[43%] w-[5vw] h-[5vw] border-4 border-gray-300 border-t-blue-500 rounded-full animate-spin">some</p> */}
            {/* {isLoading && ( 
                <>
                <div className="bg-black/50 absolute w-full h-full"></div>
                <p className="absolute left-[47%] top-[43%] w-[5vw] h-[5vw] border-4 border-gray-300 border-t-[#197a1e] rounded-full animate-spin"></p>
                </>
            )} */}
            {results.length > 0 && (
                <ul>
                    {results.slice(0, 3).map((city, index) => {
                        const pos = positions[index];
                        if (!city || !pos) return null;
                        
                        return (
                                <li
                                key={index}
                                onClick={() => handleCityClick(city)}
                                className="absolute font-[carved-wood] text-[#5f4533] text-[1.9vw] w-[13%] h-[5%] flex items-center hover:cursor-pointer"
                                style={{
                                    bottom: pos.bottom,
                                    left: pos.left,
                                }} 
                                >
                                    <p className="truncate">{city.name}, {city.country}</p>
                                </li>
                        );
                    })}
                </ul>
            )}
        </div>
    );
};

export default CitySearch;
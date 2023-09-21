package com.younder.weather.service.out;

import com.younder.weather.model.CityWeatherInfo;
import reactor.core.publisher.Mono;

public interface WeatherWebService {

    /**
     *
     * @param city for which we want to retrieve weather information from the web service
     * @return - weather information for the city requested
     */
    Mono<CityWeatherInfo> findWeatherInfoForCity(String city);
}

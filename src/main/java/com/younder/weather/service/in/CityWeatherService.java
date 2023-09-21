package com.younder.weather.service.in;

import com.younder.weather.model.CityWeatherResponse;
import reactor.core.publisher.Mono;

import java.util.Set;

public interface CityWeatherService {

    /**
     *
     * @param cities - list of cities fo which we want to find out weather information
     * @return - list of cities together with their weather information
     */
    Mono<CityWeatherResponse> getCitiesWeatherInfo(Set<String> cities);

}


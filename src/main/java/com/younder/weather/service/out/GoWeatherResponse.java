package com.younder.weather.service.out;

import java.util.List;

public record GoWeatherResponse(
        int temperature,
        int wind,
        String description,
        List<Forecast> forecastList
) {

}

record Forecast(String day, int temperature, int wind) {
}

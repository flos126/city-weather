package com.younder.weather.service.out;

import com.younder.weather.PropertiesConfig;
import com.younder.weather.model.CityWeatherInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class GoWeatherWebServiceImpl implements WeatherWebService {

    private final PropertiesConfig propertiesConfig;

    @Override
    public Mono<CityWeatherInfo> findWeatherInfoForCity(String city) {
        // Go Weather server seems to not work

/*
        WebClient client = WebClient.create(propertiesConfig.getWeatherServerUrl());
        Mono<ClientResponse> weatherResponse = client.get()
                .uri("/city")
                .retrieve()
                .bodyToMono(ClientResponse.class);

        // map to GoWeatherResponse
        // compute average
        // create result
*/

        Random random = new Random();
        var mockResult = new CityWeatherInfo(
                city, random.nextDouble(-100, 100), random.nextInt(0, 1000));
        return Mono.just(mockResult);
    }
}

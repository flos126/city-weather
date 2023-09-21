package com.younder.weather.controller;

import com.younder.weather.model.CityWeatherResponse;
import com.younder.weather.service.in.CityWeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class CityWeatherController {

    private final CityWeatherService weatherService;

    @GetMapping
    public Mono<CityWeatherResponse> getWeatherForCities(@RequestParam(name = "city") Set<String> cities) {
        if (cities.isEmpty()) return Mono.just(new CityWeatherResponse(List.of()));

        Set<String> caseInsensitiveCities = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        caseInsensitiveCities.addAll(cities);
        return weatherService.getCitiesWeatherInfo(caseInsensitiveCities);
    }

}

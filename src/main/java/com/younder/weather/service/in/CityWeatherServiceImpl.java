package com.younder.weather.service.in;

import com.younder.weather.PropertiesConfig;
import com.younder.weather.service.out.WeatherWebService;
import com.younder.weather.model.CityWeatherInfo;
import com.younder.weather.model.CityWeatherResponse;
import com.younder.weather.service.out.CityWeatherInfoStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class CityWeatherServiceImpl implements CityWeatherService {

    private final WeatherWebService weatherWebService;
    private final CityWeatherInfoStorageService cityStorage;
    private final PropertiesConfig properties;


    @Override
    public Mono<CityWeatherResponse> getCitiesWeatherInfo(Set<String> cities) {
        var monoList = cities.stream()
                .filter(c -> properties.getCityList().contains(c.toLowerCase()))
                .sorted(String::compareTo)
                .map(weatherWebService::findWeatherInfoForCity)
                .toList();
        log.info("{} cities matches", monoList.size());
        var flux = Flux.concat(monoList);

        List<CityWeatherInfo> cityWeatherInfoList = new ArrayList<>();
        flux.collectList().subscribe(cityWeatherInfoList::addAll);
        cityStorage.saveCitiesWeatherInfo(cityWeatherInfoList);

        return Mono.just(new CityWeatherResponse(cityWeatherInfoList));
    }
}

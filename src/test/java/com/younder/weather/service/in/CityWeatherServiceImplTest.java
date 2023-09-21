package com.younder.weather.service.in;

import com.younder.weather.model.CityWeatherInfo;
import com.younder.weather.service.out.CityWeatherInfoStorageService;
import com.younder.weather.service.out.WeatherWebService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class CityWeatherServiceImplTest {

    @Autowired
    private CityWeatherService cityWeatherService;

    @MockBean
    private WeatherWebService webServiceMock;

    @MockBean
    private CityWeatherInfoStorageService cityWeatherInfoStorageServiceMock;

    @Test
    public void getCitiesWeatherInfoTest(){
        when(webServiceMock.findWeatherInfoForCity("Cluj-Napoca")).thenReturn(
                Mono.just(new CityWeatherInfo("Cluj-Napoca", 5,3))
        );
        when(webServiceMock.findWeatherInfoForCity("Arad")).thenReturn(
                Mono.just(new CityWeatherInfo("Arad", 2,6))
        );
        when(webServiceMock.findWeatherInfoForCity("Bucuresti")).thenReturn(
                Mono.just(new CityWeatherInfo("Bucuresti", 6,8))
        );

        var responseMono = cityWeatherService.getCitiesWeatherInfo(Set.of("Cluj-Napoca", "Arad", "Bucuresti", "Brasov"));
        var cityResponse = responseMono.block();
        assertNotNull(cityResponse);
        assertNotNull(cityResponse.result());
        assertEquals(3, cityResponse.result().size());
        assertEquals(cityResponse.result().get(0).name(), "Arad");
        assertEquals(cityResponse.result().get(1).name(), "Bucuresti");
        assertEquals(cityResponse.result().get(2).name(), "Cluj-Napoca");
    }
}
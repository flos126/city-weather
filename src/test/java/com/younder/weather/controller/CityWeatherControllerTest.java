package com.younder.weather.controller;

import com.younder.weather.model.CityWeatherInfo;
import com.younder.weather.model.CityWeatherResponse;
import com.younder.weather.service.in.CityWeatherService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CityWeatherController.class})
class CityWeatherControllerTest {

    @Autowired
    private CityWeatherController cityWeatherController;

    @MockBean
    private CityWeatherService cityWeatherServiceMock;


    private CityWeatherInfo cityArad=new CityWeatherInfo("Arad", 5,10);
    private CityWeatherInfo cityBuc=new CityWeatherInfo("Bucuresti", 5,10);
    private CityWeatherInfo cityCluj=new CityWeatherInfo("Cluj-Napoca", 5,10);


    @Test
    public void testGetCities() throws Exception {
        when(cityWeatherServiceMock.getCitiesWeatherInfo(any())).thenReturn(
                Mono.just(new CityWeatherResponse(List.of(cityArad, cityBuc, cityCluj)))
        );

        WebTestClient
                .bindToController(cityWeatherController)
                .configureClient()
                .baseUrl("/api/weather?city=CLuj-Napoca,Bucuresti,Craiova,Timisoara,Dej,Constanta,Baia-Mare,Arad,Bistrita,Iasi,Oradea,Cluj-Napoca")
                .build()
                .get()
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(CityWeatherResponse.class)
                .consumeWith(response -> {
                    assertNotNull(response.getResponseBody());
                    var responseBody = response.getResponseBody();
                    assertEquals(responseBody.result().size(), 3);
                    assertEquals(responseBody.result().get(0).name(), cityArad.name());
                    assertEquals(responseBody.result().get(1).name(), cityBuc.name());
                    assertEquals(responseBody.result().get(2).name(), cityCluj.name());
                });
    }

}
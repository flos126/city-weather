package com.younder.weather;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties
@Getter
public class PropertiesConfig {

    @Value("#{'${cities}'.split(',')}")
    private List<String> cityList;

    @Value("${csvExportDirectory}")
    private String csvExportDirectory;

    @Value("${csvFileName}")
    private String csvFileName;

    @Value("${weather-server-url}")
    private String weatherServerUrl;

    @PostConstruct
    public void cityListToLowerCase() {
        cityList = cityList.stream()
                .map(c-> c.toLowerCase().trim())
                .toList();
    }

}

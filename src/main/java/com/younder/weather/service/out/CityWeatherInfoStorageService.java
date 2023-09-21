package com.younder.weather.service.out;

import com.younder.weather.model.CityWeatherInfo;

import java.util.List;

public interface CityWeatherInfoStorageService {

    /**
     *
     * @param cityInfoList to be saved
     */
    void saveCitiesWeatherInfo(List<CityWeatherInfo> cityInfoList);
}

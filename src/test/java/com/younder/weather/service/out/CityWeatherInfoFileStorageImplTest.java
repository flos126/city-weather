package com.younder.weather.service.out;

import com.younder.weather.PropertiesConfig;
import com.younder.weather.model.CityWeatherInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CityWeatherInfoFileStorageImplTest {

    @Autowired
    private CityWeatherInfoStorageService weatherInfoStorageService;

    @Autowired
    private PropertiesConfig propertiesConfig;

    private CityWeatherInfo cityArad=new CityWeatherInfo("Arad", 5,10);
    private CityWeatherInfo cityBuc=new CityWeatherInfo("Bucuresti", 5,10);
    private CityWeatherInfo cityCluj=new CityWeatherInfo("Cluj-Napoca", 5,10);


    @Test
    public void saveCitiesWeatherInfoTest(){
        String filePath = propertiesConfig.getCsvExportDirectory() + "/" + propertiesConfig.getCsvFileName() + ".csv";
        File dir = new File(propertiesConfig.getCsvExportDirectory());
        File csvFile = new File(filePath);
        csvFile.delete();
        dir.delete();

        assertFalse(dir.isDirectory());
        assertFalse(csvFile.isFile());

        weatherInfoStorageService.saveCitiesWeatherInfo(List.of(cityArad, cityBuc, cityCluj));
        assertTrue(dir.isDirectory());
        assertTrue(csvFile.isFile());
        csvFile.delete();
        dir.delete();
    }

}
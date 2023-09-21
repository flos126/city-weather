package com.younder.weather.service.out;

import com.younder.weather.PropertiesConfig;
import com.younder.weather.model.CityWeatherInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CityWeatherInfoFileStorageServiceImpl implements CityWeatherInfoStorageService {

    private final PropertiesConfig propertiesConfig;

    @Override
    public void saveCitiesWeatherInfo(List<CityWeatherInfo> cityInfoList) {
        try {
            if (cityInfoList.isEmpty()) {
                log.info("No info to be write to file");
                return;
            }
            log.info("{} cities found, starting writing to csv", cityInfoList.size());
            writeToCsv(cityInfoList);
        } catch (IOException e) {
            log.error("Error Creating export directory", e);
        }
    }

    private void writeToCsv(List<CityWeatherInfo> cityList) throws IOException {
        String filePath = propertiesConfig.getCsvExportDirectory() + "/" + propertiesConfig.getCsvFileName() + ".csv";
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        FileWriter writer = new FileWriter(file);

        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("Name", "temperature", "wind");
            for (CityWeatherInfo c : cityList) {
                csvPrinter.printRecord(c.name(), c.averageTemperature(), c.averageWindSpeed());
            }
            log.info("City CSV Successfully exporter");

        } catch (IOException e) {
            log.error("Error While writing CSV File", e);
        }
    }
}

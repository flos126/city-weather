# Getting Started

### About

The service determine the temperature average and wind speed average for a list of configurable list of cities.
As a response the service returns the cities that matches with the cities from configuration, together with their
temperature and wind speed average, sorted alphabetically.
The result is in JSON format and it's also saved in a csv file.


### Configuration

| Key                | Description                                                                                 | Value                                                         | Mandatory |
|--------------------|---------------------------------------------------------------------------------------------|---------------------------------------------------------------|-----------|
| weather-server-url | The url for the web service that should return information about temperature and wind speed | https://goweather.herokuapp.com/weather/{city_name}           | YES       
| cities             | The list of cities for which we want to compute the temperature and wind speed              | CLuj-Napoca, Bucuresti, Timisoara, Constanta, Baia-Mare, Arad | YES       
| csvExportDirectory | Directory path for csv export                                                               | csv                                                           |  YES      |
| csvFileName        | The name of the csv file where the information for cities is saved                          | test-city-export | YES |

### API

The service exposes an Rest endpoint for retrieving weather information for a list of cities.

request type: GET   
url: http://serverip:port/api/weather?city={city list comma separated}  
e.g. localhost:  
http://localhost:8082/api/weather?city=CLuj-Napoca,Bucuresti,Craiova,Timisoara,Dej,Constanta,Baia-Mare,Arad,Cluj-Napoca  
If a city is present multiple time on the same request, only one record will be returned for that city

### Architecture

The application was written using java 17 and spring boot framework 3.x.
For writing csv files the application uses commons-csv library. 
Junit5 and Mockito were used for integration testing
The entry point for the application is CityWeatherController class that exposes a get endpoint for retrieving weather information for the provided list of cities.
The application uses 2 types of services: 
- in - used for processing the requests coming in
- out - used for interaction with external context of the application (retrieving info from a web service, saving to storage, etc). 

### To be improved

- GoWeather not working for the moment, results are mocked, another weather server should be used
- OpenApi library can be added for generating api documentation
- The service can be containerized
- A layer of security can be added 


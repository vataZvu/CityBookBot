package by.benikov.citybookbot.citybookbot.service;

import by.benikov.citybookbot.citybookbot.entity.City;
import by.benikov.citybookbot.citybookbot.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    public CityRepository cityRepository;

    public List<City> getCityList(){
        List<City> cityList = cityRepository.findAll();
        return cityList;
    }

    public City getCityByName(String name){
        City city = cityRepository.findByCityName(name);
        return city;
    }

    public City getCityById(int id){
        City city = cityRepository.findById(id);
        return city;
    }

    public void deleteById(int id){
        cityRepository.deleteById(id);
    }
}

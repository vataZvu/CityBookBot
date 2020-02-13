package by.benikov.citybookbot.citybookbot.controller;

import by.benikov.citybookbot.citybookbot.entity.City;
import by.benikov.citybookbot.citybookbot.repository.CityRepository;
import by.benikov.citybookbot.citybookbot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bot/controller")
public class CityBookBotController {

    @Autowired
    public CityService cityService;

    @Autowired
    public CityRepository cityRepository;

    @GetMapping
    public String controller(){
        List<City> cityList  = cityService.getCityList();
        return cityList.toString();
    }

    @GetMapping("{id}")
    public String getCityById(@PathVariable int id){
        City city = cityService.getCityById(id);
        return city.toString();
    }

    @DeleteMapping("{id}")
    public void deleteCityById(@PathVariable int id){
        cityService.deleteById(id);
    }

    @PutMapping
    public void addCity(@RequestBody City city){
        cityRepository.save(city);
    }

    @PutMapping("{id}")
    public City editCity(@PathVariable int id, @RequestBody City city){
        City cityDb = cityService.getCityById(id);
        if(city.getCityName()!= null){
            cityDb.setCityName(city.getCityName());
        }
        if (city.getCityDescription()!=null){
            cityDb.setCityDescription(city.getCityDescription());
        }
        cityRepository.save(cityDb);
        return cityDb;
    }
}

package by.benikov.citybookbot.citybookbot.repository;

import by.benikov.citybookbot.citybookbot.entity.City;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CityRepository extends CrudRepository<City, Integer> {
    City findByCityName(String name);
    City findById(int id);
    List<City> findAll();
    City deleteById(int id);
}

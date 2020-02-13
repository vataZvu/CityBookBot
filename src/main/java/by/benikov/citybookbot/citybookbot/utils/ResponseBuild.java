package by.benikov.citybookbot.citybookbot.utils;

import by.benikov.citybookbot.citybookbot.entity.City;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ResponseBuild {
    public static String getMessage(City city){
        String cityName = city.getCityName();
        String cityDescription = city.getCityDescription();
        String response = cityName + "\n" + "\n" + cityDescription;
        return response;
    }
}

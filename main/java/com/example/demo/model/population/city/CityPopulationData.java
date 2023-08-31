package com.example.demo.model.population.city;

import com.example.demo.model.population.city.CityPopulationCounts;
import lombok.Data;

import java.util.List;

@Data
public class CityPopulationData {
    private String city;
    private String country;
    private List<CityPopulationCounts> populationCounts;

}

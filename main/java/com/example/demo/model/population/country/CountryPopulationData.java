package com.example.demo.model.population.country;

import lombok.Data;

import java.util.List;

@Data
public class CountryPopulationData {
    private String country;
    private String code;
    private String iso3;

    private List<Object> populationCounts;
}

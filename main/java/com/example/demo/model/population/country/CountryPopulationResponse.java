package com.example.demo.model.population.country;

import com.example.demo.model.population.country.CountryPopulationData;
import lombok.Data;

@Data
public class CountryPopulationResponse {
    private boolean error;
    private String msg;
    private CountryPopulationData data;
}

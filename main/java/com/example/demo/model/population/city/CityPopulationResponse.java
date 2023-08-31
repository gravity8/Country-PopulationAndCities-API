package com.example.demo.model.population.city;


import com.example.demo.model.population.city.CityPopulationData;
import lombok.Data;

@Data
public class CityPopulationResponse {
    private boolean error;
    private String msg;
    private CityPopulationData data;



}

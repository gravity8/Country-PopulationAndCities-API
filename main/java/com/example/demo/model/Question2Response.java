package com.example.demo.model;

import com.example.demo.model.iso2andiso3.Iso2AndIso3;
import com.example.demo.model.location.CountryLocation;
import lombok.Data;

import java.util.List;

@Data
public class Question2Response {
    private List<Object> population;
    private String capitalCity;
    private CountryLocation location;
    private String currency;
    private Iso2AndIso3 ISO2And3;

    public Question2Response(List<Object> population,
                             String capitalCity,
                             CountryLocation location,
                             String currency,
                             Iso2AndIso3 ISO2And3
    ) {
        this.population = population;
        this.capitalCity = capitalCity;
        this.location = location;
        this.currency = currency;
        this.ISO2And3 = ISO2And3;
    }
}

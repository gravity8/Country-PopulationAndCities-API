package com.example.demo.model.population.country;

import lombok.Data;

@Data
public class Country {
    private String country;

    public Country(String country) {
        this.country = country;
    }
}

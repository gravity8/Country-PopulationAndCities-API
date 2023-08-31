package com.example.demo.model.city;

import lombok.Data;

@Data
public class City {
    private String city;

    public City(String city) {
        this.city = city;
    }
}

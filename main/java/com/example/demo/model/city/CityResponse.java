package com.example.demo.model.city;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CityResponse {
    private String error;
    private String msg;
    private List<City> data = new ArrayList<>();


}

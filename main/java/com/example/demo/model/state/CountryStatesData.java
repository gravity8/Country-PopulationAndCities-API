package com.example.demo.model.state;

import com.example.demo.model.city.City;
import lombok.Data;

import java.util.List;

@Data
public class CountryStatesData {
    private City name;
    private String Iso3;
    private String Iso2;
    private List<StateObject> states;
}

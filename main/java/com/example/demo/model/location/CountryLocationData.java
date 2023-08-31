package com.example.demo.model.location;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CountryLocationData {
    private String name;
    private String capital;
    private String Iso2;
    private String Iso3;
    @JsonProperty("long")
    private int Long;
    private int lat;


}

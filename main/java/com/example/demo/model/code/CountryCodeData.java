package com.example.demo.model.code;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CountryCodeData {
    private String name;
    @JsonProperty("Iso2")
    private String Iso2;
    @JsonProperty("Iso3")
    private String Iso3;
}

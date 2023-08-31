package com.example.demo.model.capital;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CapitalResponseData {
    private String name;
    private String capital;
    private String Iso2;
    private String Iso3;
}

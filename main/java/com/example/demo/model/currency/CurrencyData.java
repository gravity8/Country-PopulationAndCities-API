package com.example.demo.model.currency;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CurrencyData {
    private String name;
    private String currency;

    private String Iso2;

    private String Iso3;
}

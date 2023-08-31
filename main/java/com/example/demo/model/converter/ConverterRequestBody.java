package com.example.demo.model.converter;

import com.example.demo.model.iso2andiso3.Iso3;
import com.example.demo.model.population.country.Country;
import lombok.Data;

@Data
public class ConverterRequestBody {
    private Country country;
    private int amount;
    private Iso3 targetCurrency;
}

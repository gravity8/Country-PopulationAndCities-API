package com.example.demo.controllers;


import com.example.demo.model.*;
import com.example.demo.model.capital.CapitalResponse;
import com.example.demo.model.code.CountryCode;
import com.example.demo.model.currency.CurrencyData;
import com.example.demo.model.currency.CurrencyResponse;
import com.example.demo.model.iso2andiso3.Iso2;
import com.example.demo.model.iso2andiso3.Iso2AndIso3;
import com.example.demo.model.iso2andiso3.Iso3;
import com.example.demo.model.location.CountryLocation;
import com.example.demo.model.location.CountryLocationData;
import com.example.demo.model.location.CountryLocationResponse;
import com.example.demo.model.population.country.Country;
import com.example.demo.model.population.country.CountryPopulationData;
import com.example.demo.model.population.country.CountryPopulationResponse;
import com.example.demo.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class CountryDataController {

    CountryDataServices countryDataServices;
    CountryIsoCodeServices countryIsoCodeServices;
    CapitalServices capitalServices;
    LocationServices locationServices;
    CurrencyServices currencyServices;

    @Autowired
    public CountryDataController(CountryDataServices countryDataServices,
                                 CountryIsoCodeServices countryIsoCodeServices,
                                 CapitalServices capitalServices,
                                 LocationServices locationServices,
                                 CurrencyServices currencyServices

    ){
        this.countryDataServices= countryDataServices;
        this.countryIsoCodeServices = countryIsoCodeServices;
        this.capitalServices = capitalServices;
        this.locationServices = locationServices;
        this.currencyServices = currencyServices;
    }

    Question2Response jsonResponse;

    @GetMapping ("/country-data/{country}")
    public ResponseEntity<Question2Response> getSingleCountryData(@PathVariable Country country){
        CountryCode countryCode =countryIsoCodeServices.getSingleCountryData(country);

        Iso2 countryIso2 = new Iso2();
        countryIso2.setIso2(countryCode.getData().getIso2());
        Iso3 countryIso3 = new Iso3(countryCode.getData().getIso3());
        Iso2AndIso3 countryIso2AndIso3 = new Iso2AndIso3();
        countryIso2AndIso3.setIso2(countryCode.getData().getIso2());
        countryIso2AndIso3.setIso3(countryCode.getData().getIso3());

        System.out.println(countryCode.getData());
//        jsonResponse.setISO2And3();
        CapitalResponse capitalResponse = capitalServices.getCountryCapital(countryIso2);
        CountryLocationResponse locationResponse = locationServices.getCountryLocation(countryIso2);
        CurrencyResponse currencyResponse = currencyServices.getCountryCurrency(countryIso2);


        CountryLocationData locationData = locationResponse.getData();
        CurrencyData currencyData = currencyResponse.getData();



        CountryLocation countryLocation = new CountryLocation(locationData.getLong(),locationData.getLat());
        String capital = capitalResponse.getData().getCapital();
        CountryPopulationResponse countryPopulationResponse = countryDataServices.getCountryData(countryIso3);
        CountryPopulationData countryPopulationData = countryPopulationResponse.getData();
        List<Object> countryPopulation = countryPopulationData.getPopulationCounts();
        String countryCurrency = currencyData.getCurrency();

        jsonResponse = new Question2Response(
                countryPopulation,
                capital,
                countryLocation,
                countryCurrency,
                countryIso2AndIso3);

        return ResponseEntity.ok(jsonResponse);
    }
}

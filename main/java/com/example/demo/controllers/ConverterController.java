package com.example.demo.controllers;

import com.example.demo.model.code.CountryCode;
import com.example.demo.model.code.CountryCodeData;
import com.example.demo.model.currency.CurrencyResponse;
import com.example.demo.model.iso2andiso3.Iso2;
import com.example.demo.model.population.country.Country;
import com.example.demo.services.CountryIsoCodeServices;
import com.example.demo.services.CurrencyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ConverterController {

    CurrencyServices currencyServices;
    CountryIsoCodeServices countryIsoCodeServices;

    @Autowired
    public ConverterController(CurrencyServices currencyServices,
                               CountryIsoCodeServices countryIsoCodeServices
    ) {
        this.currencyServices = currencyServices;
        this.countryIsoCodeServices = countryIsoCodeServices;

    }

    @GetMapping("/convert")
    public String convertCurrency(
            @RequestParam Country country,
            @RequestParam double amount,
            @RequestParam String targetCurrency
    ) {

        CountryCode countryCode = countryIsoCodeServices.getSingleCountryData(country);
        CountryCodeData countryCodeData = countryCode.getData();
        Iso2 iso2 = new Iso2(countryCodeData.getIso2());
        CurrencyResponse countryResponse = currencyServices.getCountryCurrency(iso2);
        String countryCurrency = countryResponse.getData().getCurrency();

        // Get the country currency from the csv file
        Map<String, String> currencies = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/david/Downloads/demo/src/main/resources/conversion-rates.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if(data[0].equals(countryCurrency)&&data[1].equals(targetCurrency)){
                    System.out.println(data[0]+""+data[1]+""+data[2]);
                    currencies.put(data[1],data[2]);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Get the exchange rate from the csv file
        Double exchangeRate = Double.valueOf(currencies.get(targetCurrency));

        // Convert the amount to the target currency
        Double convertedAmount = amount * exchangeRate;

        // Return the country currency and the converted amount
        return String.format("%s: %s",amount+countryCurrency, convertedAmount+targetCurrency);
    }
}


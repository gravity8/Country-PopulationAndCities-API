package com.example.demo.model.converter;

import lombok.Data;

@Data
public class ConversionRate {
    private String sourceCurrency;
    private String targetCurrency;
    private double rate;

    public ConversionRate(String sourceCurrency,String targetCurrency,double rate){
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency=targetCurrency;
        this.rate = rate;
    }
}

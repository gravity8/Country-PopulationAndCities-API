package com.example.demo.model.currency;

import com.example.demo.model.currency.CurrencyData;
import lombok.Data;

@Data
public class CurrencyResponse {
    private String error;
    private String msg;
    private CurrencyData data;
}

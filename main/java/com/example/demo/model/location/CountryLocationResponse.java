package com.example.demo.model.location;

import com.example.demo.model.location.CountryLocationData;
import lombok.Data;

@Data
public class CountryLocationResponse {
    private String error;
    private String msg;
    private CountryLocationData data;
}

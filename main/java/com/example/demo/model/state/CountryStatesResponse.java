package com.example.demo.model.state;

import com.example.demo.model.state.CountryStatesData;
import lombok.Data;

@Data
public class CountryStatesResponse {
    private String error;
    private String msg;
    private CountryStatesData data;
}

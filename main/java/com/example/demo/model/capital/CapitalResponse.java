package com.example.demo.model.capital;

import lombok.Data;

@Data
public class CapitalResponse {
    private String error;
    private String msg;
    private CapitalResponseData data;
}

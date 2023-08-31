package com.example.demo.model.code;

import lombok.Data;

@Data
public class CountryCode {
    private boolean error;
    private String msg;
    private CountryCodeData data;

}

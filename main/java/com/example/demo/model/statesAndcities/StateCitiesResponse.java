package com.example.demo.model.statesAndcities;

import lombok.Data;

import java.util.List;

@Data
public class StateCitiesResponse {
    private String error;
    private String msg;
    private List<String> data;
}

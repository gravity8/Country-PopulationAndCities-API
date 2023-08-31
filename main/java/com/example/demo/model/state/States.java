package com.example.demo.model.state;

import lombok.Data;

import java.util.List;

@Data
public class States {
    private Object cities;

    public States(Object cities){
        this.cities = cities;
    }
}

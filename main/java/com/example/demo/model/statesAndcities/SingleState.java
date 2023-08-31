package com.example.demo.model.statesAndcities;

import lombok.Data;

@Data
public class SingleState {
    private String name;

    public SingleState(String name){
        this.name =name;
    }
}

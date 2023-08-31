package com.example.demo.model.location;

import lombok.Data;

@Data
public class CountryLocation {
    private int Long;
    private int Lat;

    public CountryLocation(int Long, int Lat){
        this.Long = Long;
        this.Lat = Lat;
    }
}

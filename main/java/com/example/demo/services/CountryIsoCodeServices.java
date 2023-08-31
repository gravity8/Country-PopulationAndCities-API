package com.example.demo.services;

import com.example.demo.model.population.country.Country;
import com.example.demo.model.code.CountryCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class CountryIsoCodeServices {

    public CountryCode getSingleCountryData(Country country){
        CountryCode finalResp=null;
        try{
            String countryToString = new Gson().toJson(country);
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, countryToString);
            Request request = new Request.Builder()
                    .url("https://countriesnow.space/api/v0.1/countries/iso")
                    .method("POST", body)
                    .build();
            Response response = client.newCall(request).execute();
            String serverResp = response.peekBody(Long.MAX_VALUE).string();
            ObjectMapper mapper = new ObjectMapper();
            finalResp = mapper.readValue(serverResp, CountryCode.class);

        }
        catch(Exception ex){
            throw new RuntimeException(ex);
        }
        return finalResp;
    }

}

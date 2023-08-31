package com.example.demo.services;

import com.example.demo.model.population.country.Country;
import com.example.demo.model.state.CountryStatesResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class StatesServices {

    public CountryStatesResponse getCountryStates(Country country){
        CountryStatesResponse finalResp = null;
        try{
            String countryToString = new Gson().toJson(country);
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, countryToString);
            Request request = new Request.Builder()
                    .url("https://countriesnow.space/api/v0.1/countries/states")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            String serverResp = response.peekBody(Long.MAX_VALUE).string();
            ObjectMapper mapper= new ObjectMapper();
            finalResp = mapper.readValue(serverResp, CountryStatesResponse.class);
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
        return finalResp;
    }
}

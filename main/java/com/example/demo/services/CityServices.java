package com.example.demo.services;

import com.example.demo.model.population.country.Country;
import com.example.demo.model.city.CityResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import okhttp3.*;
import org.springframework.stereotype.Service;



//This class is reponsible for interacting directly with the External APi
@Service
public class CityServices {

    public CityResponse getCitiesInCountry(Country countryRequest){
        CityResponse finalResp = null;

        try {
            String countryInString = new Gson().toJson(countryRequest);

            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, countryInString);
            System.out.println(body);
            Request request = new Request.Builder()
                    .url("https://countriesnow.space/api/v0.1/countries/cities")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            String serverResp = response.peekBody(Long.MAX_VALUE).string();
            ObjectMapper objectMapper = new ObjectMapper();
            finalResp = objectMapper.readValue(serverResp, CityResponse.class);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
        return finalResp;
    }
}


package com.example.demo.services;

import com.example.demo.model.city.City;
import com.example.demo.model.population.city.CityPopulationResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class PopulationServices {

    public CityPopulationResponse getPopulationData(City city){
        CityPopulationResponse finalResp = null;
        try{
            String cityInString = new Gson().toJson(city);

            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, cityInString);
            Request request = new Request.Builder()
                    .url("https://countriesnow.space/api/v0.1/countries/population/cities")
                    .method("POST", body)
                    .build();
            Response response = client.newCall(request).execute();
            String serverResp = response.peekBody(Long.MAX_VALUE).string();
            ObjectMapper objectMapper = new ObjectMapper();
            finalResp = objectMapper.readValue(serverResp, CityPopulationResponse.class);

        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
        return finalResp;
    }
}

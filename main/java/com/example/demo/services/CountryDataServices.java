package com.example.demo.services;

import com.example.demo.model.population.country.CountryPopulationResponse;
import com.example.demo.model.iso2andiso3.Iso3;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class CountryDataServices {

    public CountryPopulationResponse getCountryData(Iso3 code){
        CountryPopulationResponse finalResp = null;
        try{
            String codeToString = new Gson().toJson(code);
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType,codeToString );
            Request request = new Request.Builder()
                    .url("https://countriesnow.space/api/v0.1/countries/population")
                    .method("POST", body)
                    .build();
            Response response = client.newCall(request).execute();
            String serverResp = response.peekBody(Long.MAX_VALUE).string();

            ObjectMapper mapper = new ObjectMapper();
            finalResp = mapper.readValue(serverResp, CountryPopulationResponse.class);
        }
        catch(Exception ex){
            throw new RuntimeException(ex);
        }
        return finalResp;
    }
}

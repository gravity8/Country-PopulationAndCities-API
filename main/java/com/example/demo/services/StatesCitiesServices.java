package com.example.demo.services;

import com.example.demo.model.statesAndcities.CountryAndState;
import com.example.demo.model.statesAndcities.StateCitiesResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class StatesCitiesServices {
    public StateCitiesResponse getStatesCities(CountryAndState countryAndState){
        StateCitiesResponse finalResp;

        try {
            String countryAndcityToString = new Gson().toJson(countryAndState);
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, countryAndcityToString);
            Request request = new Request.Builder()
                    .url("https://countriesnow.space/api/v0.1/countries/state/cities")
                    .method("POST", body)
                    .build();
            Response response = client.newCall(request).execute();
            String serverResp = response.peekBody(Long.MAX_VALUE).string();

            ObjectMapper mapper = new ObjectMapper();
            finalResp = mapper.readValue(serverResp, StateCitiesResponse.class);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }


        return finalResp;
    }
}

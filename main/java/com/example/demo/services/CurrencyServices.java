package com.example.demo.services;

import com.example.demo.model.currency.CurrencyResponse;
import com.example.demo.model.iso2andiso3.Iso2;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class CurrencyServices {
    public CurrencyResponse getCountryCurrency(Iso2 iso2){
        CurrencyResponse finalResp;
        try {
            String iso2ToString  = new Gson().toJson(iso2);
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, iso2ToString);
            Request request = new Request.Builder()
                    .url("https://countriesnow.space/api/v0.1/countries/currency")
                    .method("POST", body)
                    .build();
            Response response = client.newCall(request).execute();
            String serverResp = response.peekBody(Long.MAX_VALUE).string();
            ObjectMapper mapper = new ObjectMapper();

            finalResp = mapper.readValue(serverResp, CurrencyResponse.class);

        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
        return finalResp;
    }
}

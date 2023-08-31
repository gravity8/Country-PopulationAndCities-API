package com.example.demo.controllers;
import com.example.demo.model.population.country.Country;
import com.example.demo.model.state.CountryStatesData;
import com.example.demo.model.state.CountryStatesResponse;
import com.example.demo.model.statesAndcities.CountryAndState;
import com.example.demo.model.statesAndcities.SingleState;
import com.example.demo.model.statesAndcities.StateCitiesResponse;
import com.example.demo.services.StatesCitiesServices;
import com.example.demo.services.StatesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;


@RequestMapping("/api")
@RestController
public  class StatesAndCitiesController {
    StatesServices statesServices;
    StatesCitiesServices statesCitiesServices;

    @Autowired
    public StatesAndCitiesController(StatesServices statesServices,
                                     StatesCitiesServices statesCitiesServices){
        this.statesServices = statesServices;
        this.statesCitiesServices = statesCitiesServices;
    }
    @GetMapping("/states-and-cities/{country}")
    public ResponseEntity<HashMap<String, HashMap<String,List<String>>>> getCountryStatesAndCities(@PathVariable Country country){
        HashMap<String, HashMap<String,List<String>>> ListOfStateAndItsCity = new HashMap<>();
        CountryStatesResponse countryStatesResponse = statesServices.getCountryStates(country);
        CountryStatesData statesData = countryStatesResponse.getData();

        statesData.getStates().forEach(state->
                {
                    SingleState singleState = new SingleState(state.getName());
                    CountryAndState countryAndState =new CountryAndState(country.getCountry(),singleState.getName());
                    StateCitiesResponse statesCitiesResponse = statesCitiesServices.getStatesCities(countryAndState);
//
                    List<String> stateCities = statesCitiesResponse.getData();
                    HashMap<String,List<String>> cities= new HashMap<>();
                    cities.put("Cities",stateCities);


                    ListOfStateAndItsCity.put(singleState.getName(),cities);
                }
                );

        return ResponseEntity.ok(ListOfStateAndItsCity);
    }

}

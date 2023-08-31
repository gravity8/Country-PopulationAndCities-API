package com.example.demo.controllers;

import com.example.demo.model.city.CityResponse;
import com.example.demo.model.population.city.CityPopulationCounts;
import com.example.demo.model.population.city.CityPopulationResponse;
import com.example.demo.model.population.country.Country;
import com.example.demo.services.CityServices;
import com.example.demo.services.PopulationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


//This class majorly handles all my Endpoints
@RequestMapping("/api")
@RestController
public class NthCountryMaxPopulation {


    //Importing the required Services
    private final CityServices cityServices;
    private final PopulationServices populationServices;

    @Autowired
    public NthCountryMaxPopulation(CityServices cityServices, PopulationServices populationServices) {
        this.cityServices = cityServices;
        this.populationServices = populationServices;
    }

    //Instances of Each Country
    Country italy = new Country("italy");
    Country newZealand = new Country("new zealand");
    Country ghana = new Country("ghana");
    Country nigeria = new Country("nigeria");



    @GetMapping("/max-population")
    public ResponseEntity<Object> getCitiesInCountry(@RequestParam int N) {
        List<Country> countries = Arrays.asList(ghana,nigeria);
        try {
            HashMap<Integer, String> maxPopulation = new HashMap<>();
            HashMap<String, List<String>> countryAndMaxPopulation = new HashMap<>();
            for (Country country : countries) {
                PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
                CityResponse response = cityServices.getCitiesInCountry(country);
                response.getData().forEach(city -> {
                    CityPopulationResponse cityPopulationData = populationServices.getPopulationData(city);
                    if (!cityPopulationData.isError()) {
                        //I use this to get the pop
                        List<CityPopulationCounts> populationCounts = cityPopulationData.getData().getPopulationCounts();
                        if (!populationCounts.isEmpty()) {
                            int populationValue = populationCounts.get(0).getValue();

                            // Add population value to the max heap
                            maxHeap.offer(populationValue);

                            // If max heap size exceeds N, remove the smallest element
                            if (maxHeap.size() > N) {
                                maxHeap.poll();
                            }

                            // Update maxPopulation map
                            maxPopulation.put(populationValue, city.getCity());
                        }
                    }
                });
                // Retrieve N cities with highest population from the max heap
                List<String> citiesWithHighestPopulation = new ArrayList<>();
                while (!maxHeap.isEmpty()) {
                    int populationValue = maxHeap.poll();
                    citiesWithHighestPopulation.add(maxPopulation.get(populationValue));
                }
                countryAndMaxPopulation.put(country.getCountry(),citiesWithHighestPopulation);
            }


            // Set the Status Code to 200 and return a ResponseEntity containing the responses
            return ResponseEntity.ok(countryAndMaxPopulation);

        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}


package com.task.Controller;

import com.google.gson.Gson;
import com.task.Service.CountriesService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin(origins = {"*"})
@RestController
public class CountriesController{

    private final CountriesService countriesService;

    public CountriesController(CountriesService countriesService){
        this.countriesService = countriesService;
    }

    @GetMapping("/countryData")
    private String getAllCountries() {
        String response = countriesService.retrieveData();
        return response;
    }

}
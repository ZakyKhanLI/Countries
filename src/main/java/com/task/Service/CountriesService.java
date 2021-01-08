package com.task.Service;


import com.task.util.Constants;
import com.task.util.RestHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CountriesService {

    public String retrieveData(){
        ResponseEntity<String> response = RestHelper.makeCall(Constants.COUNTRY_URL);
        String responseBody = response.getBody();

        return responseBody;
    }

}

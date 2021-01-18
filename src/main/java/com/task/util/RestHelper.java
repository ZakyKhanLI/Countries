package com.task.util;

import com.task.controller.AppController;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class RestHelper {
    public static ResponseEntity<String> makeCall(String apiUrl) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Shopify-Access-Token", AppController.accessToken);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        return restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);
    }
}

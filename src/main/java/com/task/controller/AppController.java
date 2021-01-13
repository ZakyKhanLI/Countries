package com.task.controller;

import com.task.service.AppService;
import com.task.util.Constants;
import org.apache.coyote.Request;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = {"*"})
@RestController
public class AppController {

    private final AppService appService;

    public AppController(AppService appService){
        this.appService = appService;
    }

    @GetMapping("/Generate")
    private String generateCode() {
        return appService.retrieveData();
    }

    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public void method(HttpServletResponse httpServletResponse) {
        String shopifyUrl = Constants.SHOPIFY_URL;
        httpServletResponse.setHeader("Location", shopifyUrl);
        httpServletResponse.setStatus(302);
    }

    @RequestMapping(value="generate_token", method = RequestMethod.GET)
    public @ResponseBody static String getCode(@RequestParam("code") String code){
        return generateAccessToken(code);
    }

    public static String generateAccessToken(String code){
        String token = "";
        try{
            System.out.println("step 1");
            RestTemplate restTemplate = new RestTemplate();
            MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
            params.add("client_id", "ef98eff4c64e483b2e61fab4e7503843");
            params.add("client_secret", "d3433307e5a95a72acb6600c65911920");
            params.add("code", code);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(Constants.TOKEN_URL, request , String.class);
            System.out.println(String.valueOf(response));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return token;
    }
}
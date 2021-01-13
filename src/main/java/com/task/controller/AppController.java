package com.task.controller;

import com.task.service.AppService;
import com.task.util.Constants;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
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
        return code;
    }

    @PostMapping(value = "/request")
    public URI accessToken(){
        Map<String, String> params = new HashMap<>();
        params.put("client_id" , "ef98eff4c64e483b2e61fab4e7503843");
        params.put("client_secret", "d3433307e5a95a72acb6600c65911920");
        params.put("code", AppController.getCode(Constants.TOKEN_URL));
        RestTemplate template = new RestTemplate();
        return template.postForLocation(Constants.TOKEN_URL, HttpMethod.POST, params);
    }
}
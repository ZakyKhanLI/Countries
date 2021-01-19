package com.task.controller;

import com.task.entity.Products;
import com.task.service.AppService;
import com.task.util.Constants;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin(origins = {"*"})
@RestController
public class AppController {
    @Autowired
    private final AppService appService;

    public static String accessToken = "";
    public static  String API_KEY = "ef98eff4c64e483b2e61fab4e7503843";

    public AppController(AppService appService){
        this.appService = appService;
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
            RestTemplate restTemplate = new RestTemplate();
            MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
            params.add("client_id", "ef98eff4c64e483b2e61fab4e7503843");
            params.add("client_secret", "d3433307e5a95a72acb6600c65911920");
            params.add("code", code);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(Constants.TOKEN_URL, request , String.class);
            JSONObject jsonObject = new JSONObject(response.getBody());
            accessToken = jsonObject.getString("access_token");

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return token;
    }

    @GetMapping("/Product")
    private String generateCode() throws JSONException {
        return appService.retrieveData();
    }

    @GetMapping("/Product/{id}")
    private Products getProducts(@PathVariable("id") Long id) throws JSONException {
        return appService.retrieveDataById(id);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public void getData(HttpServletResponse httpServletResponse) {
        String DATA_URL = "https://"+API_KEY+":"+accessToken+"@securecod4.myshopify.com/admin/api/2020-07/products.json";
        httpServletResponse.setHeader("Location", DATA_URL);
        httpServletResponse.setStatus(302);
    }

    @PostMapping("/Product")
    public Long saveProductOnDB(@RequestBody Products products){
        appService.saveOrUpdateProductOnDB(products);
        return products.getId();
    }

    @DeleteMapping("/Product/{id}")
    public void deleteProduct(@PathVariable("id")  Long id){
        appService.deleteProduct(id);
    }
}
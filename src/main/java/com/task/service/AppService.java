package com.task.service;

import com.task.entity.Products;
import com.task.repository.AppRepository;
import com.task.util.Constants;
import com.task.util.RestHelper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class AppService {
    @Autowired
    AppRepository appRepository;

    public String retrieveData() throws JSONException {
        Products products = new Products();
        ResponseEntity<String> response = RestHelper.makeCall(Constants.PRODUCT_URL);
        JSONObject product = new JSONObject(response.getBody());
        JSONArray  getArray = product.getJSONArray("products");
        for(int i = 0; i < getArray.length(); i++){
            JSONObject objects = getArray.getJSONObject(i);
            products.setId(Long.valueOf(String.valueOf(objects.get("id"))));
            products.setTitle(String.valueOf(objects.get("title")));
            products.setBody_html(String.valueOf(objects.get("vendor")));
            products.setProduct_type(String.valueOf(objects.get("product_type")));
            appRepository.save(products);
        }
        return response.getBody();
    }

    public Products retrieveDataById(Long id) throws JSONException {
        ResponseEntity<String> response = RestHelper.makeCall(Constants.PRODUCT_URL);
        JSONObject jsonObject = new JSONObject(response.getBody());
        id = Long.valueOf(String.valueOf(jsonObject.get("id")));
        return appRepository.findById(id).get();
    }
}

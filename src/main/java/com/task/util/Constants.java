package com.task.util;

import com.task.controller.AppController;

public class Constants{
    public static String PRODUCT_URL = "https://e3519ce8d2b72750210800f3ba7115f2:"+AppController.accessToken+"@securecod4.myshopify.com/admin/api/2020-07/products.json";
    public static String SHOPIFY_URL = "https://securecod4.myshopify.com/admin/oauth/authorize?client_id=ef98eff4c64e483b2e61fab4e7503843&scope=read_products,write_products&redirect_uri=http://localhost:5000/generate_token";
    public static String TOKEN_URL = "https://securecod4.myshopify.com/admin/oauth/access_token";
}

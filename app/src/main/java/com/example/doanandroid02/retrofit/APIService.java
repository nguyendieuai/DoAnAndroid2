package com.example.doanandroid02.retrofit;

public class APIService {

    private static String base_url = "http://192.168.13.2/doan-laravel/public/";

    public static DataClient getService(){


        return RetrofitClientInstance.getClient(base_url).create(DataClient.class);
    }
}

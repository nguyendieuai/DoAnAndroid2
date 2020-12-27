package com.example.doanandroid02.retrofit;

import com.example.doanandroid02.model.Category;
import com.example.doanandroid02.model.Customer;
import com.example.doanandroid02.model.Product;
import com.example.doanandroid02.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataClient {

    @GET("api/categories")
    Call<List<Category>> getCategory();

    @GET("api/products")
    Call<List<Product>> getProduct();

    @GET("api/customers")
    Call<List<Customer>> getCustomer();

    @POST("login.php")
    @FormUrlEncoded
    Call<User> checkLogin(@Field("email") String email,
                          @Field("password") String password);


    @POST("register.php")
    @FormUrlEncoded
    Call<User> registerUser(@Field("name") String name,
                            @Field("email") String email,
                            @Field("password") String password
    );

}

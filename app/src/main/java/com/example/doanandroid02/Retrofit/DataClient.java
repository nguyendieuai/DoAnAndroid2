package com.example.doanandroid02.Retrofit;

import com.example.doanandroid02.Model.Category;
import com.example.doanandroid02.Model.Customer;
import com.example.doanandroid02.Model.Product;
import com.example.doanandroid02.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataClient {

    @GET("api/categories")
    Call<List<Category>> getResponse();

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

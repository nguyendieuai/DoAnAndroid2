package com.example.doanandroid02.repositories;

import android.util.Log;

import com.example.doanandroid02.model.Category;
import com.example.doanandroid02.model.Product;
import com.example.doanandroid02.retrofit.DataClient;
import com.example.doanandroid02.retrofit.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductRepository {
    DataClient api;
    public ProductRepository(){
        Retrofit adapter = RetrofitClientInstance.getRetrofitInstance();
        api = adapter.create(DataClient.class);
    }
    public void loadAll(DataCallBack<Product> dataCallBack){
        api.getProduct().enqueue(new Callback<List<Product>>() {

            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> products;
                products = response.body();
                String item[] = new String[products.size()];
                for (int i = 0; i < products.size(); i++) {
                    item[i] = products.get(i).getAnh();
                    item[i] = products.get(i).getTen();
                    item[i] = String.valueOf(products.get(i).getGia_sp());
                }
                dataCallBack.response(products);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

    void find(int id){

    }
}

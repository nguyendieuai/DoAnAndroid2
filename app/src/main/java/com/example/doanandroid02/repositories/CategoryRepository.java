package com.example.doanandroid02.repositories;

import com.example.doanandroid02.model.Category;
import com.example.doanandroid02.retrofit.DataClient;
import com.example.doanandroid02.retrofit.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CategoryRepository {
    DataClient api;
    public CategoryRepository(){
        Retrofit adapter = RetrofitClientInstance.getRetrofitInstance();
        api = adapter.create(DataClient.class);
    }
    public void loadAll(DataCallBack<Category> dataCallBack){
        api.getCategory().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                List<Category> categories;
                categories = response.body();
                String item[] = new String[categories.size()];
                for (int i = 0; i < categories.size(); i++) {
                    item[i] = categories.get(i).getName();
                }
                dataCallBack.response(categories);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                //
            }
        });
    }

    void find(int id){

    }
}

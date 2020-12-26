package com.example.doanandroid02.Activity;

import android.widget.Toast;

import com.example.doanandroid02.Adapter.CategoryAdapter;
import com.example.doanandroid02.Model.Category;
import com.example.doanandroid02.Model.Product;
import com.example.doanandroid02.Retrofit.DataClient;
import com.example.doanandroid02.Retrofit.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainPresenter implements MainContract.Presenter {
    MainContract.View view;
    DataClient api;

    public MainPresenter(MainContract.View view){
        this.view = view;
        Retrofit adapter = RetrofitClientInstance.getRetrofitInstance();
        api = adapter.create(DataClient.class);
    }

    @Override
    public void loadCategories() {
        api.getCategory().enqueue(new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
                List<Category> categories;
                categories = response.body();
                String item[] = new String[categories.size()];
                for (int i = 0; i < categories.size(); i++) {
                    item[i] = categories.get(i).getName();
                }
                view.updateListCategories(categories);
            }

            @Override
            public void onFailure(Call<ArrayList<Category>> call, Throwable t) {
               // Toast.makeText(MainActivity.this, "KHông kết nối được với máy chủ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void loadProducts() {

    }
}

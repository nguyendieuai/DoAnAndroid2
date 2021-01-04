package com.example.doanandroid02.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanandroid02.R;
import com.example.doanandroid02.adapter.ProductAdapter;
import com.example.doanandroid02.models.Category;
import com.example.doanandroid02.models.Product;

import java.util.List;

public class ProductByIdActivity extends AppCompatActivity implements MainContract.View {


    RecyclerView recyclerView;
    ProductAdapter productAdapter;
    MainContract.Presenter mPresenter;
    public static String category_id;
    String category_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_by_id);

        Intent intent = getIntent();
        recyclerView = findViewById(R.id.recyclerViewProductId);
        category_id = intent.getStringExtra("category_id");
        category_name = intent.getStringExtra("category_name");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(category_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPresenter = new MainPresenter(this);
        mPresenter.findProducts();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void updateListProduct(List<Product> products) {

    }

    @Override
    public void updateListCategories(List<Category> categories) {

    }

    public void updateListProductById(List<Product> productList) {
        productAdapter = new ProductAdapter(productList, getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerView.setAdapter(productAdapter);
    }
}

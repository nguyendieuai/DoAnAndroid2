package com.example.doanandroid02.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ViewFlipper;

import com.example.doanandroid02.adapter.CategoryAdapter;
import com.example.doanandroid02.adapter.ProductAdapter;
import com.example.doanandroid02.model.Category;
import com.example.doanandroid02.model.Product;
import com.example.doanandroid02.R;
import com.example.doanandroid02.util.CheckConnectInternet;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerView;
    NavigationView navigationView;
    ListView listView;
    CategoryAdapter categoryAdapter;
    ProductAdapter productAdapter;
    CheckConnectInternet checkConnectInternet;
    MainContract.Presenter mPresenter;
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = findViewById(R.id.progressBar);
        mPresenter = new MainPresenter(this);
        CheckConnection();
    }

    public void CheckConnection() {
        if (checkConnectInternet.haveNetwork(getApplicationContext())){
            Mapping();
            ActionBar();
            ActionViewFlipper();

            mPresenter.loadCategories();
            mPresenter.loadProducts();
        } else {
            checkConnectInternet.ShowToast_Info(getApplicationContext(),"Check your internet!");
        }
    }

    private void ActionViewFlipper() {
        ArrayList<String> arrayAds = new ArrayList<>();
        arrayAds.add("http://192.168.102.2/doan-laravel/public/image/slide-1.png");
        arrayAds.add("http://192.168.102.2/doan-laravel/public/image/slide-2.png");
        arrayAds.add("http://192.168.102.2/doan-laravel/public/image/slide-3.png");

        for (int i = 0; i < arrayAds.size(); i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(arrayAds.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }

        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }


    private void Mapping() {
        drawerLayout = findViewById(R.id.draverlayout);
        toolbar = findViewById(R.id.toolbarmain);
        viewFlipper = findViewById(R.id.viewflipper);
        recyclerView = findViewById(R.id.recyclerview);
        navigationView = findViewById(R.id.navigationview);
        listView = findViewById(R.id.listviewmain);
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void updateListProduct(List<Product> products) {
        productAdapter = new ProductAdapter(products, getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerView.setAdapter(productAdapter);
    }

    @Override
    public void updateListCategories(List<Category> categories) {
        categoryAdapter = new CategoryAdapter(categories, getApplicationContext());
        listView.setAdapter(categoryAdapter);
    }
}
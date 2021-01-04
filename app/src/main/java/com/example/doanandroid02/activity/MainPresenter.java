package com.example.doanandroid02.activity;


import com.example.doanandroid02.repositories.CategoryRepository;
import com.example.doanandroid02.repositories.ProductById;
import com.example.doanandroid02.repositories.ProductRepository;

public class MainPresenter implements MainContract.Presenter {
    MainContract.View view;
    CategoryRepository categoryRepository;
    ProductRepository productRepository;
    ProductById productById;


    public MainPresenter(MainContract.View view) {
        this.view = view;
        categoryRepository = new CategoryRepository();
        productRepository = new ProductRepository();
        productById = new ProductById();
    }

    @Override
    public void loadCategories() {
        categoryRepository.loadAll(data -> view.updateListCategories(data));
    }

    @Override
    public void loadProducts() {
        productRepository.loadAll(data -> {
            view.updateListProduct(data);
        });
    }

    @Override
    public void findProducts() {
        productById.loadAll(data -> view.updateListProductById(data));
    }

}

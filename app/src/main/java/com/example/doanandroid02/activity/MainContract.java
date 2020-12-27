package com.example.doanandroid02.activity;

import com.example.doanandroid02.BasePresenter;
import com.example.doanandroid02.BaseView;
import com.example.doanandroid02.model.Category;
import com.example.doanandroid02.model.Product;

import java.util.List;

public interface MainContract {

    interface View extends BaseView{
        void showProgressBar();
        void hideProgressBar();
        void updateListProduct(List<Product> products);
        void updateListCategories(List<Category> categories);
    }

    interface Presenter extends BasePresenter{
        void loadCategories();
        void loadProducts();
    }
}

package com.example.doanandroid02.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanandroid02.R;
import com.example.doanandroid02.model.Category;
import com.example.doanandroid02.model.Product;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.text.DecimalFormat;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ItemHolder> {

    List<Product> products;
    Context context;

    public ProductAdapter(List<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_sanpham,null);
        ItemHolder itemHolder = new ItemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Product product = products.get(position);
        holder.textTenSp.setText(product.getTen());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.textGiaSp.setText("Price: " + decimalFormat.format(product.gia_sp) + "VND");
        Picasso.with(context).load("http://192.168.102.2/doan-laravel/public/upload/" + product.getAnh())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .transform(new CropSquareTransformation())
                .into(holder.imgSanPham);
    }


    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{

        public ImageView imgSanPham;
        public TextView textTenSp;
        public TextView textGiaSp;

        public ItemHolder(@NonNull View itemView){
            super(itemView);
            imgSanPham = itemView.findViewById(R.id.imgSanpham);
            textTenSp = itemView.findViewById(R.id.textTenSp);
            textGiaSp = itemView.findViewById(R.id.textGiaSp);
        }
    }

    public class CropSquareTransformation implements Transformation {
        @Override public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;
            Bitmap result = Bitmap.createBitmap(source, x, y, size, size);
            if (result != source) {
                source.recycle();
            }
            return result;
        }

        @Override public String key() { return "square()"; }
    }
}

package com.example.myproject.adapter;


import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myproject.ProductDetail;
import com.example.myproject.R;
import com.example.myproject.model.CategoryModel;
import com.example.myproject.model.ProductModel;
import com.example.myproject.utils.ConstantsData;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    ArrayList<ProductModel> product;

    public ProductAdapter(ArrayList<ProductModel> product) {
        this.product = product;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.productraw, parent, false);
        return new ProductViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.txtName.setText(product.get(position).getProductname());
        holder.txtprice.setText(product.get(position).getPrice());
        Glide.with(holder.img.getContext()).load(ConstantsData.SERVER_ADDRESS + product
                .get(position).getPic1()).into(holder.img);

//        Log.e("SERVER IMG", ConstantsData.SERVER_ADDRESS+product.get(position).getPic2());

        holder.img.setOnClickListener(v -> {
            Intent intent=new Intent(holder.img.getContext(), ProductDetail.class);
            intent.putExtra("model",product.get(position));
            holder.img.getContext().startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView txtName,txtprice;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            txtName=itemView.findViewById(R.id.txtName);
            txtprice=itemView.findViewById(R.id.txtprice);


        }
    }
}

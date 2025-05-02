package com.example.myproject.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myproject.ProductActivity;
import com.example.myproject.R;
import com.example.myproject.model.CategoryModel;
import com.example.myproject.model.ProductModel;
import com.example.myproject.utils.ConstantsData;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CViewHolder> {

    ArrayList<CategoryModel> categoryModels;
    ArrayList<ProductModel> productModels;

    public CategoryAdapter(ArrayList<CategoryModel> categoryModels, ArrayList<ProductModel> productModels) {
        this.categoryModels = categoryModels;
        this.productModels = productModels;
    }

    @NonNull
    @Override
    public CViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.categoryraw, parent, false);
        return new CViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CViewHolder holder, int position) {
        CategoryModel categoryModel=categoryModels.get(position);
        holder.catname.setText(categoryModels.get(position).getCat_name());
        holder.catdesc.setText(categoryModels.get(position).getCat_desc());
        Glide.with(holder.catimg.getContext()).load(ConstantsData.SERVER_ADDRESS_IMG+categoryModels
                .get(position).getCat_pic()).into(holder.catimg);
//

        holder.catimg.setOnClickListener(view -> {
            ArrayList<ProductModel> p=new ArrayList<>();
            for (int i=0;i<productModels.size();i++){
                if(categoryModel.getCat_name().equals(productModels.get(i).getCategory())){
                    p.add(productModels.get(i));
                }

            }

            Intent intent=new Intent(holder.catimg.getContext(), ProductActivity.class);
            intent.putExtra("product",p);
            holder.catimg.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }

    public class CViewHolder extends RecyclerView.ViewHolder{
        ImageView catimg;
        TextView catname,catdesc;
        public CViewHolder(@NonNull View itemView) {
            super(itemView);
            catimg=itemView.findViewById(R.id.catimg);
            catname=itemView.findViewById(R.id.catname);
            catdesc=itemView.findViewById(R.id.catdesc);
        }
    }
}

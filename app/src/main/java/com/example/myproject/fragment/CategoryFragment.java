package com.example.myproject.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.R;
import com.example.myproject.adapter.CategoryAdapter;
import com.example.myproject.model.CategoryModel;
import com.example.myproject.model.ProductModel;

import java.util.ArrayList;

public class CategoryFragment extends Fragment {

     ImageView catimg;
     TextView catname, catdesc;
    View view;
    ArrayList<CategoryModel> category;
    RecyclerView rcylCategories;
    ArrayList<ProductModel> productModels;

    public CategoryFragment(ArrayList<CategoryModel> category, ArrayList<ProductModel> productModels) {
        this.category = category;
        this.productModels=productModels;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_category, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rcylCategories = view.findViewById(R.id.rcylCategories);

        rcylCategories.setLayoutManager(new GridLayoutManager(getContext(),2));
        rcylCategories.setAdapter(new CategoryAdapter(category,productModels));

//        catimg = view.findViewById(R.id.catimg);
//        catname = view.findViewById(R.id.catname);
//        catdesc = view.findViewById(R.id.catdesc);
//
//        if (getArguments() != null) {
//            String name = getArguments().getString("cat_name");
//            String desc = getArguments().getString("cat_desc");
//            String pic = getArguments().getString("cat_pic");
//
//            catname.setText(name);
//            catdesc.setText(desc);
//
//        }
    }
}

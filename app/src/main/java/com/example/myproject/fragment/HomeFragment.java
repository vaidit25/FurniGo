package com.example.myproject.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.myproject.R;
import com.example.myproject.adapter.CategoryAdapter;
import com.example.myproject.adapter.ProductAdapter;
import com.example.myproject.adapter.SearchAdapter;
import com.example.myproject.api.Data_Api;
import com.example.myproject.model.BannerModel;
import com.example.myproject.model.CategoryModel;
import com.example.myproject.model.CouponModel;
import com.example.myproject.model.ProductModel;
import com.example.myproject.utils.ConstantsData;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    View view;

    ImageSlider image_slider;
    ImageView profile;
    RecyclerView rcylCat,rcylCat2;
    AutoCompleteTextView search;
    public ArrayList<BannerModel> banner;
    public ArrayList<CategoryModel> category;
    public ArrayList<ProductModel> product;

    public HomeFragment(ArrayList<BannerModel> banner_data, ArrayList<CategoryModel> category_data, ArrayList<ProductModel> product_data) {
        this.banner = banner_data;
        this.category = category_data;
        this.product = product_data;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        image_slider = view.findViewById(R.id.image_slider);
        profile=view.findViewById(R.id.profile);
        rcylCat = view.findViewById(R.id.rcylCat);
        rcylCat.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rcylCat2 = view.findViewById(R.id.rcylCat2);
        rcylCat2.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        search=view.findViewById(R.id.search);
        setBanner();

        SearchAdapter searchAdapter=new SearchAdapter(getContext(),product);
        search.setThreshold(1);
        search.setAdapter(searchAdapter);


    }

    public void setBanner() {
        ArrayList<SlideModel> imgList = new ArrayList<>();

        for (int i = 0;i< banner.size();i++) {
            imgList.add(
              new SlideModel(
                      ConstantsData.SERVER_ADDRESS_IMG + banner.get(i).getImg(),
                      "",
                      ScaleTypes.FIT
              )
            );
            //Toast.makeText(getContext(),ConstantsData.SERVER_ADDRESS_IMG + banner.get(i).getImg() , Toast.LENGTH_SHORT).show();
        }

        image_slider.setImageList(imgList);

        CategoryAdapter adapter=new CategoryAdapter(category,product);
        rcylCat.setAdapter(adapter);

        ProductAdapter adapter1=new ProductAdapter(product);
        rcylCat2.setAdapter(adapter1);
    }
}
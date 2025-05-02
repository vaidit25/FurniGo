package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myproject.api.Data_Api;
import com.example.myproject.fragment.CartFragment;
import com.example.myproject.fragment.CategoryFragment;
import com.example.myproject.fragment.CuponFragment;
import com.example.myproject.fragment.HomeFragment;
import com.example.myproject.fragment.ProfileFragment;
import com.example.myproject.fragment.StoreFragment;
import com.example.myproject.model.BannerModel;
import com.example.myproject.model.CategoryModel;
import com.example.myproject.model.CouponModel;
import com.example.myproject.model.DataOutputModel;
import com.example.myproject.model.ProductModel;
import com.example.myproject.model.StoreModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

public class Home_Activity extends AppCompatActivity {
    SmoothBottomBar bottomNavigation;
    ImageView profile;
    public ArrayList<BannerModel> banner;
    public ArrayList<CategoryModel> category;
    public ArrayList<CouponModel> coupon;
    public ArrayList<ProductModel> product;
    public ArrayList<StoreModel> store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigation=findViewById(R.id.bottomNavigation);
        new Data_Api().getData(this);

        profile=findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(new ProfileFragment());
            }
        });


        bottomNavigation.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public boolean onItemSelect(int i) {
                if(i==0){
                    openFragment(new HomeFragment(banner,category,product));

                } else if (i==1) {
                    openFragment(new CategoryFragment(category,product));

                }

                else if (i==2) {
                    openFragment(new CuponFragment(coupon));
                }

                else if (i==3) {
                    Intent intent=new Intent(Home_Activity.this,CartActivity.class);
                    startActivity(intent);
                }
                else if (i==4) {
                    openFragment(new StoreFragment(store));
                }
                else {

                }
                return true;
            }
        });

    }

    public  void openFragment(Fragment fragment)
    {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.frameLayout,fragment,null);
        ft.commit();
    }



    public void replaceFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction  transaction = manager.beginTransaction();
        transaction.replace(R.id.frameLayout,fragment);
        transaction.commit();
    }

    public void getData(DataOutputModel data) {
        banner = data.getBanner_data();
        category = data.getCategory_data();
        coupon = data.getCoupon_data();
        product = data.getProduct_data();
        store=data.getStore_data();

        replaceFragment(new HomeFragment(banner,category,product));

    }
}
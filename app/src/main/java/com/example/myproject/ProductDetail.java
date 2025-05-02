package com.example.myproject;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.myproject.api.OrderApi;
import com.example.myproject.model.OrderModel;
import com.example.myproject.model.ProductModel;
import com.example.myproject.utils.ConstantsData;

import java.util.ArrayList;

public class ProductDetail extends AppCompatActivity {
    ImageSlider imgProductUser;
TextView tvProductUserPname,tvprice,pdisc,tvdesc;
Button btnAddtoCart;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvProductUserPname=findViewById(R.id.tvProductUserPname);
        tvprice=findViewById(R.id.tvprice);

        tvdesc=findViewById(R.id.tvdesc);
        btnAddtoCart=findViewById(R.id.btnAddtoCart);
        imgProductUser=findViewById(R.id.imgProductUser);

        ProductModel model= (ProductModel) getIntent().getSerializableExtra("model");

        tvProductUserPname.setText(model.getProductname());
        tvprice.setText(model.getPrice());
        tvdesc.setText(model.getDesc());
        ArrayList<SlideModel> slideModels=new ArrayList<>();
        slideModels.add(new SlideModel(ConstantsData.SERVER_ADDRESS+model.getPic1(), ScaleTypes.FIT));
        slideModels.add(new SlideModel(ConstantsData.SERVER_ADDRESS+model.getPic2(), ScaleTypes.FIT));
        slideModels.add(new SlideModel(ConstantsData.SERVER_ADDRESS+model.getPic3(), ScaleTypes.FIT));

        SharedPreferences sp=getSharedPreferences(ConstantsData.SP_NAME,MODE_PRIVATE);
        String uid=sp.getString(ConstantsData.SP_USERID,"0");

        imgProductUser.setImageList(slideModels);
        btnAddtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(uid.equals("0")){
                    Toast.makeText(ProductDetail.this, "Please login to countiu", Toast.LENGTH_SHORT).show();
                }else{
                    OrderModel orderModel=new OrderModel("",model.get_id(),uid,"model.getProductname()","model.getPic1()",1,0,0,0,"","",0,"cash","welcom10","aa");
                    new OrderApi().addOrder(ProductDetail.this,orderModel);

                }
               }
        });

    }
}
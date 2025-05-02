package com.example.myproject;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.adapter.MyOrderAdapter;
import com.example.myproject.api.OrderApi;
import com.example.myproject.model.OrderModel;
import com.example.myproject.model.OrderOutputModel;
import com.example.myproject.utils.ConstantsData;

import java.util.ArrayList;

public class OrderHistoryActivity extends AppCompatActivity {

    RecyclerView orderhistoryrcyl;
    ArrayList<OrderModel> orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_history);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        orderhistoryrcyl=findViewById(R.id.orderhistoryrcyl);

        SharedPreferences sp = getSharedPreferences(ConstantsData.SP_NAME,MODE_PRIVATE);
        String uid = sp.getString(ConstantsData.SP_USERID,"0");

        new OrderApi().order_history(this,uid,"0");
    }

    public void set(OrderOutputModel p) {

        orders=p.getOrder();
        orderhistoryrcyl.setLayoutManager(new LinearLayoutManager(this));
        orderhistoryrcyl.setAdapter(new MyOrderAdapter(orders,this));

    }
}
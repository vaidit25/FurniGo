package com.example.myproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myproject.utils.ConstantsData;
import com.example.myproject.utils.NavigationActivity;

public class Splash_Screen extends AppCompatActivity {

    Handler h = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


         h.postDelayed(new Runnable() {
             @Override
             public void run() {
                 SharedPreferences sp = getSharedPreferences(ConstantsData.SP_NAME, Context.MODE_PRIVATE);
                 boolean isLogin = sp.getBoolean(ConstantsData.SP_IS_LOGIN,false);

                 if (isLogin) {
                     Intent i= new Intent(Splash_Screen.this, Home_Activity.class);
                     startActivity(i);
                     finish();
                 } else {
                     Intent i= new Intent(Splash_Screen.this, NavigationActivity.class);
                     startActivity(i);
                     finish();
                 }

             }
         },3000);


    }
}
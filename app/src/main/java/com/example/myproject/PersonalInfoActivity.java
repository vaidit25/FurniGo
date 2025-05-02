package com.example.myproject;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myproject.utils.ConstantsData;

public class PersonalInfoActivity extends AppCompatActivity {

    TextView tvMobile,tvEmail,tvUserName;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_personal_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvUserName = findViewById(R.id.tvUserName);
        tvMobile = findViewById(R.id.tvMobile);
        tvEmail = findViewById(R.id.tvEmail);

        SharedPreferences sp = getSharedPreferences(ConstantsData.SP_NAME,MODE_PRIVATE);

        tvEmail.setText(sp.getString(ConstantsData.SP_EMAIL,"guest@gmail.com"));
        tvMobile.setText("+91 - " +sp.getString(ConstantsData.SP_MOBNO,"123456789"));
        tvUserName.setText(sp.getString(ConstantsData.SP_USERNAME,"GUEST"));
    }
}
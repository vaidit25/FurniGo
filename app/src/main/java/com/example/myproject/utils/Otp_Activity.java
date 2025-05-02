package com.example.myproject.utils;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myproject.Home_Activity;
import com.example.myproject.R;
import com.example.myproject.api.Login_Register_Api;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Otp_Activity extends AppCompatActivity {

    EditText otp1, otp2, otp3, otp4;
    Button btn_verified;
    String RegMobileno,RegUsername, RegPassword, RegEmail, VerificationId;
    TextView desp_phone;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_otp);

        desp_phone = findViewById(R.id.phone_number);



        RegUsername = getIntent().getStringExtra("username");
        RegEmail = getIntent().getStringExtra("email");
        RegMobileno = getIntent().getStringExtra("mobileno");
        RegPassword = getIntent().getStringExtra("password");
        VerificationId = getIntent().getStringExtra("verificationId");

        //Enter the code sent On
        desp_phone.setText(RegMobileno);

        otp1 = findViewById(R.id.et_otp1);
        otp2 = findViewById(R.id.et_otp2);
        otp3 = findViewById(R.id.et_otp3);
        otp4 = findViewById(R.id.et_otp4);
        setOtpBox();

        btn_verified = findViewById(R.id.btn_verifild);
        btn_verified.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOtpValid()){
                    String otp = getOtp();

                    verifyOTP(otp);

                }
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void setOtpBox() {
        otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 1)otp2.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 1)otp3.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0)otp1.requestFocus();
            }
        });
        otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 1)otp4.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0)otp2.requestFocus();
            }
        });
        otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 1 )btn_verified.callOnClick();
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0 )otp3.requestFocus();
            }
        });
    }

    private boolean isOtpValid(){
        return !(otp1.getText().toString().trim().isEmpty()||
                otp2.getText().toString().trim().isEmpty()||
                otp3.getText().toString().trim().isEmpty()||
                otp4.getText().toString().trim().isEmpty());
    }
    private String getOtp(){
        return otp1.getText().toString() +
                otp2.getText().toString() +
                otp3.getText().toString() +
                otp4.getText().toString();
    }
    public void verifyOTP(String code) {
        if (VerificationId == null || VerificationId.isEmpty()) {
            Toast.makeText(this, "Verification ID missing!", Toast.LENGTH_SHORT).show();
            return;
        }

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            OkHttpClient client = new OkHttpClient.Builder().build();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, "");

            Request request = new Request.Builder()
                    .url("https://cpaas.messagecentral.com/verification/v3/validateOtp?countryCode=91&mobileNumber=" +
                            RegMobileno + "&verificationId=" + VerificationId + "&customerId="+ ConstantsData.CUSTOMER_ID+"&code=" + code)
                    .method("GET", null)  // GET requests shouldn't have a body
                    .addHeader("authToken", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJDLUQ4MERFODlCODE3RTRGNyIsImlhdCI6MTc0MjgwOTEwMSwiZXhwIjoxOTAwNDg5MTAxfQ.qHn2sx71Wi4wA6wEmfx6ii1g54s5ejk95Cl_V5vC7wR_HpqD9RHHnu8rjPXAhpvkwaX2fjrBK0MbzJHkqcyTiA")
                    .build();

            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    runOnUiThread(() -> {
                        new Login_Register_Api().register_user(Otp_Activity.this,RegUsername,RegEmail,RegMobileno,RegPassword);
                    });
                } else {
                    runOnUiThread(() -> Toast.makeText(Otp_Activity.this, "Invalid OTP. Try again!", Toast.LENGTH_SHORT).show());
                }
            } catch (IOException e) {
                Log.e("ERROR", e.getLocalizedMessage());
                runOnUiThread(() -> Toast.makeText(Otp_Activity.this, "Network error! Try again.", Toast.LENGTH_SHORT).show());
            }
        });
    }
}
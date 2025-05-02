package com.example.myproject.utils;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Login_Signup extends AppCompatActivity {

    TextView tvsignup, tvlogin, forgot, welcome, smart;
    EditText etemail, etpassword;
    EditText username, mobileno, email, password, confirmPassword;
    Button btnlogin, btnsignup;
    //login String
    String loginemail, loginpassword;
    //Register String
    String RegUsername, RegPassword, RegMobileno, RegEmail ;
    LinearLayout loginLayout, signupLayout ;
    SharedPreferences sp;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_signup);

        //login
        etemail = findViewById(R.id.etemail);
        etpassword = findViewById(R.id.etpassword);
        btnlogin = findViewById(R.id.btnlogin);
        forgot = findViewById(R.id.forgotP);
        tvsignup = findViewById(R.id.tvsignup);
        welcome = findViewById(R.id.welcome);
        smart = findViewById(R.id.smart);


        //Layout
        loginLayout = findViewById(R.id.loginLayout);
        signupLayout = findViewById(R.id.signupLayout);


        //signup
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        mobileno = findViewById(R.id.mobileno);
        password = findViewById(R.id.password);
        tvlogin = findViewById(R.id.tvlogin);
        btnsignup = findViewById(R.id.btnsignup);

        //forgot click event
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Signup.this, Forgot_phone.class);
                startActivity(intent);
                finish();
            }
        });

        // tvsignup click
        tvsignup.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v) {
                signupLayout.setVisibility(View.VISIBLE);
                loginLayout.setVisibility(View.GONE);
                welcome.setVisibility(View.GONE);
//                smart.setVisibility(View.GONE);
//            tvlogin.setTextColor(getResources().getColor(R.color.blueColor))
            }
        });

        // tvlogin click
        tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupLayout.setVisibility(View.GONE);
                loginLayout.setVisibility(View.VISIBLE);
                welcome.setVisibility(View.VISIBLE);
//                smart.setVisibility(View.VISIBLE);
            }
        });

        //btnlogin click event
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uname = etemail.getText().toString().trim();
                String pass = etpassword.getText().toString().trim();

                if(uname.isEmpty()){
                    Toast.makeText(Login_Signup.this,"Please Enter Email-Id!",Toast.LENGTH_SHORT).show();
                }
//                else if(!Patterns.EMAIL_ADDRESS.matcher(uname).matches()){
//                    Toast.makeText(Login_Signup.this,"Enter valid Email-Id!",Toast.LENGTH_SHORT).show();
//                }
                else if(pass.isEmpty()){
                    Toast.makeText(Login_Signup.this,"Enter password",Toast.LENGTH_SHORT).show();
                } else if(!(pass.length()>=6)){
                    Toast.makeText(Login_Signup.this,"Password must be at least 6 digit.",Toast.LENGTH_SHORT).show();
                } else {
                    new Login_Register_Api().login_user(Login_Signup.this, uname, pass);
                }
            }
        });

        //btnsignup click event
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegUsername = username.getText().toString();
                RegPassword = password.getText().toString();
                RegMobileno = mobileno.getText().toString();
                RegEmail = email.getText().toString();


                if(RegUsername.isEmpty()){
                    Toast.makeText(Login_Signup.this,"Please Enter Username",Toast.LENGTH_SHORT).show();
                } else if(RegEmail.isEmpty()){
                    Toast.makeText(Login_Signup.this,"please Enter Email Id.",Toast.LENGTH_SHORT).show();
                } else if(!Patterns.EMAIL_ADDRESS.matcher(RegEmail
                ).matches()){
                    Toast.makeText(Login_Signup.this,"Enter valid email",Toast.LENGTH_SHORT).show();
                } else if(RegMobileno.isEmpty()){
                    Toast.makeText(Login_Signup.this,"please Enter phone no.",Toast.LENGTH_SHORT).show();
                } else if((RegMobileno.length()!=10)){
                    Toast.makeText(Login_Signup.this,"phone number must be 10digit.",Toast.LENGTH_SHORT).show();
                } else if(RegPassword.isEmpty()){
                    Toast.makeText(Login_Signup.this,"Enter password ",Toast.LENGTH_SHORT).show();
                } else if(!(RegPassword.length()>=6)){
                    Toast.makeText(Login_Signup.this,"password must be at least 6 digit.",Toast.LENGTH_SHORT).show();
                } else {
                    generateOTP(RegMobileno);
                }

            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    // Login Validation_____________________________________________
    public boolean Validation_login(){
        loginemail = etemail.getText().toString();
        loginpassword = etpassword.getText().toString();




        return true;
    }

    // Signup Validation _____________________________________________
    public boolean Validation_signup(){
        String s_uname = username.getText().toString();
        String s_pass = password.getText().toString();
        String s_ph = mobileno.getText().toString();
        String s_em = email.getText().toString();


        if(s_uname.isEmpty()){
//            username.setError("Please enter username!");
            Toast.makeText(Login_Signup.this,"Please Enter Username",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(s_em.isEmpty()){
//            password.setError("Please enter username!");
            Toast.makeText(Login_Signup.this,"please Enter Email Id.",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(s_em).matches()){
            Toast.makeText(Login_Signup.this,"Enter valid email",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(s_ph.isEmpty()){
//            password.setError("Please enter username!");
            Toast.makeText(Login_Signup.this,"please Enter phone no.",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!(s_ph.length()>=10)){
//            password.setError("password must be at least 10 digit.");
            Toast.makeText(Login_Signup.this,"phone number must be 10digit.",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(s_pass.isEmpty()){
//            password.setError("Please enter username!");
            Toast.makeText(Login_Signup.this,"Enter password ",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!(s_pass.length()>=6)){
//            password.setError("password must be at least 6 digit.");
            Toast.makeText(Login_Signup.this,"password must be at least 6 digit.",Toast.LENGTH_SHORT).show();
            return false;

        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    public void generateOTP(String mobileno) {
        ExecutorService executor = Executors.newSingleThreadExecutor(); // Run in background thread
        executor.execute(() -> {
            OkHttpClient client = new OkHttpClient.Builder().build();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, "");

            Request request = new Request.Builder()
                    .url("https://cpaas.messagecentral.com/verification/v3/send?countryCode=91&customerId=C-252FD890E9F9453&flowType=SMS&mobileNumber=" + mobileno)
                    .method("POST", body)
                    .addHeader("authToken", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJDLUQ4MERFODlCODE3RTRGNyIsImlhdCI6MTc0MjgwOTEwMSwiZXhwIjoxOTAwNDg5MTAxfQ.qHn2sx71Wi4wA6wEmfx6ii1g54s5ejk95Cl_V5vC7wR_HpqD9RHHnu8rjPXAhpvkwaX2fjrBK0MbzJHkqcyTiA")
                    .build();

            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful() && response.body() != null) {
                    String responseBody = response.body().string();

                    JSONObject jsonResponse = new JSONObject(responseBody);

                    // Extract verificationId from the response
                    JSONObject data = jsonResponse.getJSONObject("data");
                    String verificationId = data.getString("verificationId");

                    // Send data to OTPActivity
                    runOnUiThread(() -> {
                        Intent intent = new Intent(Login_Signup.this, Otp_Activity.class);

                        intent.putExtra("mobileno", RegMobileno);
                        intent.putExtra("username", RegUsername);
                        intent.putExtra("password", RegPassword);
                        intent.putExtra("email", RegEmail);
                        intent.putExtra("verificationId", verificationId); // Pass verificationId
                        startActivity(intent);
                    });
                } else {
                    runOnUiThread(() -> Toast.makeText(Login_Signup.this, "OTP request failed", Toast.LENGTH_SHORT).show());
                }
            } catch (IOException | JSONException e) {
                Log.e("ERROR", e.getLocalizedMessage());
                runOnUiThread(() -> Toast.makeText(Login_Signup.this, "Network error! Try again.", Toast.LENGTH_SHORT).show());
            }
        });
    }
}
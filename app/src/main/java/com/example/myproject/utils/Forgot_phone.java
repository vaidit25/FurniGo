package com.example.myproject.utils;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myproject.R;

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

public class Forgot_phone extends AppCompatActivity {

    EditText et_mobile_number;
    Button btn_proceed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forgot_phone);
            et_mobile_number = findViewById(R.id.et_mobile_number);
            btn_proceed = findViewById(R.id.btn_proceed);

            btn_proceed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String mobno = et_mobile_number.getText().toString();
                    if (TextUtils.isEmpty(mobno)){
                        Toast.makeText(Forgot_phone.this, "Enter Mobile no!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        generateOTP(mobno);
                    }
                }
            });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void generateOTP(String mobileno) {
        ExecutorService executor = Executors.newSingleThreadExecutor(); // Run in background thread
        executor.execute(() -> {
            OkHttpClient client = new OkHttpClient.Builder().build();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, "");

            Request request = new Request.Builder()
                    .url("https://cpaas.messagecentral.com/verification/v3/send?countryCode=91&customerId=C-6C10D5C021D94B8&flowType=SMS&mobileNumber=" + mobileno)
                    .method("POST", body)
                    .addHeader("authToken", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJDLTZDMTBENUMwMjFEOTRCOCIsImlhdCI6MTczODM5NDA0MywiZXhwIjoxODk2MDc0MDQzfQ.eFntIoOReL64pan14g8tAXlrg9hymV_ebWRc5lBTGlg69mmI0B2LwcI4cyXjFXwZMH2YDaTQ--IQoCG3TWRZvQ")
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
                        Intent intent = new Intent(Forgot_phone.this, Otp_Activity.class);
                        intent.putExtra("mobileno", mobileno);
//                        intent.putExtra("username", username);
//                        intent.putExtra("password", password);
//                        intent.putExtra("email", email);
                        intent.putExtra("VerificationId", verificationId); // Pass verificationId
                        startActivity(intent);
                    });
                } else {
                    runOnUiThread(() -> Toast.makeText(Forgot_phone.this, "OTP request failed", Toast.LENGTH_SHORT).show());
                }
            } catch (IOException | JSONException e) {
                Log.e("ERROR", e.getLocalizedMessage());
                runOnUiThread(() -> Toast.makeText(Forgot_phone.this, "Network error! Try again.", Toast.LENGTH_SHORT).show());
            }
        });
    }
}
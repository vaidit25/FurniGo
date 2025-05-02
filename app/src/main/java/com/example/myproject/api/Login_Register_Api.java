package com.example.myproject.api;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myproject.Home_Activity;
import com.example.myproject.utils.Login_Signup;
import com.example.myproject.model.PersonOutputModel;
import com.example.myproject.utils.ConstantsData;
import com.google.gson.Gson;

import java.util.Hashtable;
import java.util.Map;

public class Login_Register_Api {
    public void register_user(Context context, String username, String email, String mobileno, String password ){
        String URL = ConstantsData.SERVER_ADDRESS + ConstantsData.REGISTER_METHOD;
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                PersonOutputModel p = new Gson().fromJson(response,PersonOutputModel.class);
                if (p.isStatus()){
                    Toast.makeText(context, p.getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, Login_Signup.class);
                    context.startActivity(intent);
                }else {
                    Toast.makeText(context, p.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError Volleyerror) {
                Toast.makeText(context, "Error  " +Volleyerror.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Hashtable<String, String> map = new Hashtable<>();
                map.put("username", username);
                map.put("email", email);
                map.put("password", password);
                map.put("mobileno", mobileno);
                return map;

            }
        };
        requestQueue.add(stringRequest);

    }

    public void login_user(Context context, String email, String password ){
        String URL =  ConstantsData.LOGIN_METHOD;
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                PersonOutputModel p = new Gson().fromJson(response,PersonOutputModel.class);
                if (p.isStatus()){
                    SharedPreferences sp=context.getSharedPreferences(ConstantsData.SP_NAME,Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed= sp.edit();
                    ed.putString(ConstantsData.SP_USERID,p.getPerson().getId());
                    ed.putString(ConstantsData.SP_EMAIL,p.getPerson().getEmail());
                    ed.putString(ConstantsData.SP_MOBNO,p.getPerson().getMobileno());
                    ed.putString(ConstantsData.SP_USERNAME,p.getPerson().getUsername());
                    ed.putBoolean(ConstantsData.SP_IS_LOGIN,true);
                    ed.commit();
                    Toast.makeText(context, p.getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, Home_Activity.class);
                    context.startActivity(intent);
                }else {
                    Toast.makeText(context, p.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("LOGIN ERROR", volleyError.toString());
                Toast.makeText(context, "Error  " +volleyError, Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Hashtable<String, String> map = new Hashtable<>();

                map.put("email", email);
                map.put("password", password);

                return map;

            }
        };
        requestQueue.add(stringRequest);

    }
}

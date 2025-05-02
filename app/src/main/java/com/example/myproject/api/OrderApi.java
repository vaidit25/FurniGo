package com.example.myproject.api;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myproject.CartActivity;
import com.example.myproject.Home_Activity;
import com.example.myproject.OrderHistoryActivity;
import com.example.myproject.ThankYouActivity;
import com.example.myproject.model.CouponOutputModel;
import com.example.myproject.model.OrderModel;
import com.example.myproject.model.OrderOutputModel;
import com.example.myproject.model.PersonOutputModel;
import com.example.myproject.utils.ConstantsData;
import com.example.myproject.utils.CustomProgressDialog;
import com.example.myproject.model.OrderModel;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class OrderApi {

    public void addOrder(Context context, OrderModel model) {

        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(
                Request.Method.POST,
                ConstantsData.ADD_ORDER_METHOD,
                response -> {
                    OrderOutputModel o = new Gson().fromJson(response, OrderOutputModel.class);
                    if (o.isStatus()) {
                        Toast.makeText(context, "Item Added To Cart", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    Log.e("SERVER ERROR", error.toString());
                    Toast.makeText(context, "SERVER ERROR: " + error, Toast.LENGTH_SHORT).show();
                }
        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("uid",model.getUid());
                map.put("pid",model.getPid());
                map.put("date","date");
                map.put("time","time");
                map.put("qty","1");
                map.put("c_o",model.getC_o());
                map.put("c_code",model.getC_code());
                map.put("address",model.getAddress());
                return map;
            }
        };
        queue.add(request);

    }

    public void getOrder(Context context, String uid,String status) {

        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(
                Request.Method.POST,
                ConstantsData.GET_ORDER_METHOD,
                response -> {
                    OrderOutputModel o = new Gson().fromJson(response, OrderOutputModel.class);
                    if (o.isStatus()) {
                        Toast.makeText(context, "Item Getting", Toast.LENGTH_SHORT).show();
                        ((CartActivity)context).setCart(o);
                    }
                },
                error -> {
                    Log.e("SERVER ERROR", error.toString());
                    Toast.makeText(context, "SERVER ERROR: " + error, Toast.LENGTH_SHORT).show();
                }
        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("uid",uid);
                map.put("status",status);
                return map;
            }
        };
        queue.add(request);
    }

    public void removeOrder(Context context, String id) {

        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(
                Request.Method.POST,
                ConstantsData.DELETE_ORDER_METHOD,
                response -> {
                    OrderOutputModel o = new Gson().fromJson(response, OrderOutputModel.class);
                    if (o.isStatus()) {
                        Toast.makeText(context, "Item Getting", Toast.LENGTH_SHORT).show();
                        ((CartActivity)context).setCart(o);
                    }
                },
                error -> {
                    Log.e("SERVER ERROR", error.toString());
                    Toast.makeText(context, "SERVER ERROR: " + error, Toast.LENGTH_SHORT).show();
                }
        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("id",id);
                return map;
            }
        };
        queue.add(request);
    }


    public void updateOrder(Context context,String qty, String id,String uid) {

        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(
                Request.Method.POST,
                ConstantsData.UPDATE_QTY_METHOD,
                response -> {
                    OrderOutputModel o = new Gson().fromJson(response, OrderOutputModel.class);
                    if (o.isStatus()) {
                        ((CartActivity)context).setCart(o);
                    }
                },
                error -> {
                    Log.e("SERVER ERROR", error.toString());
                    Toast.makeText(context, "SERVER ERROR: " + error, Toast.LENGTH_SHORT).show();
                }
        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("id",id);
                map.put("uid",uid);
                map.put("qty",qty);
                return map;
            }
        };
        queue.add(request);
    }




    public void confirmOrder(Context context, OrderModel model) {

        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(
                Request.Method.POST,
                ConstantsData.CONFIRM,
                response -> {
                    OrderOutputModel o = new Gson().fromJson(response, OrderOutputModel.class);
                    if (o.isStatus()) {
                        Intent intent=new Intent(context, ThankYouActivity.class);
                        context.startActivity(intent);
                    }
                },
                error -> {
                    Log.e("SERVER ERROR", error.toString());
                    Toast.makeText(context, "SERVER ERROR: " + error, Toast.LENGTH_SHORT).show();
                }
        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("uid",model.getUid());
                map.put("c_o",model.getC_o());
                map.put("c_code",model.getC_code());
                map.put("c_discount",model.getC_discount()+"");
                map.put("address",model.getAddress());
                return map;
            }
        };
        queue.add(request);

    }

    public void applyCoupon(Context context,String code) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(
                Request.Method.POST,
                ConstantsData.APPLY_COUPON_METHOD,
                response -> {
                    CouponOutputModel c = new Gson().fromJson(response, CouponOutputModel.class);
                    if (c.isStatus()) {
                        ((CartActivity)context).setCoupon(c);
                    } else {
                        Toast.makeText(context, "Invalid Coupon Code", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    Toast.makeText(context, "SERVER ERROR: " + error, Toast.LENGTH_SHORT).show();
                    Log.e("APPLY COUPON ERROR",error.toString());
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String > map = new HashMap<>();
                map.put("code",code);
                return map;
            }
        };
        queue.add(request);
    }

    public void order_history(Context context, String uid,String status){

        CustomProgressDialog customProgressDialog=new CustomProgressDialog(context);
        customProgressDialog.show();


        RequestQueue requestQueue= Volley.newRequestQueue(context);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, ConstantsData.ORDER_HISTORY_METHOD, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                customProgressDialog.dismiss();
                OrderOutputModel p= new Gson().fromJson(response,OrderOutputModel.class);
                if(p.isStatus()){
                    //if(context instanceof CartActivity){
                        Toast.makeText(context, p.getMessage(), Toast.LENGTH_SHORT).show();
                        ((OrderHistoryActivity)context).set(p);

                    //}

                }else{
                    Toast.makeText(context, p.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                customProgressDialog.dismiss();
                Toast.makeText(context, "error:"+volleyError.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map=new HashMap<>();
                map.put("uid",uid);
                map.put("status",status);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }



}



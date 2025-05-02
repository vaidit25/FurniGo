package com.example.myproject.api;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.PixelCopy;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myproject.Home_Activity;
import com.example.myproject.fragment.HomeFragment;
import com.example.myproject.model.DataOutputModel;
import com.example.myproject.utils.ConstantsData;
import com.google.gson.Gson;

public class Data_Api {

    public void getData(Context context) {

        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setTitle("Please Wait ....");
        dialog.setCancelable(false);
        dialog.show();

        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest request = new StringRequest(
                Request.Method.GET,
                ConstantsData.DATA_METHOD,
                response -> {
                    dialog.dismiss();
                    DataOutputModel data = new Gson().fromJson(response,DataOutputModel.class);
                    ((Home_Activity)context).getData(data);
                },
                error -> {
                    dialog.dismiss();
                    Log.e("SERVER ERROR", error.toString());
                    Toast.makeText(context, "SERVER ERROR: "+error,Toast.LENGTH_SHORT).show();
                }
        );
        queue.add(request);
    }

}

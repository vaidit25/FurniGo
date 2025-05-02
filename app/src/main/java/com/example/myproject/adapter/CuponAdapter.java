package com.example.myproject.adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myproject.R;
import com.example.myproject.model.CouponModel;
import com.example.myproject.utils.ConstantsData;

import java.util.ArrayList;
import java.util.List;



public class CuponAdapter extends RecyclerView.Adapter<CuponAdapter.CouponViewHolder> {
    ArrayList<CouponModel> couponList;

    public CuponAdapter(ArrayList<CouponModel> couponList) {
        this.couponList = couponList;
    }

    @NonNull
    @Override
    public CouponViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cuponraw, parent, false);
        return new CouponViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CouponViewHolder holder, int position) {
        CouponModel coupon = couponList.get(position);
        holder.couponcode.setText(coupon.getC_code());
        holder.coupondiscount.setText(coupon.getC_discount());
        holder.couponmaxamt.setText(coupon.getC_max_amt());

        holder.btnApply.setOnClickListener(v -> {
            // Get text from EditText
            String textToCopy = coupon.getC_code();

            ClipboardManager clipboard = ContextCompat.getSystemService(holder.btnApply.getContext(), ClipboardManager.class);

            if (clipboard != null) {
                ClipData clip = ClipData.newPlainText("Copied Text", textToCopy);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(holder.btnApply.getContext(), "Code copied to clipboard!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(holder.btnApply.getContext(), "Failed to access clipboard.", Toast.LENGTH_SHORT).show();
            }
        });

        Glide.with(holder.btnApply.getContext()).load(ConstantsData.SERVER_ADDRESS_IMG+coupon.getC_pic()).into(holder.img);


        // Assuming you have a method to load image from URL or resource
        // loadImage(holder.couponImage, coupon.getC_pic());

    }

    @Override
    public int getItemCount() {
        return couponList.size();
    }

    public static class CouponViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView  couponcode, coupondiscount, couponmaxamt;
        CardView btnApply;

        public CouponViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            couponcode = itemView.findViewById(R.id.cuponcode);
            coupondiscount = itemView.findViewById(R.id.cupondiscount);
            couponmaxamt = itemView.findViewById(R.id.cuponmaxamt);
            btnApply = itemView.findViewById(R.id.btnApply);
        }
    }
}

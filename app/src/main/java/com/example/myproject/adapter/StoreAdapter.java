package com.example.myproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myproject.R;
import com.example.myproject.model.StoreModel;
import com.example.myproject.utils.ConstantsData;

import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewHolder> {

    private Context context;
    private List<StoreModel> storeList;

    public StoreAdapter(Context context, List<StoreModel> storeList) {
        this.context = context;
        this.storeList = storeList;
    }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.raw_store, parent, false);
        return new StoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, int position) {
        StoreModel store = storeList.get(position);

        holder.storeName.setText(store.getStore_name());
        holder.storeMobile.setText("Mobile: " + store.getStore_mobileno());
        holder.storeAddress.setText("Address: " + store.getStore_address());
        holder.storePincode.setText("Pincode: " + store.getStore_pincode());

        holder.storeMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + store.getStore_mobileno()));
                context.startActivity(intent);
            }
        });

        holder.storeAddress.setOnClickListener(v -> {
            Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(store.getStore_address()));
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            context.startActivity(mapIntent);
        });



        // Load image using Glide
        Glide.with(context)
                .load(ConstantsData.SERVER_ADDRESS_IMG+store.getStore_pic())
                .placeholder(R.drawable.ic_launcher_foreground) // Placeholder image
                .into(holder.storeImage);
    }

    @Override
    public int getItemCount() {
        return storeList.size();
    }

    public static class StoreViewHolder extends RecyclerView.ViewHolder {
        ImageView storeImage;
        TextView storeName, storeMobile, storeAddress, storePincode;

        public StoreViewHolder(@NonNull View itemView) {
            super(itemView);
            storeImage = itemView.findViewById(R.id.storeImage);
            storeName = itemView.findViewById(R.id.storeName);
            storeMobile = itemView.findViewById(R.id.storeMobile);
            storeAddress = itemView.findViewById(R.id.storeAddress);
            storePincode = itemView.findViewById(R.id.storePincode);
        }
    }
}

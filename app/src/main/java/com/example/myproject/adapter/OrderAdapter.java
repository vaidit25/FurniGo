package com.example.myproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myproject.R;
import com.example.myproject.model.OrderModel;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private Context context;
    private List<OrderModel> orderList;

    public OrderAdapter(Context context, List<OrderModel> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        OrderModel order = orderList.get(position);
        holder.textViewProductName.setText(order.getPname());
        holder.textViewPrice.setText(String.valueOf(order.getAmount()));
        holder.textViewQuantity.setText(String.valueOf(order.getQty()));
        // Load image using your preferred library such as Glide or Picasso
        // Glide.with(context).load(order.getPic1()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewProductName;
        TextView textViewPrice;
        TextView textViewQuantity;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
//            imageView = itemView.findViewById(R.id.imageView);
//            textViewProductName = itemView.findViewById(R.id.textViewProductName);
//            textViewPrice = itemView.findViewById(R.id.textViewPrice);
//            textViewQuantity = itemView.findViewById(R.id.textViewQuantity);
        }
    }
}


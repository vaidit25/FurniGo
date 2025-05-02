package com.example.myproject.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myproject.R;
import com.example.myproject.model.OrderModel;
import com.example.myproject.utils.ConstantsData;
import com.example.myproject.model.OrderModel;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private List<com.example.myproject.model.OrderModel> orderList;

    public onClickListener onClickListener;

    public CartAdapter(Context context, List<com.example.myproject.model.OrderModel> orderList, onClickListener onClickListener) {
        this.context = context;
        this.orderList = orderList;
        this.onClickListener=onClickListener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.raw_order, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        OrderModel order = orderList.get(position);

        // Bind data to views
        holder.cartproductname.setText(order.getPname());
        holder.qty.setText(String.valueOf(order.getQty()));
        Glide.with(context).load(ConstantsData.SERVER_ADDRESS + order.getPic1()).into(holder.cartimg);

        // Update total price initially
        updateTotalPrice(holder, order);

        // Handle quantity increment
        holder.qty_add.setOnClickListener(v -> {
            int currentQty = order.getQty();
            currentQty++;
            order.setQty(currentQty);
            holder.qty.setText(String.valueOf(currentQty));
            updateTotalPrice(holder, order);
            notifyItemChanged(position); // Ensure RecyclerView updates correctly
            onClickListener.onPlus(order);
        });

        // Handle quantity decrement
        holder.qty_less.setOnClickListener(v -> {
            int currentQty = order.getQty();
            if (currentQty > 1) {
                currentQty--;
                order.setQty(currentQty);
                holder.qty.setText(String.valueOf(currentQty));
                updateTotalPrice(holder, order);
                notifyItemChanged(position); // Ensure RecyclerView updates correctly
                onClickListener.onMinus(order);
            }
        });

        // Handle delete button
        holder.btndeletecart.setOnClickListener(v -> {
            int positionToRemove = holder.getAdapterPosition();
            if (positionToRemove != RecyclerView.NO_POSITION) {
                OrderModel removedOrder = orderList.get(positionToRemove);
                orderList.remove(positionToRemove);
                notifyItemRemoved(positionToRemove);
                notifyItemRangeChanged(positionToRemove, orderList.size());
                onClickListener.onDelete(removedOrder);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    // Helper method to update total price
    private void updateTotalPrice(CartViewHolder holder, OrderModel order) {
        int totalAmount = order.getAmount() * order.getQty();
        holder.totamt.setText(String.valueOf(totalAmount));
    }

    // ViewHolder class
    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView cartproductname, qty, totamt;
        ImageView btndeletecart,cartimg;
        TextView qty_less, qty_add;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            cartproductname = itemView.findViewById(R.id.cartproductname);
            cartimg = itemView.findViewById(R.id.cartimg);

            qty = itemView.findViewById(R.id.qty);
            totamt = itemView.findViewById(R.id.totamt);
            btndeletecart = itemView.findViewById(R.id.btndeletecart);
            qty_less = itemView.findViewById(R.id.qty_less);
            qty_add = itemView.findViewById(R.id.qty_add);
        }
    }

    public  interface onClickListener{
            public void onPlus(OrderModel orderModel);
            public void onMinus(OrderModel orderModel);
            public void onDelete(OrderModel orderModel);
    }
}

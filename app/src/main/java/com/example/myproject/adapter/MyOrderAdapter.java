package com.example.myproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myproject.R;
import com.example.myproject.model.OrderModel;
import com.example.myproject.utils.ConstantsData;
import com.transferwise.sequencelayout.SequenceLayout;
import com.transferwise.sequencelayout.SequenceStep;

import java.util.ArrayList;
import java.util.List;


public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.CartViewHolder> {
    List<OrderModel> orderModels;
    ArrayList<String> steps;
    Context context;

    public MyOrderAdapter(List<OrderModel> orderModels , Context context) {
        this.orderModels = orderModels;
        this.context = context;
        steps=new ArrayList<>();
        steps.add("Order Placed");
        steps.add("Dispatched");
        steps.add("Shipping");
        steps.add("Delivered");

    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rawmyorder, parent, false);
        CartViewHolder cartViewHolder = new CartViewHolder(view);
        return cartViewHolder;
    }

    @NonNull


    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        OrderModel orderModel = orderModels.get(position);
        Glide.with(context).load(ConstantsData.SERVER_ADDRESS+orderModel.getPic1()).into(holder.orderShoesImage);
        Toast.makeText(context, ConstantsData.SERVER_ADDRESS+orderModel.getPic1(), Toast.LENGTH_SHORT).show();
        holder.orderShoesName.setText(orderModel.getPname());
        holder.orderShoesPrice.setText(orderModel.getAmount()+"");
        holder.orderShoesSize.setText("Quantity:"+orderModel.getQty()+"");


        holder.orderCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.tracker.setVisibility(View.VISIBLE);
            }
        });

        holder.tracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.tracker.setVisibility(View.GONE);

            }
        });
        holder.step1.setSubtitle("Your Order has Received,Order Will be Dispatch within few Hours");
        holder.step2.setSubtitle("Order dispatched from SOLEGLIDE's Warehouse");
        holder.step3.setSubtitle("Order is being Shipped");
        holder.step4.setSubtitle("Order is Out for delivery");
        holder.step5.setSubtitle("Delieverd order");





        if(orderModel.getStatus()==1){
            holder.step1.setActive(true);
        }else if(orderModel.getStatus()==2){
            holder.step2.setActive(true);

        }else if(orderModel.getStatus()==3){
            holder.step3.setActive(true);

        }else if(orderModel.getStatus()==4){
            holder.step4.setActive(true);

        }else{
            holder.step5.setActive(true);

        }



    }

    @Override
    public int getItemCount() {
        return orderModels.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView orderShoesImage;
        SequenceLayout tracker;
        CardView orderCard;
        SequenceStep step1,step2,step3,step4,step5;
        TextView orderShoesName, orderShoesPrice, orderShoesColor, orderShoesSize;


        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            orderShoesImage = itemView.findViewById(R.id.orderShoesImage);

            orderShoesName = itemView.findViewById(R.id.orderShoesName);
            orderShoesPrice = itemView.findViewById(R.id.orderShoesPrice);
            orderShoesColor = itemView.findViewById(R.id.orderShoesColor);
            orderShoesSize = itemView.findViewById(R.id.orderShoesSize);

            step1=itemView.findViewById(R.id.step1);
            step2=itemView.findViewById(R.id.step2);
            step3=itemView.findViewById(R.id.step3);
            step4=itemView.findViewById(R.id.step4);
            step5=itemView.findViewById(R.id.step5);
            tracker=itemView.findViewById(R.id.tracker);
            orderCard=itemView.findViewById(R.id.orderCard);



        }
    }

    public interface OnClickListener {
        public void onClickPlus(OrderModel orderModel);

        public void onClickMinus(OrderModel orderModel);

        public void removeOrder(OrderModel orderModel);

    }

}
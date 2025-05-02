package com.example.myproject;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.adapter.CartAdapter;
import com.example.myproject.api.OrderApi;
import com.example.myproject.model.CouponOutputModel;
import com.example.myproject.model.OrderModel;
import com.example.myproject.model.OrderOutputModel;
import com.example.myproject.utils.ConstantsData;
import com.example.myproject.utils.Login_Signup;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class CartActivity extends AppCompatActivity implements PaymentResultListener {

    RecyclerView rcylCart;

    EditText etCode;

    String c_code="qwej";
    Button applyoffer,btnCheckout;

    TextView tvChangeAddress,tvaddress,tvtotal,tvCouponOffer,tvDeliveryCharge,tvitem_total,phoneNumber;

    EditText t1,t2,t3,t4,t5,t6;
    RadioButton rbtnCOD,rbtnOnline;
    String c_o="cash";
    ImageView Back;

    String uid;

    RadioGroup rdbPayment;
    String address="";
    Button btnSaveAddress;
    LinearLayout nonempty,empty;
    String mob;
    double total=0,gst=0,amt=0,coupon=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);


        setContentView(R.layout.activity_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        SharedPreferences sp=getSharedPreferences(ConstantsData.SP_NAME,MODE_PRIVATE);
        uid=sp.getString(ConstantsData.SP_USERID,"");
        mob=sp.getString(ConstantsData.SP_MOBNO,"");
        new OrderApi().getOrder(this,uid,"0");
        rcylCart=findViewById(R.id.rcylCart);



        nonempty=findViewById(R.id.nonempty);
        phoneNumber=findViewById(R.id.phoneNumber);
        empty=findViewById(R.id.empty);
        etCode=findViewById(R.id.etCode);
        rdbPayment=findViewById(R.id.rdbPayment);
        applyoffer=findViewById(R.id.applyoffer);
        tvChangeAddress=findViewById(R.id.tvChangeAddress);
        tvaddress=findViewById(R.id.address);
        btnCheckout=findViewById(R.id.btnCheckout);
        Back=findViewById(R.id.Back);
        rbtnCOD=findViewById(R.id.rbtnCOD);
        rbtnOnline=findViewById(R.id.rbtnOnline);
        tvtotal=findViewById(R.id.tvtotal);
        tvCouponOffer=findViewById(R.id.tvCouponOffer);
        tvDeliveryCharge=findViewById(R.id.tvDeliveryCharge);
        tvitem_total=findViewById(R.id.tvitem_total);


        phoneNumber.setText(mob);

        Back.setOnClickListener(v->{
            finish();
        });

        final BottomSheetDialog bottomSheetTeachersDialog = new BottomSheetDialog(CartActivity.this);

        View layout = LayoutInflater.from(CartActivity.this).inflate(R.layout.address,null);

        // passing our layout file to our bottom sheet dialog.
        bottomSheetTeachersDialog.setContentView(layout);
        t1=layout.findViewById(R.id.etFullName);
        t2=layout.findViewById(R.id.etAddress);
        t3=layout.findViewById(R.id.etCity);
        t4=layout.findViewById(R.id.etState);
        t5=layout.findViewById(R.id.etZipCode);
        btnSaveAddress=layout.findViewById(R.id.btnSaveAddress);

        // below line is to set our bottom sheet dialog as cancelable.
        bottomSheetTeachersDialog.setCancelable(false);

        bottomSheetTeachersDialog.setCanceledOnTouchOutside(true);

        btnSaveAddress.setOnClickListener(v -> {
            address=t1.getText().toString()+"," + t2.getText().toString()+"," +t3.getText().toString()
                    +t4.getText().toString()+t5.getText().toString();
            tvaddress.setText(address);
            bottomSheetTeachersDialog.dismiss();

        });

        tvChangeAddress.setOnClickListener(v->{

            bottomSheetTeachersDialog.show();

        });

        applyoffer.setOnClickListener(v -> {
            String code = etCode.getText().toString().trim();
            if (code == null && code.isEmpty()) {
                Toast.makeText(this, "Enter Coupon Code", Toast.LENGTH_SHORT).show();
            } else {
                new OrderApi().applyCoupon(this,code);
            }
        });


        if(uid.equals("0")){
            Intent intent=new Intent(CartActivity.this, Login_Signup.class);
            startActivity(intent);
        }else{
            new OrderApi().getOrder(this,uid,"0");
        }



        btnCheckout.setOnClickListener(view -> {
            if(address.equals("")){
                Toast.makeText(this, "Please add Delivery Address", Toast.LENGTH_SHORT).show();
            }else{
                if(rdbPayment.getCheckedRadioButtonId()==R.id.rbtnCOD) {
                    c_o="cash";
                }else{
                    c_o="online";
                }

                if(c_o.equals("cash")){
                    OrderModel model=new OrderModel("","",uid,"","",1,0,0,
                            0,"","",1,c_o,c_code,address);
                    new OrderApi().confirmOrder(CartActivity.this,model);

                }else{
                    openRazorPay();
                }

            }


        });


//


    }

    public void openRazorPay(){
        Checkout checkout = new Checkout();

        // set your id as below
        checkout.setKeyID("rzp_test_zanTFg1iOlufyX");

        // set image
        checkout.setImage(R.mipmap.ic_launcher);

        // initialize json object
        JSONObject object = new JSONObject();
        try {
            // to put name
            object.put("name", "Furnigo");

            // put description
            object.put("description", "Test payment");

            // to set theme color
            object.put("theme.color", "#bf0d32");

            // put the currency
            object.put("currency", "INR");

            // put amount
            object.put("amount", Math.round(total*100));

            // put mobile number
            object.put("prefill.contact", "6352204367");

            // put email
            object.put("prefill.email", "surtivaidit@gmail.com");

            // open razorpay to checkout activity
            checkout.open(CartActivity.this, object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    public  void setCart(OrderOutputModel model){
        total=0;gst=0;amt=0;

        if(model.getOrder().size()==0){
            empty.setVisibility(View.VISIBLE);
            nonempty.setVisibility(View.GONE);
        }
        else {
            empty.setVisibility(View.GONE);
            nonempty.setVisibility(View.VISIBLE);
        }

        for (int i=0;i<model.getOrder().size();i++){
            amt+=Double.parseDouble(model.getOrder().get(i).getTotal_amount()+"");
        }

        gst= Math.round(amt *0.05);

        total=amt+gst;
        tvtotal.setText(Math.round(total)+"");
        tvDeliveryCharge.setText(gst+"");
        tvitem_total.setText(Math.round(amt)+"");
        rcylCart.setLayoutManager(new LinearLayoutManager(this));
        CartAdapter adapter=new CartAdapter(this, model.getOrder(), new CartAdapter.onClickListener() {
            @Override
            public void onPlus(OrderModel orderModel) {
                new OrderApi().updateOrder(CartActivity.this,orderModel.getQty()+"",orderModel.get_id(),uid);

            }

            @Override
            public void onMinus(OrderModel orderModel) {
                new OrderApi().updateOrder(CartActivity.this,orderModel.getQty()+"",orderModel.get_id(),uid);

            }

            @Override
            public void onDelete(OrderModel orderModel) {
                new OrderApi().removeOrder(CartActivity.this,orderModel.get_id());
            }
        });
        rcylCart.setAdapter(adapter);
    }

    @Override
    public void onPaymentSuccess(String s) {
        OrderModel model=new OrderModel("","",uid,"","",0,0,0,
                20,"","",1,"online",c_code,address);
        new OrderApi().confirmOrder(CartActivity.this,model);

    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Payment error", Toast.LENGTH_SHORT).show();
    }

    public void setCoupon(CouponOutputModel c) {
        coupon = Double.parseDouble(c.getCoupon_data().getC_discount());
        tvCouponOffer.setText(String.valueOf(coupon));
        double per=total *(coupon/100);
        total = total - per;
        tvtotal.setText(Math.round(total) + "");
    }
}
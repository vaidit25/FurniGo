package com.example.myproject.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myproject.R;
import com.example.myproject.adapter.CuponAdapter;
import com.example.myproject.model.CouponModel;

import java.util.ArrayList;


public class CuponFragment extends Fragment {

    ImageView img;
    View view;
    ArrayList<CouponModel> couponModels;
    TextView cuponcode, cupondiscount, cuponmaxamt;
    RecyclerView rcylcupon;


    public CuponFragment(ArrayList<CouponModel> couponModels) {
        // Required empty public constructor
        this.couponModels=couponModels;

    }

//    public CuponFragment() {
//
//    }
//
//
//    public static CuponFragment newInstance(String param1, String param2) {
//        CuponFragment fragment = new CuponFragment();
//        Bundle args = new Bundle();
//      //  args.putString("cupon_code",code );
//       // args.putString("cupon_discount", discount);
//       // args.putString("cupon_maxamt", maxamt);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//         //   mParam1 = getArguments().getString(ARG_PARAM1);
//          //  mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cupon, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        img = view.findViewById(R.id.img);
        cuponcode = view.findViewById(R.id.cuponcode);
        cupondiscount = view.findViewById(R.id.cupondiscount);
        cuponmaxamt = view.findViewById(R.id.cuponmaxamt);
        rcylcupon=view.findViewById(R.id.rcylcupon);

        rcylcupon.setLayoutManager(new LinearLayoutManager(getContext()));
        rcylcupon.setAdapter(new CuponAdapter(couponModels));



//        if (getArguments() != null) {
//            String name = getArguments().getString("cupon_code");
//            String desc = getArguments().getString("cupon_discount");
//            String pic = getArguments().getString("cupon_maxamt");

          //  cuponcode.setText(code);
          //  cupondiscount.setText(discount);
          //  cuponmaxamt.setText(maxamt);


        }
    }

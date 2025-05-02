package com.example.myproject.fragment;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myproject.AboutUsActivity;
import com.example.myproject.HelpSupportActivity;
import com.example.myproject.OrderHistoryActivity;
import com.example.myproject.PersonalInfoActivity;
import com.example.myproject.VersionActivity;
import com.example.myproject.utils.Login_Signup;
import com.example.myproject.R;
import com.example.myproject.utils.ConstantsData;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.File;


public class ProfileFragment extends Fragment {

    ImageView image1,camera;
    Button btnlogout;
    View view;
    TextView Profile_name,back ;
    File file;
    CardView cv_personal,cv_aboutus,cv_help,cv_myorder;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_profile,container,false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        CardView cv_personal=view.findViewById(R.id.cv_personal);
        cv_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), PersonalInfoActivity.class);
                startActivity(intent);
            }
        });

        cv_help = view.findViewById(R.id.cv_help);
        cv_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), HelpSupportActivity.class);
                startActivity(intent);
            }
        });

        cv_myorder = view.findViewById(R.id.cv_myorder);
        cv_myorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), OrderHistoryActivity.class);
                startActivity(intent);
            }
        });

        Profile_name =view.findViewById(R.id.Profile_name);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(ConstantsData.SP_NAME, Context.MODE_PRIVATE);
        String username=sharedPreferences.getString(ConstantsData.SP_USERNAME,"");

        Profile_name.setText(username);


        image1 =view.findViewById(R.id.image1);
        camera =view.findViewById(R.id.camera);
        btnlogout =view.findViewById(R.id.btnlogout);
        cv_aboutus=view.findViewById(R.id.cv_aboutus);



        SharedPreferences sp = getActivity().getSharedPreferences(ConstantsData.SP_IS_LOGIN, MODE_PRIVATE);
        String pic=sp.getString(ConstantsData.KEY_PIC,"");

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getActivity(), Login_Signup.class);
                SharedPreferences.Editor ed = sp.edit();
                ed.clear();
                ed.apply();
                startActivity(i1);
            }


        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(getActivity())
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });

        cv_aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), AboutUsActivity.class);
                startActivity(intent);

            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Uri uri = data.getData();
            image1.setImageURI(uri);

            SharedPreferences sp=getActivity().getSharedPreferences(ConstantsData.SP_IS_LOGIN,MODE_PRIVATE);

            SharedPreferences.Editor ed=sp.edit();
            ed.putString(ConstantsData.KEY_PIC,uri.getPath());
            ed.commit();


        }


    }

    private void loadImageFromPath(String filePath) {
        file = new File(filePath);

        if (file.exists()) {
            // Decode the image file into a Bitmap
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());

            // Set the Bitmap to the ImageView
            image1.setImageBitmap(bitmap);

        } else {
            // Handle the case where the file does not exist
            image1.setImageResource(R.mipmap.profile); // Set a default image or handle error
        }
    }
}
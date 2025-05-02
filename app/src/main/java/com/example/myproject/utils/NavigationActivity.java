package com.example.myproject.utils;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.myproject.R;
import com.example.myproject.ViewPagerAdapter;

public class NavigationActivity extends AppCompatActivity {

    ViewPager sliderViewPager;
    LinearLayout dotIndecator;
    ViewPagerAdapter viewPagerAdapter;
    Button backButton, skipButton, nextButton;
    TextView[] dots;

    ViewPager.OnPageChangeListener viewPagerLister = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setDotIndicator(position);
            if(position > 0){
                backButton.setVisibility(View.VISIBLE);
            }
            else {
                backButton.setVisibility(View.INVISIBLE);
            }

            if (position == 2){
                nextButton.setText("Finish");
            }
            else{
                nextButton.setText("Next");
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_navigation);

        backButton = findViewById(R.id.btn_back);
        nextButton = findViewById(R.id.btn_next);
        skipButton = findViewById(R.id.btn_skip);
        dotIndecator = (LinearLayout) findViewById(R.id.DotIndicator);
        sliderViewPager = (ViewPager) findViewById(R.id.slideViewPage);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentItem = sliderViewPager.getCurrentItem();
                if (currentItem > 0) {
                    sliderViewPager.setCurrentItem(currentItem - 1, true);
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentItem = sliderViewPager.getCurrentItem();
                if (currentItem < 2) {
                    sliderViewPager.setCurrentItem(currentItem + 1, true);
                } else {
                    Intent intent = new Intent(NavigationActivity.this, Login_Signup.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigationActivity.this, Login_Signup.class);
                startActivity(intent);
                finish();
            }
        });

         viewPagerAdapter = new ViewPagerAdapter(this);
         sliderViewPager.setAdapter(viewPagerAdapter);

         setDotIndicator(0);
         sliderViewPager.addOnPageChangeListener(viewPagerLister);



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
    public void setDotIndicator(int position){
        dots = new TextView[3];
        dotIndecator.removeAllViews();

        for (int i=0;i<dots.length;i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226",Html.FROM_HTML_MODE_LEGACY));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.white,getTheme()));
            dotIndecator.addView(dots[i]);

        }
            dots[position].setTextColor(getResources().getColor(R.color.lavender,getTheme()));
    }

}
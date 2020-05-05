package com.creative.rainbow.sekolah;

import android.os.Bundle;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.creative.rainbow.MainActivity;
import com.creative.rainbow.R;
import com.creative.rainbow.adapter.SliderAdapter;
import com.creative.rainbow.model.Model;

import java.util.ArrayList;
import java.util.List;

public class Bogor extends MainActivity {

    private LinearLayout dots;
    //    private Integer[] colors = null;
//    private ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBackgroundImg2();

        List<Model> models = new ArrayList<>();
        models.add(new Model("smagasem"));//nama n logo sekolah
        models.add(new Model("smalasem"));

        ViewPager slideViewPager = findViewById(R.id.slideViewPager);
        dots = findViewById(R.id.dots);

        SliderAdapter sliderAdapter = new SliderAdapter(this, models, "Bogor");
        slideViewPager.setAdapter(sliderAdapter);
        addDotsIndicator(0);
        slideViewPager.addOnPageChangeListener(viewListener);

//        Integer[] colors_temp = {
//                getResources().getColor(R.color.color1),
//                getResources().getColor(R.color.color4)
//        };
//        colors = colors_temp;
    }

    public void addDotsIndicator(int position){
        TextView[] mDots = new TextView[2];
        dots.removeAllViews();

        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.trans));

            dots.addView(mDots[i]);
        }

        mDots[position].setTextColor(getResources().getColor(R.color.white));
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {
//            if (i < (sliderAdapter.getCount() -1) && i < (colors.length - 1)) {
//                slideViewPager.setBackgroundColor(
//
//                        (Integer) argbEvaluator.evaluate(
//                                v,
//                                colors[i],
//                                colors[i + 1]
//                        )
//                );
//            }
//
//            else {
//                slideViewPager.setBackgroundColor(colors[colors.length - 1]);
//            }
        }

        @Override
        public void onPageSelected(int i) {

            addDotsIndicator(i);

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}

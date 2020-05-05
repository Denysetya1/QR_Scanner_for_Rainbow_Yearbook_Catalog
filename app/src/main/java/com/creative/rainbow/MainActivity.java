package com.creative.rainbow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.creative.rainbow.sekolah.Kendal;
import com.creative.rainbow.sekolah.Klaten;
import com.creative.rainbow.sekolah.Semarang;
import com.creative.rainbow.sekolah.Solo;

public class MainActivity extends AppCompatActivity {

    GridLayout mainGrid;

//    int []arr = {R.drawable.ig,R.drawable.ig2,R.drawable.ig3,R.drawable.ig4};
//    String []arr2 = {"Semarang", "Solo", "Bandung", "Bogor"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kota_layout);
        initBackgroundImg();

        mainGrid = findViewById(R.id.mainGrid);
        setToggleEvent(mainGrid);
    }

    private void initBackgroundImg() {
        ImageView background = findViewById(R.id.backgroudkota);
        Glide.with(this)
                .load(R.drawable.kota)
                .centerCrop()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(background);
    }
    protected void initBackgroundImg2() {
        ImageView background = findViewById(R.id.backgroudsekolah);
        Glide.with(this)
                .load(R.drawable.sekolah)
                .centerCrop()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(background);
    }
    protected void initBackgroundImg3() {
        ImageView background = findViewById(R.id.ivBgContent);
        Glide.with(this)
                .load(R.drawable.scan)
                .centerCrop()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(background);
    }

    private void setToggleEvent(final GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (finalI == 0){ //urutkan sesuai kota_layout
                        Intent intent = new Intent(MainActivity.this, Semarang.class);
                        startActivity(intent);
                    } else if (finalI == 1){
                        Intent intent = new Intent(MainActivity.this, Solo.class);
                        startActivity(intent);
                    } else if (finalI == 2) {
                        Intent intent = new Intent(MainActivity.this, Klaten.class);
                        startActivity(intent);
                    } else if (finalI == 3) {
                        Intent intent = new Intent(MainActivity.this, Kendal.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        img = findViewById(navid);
//        app.setImage(img, navdid);
//        layout = findViewById(layoutid);
//        app.setBackground(layout, bgid);
//    }

}

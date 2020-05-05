package com.creative.rainbow.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.creative.rainbow.R;
import com.creative.rainbow.model.Model;
import com.creative.rainbow.utama.SmagaSem;
import com.creative.rainbow.utama.SmalaSem;
import com.creative.rainbow.utama.SmalaSolo;
import com.creative.rainbow.utama.SmandaSem;
import com.creative.rainbow.utama.SmansaKendal;
import com.creative.rainbow.utama.SmansaKlaten;
import com.creative.rainbow.utama.SmanseSem;
import com.creative.rainbow.utama.SmansixSem;
import com.creative.rainbow.utama.SmansixSolo;

import java.util.List;

public class SliderAdapter extends PagerAdapter {

    private Context context;
    private List<Model> models;
    private String kota;
    //Logo sekolah ditambah jika ada sekolah baru
    private int[] imgs1 ={
//            R.drawable.smandasem,
            R.drawable.smagasem,
            R.drawable.smalasem,
            R.drawable.smansixsem,
            R.drawable.smansesem
    };
    private int[] imgs2 ={
            R.drawable.sma5solo,
            R.drawable.smansixsolo
    };
    private int[] imgs3 ={
            R.drawable.smansaklaten
    };
    private int[] imgs4 ={
            R.drawable.smansakendal
    };

    public SliderAdapter(Context context, List<Model> models, String kota) {
        this.context = context;
        this.models = models;
        this.kota = kota;
    }


    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = view.findViewById(R.id.slide_image);
        TextView title = view.findViewById(R.id.title);
        String kota1 = "Semarang";//Nambah kota
        String kota2 = "Solo";
        String kota3 = "Klaten";
        String kota4 = "Kendal";
//        String kota4 = "Bandung";

        if (kota.equals(kota1)){
            Glide.with(context)
                    .load(imgs1[position])
                    .fitCenter()
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(slideImageView);
        } else if (kota.equals(kota2)){
            Glide.with(context)
                    .load(imgs2[position])
                    .fitCenter()
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(slideImageView);
        } else if (kota.equals(kota3)){
            Glide.with(context)
                    .load(imgs3[position])
                    .fitCenter()
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(slideImageView);
        } else if (kota.equals(kota4)){
            Glide.with(context)
                    .load(imgs4[position])
                    .fitCenter()
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(slideImageView);
        }

        //slideImageView.setImageResource(models.get(position).getImage());
        title.setText(models.get(position).getTitle());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cek = models.get(position).getTitle();
                String smandasem = "smandasem";
                String smagasem = "smagasem";
                String smalasem = "smalasem";
                String smansixsem = "smansixsem";
                String smansesem = "smansesem";
                String smalasolo = "smalasolo";
                String smansixsolo = "smansixsolo";
                String smansaklaten = "smansaklaten";
                String smansakendal = "smansakendal";
                if(cek.equals(smandasem)){
                    Intent intent = new Intent(context, SmandaSem.class);
                    context.startActivity(intent);
                } else if (cek.equals(smagasem)){
                    Intent intent = new Intent(context, SmagaSem.class);
                    context.startActivity(intent);
                } else if (cek.equals(smalasem)){
                    Intent intent = new Intent(context, SmalaSem.class);
                    context.startActivity(intent);
                } else if (cek.equals(smansixsem)){
                    Intent intent = new Intent(context, SmansixSem.class);
                    context.startActivity(intent);
                } else if (cek.equals(smansesem)){
                    Intent intent = new Intent(context, SmanseSem.class);
                    context.startActivity(intent);
                } else if (cek.equals(smalasolo)){
                    Intent intent = new Intent(context, SmalaSolo.class);
                    context.startActivity(intent);
                } else if (cek.equals(smansixsolo)){
                    Intent intent = new Intent(context, SmansixSolo.class);
                    context.startActivity(intent);
                } else if (cek.equals(smansaklaten)){
                    Intent intent = new Intent(context, SmansaKlaten.class);
                    context.startActivity(intent);
                } else if (cek.equals(smansakendal)){
                    Intent intent = new Intent(context, SmansaKendal.class);
                    context.startActivity(intent);
                }
                // finish();
            }
        });

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((RelativeLayout)object);

    }
}

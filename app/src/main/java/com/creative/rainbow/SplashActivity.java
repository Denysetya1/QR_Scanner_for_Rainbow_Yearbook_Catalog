package com.creative.rainbow;

import android.content.Intent;
import android.view.WindowManager;

import androidx.appcompat.app.ActionBar;

import com.creative.rainbow.util.Constants;
import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;


public class SplashActivity extends AwesomeSplash {
    @Override
    public void initSplash(ConfigSplash configSplash) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Background
        configSplash.setBackgroundColor(R.color.bg_splash);
        configSplash.setAnimCircularRevealDuration(2000);
        configSplash.setRevealFlagX(Flags.REVEAL_LEFT);
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM);

        //Customize Path
        configSplash.setPathSplash(Constants.rainbow); //set path String
        configSplash.setOriginalHeight(700); //in relation to your svg (path) resource
        configSplash.setOriginalWidth(1290); //in relation to your svg (path) resource
        configSplash.setAnimPathStrokeDrawingDuration(1500);
        configSplash.setPathSplashStrokeSize(3); //I advise value be <5
        configSplash.setPathSplashStrokeColor(R.color.outer); //any color you want form colors.xml
        configSplash.setAnimPathFillingDuration(1500);
        configSplash.setPathSplashFillColor(R.color.title); //path object filling color


//        //Logo
//        configSplash.setLogoSplash(R.drawable.awal_background);
//        configSplash.setAnimLogoSplashDuration(2000);
//        configSplash.setAnimTitleTechnique(Techniques.FadeIn);

        //Titlle
        configSplash.setTitleSplash(" ");
        configSplash.setTitleTextColor(R.color.title);
        configSplash.setTitleTextSize(16f);
        configSplash.setAnimTitleDuration(2000);
        configSplash.setAnimTitleTechnique(Techniques.DropOut);
        configSplash.setTitleFont("font/Gavabon.otf");
    }

    @Override
    public void animationsFinished() {
        startActivity(new Intent(SplashActivity.this, Loading.class));

    }
}

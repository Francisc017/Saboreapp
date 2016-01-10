package com.example.provenlogic1.googlemap_connect;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreem extends Activity {
    private static final long SPLASH_SCREEN_DELAY = 3000;
    private ImageView ivAnimacion;
    private AnimationDrawable savingAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Hide title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screem);

        ivAnimacion = (ImageView)findViewById(R.id.animacion);
        ivAnimacion.setBackgroundResource(R.drawable.animacion);
        savingAnimation = (AnimationDrawable)ivAnimacion.getBackground();
        savingAnimation.start();
        // Set portrait orientation

        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                // Start the next activity
                Intent mainIntent = new Intent().setClass(
                        SplashScreem.this, login.class);
                startActivity(mainIntent);

                // Close the activity so the user won't able to go back this
                // activity pressing Back button
                finish();
            }
        };

        // Simulate a long loading process on application startup.
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }








}
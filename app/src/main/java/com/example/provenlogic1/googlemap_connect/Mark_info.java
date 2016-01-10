package com.example.provenlogic1.googlemap_connect;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class Mark_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.marker_toolbar);
        setSupportActionBar(toolbar);
    }

}

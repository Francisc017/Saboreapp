package com.example.provenlogic1.googlemap_connect;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Mely on 12-Dec-15.
 */
public class login extends Activity {

    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

    }
    public void inicio(View view) {
        Intent mainIntent = new Intent().setClass(
                login.this, inicio.class);

        startActivity(mainIntent);


        // Close the activity so the user won't able to go back this
        // activity pressing Back button
        finish();
    }

    public void test(View view) {
        Intent mainIntent = new Intent().setClass(
                login.this, Lista.class);

        startActivity(mainIntent);


        // Close the activity so the user won't able to go back this
        // activity pressing Back button
        finish();
    }

}

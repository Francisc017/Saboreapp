package com.example.provenlogic1.googlemap_connect;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Mely on 11-Dec-15.
 */
public class inicio extends Activity{

    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);

    }
    public void iniciar(View view) {
        Intent mainIntent = new Intent().setClass(
               inicio.this, MainActivity.class);

        startActivity(mainIntent);


        // Close the activity so the user won't able to go back this
        // activity pressing Back button
        finish();
    }
    public void lista(View view) {
        Intent mainIntent = new Intent().setClass(
                inicio.this, Lista.class);

        startActivity(mainIntent);


        // Close the activity so the user won't able to go back this
        // activity pressing Back button
        finish();
    }


}

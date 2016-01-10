package com.example.provenlogic1.googlemap_connect;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Felipe on 04-01-2016.
 */
public class WidgetConfig extends Activity {

    private int widgetId = 0;

    private Button btnAceptar;
    private ImageView btnImg;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget);

        //Obtenemos el Intent que ha lanzado esta ventana
        //y recuperamos sus parámetros
        Intent intentOrigen = getIntent();
        Bundle params = intentOrigen.getExtras();

        //Obtenemos el ID del widget que se está configurando
        widgetId = params.getInt(
                AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);

        //Establecemos el resultado por defecto (si se pulsa el botón 'Atrás'
        //del teléfono será éste el resultado devuelto).
        setResult(RESULT_CANCELED);

        //Obtenemos la referencia a los controles de la pantalla
        btnAceptar = (Button)findViewById(R.id.BtnNext);
        btnImg = (ImageView)findViewById(R.id.img_btn);


    }
}
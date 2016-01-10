package com.example.provenlogic1.googlemap_connect;

/**
 * Created by Felipe on 04-01-2016.
 */

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

public class Widget extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context,
                         AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        RemoteViews controles = new RemoteViews(context.getPackageName(), R.layout.widget);
        controles.setImageViewResource(R.id.img_btn, nueva_publicidad());
        appWidgetManager.updateAppWidget(0,controles);
    }

    private int nueva_publicidad() {
        return R.drawable.oferta_demo_2;
    }
}

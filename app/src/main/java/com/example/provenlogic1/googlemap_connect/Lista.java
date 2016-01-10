package com.example.provenlogic1.googlemap_connect;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Mely on 02-Jan-16.
 */


public class Lista extends ListActivity {
    ListView lv;
    TextView seleccionado;
    List<Locales>loca = new ArrayList<Locales>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista);
        lv=(ListView)findViewById(R.id.list);
        seleccionado = (TextView) findViewById(R.id.seleccionado);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this);
        ParseQuery<Locales> query = new ParseQuery<Locales>("Locales");
        query.findInBackground(new FindCallback<Locales>() {
            @Override
            public void done(List<Locales> objects, ParseException e) {
                for (Locales loc : objects) {
                    Locales newlocal = new Locales();
                    newlocal.setName(loc.getName());
                    newlocal.setDescripcion(loc.getDescripcion());
                    loca.add(newlocal);
                }
                lv.setAdapter(new ArrayAdapter<Locales>(Lista.this, android.R.layout.simple_expandable_list_item_1, loca));
            }
        });

    }


}
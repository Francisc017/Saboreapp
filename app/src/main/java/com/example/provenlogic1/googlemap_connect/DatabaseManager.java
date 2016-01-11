package com.example.provenlogic1.googlemap_connect;

import android.app.Application;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Felipe on 11-01-2016.
 */
public class DatabaseManager extends Application{
    private ParseObject users;
    private ParseObject business;
    private ParseObject favourites;
    private ParseObject offers;

    private List<Locales> locales;

    public DatabaseManager(){
        ParseObject.registerSubclass(Locales.class);
        Parse.initialize(this, String.valueOf(R.string.parse_id_app), String.valueOf(R.string.parse_client_id));

    }

    public boolean new_user(){
        return true;
    }


    public List<Locales> getBusinesses() {
        ParseQuery<Locales> query = new ParseQuery<Locales>("Locales");
        locales = new ArrayList<Locales>();
        query.findInBackground(new FindCallback<Locales>() {
            public void done(List<Locales> objects, ParseException e) {

                if (e == null) {
                    for (Locales loc : objects) {
                            locales.add(loc);
                    }
                }
                else {
                    // handle Parse Exception here
                }
            }
        });
        return locales;
    }
}

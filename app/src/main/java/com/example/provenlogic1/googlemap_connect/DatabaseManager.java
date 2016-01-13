package com.example.provenlogic1.googlemap_connect;

import android.app.Application;
import android.content.Context;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Felipe on 11-01-2016.
 */
public class DatabaseManager {
    private ParseObject users;
    private ParseObject business;
    private ParseObject favorites;
    private ParseObject offers;

    private List<Locales> locales;

    public DatabaseManager(Context context){
        ParseObject.registerSubclass(Locales.class);
        Parse.initialize(context, String.valueOf(R.string.parse_id_app), String.valueOf(R.string.parse_client_id));

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

    public List<Comments> getComments(Locales local){
        return null;
    }

    public List<Favorites> getFavorites(User user){
        return null;
    }
}

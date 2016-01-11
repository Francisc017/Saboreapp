package com.example.provenlogic1.googlemap_connect;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, GoogleMap.OnMarkerClickListener {

    private ListView lista;
    private Map<Marker, Locales> locales;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Inicialización de bd
        ParseObject.registerSubclass(Locales.class);
        Parse.initialize(this, getString(R.string.parse_id_app), getString(R.string.parse_client_id));

        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //menu lateral
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        locales = new HashMap<>();


        try {
            // Loading map
            initilizeMap();


            double latitude = 0;
            double longitude = 0;
            GPSTracker gps;
            gps = new GPSTracker(this);
            if(gps.canGetLocation()){
                latitude=gps.getLatitude();
                longitude=gps.getLongitude();
                LatLng UPV = new LatLng(latitude, longitude);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(UPV, 15));



            }else{
                gps.showSettingsAlert();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**function to load map If map is not created it will create it for you **/

    GoogleMap googleMap;
    private void initilizeMap() throws ParseException {

        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap();

            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

            // Showing / hiding your current location
            googleMap.setMyLocationEnabled(true);

            // Enable / Disable zooming controls
            googleMap.getUiSettings().setZoomControlsEnabled(true);

            // Enable / Disable my location button
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);

            // Enable / Disable Compass icon
            googleMap.getUiSettings().setCompassEnabled(true);

            // Enable / Disable Rotate gesture
            googleMap.getUiSettings().setRotateGesturesEnabled(true);

            // Enable / Disable zooming functionality
            googleMap.getUiSettings().setZoomGesturesEnabled(true);

            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Lo siento! no se ha podido cargar el mapa", Toast.LENGTH_SHORT)
                        .show();
            }

            locales.clear();
            ParseQuery<Locales> query = new ParseQuery<Locales>("Locales");

            query.findInBackground(new FindCallback<Locales>() {
                @Override
                public void done(List<Locales> objects, ParseException e) {
                    for (Locales loc : objects) {
                        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.burger);
                        Marker marker = googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(Double.parseDouble(loc.getString("Latitud")),Double.parseDouble(loc.getString("Longitud"))))
                                .title(loc.getString("Nombre"))
                                .snippet(loc.getString("Descripcion"))
                                .icon(icon));
                        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                            @Override
                            public void onInfoWindowClick(Marker arg0) {
                                // call an activity(xml file)
                                Intent I = new Intent(MainActivity.this, Lista.class);
                                startActivity(I);

                            }

                        });
                        locales.put(marker, loc);
                    }
                }
            });

            googleMap.setOnMarkerClickListener((GoogleMap.OnMarkerClickListener) this);


        }



    }
    /**
     * handle marker click event
     */
    @Override
    public boolean onMarkerClick(Marker marker) {
        try{


            //obtener datos del marcador actual
            String title = marker.getTitle();
            marker.hideInfoWindow();
            String id= marker.getId();
            locales.get(marker).getDescripcion();

            //obtiene tamaño de pantalla
            int screenSize = findViewById(R.id.main_screen).getHeight();
            int mMapHeight = findViewById(R.id.map_wrapper).getHeight();


            if(mMapHeight > (Integer)(3*screenSize)/4){ //on full map

                //creacion de activity_mark oculto
                findViewById(R.id.map_wrapper).getLayoutParams().height = (Integer)((mMapHeight)/3);
                //crea action bar
                Toolbar infoToolbar = (Toolbar) findViewById(R.id.marker_toolbar);
                setSupportActionBar(infoToolbar);
                getSupportActionBar().setTitle(marker.getTitle());
                getSupportActionBar().setIcon(R.drawable.burger_marker_toolbar);

                //centrar vista en marker actual
                LatLng marker_position=marker.getPosition();
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker_position, 15));

                //muestra layout final
                findViewById(R.id.main_screen).requestLayout();

            }else{//on dwarfed map
                findViewById(R.id.map_wrapper).getLayoutParams().height = mMapHeight*3;
                findViewById(R.id.main_screen).requestLayout();
            }
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        if (id == R.id.ranking) {
            // Handle the camera action
        } else if (id == R.id.visitados) {

        } else if (id == R.id.favoritos) {



        } else if (id == R.id.salir) {



        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void setInfo(){

    }



}
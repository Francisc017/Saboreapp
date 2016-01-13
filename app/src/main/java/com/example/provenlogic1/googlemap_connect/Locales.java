package com.example.provenlogic1.googlemap_connect;


import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Locales")
public class Locales extends ParseObject{
    public String getName(){
        return getString("Nombre");
    }

    public void setName(String name){
        put("Nombre",name);
    }

    public Double getLatitud(){
        return Double.parseDouble(getString("Latitud"));
    }

    public void setLatitud(String lat){
        put("Latitud",lat);
    }

    public Double getLongitud(){
        return Double.parseDouble(getString("Longitud"));
    }

    public void setLongitud(String lon){
        put("Longitud",lon);
    }

    public String getDescripcion(){
        return getString("Descripcion");
    }

    public void setDescripcion(String desc){
        put("Descripcion",desc);
    }

    @Override
    public String toString(){
        return getString("Nombre") + "\n" + getString("Descripcion");
    }
}
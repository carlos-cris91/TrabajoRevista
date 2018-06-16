package com.example.sala305_pc_14.listviewrevista;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Articulo {

    private String date_published;
    private String tile;
    private String pdf;


    public Articulo(){

    }
    public Articulo(JSONObject jsonObject) throws JSONException
    {
        this.date_published = jsonObject.getString("date_published");
        this.tile =jsonObject.getString("title");
        this.pdf = jsonObject.getString("pdf");
    }
    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String getDate_published() {
        return date_published;
    }

    public void setDate_published(String date_published) {
        this.date_published = date_published;
    }

    public String getTile() {
        return tile;
    }

    public void setTile(String tile) {
        this.tile = tile;
    }

    public static ArrayList<Articulo> JsonObjectsBuil(JSONArray datos) throws JSONException {

        ArrayList<Articulo> revistas = new ArrayList<>();
        for(int i = 0;i< datos.length(); i++){
            revistas.add(new Articulo(datos.getJSONObject(i)));
        }
        return revistas;
    }
}

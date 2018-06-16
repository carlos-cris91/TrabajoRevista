package com.example.sala305_pc_14.listviewrevista;

import android.text.Html;
import android.text.Spanned;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

class Revista {

    private String image;
    private Spanned descripcion;
    private String titulo;
    private String volume;
    private String number;


    public Revista(String image, Spanned descripcion, String titulo) {
        this.image = image;
        this.descripcion = descripcion;
        this.titulo = titulo;
    }
    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Revista(){

}


    public Revista(JSONObject jsonObject) throws JSONException {
        this.titulo = jsonObject.getString("title");
        this.descripcion = Html.fromHtml(jsonObject.getString("portada"));
        this.image = jsonObject.getString("portada");

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Spanned getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(Spanned descripcion) {
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public static ArrayList<Revista> JsonObjectsBuil(JSONArray datos) throws JSONException {

        ArrayList<Revista> revistas = new ArrayList<>();
        for(int i = 0;i< datos.length(); i++){
            revistas.add(new Revista(datos.getJSONObject(i)));
        }
        return revistas;
    }
}

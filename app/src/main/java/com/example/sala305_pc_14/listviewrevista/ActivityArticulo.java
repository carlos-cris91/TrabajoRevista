package com.example.sala305_pc_14.listviewrevista;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.QuickContactBadge;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import webservice.Asynchtask;
import webservice.WebService;

public class ActivityArticulo extends AppCompatActivity implements Asynchtask

{
    ListView listView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_noticias);
        if(Build.VERSION.SDK_INT >= 23){
            if(checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                Log.e("Permision error","You haver permission");
            }
            else{
                Log.e("Permission error","You have asked for permission");
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
            }
        }
        else{
            Log.e("Permission error","You alreadey have the permission");
        }
        listView = (ListView)this.findViewById(R.id.lvarticulos);
        String url = "http://revistas.uteq.edu.ec/ws/getarticles.php?volumen=1&num=1";
        Map<String,String> datos = new HashMap<>();
        WebService ws = new WebService(url,datos,this,this);
        ws.execute("");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Articulo ar = (Articulo)listView.getAdapter().getItem(i);
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(ar.getPdf()));
                request.setDescription("PDF Paper");
                request.setTitle("Pdf Article");
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
                    request.allowScanningByMediaScanner();
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                }
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"filedonwload.pdf");
                DownloadManager manager = (DownloadManager)ActivityArticulo.this.getSystemService(Context.DOWNLOAD_SERVICE);
                try{
                    manager.enqueue(request);
                }
                catch(Exception e){
                    Toast.makeText(ActivityArticulo.this,e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    @Override
    public void processFinish(String result) throws JSONException {
        try{
            JSONObject json = new JSONObject(result);
            JSONArray jsonArray = json.getJSONArray("articles");
            Articulo articulo = new Articulo();
            ArrayList<Articulo> articulos = Articulo.JsonObjectsBuil(jsonArray);
            AdapterArtitulo ad = new AdapterArtitulo(ActivityArticulo.this,  articulos);
            listView.setAdapter(ad);
        }
        catch (JSONException ex){
            Log.e("ERROR " , ex.getMessage());
            ex.printStackTrace();
        }
    }
}

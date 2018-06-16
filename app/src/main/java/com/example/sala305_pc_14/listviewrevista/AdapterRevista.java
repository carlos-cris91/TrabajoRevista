package com.example.sala305_pc_14.listviewrevista;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class AdapterRevista extends BaseAdapter
{

    ArrayList<Revista> revistas;
    Context context;

    public AdapterRevista(Context context, ArrayList<Revista> revistas){
        this.context = context;
        this.revistas = revistas;
    }
    @Override
    public int getCount() {
        return revistas.size();
    }

    @Override
    public Object getItem(int position) {
        return revistas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.rowrevista, null);
        TextView tvtitulo = (TextView)item.findViewById(R.id.tvtitlerevista);
        TextView tvdescripcion = (TextView)item.findViewById(R.id.tvdescripcion);
        ImageView imagen = (ImageView)item.findViewById(R.id.imagerevista);
        tvtitulo.setText(revistas.get(position).getTitulo());
        tvdescripcion.setText(revistas.get(position).getDescripcion());
        Glide.with(context).load(revistas.get(position).getImage()).into(imagen);
        return item;
    }
}

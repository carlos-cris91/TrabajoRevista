package com.example.sala305_pc_14.listviewrevista;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class AdapterArtitulo extends BaseAdapter{
    private ArrayList<Articulo> list;
    Context context;
    public AdapterArtitulo(Context context,ArrayList<Articulo> list){
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public Articulo getItem(int position) {
        return list.get(position);
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.rowarticulo, null);
        ImageView im = (ImageView) item.findViewById(R.id.imagearticulo);
        TextView tvtitle = (TextView)item.findViewById(R.id.tvtituloarticulo);
        TextView tvfechapublicacion = (TextView) item.findViewById(R.id.tvfechapublicacion);
        ImageView img = (ImageView)item.findViewById(R.id.imagearticulo);
        tvtitle.setText(list.get(position).getTile());
        tvfechapublicacion.setText(list.get(position).getDate_published());
        Glide.with(context).load("https://cdn.icon-icons.com/icons2/886/PNG/512/file_pdf_download_icon-icons.com_68954.png").into(img);
        return item;
    }
}

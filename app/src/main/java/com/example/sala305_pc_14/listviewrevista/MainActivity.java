package com.example.sala305_pc_14.listviewrevista;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import webservice.Asynchtask;
import webservice.WebService;
public class MainActivity extends AppCompatActivity implements Asynchtask
{
    ListView lvrevista;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvrevista = (ListView)this.findViewById(R.id.lvrevistas);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.header_listview_revista,null);
        String url = "http://revistas.uteq.edu.ec/ws/getrevistas.php";

        //http://revistas.uteq.edu.ec/ws/getarticles.php?volumen=1&num=1
        Map<String,String> datos = new HashMap<>();
        WebService ws = new WebService(url,datos,this,this);
        ws.execute("");
        lvrevista.addHeaderView(view);

        lvrevista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this,ActivityArticulo.class);
                Revista a = (Revista) lvrevista.getItemAtPosition(position);
                //i.putExtra("volumen", a.;
                MainActivity.this.startActivity(i);
            }
        });
    }
    @Override
    public void processFinish(String result) throws JSONException {
        try{
            JSONObject json = new JSONObject(result);
            JSONArray jsonArray = json.getJSONArray("issues");
            Revista revista = new Revista();
            ArrayList<Revista> revistas = Revista.JsonObjectsBuil(jsonArray);
            AdapterRevista ad = new AdapterRevista(MainActivity.this,  revistas);
            lvrevista.setAdapter(ad);
        }
        catch (JSONException ex){
            Log.e("ERROR " , ex.getMessage());
            ex.printStackTrace();
        }
    }
}

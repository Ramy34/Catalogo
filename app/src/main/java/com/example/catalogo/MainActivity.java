package com.example.catalogo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.catalogo.producto.Producto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONArray>{

    ListView lv;
    ProgressBar pbConexion;

    String url;
    RequestQueue queue;
    JsonArrayRequest request;
    ArrayList<Producto> productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.lv);
        pbConexion = findViewById(R.id.pbConexion);

        productos = new ArrayList<Producto>();
        queue = Volley.newRequestQueue(this);
        url = getResources().getString(R.string.urlCatalogo);
        request = new JsonArrayRequest(Request.Method.GET, url, null, this, this);
        queue.add(request);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        pbConexion.setVisibility(View.GONE);
        finish();
    }

    @Override
    public void onResponse(JSONArray response) {
        pbConexion.setVisibility(View.GONE);
        Log.d("Respuesta", response.toString());
        JSONObject jsonObject;
        try {
            for(int i=0; i < response.length(); i++){
                jsonObject = response.getJSONObject(i);
                int id = jsonObject.getInt(getResources().getString(R.string.id));
                String name = jsonObject.getString(getResources().getString(R.string.name));
                String thumbnail_url = jsonObject.getString(getResources().getString(R.string.thumbnail_url));
                double price = jsonObject.getDouble(getResources().getString(R.string.price));
                String provider = jsonObject.getString(getResources().getString(R.string.provider));
                double delivery = jsonObject.getDouble(getResources().getString(R.string.delivery));
                Producto producto = new Producto(id, name, thumbnail_url, price, provider, delivery);
                productos.add(producto);
            }
            Adaptador adaptador = new Adaptador(this, productos);
            lv.setAdapter(adaptador);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    intent.putExtra(getResources().getString(R.string.id), id);
                    startActivity(intent);
                }
            });

        }catch (JSONException e){

        }
    }
}

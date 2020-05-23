package com.example.catalogo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

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
    Toolbar barra;
    MediaPlayer mp;

    int id;
    String name;
    String thumbnail_url;
    double price;
    String provider;
    double delivery;

    String url;
    RequestQueue queue;
    JsonArrayRequest request;
    ArrayList<Producto> productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(R.string.tienda);
        lv = findViewById(R.id.lv);
        pbConexion = findViewById(R.id.pbConexion);
        barra = findViewById(R.id.barra);
        setSupportActionBar(barra);

        mp = MediaPlayer.create(this, R.raw.fondo);
        mp.setLooping(true);
        mp.start();

        productos = new ArrayList<Producto>();
        queue = Volley.newRequestQueue(this);
        url = getResources().getString(R.string.urlCatalogo);
        request = new JsonArrayRequest(Request.Method.GET, url, null, this, this);
        queue.add(request);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mp.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mp.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mp.isPlaying()){
            mp.stop();
            mp.release();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actions, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.info){
            mostrarDialogo();
        }
        return super.onOptionsItemSelected(item);
    }

    private void mostrarDialogo() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.info)
                .setMessage(R.string.mensaje)
                .setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        pbConexion.setVisibility(View.GONE);
        Toast.makeText(MainActivity.this, getResources().getString(R.string.error), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONArray response) {
        pbConexion.setVisibility(View.GONE);
        Log.d("Respuesta", response.toString());
        JSONObject jsonObject;
        try {
            for(int i=0; i < response.length(); i++){
                jsonObject = response.getJSONObject(i);
                id = jsonObject.getInt(getResources().getString(R.string.id));
                name = jsonObject.getString(getResources().getString(R.string.name));
                thumbnail_url = jsonObject.getString(getResources().getString(R.string.thumbnail_url));
                price = jsonObject.getDouble(getResources().getString(R.string.price));
                provider = jsonObject.getString(getResources().getString(R.string.provider));
                delivery = jsonObject.getDouble(getResources().getString(R.string.delivery));
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
                    intent.putExtra(getResources().getString(R.string.posicion), mp.getCurrentPosition());
                    startActivity(intent);
                    mp.pause();
                }
            });

        }catch (JSONException e){

        }
    }
}

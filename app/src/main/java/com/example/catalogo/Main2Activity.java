package com.example.catalogo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.catalogo.producto.Producto;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<String>{

    ImageView imArti;
    TextView tvDescip, tvName;

    String url;
    RequestQueue queue;
    StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();

        final long id = bundle.getLong(getResources().getString(R.string.id));

        imArti = findViewById(R.id.imArti);
        tvDescip = findViewById(R.id.tvDescripcion);
        tvName = findViewById(R.id.tvName);

        queue = Volley.newRequestQueue(this);
        url = getResources().getString(R.string.urlDescrip) + id;
        request = new StringRequest(Request.Method.POST, url, this, this){
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put(getResources().getString(R.string.id), String.valueOf(id));
                return params;
            }
        };
        queue.add(request);

    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            String nombre = jsonObject.getString(getResources().getString(R.string.name));
            String imag_url = jsonObject.getString(getResources().getString(R.string.imag_url));
            String descripcion = jsonObject.getString(getResources().getString(R.string.desc));
            tvName.setText(nombre);
            tvDescip.setText(descripcion);
            Picasso.with(this)
                    .load(imag_url)
                    .into(imArti);
        }catch(JSONException e) {

        }

    }
}

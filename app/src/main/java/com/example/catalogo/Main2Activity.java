package com.example.catalogo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<String>{

    ImageView imArti;
    TextView tvDescip;
    ProgressBar pbConec;
    Toolbar barra;

    String url;
    RequestQueue queue;
    StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();

        assert bundle != null;
        final long id = bundle.getLong(getResources().getString(R.string.id));

        imArti = findViewById(R.id.imArti);
        tvDescip = findViewById(R.id.tvDescripcion);
        pbConec = findViewById(R.id.pbConec);
        barra = findViewById(R.id.barra);

        setSupportActionBar(barra);

        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);


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
        pbConec.setVisibility(View.GONE);
        Toast.makeText(Main2Activity.this, getResources().getString(R.string.error), Toast.LENGTH_LONG).show();
        finish();

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onResponse(String response) {
        pbConec.setVisibility(View.GONE);
        try {
            JSONObject jsonObject = new JSONObject(response);
            String nombre = jsonObject.getString(getResources().getString(R.string.name));
            String imag_url = jsonObject.getString(getResources().getString(R.string.imag_url));
            String descripcion = jsonObject.getString(getResources().getString(R.string.desc));
            String newNom = new String(nombre.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            String newDesc = new String(descripcion.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

            setTitle(newNom);
            tvDescip.setText(newDesc);
            Picasso.with(this)
                    .load(imag_url)
                    .into(imArti);
        }catch(JSONException e) {
            Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show();

        }

    }
}

package com.example.catalogo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;

public class Main3Activity extends AppCompatActivity {

    ImageView imView;
    Toolbar tool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        imView = findViewById(R.id.imView);

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();

        tool = findViewById(R.id.tool);
        setSupportActionBar(tool);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

        final String imagen = bundle.getString(getResources().getString(R.string.url_img));

        Picasso.with(this).load(imagen).into(imView);
    }
}

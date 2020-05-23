package com.example.catalogo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.catalogo.producto.Producto;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    private static LayoutInflater inflater = null;
    Context contexto;
    ArrayList<Producto> productos;

    public Adaptador(Context contexto, ArrayList<Producto> productos) {
        this.contexto = contexto;
        this.productos = productos;
        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return productos.size();
    }

    @Override
    public Object getItem(int position) {
        return productos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return productos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View vista = inflater.inflate(R.layout.elemento_lista,null);
        TextView tvNombre = vista.findViewById(R.id.tvNombre);
        TextView tvPrecio = vista.findViewById(R.id.tvPrecio);
        TextView tvProveedor = vista.findViewById(R.id.tvProvedor);
        TextView tvEnvio = vista.findViewById(R.id.tvEnvio);
        ImageView iv = vista.findViewById(R.id.iv);

        double precio = productos.get(position).getPrice();
        double envio = productos.get(position).getDelivery();

        tvNombre.setText(productos.get(position).getName());
        tvPrecio.setText(String.format(contexto.getResources().getString(R.string.precio), precio));
        tvProveedor.setText(productos.get(position).getProvider());
        tvEnvio.setText(String.format(contexto.getResources().getString(R.string.envio), envio));

        Picasso.with(contexto)
                .load(productos.get(position).getThumbnail_url())
                .into(iv);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(contexto, Main3Activity.class);
                intent.putExtra(contexto.getResources().getString(R.string.url_img), productos.get(position).getThumbnail_url());
                contexto.startActivity(intent);
            }
        });

        return vista;
    }
}

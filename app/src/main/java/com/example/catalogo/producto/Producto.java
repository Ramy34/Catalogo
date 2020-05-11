package com.example.catalogo.producto;

public class Producto {
    private int id;
    private double price, delivery;
    private String name, thumbnail_url, provider;

    public Producto(int id, String name, String thumbnail_url, double price, String provider, double delivery){
        this.id = id;
        this.price = price;
        this.delivery = delivery;
        this.name = name;
        this.thumbnail_url = thumbnail_url;
        this.provider = provider;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDelivery() {
        return delivery;
    }

    public void setDelivery(double delivery) {
        this.delivery = delivery;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}

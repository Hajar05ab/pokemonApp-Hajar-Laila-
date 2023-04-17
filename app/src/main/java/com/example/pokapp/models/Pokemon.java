package com.example.pokapp.models;

public class Pokemon {
    private int id;
    private String name;
    private String url;


    public Pokemon() {  }

    public int getId() {
        String[] avatar = url.split("/");
        return Integer.parseInt(avatar[avatar.length - 1]);
    }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
}

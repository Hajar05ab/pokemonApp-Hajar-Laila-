package com.example.pokapp.models;

import com.google.gson.annotations.SerializedName;

public class PokemonInfo {
    @SerializedName("base_experience")
    private int experience;
    private int height;
    private int weight;

    public PokemonInfo(){}

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}

package com.example.pokapp.response;

import com.example.pokapp.models.Pokemon;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PokemonResponse {
    @SerializedName("results")
    private List<Pokemon> pokemonCall = new ArrayList<>();

    public PokemonResponse() {  }

    public List<Pokemon> getPokemonCall() { return pokemonCall; }
    public void setPokemonCall(List<Pokemon> pokemonCall) { this.pokemonCall = pokemonCall; }
}

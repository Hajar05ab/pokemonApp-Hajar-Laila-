package com.example.pokapp.services;

import com.example.pokapp.models.PokemonInfo;
import com.example.pokapp.response.PokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IPokemonServiceAPI {
    @GET("pokemon")
    Call<PokemonResponse> POKEMON_RESPONSE_CALL(@Query("offset") int offset, @Query("limit") int limit);

    @GET("pokemon/{id}")
    Call<PokemonInfo> POKEMON_INFO_CALL(@Path("id") int id);
}

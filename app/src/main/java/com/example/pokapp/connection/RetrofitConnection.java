package com.example.pokapp.connection;

import com.example.pokapp.models.PokemonInfo;
import com.example.pokapp.response.PokemonResponse;
import com.example.pokapp.services.IPokemonServiceAPI;

import java.util.Objects;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConnection {
    private static Retrofit retrofit;

    private RetrofitConnection(){
        try {
            retrofit = new Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/")
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }catch (Exception exception){ exception.printStackTrace(); }
    }

    private static Retrofit getInstance(){
        if(Objects.isNull(retrofit)) new RetrofitConnection();
        return retrofit;
    }

    private static IPokemonServiceAPI getInterfaceInstance(){
        return RetrofitConnection.getInstance().create(IPokemonServiceAPI.class);
    }

    public static Call<PokemonResponse> getPokemonResponse(int offset, int limit){
        return RetrofitConnection.getInterfaceInstance().POKEMON_RESPONSE_CALL(offset, limit);
    }

    public static  Call<PokemonInfo> getPokemonInfoCall(int id){
        return RetrofitConnection.getInterfaceInstance().POKEMON_INFO_CALL(id);
    }
}

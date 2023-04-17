# PokemonApp
C'est une application qui consomme de l'API


# Les ressources utilisés:
**Pokeapi** (https://pokeapi.co/).
**Picasso** pour load les images en URL.
**Lombok project** pour générer les constructeurs, getters et setters dans les classes **POJO**.
**RecyclerView** pour charger les articles 'items' et structurer l'affichage des données.
**Retrofit** pour faciliter la consommation l'API (https://square.github.io/retrofit/).
**Icon** (https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/).


# Class Pokemon:

```java
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


```

# Class PokemonInfo:

'''java
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

'''

# Class MainActivity :

'''java
package com.example.pokapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

import com.example.pokapp.adapter.ListPokemonAdapter;
import com.example.pokapp.connection.RetrofitConnection;
import com.example.pokapp.response.PokemonResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ListPokemonAdapter listPokemonAdapter;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);

        listPokemonAdapter = new ListPokemonAdapter(this);

        recyclerView = (RecyclerView) findViewById(R.id.itemsPokemon);
        recyclerView.setAdapter(listPokemonAdapter);
        recyclerView.setHasFixedSize(true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        RetrofitConnection.getPokemonResponse(0, 20).enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                if(response.isSuccessful()) {
                    listPokemonAdapter.copyListPokemon(response.body().getPokemonCall());
                }
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {
                Log.i("POKEMON", "EROOR : " + t.getMessage());
            }
        });
    }
}

'''

# Class PokemonActivity :

'''java
package com.example.pokapp;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pokapp.connection.RetrofitConnection;
import com.example.pokapp.models.PokemonInfo;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonActivity extends AppCompatActivity {
    private Intent intent;
    private TextView textView;
    private ProgressBar progressBar_1, progressBar_2, progressBar_3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiry_pokemon);

        intent = getIntent();

        textView = findViewById(R.id.namePok);

        progressBar_1 = findViewById(R.id.progress_1);
        progressBar_2 = findViewById(R.id.progress_2);
        progressBar_3 = findViewById(R.id.progress_3);

        int id = Integer.parseInt(intent.getStringExtra("id"));

        textView.setText(intent.getStringExtra("name"));

        Picasso.get().load(intent.getStringExtra("imageUrl")).into(((ImageView) findViewById(R.id.imageView)));

        RetrofitConnection.getPokemonInfoCall(id).enqueue(new Callback<PokemonInfo>() {
            @Override
            public void onResponse(Call<PokemonInfo> call, Response<PokemonInfo> response) {
                if(response.isSuccessful()){
                    PokemonInfo pokemonInfo = response.body();
                    ObjectAnimator.ofInt(progressBar_1, "progress", 1, pokemonInfo.getExperience()).setDuration(500).start();
                    ObjectAnimator.ofInt(progressBar_2, "progress", 1, pokemonInfo.getHeight()).setDuration(500).start();
                    ObjectAnimator.ofInt(progressBar_3, "progress", 1, pokemonInfo.getWeight()).setDuration(500).start();
                }
            }

            @Override
            public void onFailure(Call<PokemonInfo> call, Throwable t) {
                Log.i("POKEMON", "EROOR : " + t.getMessage());
            }
        });
    }
}

'''

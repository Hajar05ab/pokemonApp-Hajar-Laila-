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

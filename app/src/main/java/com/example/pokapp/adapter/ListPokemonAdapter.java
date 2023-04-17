package com.example.pokapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokapp.PokemonActivity;
import com.example.pokapp.R;
import com.example.pokapp.models.Pokemon;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListPokemonAdapter extends RecyclerView.Adapter<ListPokemonAdapter.ViewHolder> {
    private String TAG = "POKEMON";
    private List<Pokemon> ListPokemon;
    private Context context;
    public ListPokemonAdapter(@NonNull Context context) {
        this.context = context;
        this.ListPokemon = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_launcher_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Pokemon pokemon = ListPokemon.get(i);
        String imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/" + pokemon.getId() + ".png";

        Picasso.get().load(imageUrl).into(viewHolder.imageView);
        viewHolder.textViewName.setText(pokemon.getName());

        viewHolder.itemView.setOnClickListener((view) -> {
            Intent intent = new Intent(context, PokemonActivity.class);
            intent.putExtra("id", String.valueOf(pokemon.getId()));
            intent.putExtra("name", pokemon.getName());
            intent.putExtra("imageUrl", imageUrl);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return ListPokemon.size();
    }

    public void copyListPokemon(List<Pokemon> ListPokemon){
        this.ListPokemon.addAll(ListPokemon);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textViewName;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.itemImage);
            textViewName = (TextView) itemView.findViewById(R.id.itemTitle);
        }
    }
}

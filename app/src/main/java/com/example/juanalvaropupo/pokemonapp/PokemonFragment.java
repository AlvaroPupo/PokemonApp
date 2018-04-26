package com.example.juanalvaropupo.pokemonapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.juanalvaropupo.pokemonapp.MainActivity.POKEMON_NAME;

public class PokemonFragment extends Fragment{

    private String baseUrl = "http://pokeapi.co/api/v2/pokemon/";
    private Retrofit retrofit;
    private PokemonRetrofit pokemonRetrofit;
    private PokemonFragment pokemonFragment;
    private String pokemonName;

    @BindView(R.id.pokemon_imageview)
    protected ImageView PokemonImageView;

    @BindView(R.id.effect_pokemon_textview)
    protected TextView effectPokemonTextview;

    @BindView(R.id.pokemon_name_textview)
    protected TextView pokemonNameTextview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pokemon, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    public static PokemonFragment newInstance() {

        Bundle args = new Bundle();

        PokemonFragment fragment = new PokemonFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();

        pokemonName = getArguments().getString(POKEMON_NAME);

        setUpRetrofit();
        searchPokemonInfo(pokemonName);
    }

    public void searchPokemonInfo(String pokemonName) {
        pokemonRetrofit.getFindPokemonInfo(pokemonName).enqueue(new Callback<PokemonRetrofit.FindPokemonInfo>() {
            @Override
            public void onResponse(Call<PokemonRetrofit.FindPokemonInfo> call, Response<PokemonRetrofit.FindPokemonInfo> response) {
                if(response.isSuccessful()) {
                    effectPokemonTextview.setText(response.body().getPokemonInfo());
                    changeName();
                } else {
                    Toast.makeText(getContext(), "Sorry there was an error, please try again", Toast.LENGTH_LONG).show();

                }
            }
            @Override
            public void onFailure(Call<PokemonRetrofit.FindPokemonInfo> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    private void setUpRetrofit(){

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        pokemonRetrofit = retrofit.create(PokemonRetrofit.class);
    }
    private void changeName(){
        pokemonNameTextview.setText(getString(R.string.pokemon_string, pokemonName));
    }
}

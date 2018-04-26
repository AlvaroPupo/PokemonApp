package com.example.juanalvaropupo.pokemonapp;

import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonRetrofit {

    @GET("{name}")
    Call<FindPokemonInfo> getFindPokemonInfo(@Path("name")String name);

    class FindPokemonInfo{

        @SerializedName("pokemonInfo")
        private String pokemonInfo;
        public String getPokemonInfo(){
            return pokemonInfo;
        }
    }
}

package com.example.juanalvaropupo.pokemonapp;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private PokemonFragment pokemonFragment;

    public static final String POKEMON_NAME = "pokemon_name";

    @BindView(R.id.pokemon_name_edittext)
    protected TextInputEditText pokemonNameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.capture_button)
    protected void captureButtonClicked() {
        if (TextUtils.isEmpty(pokemonNameInput.getText().toString())) {
            Toast.makeText(this, "Please enter a pokemon name", Toast.LENGTH_LONG).show();
        } else {
            pokemonFragment = PokemonFragment.newInstance();

            Bundle bundle = new Bundle();
            bundle.putString(POKEMON_NAME, pokemonNameInput.getText().toString());
            pokemonFragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, pokemonFragment).commit();
        }
    }

    @Override
    public void onBackPressed() {

        if (pokemonFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().remove(pokemonFragment).commit();
        } else {
            super.onBackPressed();
        }
    }

    }



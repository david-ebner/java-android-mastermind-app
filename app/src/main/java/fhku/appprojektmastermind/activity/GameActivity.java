package fhku.appprojektmastermind.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import fhku.appprojektmastermind.container.ColorGuessAdapter;
import fhku.appprojektmastermind.container.ColorRepertoireView;
import fhku.appprojektmastermind.MastermindGame;
import fhku.appprojektmastermind.R;

public class GameActivity extends AppCompatActivity {

    private MastermindGame game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // set the difficulty according to an Intent from the MainActivity
        //TODO: number of available colors will be a difficulty setting, too
        int colorPatternSize = getIntent().getIntExtra("colorPatternSize", 4);
        int allowedColorGuesses = getIntent().getIntExtra("allowedColorGuesses", 10);

        game = new MastermindGame(colorPatternSize, allowedColorGuesses);

        // set up a ColorGuessAdapter for the RecyclerView
        ColorGuessAdapter adapter = new ColorGuessAdapter(game.getColorGuesses());

        // assign the game's ColorGuessAdapter (and a LayoutManager) to the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.guessList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // assign the game's ColorRepertoire
        ColorRepertoireView colorRepertoireView = findViewById(R.id.colorRepertoire);
        colorRepertoireView.setColorRepertoire(game.getColorRepertoire());
    }
}
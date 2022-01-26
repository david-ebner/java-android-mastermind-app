package fhku.appprojektmastermind.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

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
        ColorGuessAdapter adapter = new ColorGuessAdapter(game.getColorGuesses(), game);

        // set up a LinearLayoutManager with reverse order
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);

        // assign the game's ColorGuessAdapter and LayoutManager to the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.guessList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        // assign the game's ColorRepertoire
        ColorRepertoireView colorRepertoireView = findViewById(R.id.colorRepertoire);
        colorRepertoireView.setColorRepertoire(game.getColorRepertoire());
    }

    private void openLoseDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_lose);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btn_lose_to_menu = dialog.findViewById(R.id.btn_lose_to_menu);
        ImageView imageViewClose = dialog.findViewById(R.id.win_close);

        btn_lose_to_menu.setOnClickListener(view -> backToMenu());

        imageViewClose.setOnClickListener(view -> dialog.dismiss());

        dialog.show();
    }

    private void openWinDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_win);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btn_win_to_menu = dialog.findViewById(R.id.btn_win_to_menu);
        ImageView imageViewClose = dialog.findViewById(R.id.win_close);

        btn_win_to_menu.setOnClickListener(view -> backToMenu());

        imageViewClose.setOnClickListener(view -> dialog.dismiss());

        dialog.show();
    }

    private void backToMenu() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}


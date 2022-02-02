package fhku.appprojektmastermind.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

import fhku.appprojektmastermind.container.GuessRoundAdapter;
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
        int colorPatternLength = getIntent().getIntExtra("colorPatternLength", 4);
        int allowedGuessRounds = getIntent().getIntExtra("allowedGuessRounds", 10);
        boolean allowDuplicates = getIntent().getBooleanExtra("allowDuplicates",true);

        game = new MastermindGame(colorPatternLength, allowedGuessRounds, allowDuplicates);

        // set up a GuessRoundAdapter for the RecyclerView
        GuessRoundAdapter adapter = new GuessRoundAdapter(game);

        // set up a LinearLayoutManager with reverse order
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);

        // assign the game's GuessRoundAdapter and LayoutManager to the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.guessRoundsView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        // assign the game's ColorRepertoire
        ColorRepertoireView colorRepertoireView = findViewById(R.id.colorRepertoire);
        colorRepertoireView.setColorList(game.getColorRepertoire());

        // assign the game's TargetList
        //  TODO: make a TargetList View and implement it here
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
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    // handles action bar up button
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
            return true;
        }
        return false;
    }

    // handles back button
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
}


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
import android.view.View;
import android.widget.ImageView;

import fhku.appprojektmastermind.container.GuessRoundAdapter;
import fhku.appprojektmastermind.container.ColorRepertoireView;
import fhku.appprojektmastermind.MastermindGame;
import fhku.appprojektmastermind.R;
import fhku.appprojektmastermind.container.TargetListView;

public class GameActivity extends AppCompatActivity {

    private MastermindGame game;
    private TargetListView targetListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // set the difficulty according to an Intent from the MainActivity
        MastermindGame.Difficulty difficulty = (MastermindGame.Difficulty) getIntent().getSerializableExtra("difficulty");
        game = new MastermindGame(difficulty, this);

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
        targetListView = findViewById(R.id.targetList);
        targetListView.setVisibility(View.GONE);
        targetListView.setColorList(game.getTargetList());
    }

    public void openEndOfGameDialog(boolean hasWon) {
        Dialog dialogue = new Dialog(this);
        int contentView;
        int buttonView;
        int closeView;
        if (hasWon) {
            contentView = R.layout.dialogue_win;
            buttonView = R.id.btn_win_to_menu;
            closeView = R.id.win_close;
        } else {
            contentView = R.layout.dialogue_lose;
            buttonView = R.id.btn_lose_to_menu;
            closeView = R.id.lose_close;
        }
        dialogue.setContentView(contentView);
        dialogue.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialogue.findViewById(buttonView).setOnClickListener(view -> backToMenu());
        dialogue.<ImageView>findViewById(closeView).setOnClickListener(view -> dialogue.dismiss());

        dialogue.show();
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

    public void setTargetListVisibility(boolean visible) {
        this.targetListView.setVisibility(
                (visible ? View.VISIBLE : View.GONE)
        );
    }
}


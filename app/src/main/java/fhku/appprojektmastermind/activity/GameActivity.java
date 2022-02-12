package fhku.appprojektmastermind.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import fhku.appprojektmastermind.container.GuessRoundAdapter;
import fhku.appprojektmastermind.container.ColorRepertoireView;
import fhku.appprojektmastermind.MastermindGame;
import fhku.appprojektmastermind.R;
import fhku.appprojektmastermind.container.TargetListView;

public class GameActivity extends AppCompatActivity {

    private MastermindGame game;
    private TargetListView targetListView;
    private Animation repertoireAnim;

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

        repertoireAnim = AnimationUtils.loadAnimation(this, R.anim.view_animation_start);
        repertoireAnim.setStartOffset(500);
        repertoireAnim.setDuration(500);
        colorRepertoireView.startAnimation(repertoireAnim);



        // assign the game's TargetList
        targetListView = findViewById(R.id.targetList);
        targetListView.setVisibility(View.GONE);
        targetListView.setColorList(game.getTargetList());


        // set Animations
        repertoireAnim = AnimationUtils.loadAnimation(this, R.anim.view_animation_start);
        repertoireAnim.setStartOffset(800);
        repertoireAnim.setDuration(500);
        colorRepertoireView.startAnimation(repertoireAnim);
    }

    public void openEndOfGameDialog(boolean hasWon) {
        Dialog dialog = new Dialog(this);
        int contentView;
        int buttonView;
        int closeView;
        if (hasWon) {
            contentView = R.layout.dialog_win;
            buttonView = R.id.btn_win_to_menu;
            closeView = R.id.win_close;
        } else {
            contentView = R.layout.dialog_lose;
            buttonView = R.id.btn_lose_to_menu;
            closeView = R.id.lose_close;
        }
        dialog.setContentView(contentView);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.findViewById(buttonView).setOnClickListener(view -> backToMenu());
        dialog.<ImageView>findViewById(closeView).setOnClickListener(view -> dialog.dismiss());

        dialog.show();
    }

    private void backToMenu() {
        finish();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            backToMenu();
            return true;
        }
        return false;
    }

    // handles back button
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    public void setTargetListVisibility(boolean visible) {
        this.targetListView.setVisibility(
                (visible ? View.VISIBLE : View.GONE)
        );
    }
}


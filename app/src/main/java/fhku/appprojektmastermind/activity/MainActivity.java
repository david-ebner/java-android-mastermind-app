package fhku.appprojektmastermind.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import fhku.appprojektmastermind.ButtonTouchListener;
import fhku.appprojektmastermind.MastermindGame;
import fhku.appprojektmastermind.R;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        ImageView logo = findViewById(R.id.logo);
        Button btn_start = findViewById(R.id.btn_start);
        Button btn_guide = findViewById(R.id.btn_guide);

        btn_start.setOnTouchListener(new ButtonTouchListener());
        btn_start.setOnClickListener(view -> showDifficultyDialog());

        btn_guide.setOnTouchListener(new ButtonTouchListener());
        btn_guide.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), GuideActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        });

        // set Animations
        Animation logoAnim = AnimationUtils.loadAnimation(this, R.anim.slide_up);
        logoAnim.setStartOffset(200);
        logoAnim.setDuration(1200);

        Animation btn1Anim = AnimationUtils.loadAnimation(this, R.anim.slide_up);
        btn1Anim.setStartOffset(700);
        btn1Anim.setDuration(1000);

        Animation btn2Anim = AnimationUtils.loadAnimation(this, R.anim.slide_up);
        btn2Anim.setStartOffset(900);
        btn2Anim.setDuration(1000);

        logo.startAnimation(logoAnim);
        btn_start.startAnimation(btn1Anim);
        btn_guide.startAnimation(btn2Anim);
    }

    // displaying the difficulty Dialog
    @SuppressLint("ClickableViewAccessibility")
    private void showDifficultyDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_difficulty);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        Button btn_kids = dialog.findViewById(R.id.btn_difficulty_kids);
        Button btn_easy = dialog.findViewById(R.id.btn_difficulty_easy);
        Button btn_hard = dialog.findViewById(R.id.btn_difficulty_hard);
        Button btn_master = dialog.findViewById(R.id.btn_difficulty_master);

        ButtonTouchListener buttonTouchListener = new ButtonTouchListener();
        btn_kids.setOnTouchListener(buttonTouchListener);
        btn_easy.setOnTouchListener(buttonTouchListener);
        btn_hard.setOnTouchListener(buttonTouchListener);
        btn_master.setOnTouchListener(buttonTouchListener);

        btn_kids.setOnClickListener(view -> startNewGame(MastermindGame.Difficulty.KIDS, dialog));
        btn_easy.setOnClickListener(view -> startNewGame(MastermindGame.Difficulty.EASY, dialog));
        btn_hard.setOnClickListener(view -> startNewGame(MastermindGame.Difficulty.HARD, dialog));
        btn_master.setOnClickListener(view -> startNewGame(MastermindGame.Difficulty.MASTER, dialog));

        dialog.show();
    }

    private void startNewGame(MastermindGame.Difficulty difficulty, Dialog dialog) {
        dialog.dismiss();
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("difficulty", difficulty);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }
}
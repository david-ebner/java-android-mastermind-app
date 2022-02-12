package fhku.appprojektmastermind.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import fhku.appprojektmastermind.MastermindGame;
import fhku.appprojektmastermind.R;

public class MainActivity extends AppCompatActivity {
    ImageView logo;
    Button btn_start, btn_guide;
    Animation logoAnim, btn1Anim, btn2Anim, scaleUp, scaleDown;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        logo = findViewById(R.id.logo);
        btn_start = findViewById(R.id.btn_start);
        btn_guide = findViewById(R.id.btn_guide);


        btn_start.setOnTouchListener((v, event) -> {
            scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);
            scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    v.startAnimation(scaleDown);
                    break;
                case MotionEvent.ACTION_UP:
                    v.performClick();
                    v.startAnimation(scaleUp);

                    showCustomDialog();
                    break;
            }
            return false;
        });


        btn_guide.setOnTouchListener((v, event) -> {
            scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);
            scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    v.startAnimation(scaleDown);
                    break;
                case MotionEvent.ACTION_UP:
                    v.performClick();
                    v.startAnimation(scaleUp);

                    Intent intent = new Intent(v.getContext(), GuideActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                    break;
            }
            return false;
        });


        // set Animations
        logoAnim = AnimationUtils.loadAnimation(this, R.anim.view_animation_start);
        logoAnim.setStartOffset(200);
        logoAnim.setDuration(1200);

        btn1Anim = AnimationUtils.loadAnimation(this, R.anim.view_animation_start);
        btn1Anim.setStartOffset(700);
        btn1Anim.setDuration(1000);

        btn2Anim = AnimationUtils.loadAnimation(this, R.anim.view_animation_start);
        btn2Anim.setStartOffset(900);
        btn2Anim.setDuration(1000);

        logo.startAnimation(logoAnim);
        btn_start.startAnimation(btn1Anim);
        btn_guide.startAnimation(btn2Anim);
    }

    // displaying the difficulty Dialog
    @SuppressLint("ClickableViewAccessibility")
    private void showCustomDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_difficulty);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btn_kids = dialog.findViewById(R.id.btn_difficulty_kids);
        Button btn_easy = dialog.findViewById(R.id.btn_difficulty_easy);
        Button btn_hard = dialog.findViewById(R.id.btn_difficulty_hard);
        Button btn_master = dialog.findViewById(R.id.btn_difficulty_master);


        btn_kids.setOnTouchListener((v, event) -> {
            scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);
            scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    v.startAnimation(scaleDown);
                    break;
                case MotionEvent.ACTION_UP:
                    v.performClick();
                    v.startAnimation(scaleUp);

                    startNewGame(MastermindGame.Difficulty.KIDS, dialog);
                    break;
            }
            return false;
        });

        btn_easy.setOnTouchListener((v, event) -> {
            scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);
            scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    v.startAnimation(scaleDown);
                    break;
                case MotionEvent.ACTION_UP:
                    v.performClick();
                    v.startAnimation(scaleUp);

                    startNewGame(MastermindGame.Difficulty.EASY, dialog);
                    break;
            }
            return false;
        });

        btn_hard.setOnTouchListener((v, event) -> {
            scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);
            scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    v.startAnimation(scaleDown);
                    break;
                case MotionEvent.ACTION_UP:
                    v.performClick();
                    v.startAnimation(scaleUp);

                    startNewGame(MastermindGame.Difficulty.HARD, dialog);
                    break;
            }
            return false;
        });

        btn_master.setOnTouchListener((v, event) -> {
            scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);
            scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    v.startAnimation(scaleDown);
                    break;
                case MotionEvent.ACTION_UP:
                    v.performClick();
                    v.startAnimation(scaleUp);

                    startNewGame(MastermindGame.Difficulty.MASTER, dialog);
                    break;
            }
            return false;
        });


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
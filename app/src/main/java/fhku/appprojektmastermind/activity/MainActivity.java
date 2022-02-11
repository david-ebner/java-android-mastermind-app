package fhku.appprojektmastermind.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Objects;

import fhku.appprojektmastermind.MastermindGame;
import fhku.appprojektmastermind.R;

public class MainActivity extends AppCompatActivity {
    ImageView logo;
    Button btn_start, btn_guide;
    Animation logoAnim, btn1Anim, btn2Anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        logo = findViewById(R.id.logo);
        btn_start = findViewById(R.id.btn_start);
        btn_guide = findViewById(R.id.btn_guide);




        btn_start.setOnClickListener(view -> showCustomDialog());


        btn_guide.setOnClickListener(view ->{
            Intent intent = new Intent(view.getContext(), GuideActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        });


        // set Animations
        logoAnim = AnimationUtils.loadAnimation(this, R.anim.start_fade_in);
        logoAnim.setStartOffset(200);
        logoAnim.setDuration(1200);

        btn1Anim = AnimationUtils.loadAnimation(this, R.anim.start_fade_in);
        btn1Anim.setStartOffset(700);
        btn1Anim.setDuration(1000);

        btn2Anim = AnimationUtils.loadAnimation(this, R.anim.start_fade_in);
        btn2Anim.setStartOffset(900);
        btn2Anim.setDuration(1000);

        logo.startAnimation(logoAnim);
        btn_start.startAnimation(btn1Anim);
        btn_guide.startAnimation(btn2Anim);
    }

    // displaying the difficulty Dialog
    private void showCustomDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_difficulty);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btn_kids = dialog.findViewById(R.id.btn_difficulty_kids);
        Button btn_easy = dialog.findViewById(R.id.btn_difficulty_easy);
        Button btn_hard = dialog.findViewById(R.id.btn_difficulty_hard);
        Button btn_master = dialog.findViewById(R.id.btn_difficulty_master);


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
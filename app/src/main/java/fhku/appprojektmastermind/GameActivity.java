package fhku.appprojektmastermind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;

import java.util.List;

public class GameActivity extends AppCompatActivity {

    protected ColorGuessAdapter adapter;
    protected int colorPatternSize;
    protected int allowedColorGuesses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // set the difficulty according to an Intent from the MainActivity
        //TODO: number of available colors will be a difficulty setting, too
        colorPatternSize = getIntent().getIntExtra("colorPatternSize", 4);
        allowedColorGuesses = getIntent().getIntExtra("allowedColorGuesses", 15);

        // create a ColorGuessAdapter for the RecyclerView
        List<ColorGuess> guessList = ColorGuess.emptyGuessList(colorPatternSize, allowedColorGuesses);
        // Testing the first three Lines with already inserted colors
        guessList.set(0, new ColorGuess(new Integer[]{Color.RED, Color.BLUE, Color.GREEN, Color.MAGENTA}));
        guessList.set(1, new ColorGuess(new Integer[]{Color.CYAN, Color.RED, Color.GREEN, Color.YELLOW}));
        guessList.set(2, new ColorGuess(new Integer[]{Color.BLUE, null, Color.MAGENTA, Color.CYAN}));
        adapter = new ColorGuessAdapter(guessList);

        // assign the ColorGuessAdapter (and a LayoutManager) to the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.guessList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
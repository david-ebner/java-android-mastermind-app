package fhku.appprojektmastermind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class GameActivity extends AppCompatActivity {

    protected ColorGuessAdapter adapter;
    protected int colorPatternSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // set the difficulty according to an Intent from the MainActivity
        // TODO: number of available colors will be a difficulty setting, too
        colorPatternSize = getIntent().getIntExtra("colorPatternSize", 4);

//        ColorGuess[] testList = {
//                new ColorGuess(new int[] {1, 2, 3, 4}),
//                new ColorGuess(new int[] {2, 3, 4, 5}),
//                new ColorGuess(new int[] {3, 4, 5, 6}),
//                new ColorGuess(new int[] {0, 0, 0, 0})};
//        adapter = new ColorGuessAdapter(Arrays.asList(testList));

        // create a ColorGuessAdapter for the RecyclerView
        adapter = new ColorGuessAdapter(ColorGuess.emptyGuessList(4, 15));

        // assign the ColorGuessAdapter (and a LayoutManager) to the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.guessList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
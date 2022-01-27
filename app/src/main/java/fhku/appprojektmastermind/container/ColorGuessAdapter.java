package fhku.appprojektmastermind.container;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

import fhku.appprojektmastermind.MastermindGame;
import fhku.appprojektmastermind.R;

public class ColorGuessAdapter extends RecyclerView.Adapter<ColorGuessAdapter.ColorGuessViewHolder> {

    protected MastermindGame game;
    protected List<ColorGuess> guessRounds;

    public ColorGuessAdapter(List<ColorGuess> guessRounds, MastermindGame game) {
        this.guessRounds = guessRounds;
        this.game = game; //TODO: really hand over 'game' ??
    }

    // implements one guess-round
    public static class ColorGuessViewHolder extends RecyclerView.ViewHolder {
        public ColorGuessView colorGuessView;
        public Button buttonSubmit;

        public ColorGuessViewHolder(@NonNull View itemView) {
            super(itemView);
            colorGuessView = itemView.findViewById(R.id.colorGuessView);
            buttonSubmit = itemView.findViewById(R.id.button_submit);

            //TODO: instead of the button, the match-information for previous guesses should be displayed
        }
    }

    @Override
    public int getItemCount() {
        return guessRounds.size();
    }

    @NonNull
    @Override
    public ColorGuessViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MaterialCardView card = (MaterialCardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_color_guess, parent, false);
        return new ColorGuessViewHolder(card);
    }

    @Override
    public void onBindViewHolder(@NonNull ColorGuessViewHolder holder, int position) {
        ColorGuess guess = guessRounds.get(position);
        holder.colorGuessView.setColorGuess(guess);

        holder.buttonSubmit.setEnabled(guess.isActive());
        holder.buttonSubmit.setVisibility(guess.isDone() ? View.INVISIBLE : View.VISIBLE);

        holder.buttonSubmit.setOnClickListener(view -> {
            game.validateLatestColorGuess();
            notifyDataSetChanged();
        });

        //TODO: match-information for previous guesses should be filled
    }
}

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

public class GuessRoundAdapter extends RecyclerView.Adapter<GuessRoundAdapter.GuessRoundViewHolder> {

    protected MastermindGame game;
    protected List<ColorGuess> guessRounds;

    public GuessRoundAdapter(MastermindGame game) {
        this.game = game;
        this.guessRounds = game.getGuessRounds();
    }

    // implements one guess-round
    public static class GuessRoundViewHolder extends RecyclerView.ViewHolder {
        public ColorGuessView colorGuessView;
        public Button buttonSubmit;
        public RoundValidatorView roundValidatorView;

        public GuessRoundViewHolder(@NonNull View itemView) {
            super(itemView);
            colorGuessView = itemView.findViewById(R.id.colorGuessView);
            buttonSubmit = itemView.findViewById(R.id.button_submit);
            roundValidatorView = itemView.findViewById(R.id.roundValidatorView);

            //TODO: instead of the button, the match-information for previous guesses should be displayed
        }
    }

    @Override
    public int getItemCount() {
        return guessRounds.size();
    }

    @NonNull
    @Override
    public GuessRoundViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MaterialCardView card = (MaterialCardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_color_guess, parent, false);
        return new GuessRoundViewHolder(card);
    }

    @Override
    public void onBindViewHolder(@NonNull GuessRoundViewHolder holder, int position) {
        ColorGuess guess = guessRounds.get(position);
        holder.colorGuessView.setColorList(guess);

        //  TODO: Button should only be enabled if all Colorsball are inserted (guess.isDone())
        holder.buttonSubmit.setEnabled(guess.isModifiable());
        holder.buttonSubmit.setVisibility(guess.isModifiable() ? View.VISIBLE : View.INVISIBLE);
        holder.roundValidatorView.setVisibility(guess.isModifiable() ? View.INVISIBLE : View.VISIBLE);

        holder.buttonSubmit.setOnClickListener(view -> {
            game.validateLatestColorGuess();
            notifyDataSetChanged();
        });

        //TODO: match-information for previous guesses should be filled
    }
}

package fhku.appprojektmastermind.container;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import fhku.appprojektmastermind.MastermindGame;
import fhku.appprojektmastermind.R;

public class GuessRoundAdapter extends RecyclerView.Adapter<GuessRoundAdapter.GuessRoundViewHolder> {

    protected MastermindGame game;

    public GuessRoundAdapter(MastermindGame game) {
        this.game = game;
    }

    public static class GuessRoundViewHolder extends RecyclerView.ViewHolder {
        public ColorGuessView colorGuessView;
        public Button buttonSubmit;
        public RoundValidatorView roundValidatorView;

        public GuessRoundViewHolder(@NonNull View itemView) {
            super(itemView);
            colorGuessView = itemView.findViewById(R.id.colorGuessView);
            buttonSubmit = itemView.findViewById(R.id.button_submit);
            roundValidatorView = itemView.findViewById(R.id.roundValidatorView);
        }
    }

    @Override
    public int getItemCount() {
        return game.getGuessRounds().size();
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
        GuessRound guessRound = game.getGuessRounds().get(position);

        holder.colorGuessView.setColorList(guessRound.getColorGuess());
        holder.roundValidatorView.setColorList(guessRound.getRoundValidator());

        boolean isGuessRoundModifiable = guessRound.getColorGuess().isModifiable();
        holder.buttonSubmit.setEnabled(isGuessRoundModifiable);
        holder.buttonSubmit.setVisibility(isGuessRoundModifiable ? View.VISIBLE : View.INVISIBLE);
        holder.roundValidatorView.setVisibility(isGuessRoundModifiable ? View.INVISIBLE : View.VISIBLE);

        holder.buttonSubmit.setOnClickListener(view -> {
            if (!guessRound.getColorGuess().isFilled()) {
                Toast.makeText(this.game.getGameActivity(),"A real Mastermind uses every opportunity!", Toast.LENGTH_SHORT).show();
                return;
            }

            guessRound.validate(game.getTargetList());
            if (guessRound.isCorrect()) {
                notifyItemChanged(position);
                game.endGame(true);
            } else if (position + 1 == getItemCount()) {
                notifyItemChanged(position);
                game.endGame(false);
            } else {
                game.playNextGuess();
                notifyItemRangeChanged(position, 2);
            }
        });
    }
}

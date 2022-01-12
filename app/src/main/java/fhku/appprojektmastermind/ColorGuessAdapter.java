package fhku.appprojektmastermind;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class ColorGuessAdapter extends RecyclerView.Adapter<ColorGuessAdapter.ColorGuessViewHolder> {

    protected List<ColorGuess> guessList;
    public ColorGuessAdapter(List<ColorGuess> guessList) {
        this.guessList = guessList;
    }

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
        return guessList.size();
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
        ColorGuess guess = guessList.get(position);
//        holder.colorGuessView.draw(guess);
        holder.colorGuessView.setColorGuess(guess);

        //TODO: match-information for previous guesses should be filled
    }
}

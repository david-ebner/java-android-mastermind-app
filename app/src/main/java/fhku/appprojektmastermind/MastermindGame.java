package fhku.appprojektmastermind;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import fhku.appprojektmastermind.color.ColorBall;
import fhku.appprojektmastermind.color.PresetColorBall;
import fhku.appprojektmastermind.container.ColorGuess;
import fhku.appprojektmastermind.container.ColorList;
import fhku.appprojektmastermind.container.ColorRepertoire;
import fhku.appprojektmastermind.container.GuessRound;
import fhku.appprojektmastermind.container.RoundValidator;

public class MastermindGame {
    private  int colorPatternLength;
    private  int allowedGuessRounds;
    private  boolean allowDuplicates;
    private final List<ColorBall> PLAY_COLORS;

    private final List<GuessRound> GUESS_ROUNDS;

    private final ColorRepertoire COLOR_REPERTOIRE;
    private final ColorList TARGET_LIST;

    private int activeColorGuessIndex = 0;

    public enum Difficulty {
        KIDS, EASY, HARD, MASTER
    }

    public MastermindGame(Difficulty difficulty) {
        setDifficulty(difficulty);

        // set the Play Colors
        PLAY_COLORS = PresetColorBall.getPlayColors();

        GUESS_ROUNDS = GuessRound.emptyGuessRounds(this.colorPatternLength, this.allowedGuessRounds);

        // set up a ColorRepertoire containing all available PresetColorBalls
        COLOR_REPERTOIRE = new ColorRepertoire(PLAY_COLORS);

        GUESS_ROUNDS.get(0).getColorGuess().setModifiable();
        TARGET_LIST = ColorList.createRandomTargetList(this.colorPatternLength, this.allowDuplicates, PLAY_COLORS);
    }

    private void setDifficulty(Difficulty difficulty) {
        switch (difficulty) {
            case MASTER:
                this.colorPatternLength = 5;
                this.allowedGuessRounds = 15;
                this.allowDuplicates = true;
                break;
            case HARD:
                this.colorPatternLength = 4;
                this.allowedGuessRounds = 10;
                this.allowDuplicates = true;
                break;
            case KIDS:
                this.colorPatternLength = 3;
                this.allowedGuessRounds = 10;
                this.allowDuplicates = false;
                break;
            case EASY:
            default:
                this.colorPatternLength = 4;
                this.allowedGuessRounds = 10;
                this.allowDuplicates = false;
        }
    }

    public void showCongratulations() {
        Log.i("game", "YOU'VE WON");
        // TODO: use GameActivity.openWinDialog() instead
    }

    public void showGameOver() {
        Log.i("game", "YOU'VE LOST");
        // TODO: use GameActivity.openLoseDialog() instead
    }

    public void playNextGuess() {
        GUESS_ROUNDS.get(activeColorGuessIndex).getColorGuess().setDone();
        GUESS_ROUNDS.get(++activeColorGuessIndex).getColorGuess().setModifiable();
    }

    public List<GuessRound> getGuessRounds() {
        return GUESS_ROUNDS;
    }

    public ColorRepertoire getColorRepertoire() {
        return COLOR_REPERTOIRE;
    }

    public ColorList getTargetList() {
        return TARGET_LIST;
    }
}

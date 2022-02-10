package fhku.appprojektmastermind;

import java.util.List;

import fhku.appprojektmastermind.activity.GameActivity;
import fhku.appprojektmastermind.color.ColorBall;
import fhku.appprojektmastermind.color.PresetColorBall;
import fhku.appprojektmastermind.container.ColorList;
import fhku.appprojektmastermind.container.ColorRepertoire;
import fhku.appprojektmastermind.container.GuessRound;

public class MastermindGame {
    private  int colorPatternLength;
    private  int allowedGuessRounds;
    private  boolean allowDuplicates;

    private final List<GuessRound> GUESS_ROUNDS;

    private final ColorRepertoire COLOR_REPERTOIRE;
    private final ColorList TARGET_LIST;

    private final GameActivity gameActivity;

    private int activeColorGuessIndex = 0;

    public enum Difficulty {
        KIDS, EASY, HARD, MASTER
    }

    public MastermindGame(Difficulty difficulty, GameActivity gameActivity) {
        this.gameActivity = gameActivity;
        setDifficulty(difficulty);

        // set the Play Colors
        List<ColorBall> playColors = PresetColorBall.getPlayColors();

        GUESS_ROUNDS = GuessRound.emptyGuessRounds(this.colorPatternLength, this.allowedGuessRounds);

        // set up a ColorRepertoire containing all available PresetColorBalls
        COLOR_REPERTOIRE = new ColorRepertoire(playColors);

        GUESS_ROUNDS.get(0).getColorGuess().setModifiable();
        TARGET_LIST = ColorList.createRandomTargetList(this.colorPatternLength, this.allowDuplicates, playColors);
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

    public void endGame(boolean hasWon) {
        setCurrentGuessDone();
        this.COLOR_REPERTOIRE.setDone();
        this.gameActivity.setTargetListVisibility(true);
        this.gameActivity.openEndOfGameDialog(hasWon);
    }

    public void playNextGuess() {
        setCurrentGuessDone();
        GUESS_ROUNDS.get(++activeColorGuessIndex).getColorGuess().setModifiable();
    }

    private void setCurrentGuessDone() {
        GUESS_ROUNDS.get(activeColorGuessIndex).getColorGuess().setDone();
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

    public GameActivity getGameActivity() {
        return gameActivity;
    }
}

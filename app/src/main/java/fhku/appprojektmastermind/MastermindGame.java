package fhku.appprojektmastermind;

import android.util.Log;

import java.util.List;

import fhku.appprojektmastermind.color.ColorBall;
import fhku.appprojektmastermind.color.PresetColorBall;
import fhku.appprojektmastermind.container.ColorGuess;
import fhku.appprojektmastermind.container.ColorList;
import fhku.appprojektmastermind.container.ColorRepertoire;
import fhku.appprojektmastermind.container.RoundValidator;

public class MastermindGame {
    private  int colorPatternLength;
    private  int allowedGuessRounds;
    private  boolean allowDuplicates;
    private final List<ColorBall> PLAY_COLORS;

    private final List<ColorGuess> COLOR_GUESS_ROUNDS;
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

        COLOR_GUESS_ROUNDS = ColorGuess.emptyGuessList(this.colorPatternLength, this.allowedGuessRounds);
        setupColorGuessListForTesting();

        // set up a ColorRepertoire containing all available PresetColorBalls
        COLOR_REPERTOIRE = new ColorRepertoire(PLAY_COLORS);

        COLOR_GUESS_ROUNDS.get(0).setModifiable();
        TARGET_LIST = ColorList.createRandomTargetList(this.colorPatternLength, this.allowDuplicates, PLAY_COLORS);
    }

    private void setDifficulty(Difficulty difficulty) {
        switch (difficulty) {
            case MASTER:
            case HARD:
            case KIDS:
            case EASY:
            default:
                this.colorPatternLength = 4;
                this.allowedGuessRounds = 10;
                this.allowDuplicates = true;
        }
    }

    public List<ColorGuess> getGuessRounds() {
        return COLOR_GUESS_ROUNDS;
    }

    public ColorRepertoire getColorRepertoire() {
        return COLOR_REPERTOIRE;
    }

    public ColorList getTargetList() {
        return TARGET_LIST;
    }

    private void playNextGuess() {
        COLOR_GUESS_ROUNDS.get(activeColorGuessIndex).setDone();
        COLOR_GUESS_ROUNDS.get(++activeColorGuessIndex).setModifiable();
    }

    public RoundValidator validateLatestColorGuessRound() {
        ColorGuess latestGuess = COLOR_GUESS_ROUNDS.get(activeColorGuessIndex);

        RoundValidator roundValidator = RoundValidator.validate(
                latestGuess.getColorBalls(),
                TARGET_LIST.getColorBalls()
        );

        if (hasWon(roundValidator)) {
            //TODO: show "dialog_win"
            Log.i("playing", "YOU'VE WON!");
        } else if (allGuessesUsed()) {
            latestGuess.setDone();
            //  TODO: show "dialog_lose"
            //  TODO: show actual winning colors
            Log.i("playing", "YOU'VE LOST!");
        } else {
            playNextGuess();
            Log.i("playing", "next round!");
        }
        return roundValidator;
    }

    private boolean hasWon(RoundValidator currRound) {
        return currRound.getNumRightPos() == currRound.getColorPatternLength();
    }

    private boolean allGuessesUsed() {
        return activeColorGuessIndex + 1 == allowedGuessRounds;
    }


    private void setupColorGuessListForTesting() {
        COLOR_GUESS_ROUNDS.set(0, new ColorGuess(List.of(
                PresetColorBall.RED.getBall(),
                PresetColorBall.GREEN.getBall(),
                PresetColorBall.BLUE.getBall(),
                PresetColorBall.YELLOW.getBall()
        )));
        /*COLOR_GUESS_ROUNDS.set(1, new ColorGuess(List.of(
                PresetColorBall.BROWN.getBall(),
                PresetColorBall.RED.getBall(),
                PresetColorBall.GREEN.getBall(),
                PresetColorBall.YELLOW.getBall()
        )));
        COLOR_GUESS_ROUNDS.set(2, new ColorGuess(List.of(
                PresetColorBall.YELLOW.getBall(),
                new EmptyColorBall(),
                PresetColorBall.RED.getBall(),
                PresetColorBall.BLUE.getBall()
        )));*/
    }
}

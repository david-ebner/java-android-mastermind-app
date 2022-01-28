package fhku.appprojektmastermind;

import java.util.List;

import fhku.appprojektmastermind.color.ColorBall;
import fhku.appprojektmastermind.color.PresetColorBall;
import fhku.appprojektmastermind.container.ColorGuess;
import fhku.appprojektmastermind.container.ColorRepertoire;

public class MastermindGame {
    private final int COLOR_PATTERN_LENGTH;
    private final int ALLOWED_GUESS_ROUNDS;
    private final boolean ALLOW_DUPLICATES;
    private final List<ColorBall> PLAY_COLORS;

    private final List<ColorGuess> COLOR_GUESS_ROUNDS;
    private final ColorRepertoire COLOR_REPERTOIRE;
    private final ColorGuess TARGET_LIST;

    private int activeColorGuessIndex = 0;

    public MastermindGame(int colorPatternLength, int allowedGuessRounds, boolean allowDuplicates) {
        COLOR_PATTERN_LENGTH = colorPatternLength;
        ALLOWED_GUESS_ROUNDS = allowedGuessRounds;
        ALLOW_DUPLICATES = allowDuplicates;

        // set the Play Colors
        PLAY_COLORS = PresetColorBall.getPlayColors();

        COLOR_GUESS_ROUNDS = ColorGuess.emptyGuessList(COLOR_PATTERN_LENGTH, ALLOWED_GUESS_ROUNDS);
        setupColorGuessListForTesting();

        // set up a ColorRepertoire containing all available PresetColorBalls
        COLOR_REPERTOIRE = new ColorRepertoire(PLAY_COLORS);

        COLOR_GUESS_ROUNDS.get(0).setActive();

        TARGET_LIST = ColorGuess.createTargetList(COLOR_PATTERN_LENGTH, ALLOW_DUPLICATES, PLAY_COLORS);
    }

    public List<ColorGuess> getGuessRounds() {
        return COLOR_GUESS_ROUNDS;
    }

    public ColorRepertoire getColorRepertoire() {
        return COLOR_REPERTOIRE;
    }

    public ColorGuess getTargetList() {
        return TARGET_LIST;
    }

    private void playNextGuess() {
        COLOR_GUESS_ROUNDS.get(activeColorGuessIndex).setDone();
        COLOR_GUESS_ROUNDS.get(++activeColorGuessIndex).setActive();
    }

    public void validateLatestColorGuess() {
        if (hasWon()) {
            //TODO: show "success"
        } else if (allGuessesUsed()) {
            COLOR_GUESS_ROUNDS.get(activeColorGuessIndex).setDone();
            //TODO: show "game over" and the actual winning colors
        } else {
            playNextGuess();
        }
    }

    private boolean hasWon() {
        //TODO: validate latest ColorGuess
        return false;
    }

    private boolean allGuessesUsed() {
        return activeColorGuessIndex + 1 == ALLOWED_GUESS_ROUNDS;
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

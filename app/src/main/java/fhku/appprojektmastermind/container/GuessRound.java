package fhku.appprojektmastermind.container;

import java.util.ArrayList;
import java.util.List;

import fhku.appprojektmastermind.color.ColorBall;

public class GuessRound {
    private ColorGuess colorGuess;
    private RoundValidator roundValidator;
    private int numberOfGuessRounds;
    private int currentGuessIndex;

    public GuessRound(int colorPatternLength, int numberOfGuessRounds, int currentGuessIndex) {
        this.colorGuess = new ColorGuess(ColorBall.createEmptyColorBalls(colorPatternLength));
        this.roundValidator = new RoundValidator(new ArrayList<>());
        this.numberOfGuessRounds = numberOfGuessRounds;
        this.currentGuessIndex = currentGuessIndex;
    }

    public ColorGuess getColorGuess() {
        return colorGuess;
    }

    public RoundValidator getRoundValidator() {
        return roundValidator;
    }

    public static List<GuessRound> emptyGuessRounds(int colorPatternLength, int numberOfGuessRounds) {
        List<GuessRound> list = new ArrayList<>();
        for (int i = 0; i < numberOfGuessRounds; i++) {
            list.add(new GuessRound(colorPatternLength, numberOfGuessRounds, i));
        }
        return list;
    }

    public void validate(ColorList targetList) {
        roundValidator.update(colorGuess.getColorBalls(), targetList.getColorBalls());
    }

    public boolean isCorrect() {
        return roundValidator.getNumRightPos() == roundValidator.getColorPatternLength();
    }
}

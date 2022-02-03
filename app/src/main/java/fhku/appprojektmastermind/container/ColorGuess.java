package fhku.appprojektmastermind.container;

import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import fhku.appprojektmastermind.color.ColorBall;

public class ColorGuess extends ColorList {
    private RoundValidator roundValidator;

    public ColorGuess(List<ColorBall> colorBalls) {
        super(colorBalls);
        roundValidator = new RoundValidator(new ArrayList<>());
    }

    public RoundValidator getRoundValidator() {
        return roundValidator;
    }

    public void setRoundValidator(RoundValidator roundValidator) {
        this.roundValidator = roundValidator;
    }

    public static List<ColorGuess> emptyGuessList(int patternLength, int guessRounds) {
        List<ColorGuess> list = new ArrayList<>();
        for (int i = 0; i < guessRounds; i++) {
            list.add(new ColorGuess(ColorBall.createEmptyColorBalls(patternLength)));
        }
        return list;
    }
}

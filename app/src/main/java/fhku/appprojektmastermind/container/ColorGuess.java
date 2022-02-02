package fhku.appprojektmastermind.container;

import java.util.ArrayList;
import java.util.List;

import fhku.appprojektmastermind.color.ColorBall;

public class ColorGuess extends ColorList{

    public ColorGuess(List<ColorBall> colorBalls) {
        super(colorBalls);
    }

    public static List<ColorGuess> emptyGuessList(int patternLength, int guessRounds) {
        List<ColorGuess> list = new ArrayList<>();
        for (int i = 0; i < guessRounds; i++) {
            list.add(new ColorGuess(ColorBall.createEmptyColorBalls(patternLength)));
        }
        return list;
    }
}

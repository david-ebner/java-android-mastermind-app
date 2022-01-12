package fhku.appprojektmastermind.container;

import java.util.ArrayList;
import java.util.List;

import fhku.appprojektmastermind.color.ColorBall;

public class ColorGuess {
    private List<ColorBall> colorBalls;

    public ColorGuess(List<ColorBall> colorBalls) {
        this.colorBalls = colorBalls;
    }

    public List<ColorBall> getColorBalls() {
        return new ArrayList<>(colorBalls);
    }

    public static List<ColorGuess> emptyGuessList(int listLength, int guessLength) {
        List<ColorGuess> list = new ArrayList<>();
        for (int i = 0; i < listLength; i++) {
            list.add(new ColorGuess(ColorBall.createEmptyColorBalls(guessLength)));
        }
        return list;
    }
}

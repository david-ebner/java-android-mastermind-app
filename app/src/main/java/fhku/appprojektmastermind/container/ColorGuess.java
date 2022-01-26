package fhku.appprojektmastermind.container;

import java.util.ArrayList;
import java.util.List;

import fhku.appprojektmastermind.color.ColorBall;

public class ColorGuess {
    private List<ColorBall> colorBalls;
    private boolean active = false;

    public ColorGuess(List<ColorBall> colorBalls) {
        this.colorBalls = colorBalls;
    }

    public List<ColorBall> getColorBalls() {
        return new ArrayList<>(colorBalls);
    }

    public void setColorBalls(List<ColorBall> colorBalls) {
        this.colorBalls = colorBalls;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public static List<ColorGuess> emptyGuessList(int listLength, int guessLength) {
        List<ColorGuess> list = new ArrayList<>();
        for (int i = 0; i < listLength; i++) {
            list.add(new ColorGuess(ColorBall.createEmptyColorBalls(guessLength)));
        }
        return list;
    }
}

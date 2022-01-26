package fhku.appprojektmastermind.container;

import java.util.ArrayList;
import java.util.List;

import fhku.appprojektmastermind.color.ColorBall;

public class ColorGuess {
    private List<ColorBall> colorBalls;
    private boolean active = false;
    private boolean done = false;

    public ColorGuess(List<ColorBall> colorBalls) {
        this.colorBalls = colorBalls;
    }

    public List<ColorBall> getColorBalls() {
        return new ArrayList<>(colorBalls);
    }

    public void setColorBalls(List<ColorBall> colorBalls) {
        this.colorBalls = colorBalls;
    }

    public void setActive() {
        this.active = true;
    }

    public boolean isActive() {
        return active;
    }

    public void setDone() {
        this.done = true;
        this.active = false;
    }

    public boolean isDone() {
        return done;
    }

    public static List<ColorGuess> emptyGuessList(int listLength, int guessLength) {
        List<ColorGuess> list = new ArrayList<>();
        for (int i = 0; i < listLength; i++) {
            list.add(new ColorGuess(ColorBall.createEmptyColorBalls(guessLength)));
        }
        return list;
    }
}

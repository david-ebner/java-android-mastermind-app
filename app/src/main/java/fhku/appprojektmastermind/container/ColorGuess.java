package fhku.appprojektmastermind.container;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fhku.appprojektmastermind.color.ColorBall;
import fhku.appprojektmastermind.color.PresetColorBall;

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

    public static List<ColorGuess> emptyGuessList(int patternLength, int guessRounds) {
        List<ColorGuess> list = new ArrayList<>();
        for (int i = 0; i < guessRounds; i++) {
            list.add(new ColorGuess(ColorBall.createEmptyColorBalls(patternLength)));
        }
        return list;
    }


    public static ColorGuess createTargetList(int patternLength, boolean allowDuplicates){
        //  TODO: only allow Colors which are also in ColorRepertoire
        List<ColorBall> allPossibleColors = new ArrayList<>();
        for (PresetColorBall color: PresetColorBall.values()) {
            allPossibleColors.add(color.getBall());
        }

        List<ColorBall> targetList = new ArrayList<>();
        Random rand = new Random();

        if (allowDuplicates) {  // Duplicates allowed
            for (int i = 0; i < patternLength; i++) {
                int randIndex = rand.nextInt(allPossibleColors.size());

                targetList.add(allPossibleColors.get(randIndex));
            }
        } else {  // Duplicates not allowed
            for (int i = 0; i < patternLength; i++) {
                int randIndex = rand.nextInt(allPossibleColors.size());

                targetList.add(allPossibleColors.get(randIndex));
                allPossibleColors.remove(randIndex);
            }
        }

        return new ColorGuess(targetList);
    }
}

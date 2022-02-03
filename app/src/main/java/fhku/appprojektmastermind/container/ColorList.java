package fhku.appprojektmastermind.container;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fhku.appprojektmastermind.color.ColorBall;

public class ColorList {

    protected List<ColorBall> colorBalls;
    private boolean modifiable = false;
    private boolean done = false;

    public ColorList(List<ColorBall> colorBalls) {
        this.colorBalls = colorBalls;
    }

    public List<ColorBall> getColorBalls() {
        return new ArrayList<>(colorBalls);
    }

    public void setModifiable() {
        this.modifiable = true;
    }

    public boolean isModifiable() {
        return modifiable;
    }

    public void setDone() {
        this.done = true;
        this.modifiable = false;
    }

    public boolean isDone() {
        return done;
    }

    public static ColorList createRandomTargetList(int patternLength, boolean allowDuplicates, List<ColorBall> playColors){
        List<ColorBall> targetList = new ArrayList<>();
        Random rand = new Random();

        if (allowDuplicates) {  // Duplicates allowed
            for (int i = 0; i < patternLength; i++) {
                int randIndex = rand.nextInt(playColors.size());

                targetList.add(playColors.get(randIndex));
            }
        } else {  // Duplicates not allowed
            List<ColorBall> colorsAllowed = new ArrayList<>(playColors);
            for (int i = 0; i < patternLength; i++) {
                int randIndex = rand.nextInt(colorsAllowed.size());

                targetList.add(colorsAllowed.get(randIndex));
                colorsAllowed.remove(randIndex);
            }
        }
        return new ColorList(targetList);
    }
}

package fhku.appprojektmastermind.container;

import java.util.ArrayList;
import java.util.List;

import fhku.appprojektmastermind.color.ColorBall;
import fhku.appprojektmastermind.color.PresetColorBall;

public class RoundValidator extends ColorList {
    private int numRightPos;
    private int numWrongPos;
    private int colorPatternLength;

    public RoundValidator(List<ColorBall> colorBalls) {
        super(colorBalls);
    }

    public void update(List<ColorBall> currRound, List<ColorBall> targetColors) { // TODO: not pretty yet
        int rightPos = 0;
        int wrongPos = 0;
        List<Integer> currColorInts = new ArrayList<>();
        List<Integer> targetColorInts = new ArrayList<>();

        for (int i = 0; i < targetColors.size(); i++) {
            currColorInts.add(currRound.get(i).getColorInt());
            targetColorInts.add(targetColors.get(i).getColorInt());
        }

        // get rightPos
        for (int i = 0; i < targetColorInts.size(); i++) {
            if (targetColorInts.get(i).equals(currColorInts.get(i))) {
                rightPos++;
                targetColorInts.set(i, null);
                currColorInts.set(i, null);
            }
        }

        // get wrongPos
        for (int i = 0; i < targetColorInts.size(); i++) {
            if (targetColorInts.get(i) != null || currColorInts.get(i) != null) {
                for (int j = 0; j < targetColorInts.size(); j++) {
                    if (targetColorInts.get(j) != null && currColorInts.get(i).equals(targetColorInts.get(j))) {
                        wrongPos++;
                        targetColorInts.set(j, null);
                        break;
                    }
                }
            }
        }

        List<ColorBall> validationColorBalls = new ArrayList<>();
        for (int i = 0; i < rightPos; i++) {
            validationColorBalls.add(PresetColorBall.WHITE.getBall());
        }
        for (int i = 0; i < wrongPos; i++) {
            validationColorBalls.add(PresetColorBall.BLACK.getBall());
        }
        while (validationColorBalls.size() < currRound.size()) {
            validationColorBalls.add(new ColorBall(null));
        }

        this.numRightPos = rightPos;
        this.numWrongPos = wrongPos;
        this.colorPatternLength = currRound.size();
        this.setColorBalls(validationColorBalls);
    }

    public void setNumRightPos(int numRightPos) {
        this.numRightPos = numRightPos;
    }

    public void setNumWrongPos(int numWrongPos) {
        this.numWrongPos = numWrongPos;
    }

    public void setColorPatternLength(int colorPatternLength) {
        this.colorPatternLength = colorPatternLength;
    }

    public int getNumRightPos() {
        return numRightPos;
    }

    public int getNumWrongPos() {
        return numWrongPos;
    }

    public int getColorPatternLength() {
        return colorPatternLength;
    }
}

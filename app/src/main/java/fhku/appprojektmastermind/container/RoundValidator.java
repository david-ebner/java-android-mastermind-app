package fhku.appprojektmastermind.container;

import java.util.ArrayList;
import java.util.List;

import fhku.appprojektmastermind.color.ColorBall;

public class RoundValidator {
    private final int numRightPos;
    private final int numWrongPos;
    private final int colorPatternLength;

    public RoundValidator(List<ColorBall> currRound, List<ColorBall> targetColors) {
        int rightPos = 0;
        int wrongPos = 0;
        List<Integer> curr = new ArrayList<>();
        List<Integer> target = new ArrayList<>();

        for (int i = 0; i < targetColors.size(); i++) {
            curr.add(currRound.get(i).getColorInt());
            target.add(targetColors.get(i).getColorInt());
        }

        // get rightPos
        for (int i = 0; i < target.size(); i++) {
            if (target.get(i).equals(curr.get(i))) {
                rightPos++;
                target.set(i, null);
                curr.set(i, null);
            }
        }

        // get wrongPos
        for (int i = 0; i < target.size(); i++) {
            if (target.get(i) != null || curr.get(i) != null) {
                for (int j = 0; j < target.size(); j++) {
                    if (target.get(j) != null && curr.get(i).equals(target.get(j))) {
                        wrongPos++;
                        target.set(j, null);
                        break;
                    }
                }
            }
        }

        this.numRightPos = rightPos;
        this.numWrongPos = wrongPos;
        this.colorPatternLength = currRound.size();
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

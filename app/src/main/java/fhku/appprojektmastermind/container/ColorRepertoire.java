package fhku.appprojektmastermind.container;

import java.util.ArrayList;
import java.util.List;

import fhku.appprojektmastermind.color.ColorBall;
import fhku.appprojektmastermind.color.PresetColorBall;

public class ColorRepertoire {
    private final List<ColorBall> COLOR_BALLS = new ArrayList<>();

    public ColorRepertoire(PresetColorBall... presetColorBalls) {
        for (PresetColorBall color : presetColorBalls) {
            this.COLOR_BALLS.add(color.getBall());
        }
    }

    public List<ColorBall> getColorBalls() {
        return new ArrayList<>(COLOR_BALLS);
    }
}

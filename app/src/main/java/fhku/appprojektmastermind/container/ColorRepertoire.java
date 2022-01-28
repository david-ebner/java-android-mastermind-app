package fhku.appprojektmastermind.container;

import java.util.ArrayList;
import java.util.List;

import fhku.appprojektmastermind.color.ColorBall;
import fhku.appprojektmastermind.color.PresetColorBall;

public class ColorRepertoire {
    private final List<ColorBall> COLOR_BALLS;

    public ColorRepertoire(List<ColorBall> playColors) {
        COLOR_BALLS = playColors;
    }

    public List<ColorBall> getColorBalls() {
        return new ArrayList<>(COLOR_BALLS);
    }
}

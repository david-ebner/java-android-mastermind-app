package fhku.appprojektmastermind.color;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

public enum PresetColorBall {
    RED (Color.rgb(255, 92, 95)),
    GREEN (Color.rgb(0, 224, 67)),
    BLUE (Color.rgb(120, 131, 247)),
    YELLOW (Color.rgb(255, 239, 10)),
    ORANGE (Color.rgb(252, 188, 38)),
    PINK (Color.rgb(254, 112, 247)),

    GREY (Color.rgb(171,171,171)),
    BLACK (Color.BLACK),
    WHITE (Color.WHITE);

    private final int COLOR;
    PresetColorBall(int color) {
        this.COLOR = color;
    }

    public ColorBall getBall() {
        return new ColorBall(this.COLOR);
    }

    public static List<ColorBall> getPlayColors() {
        List<ColorBall> playColors = new ArrayList<>();
        List<ColorBall> allColors = new ArrayList<>();
        for (PresetColorBall color: PresetColorBall.values()) {
            allColors.add(color.getBall());
        }

        for (int i = 0; i < 6; i++) {
            playColors.add(allColors.get(i));
        }

        return playColors;
    }
}

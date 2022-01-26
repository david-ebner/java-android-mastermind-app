package fhku.appprojektmastermind.color;

import android.graphics.Color;

public enum PresetColorBall {
    RED (Color.rgb(255, 92, 95)),
    GREEN (Color.rgb(0, 224, 67)),
    BLUE (Color.rgb(120, 131, 247)),
    YELLOW (Color.rgb(255, 239, 10)),
    ORANGE (Color.rgb(252, 188, 38)),
    PINK (Color.rgb(254, 112, 247)),
    BROWN (Color.rgb(112,80,0)),
    BLACK (Color.BLACK);

    private final int COLOR;
    PresetColorBall(int color) {
        this.COLOR = color;
    }

    public ColorBall getBall() {
        return new ColorBall(this.COLOR);
    }
}

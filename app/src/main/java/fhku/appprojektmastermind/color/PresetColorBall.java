package fhku.appprojektmastermind.color;

import android.graphics.Color;

public enum PresetColorBall {
    RED (Color.RED),
    GREEN (Color.GREEN),
    BLUE (Color.BLUE),
    YELLOW (Color.YELLOW),
    BROWN (Color.rgb(184,134,11)),
    BLACK (Color.BLACK);

    private final int COLOR;
    PresetColorBall(int color) {
        this.COLOR = color;
    }

    public ColorBall getBall() {
        return new ColorBall(this.COLOR);
    }
}

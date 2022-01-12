package fhku.appprojektmastermind;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

public class ColorBall {
    private Integer color = Color.BLACK;

    public ColorBall() {
    }

    public ColorBall(Integer color) {
        if (color != null) {
            this.color = color;
        }
    }

    public int getColorInt() {
        return color;
    }

    public boolean isEmpty() {
        return false;
    }

    public static List<ColorBall> createEmptyColorBalls(int listLength) {
        List<ColorBall> list = new ArrayList<>();
        for (int i = 0; i < listLength; i++) {
            list.add(new EmptyColorBall());
        }
        return list;
    }
}

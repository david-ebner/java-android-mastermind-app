package fhku.appprojektmastermind.color;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

public class ColorBall {
    private Integer color = Color.BLACK;
    private boolean empty = true;

    public ColorBall() {
    }

    public ColorBall(Integer color) {
        if (color != null) {
            this.color = color;
            this.empty = false;
        }
    }

    public int getColorInt() {
        return color;
    }

    public boolean isEmpty() {
        return empty;
    }

    public static List<ColorBall> createEmptyColorBalls(int listLength) {
        List<ColorBall> list = new ArrayList<>();
        for (int i = 0; i < listLength; i++) {
            list.add(new ColorBall());
        }
        return list;
    }

    public void setColor(Integer color) {
        this.color = color;
        this.empty = false;
    }
}

package fhku.appprojektmastermind;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ColorGuessView extends View {
    /** A <code>List</code> mapping all the different colors (e.g. 1=red, 3=blue, ...)
     */
    protected List<Paint> paintList;
    // TODO: these colors were just created for testing and should be replaced later
    private final int[][] colors = {
            {0,0,0},        //black for empty fields
            {255,0,0},      //red
            {0,255,0},      //lime
            {0,0,255},      //blue
            {255,255,0},    //yellow
            {0,255,255},    //cyan
            {255,0,255}     //magenta
    };
    private ColorGuess colorGuess;

    public ColorGuessView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaintList();
        // colorGuess needs to be initialized, but with length 0 nothing will be drawn.
        colorGuess = ColorGuess.emptyGuess(0);
    }

    private void initPaintList() {
        paintList = new ArrayList<>();
        for (int[] color : colors) {
            Paint paint = new Paint();
            paint.setColor(Color.rgb(color[0], color[1], color[2]));
            if (Arrays.equals(color, new int[]{0, 0, 0})) {
                // only draw the border if a position is empty (rgb = 0, 0, 0 = black)
                //TODO: find better way of implementing "empty" colors
                paint.setStyle(Paint.Style.STROKE);
            } else {
                // for all other positions: fill the circle with color
                paint.setStyle(Paint.Style.FILL);
            }
            paintList.add(paint);
        }
    }

    public void draw(ColorGuess colorGuess) {
        this.colorGuess = colorGuess;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // for all colors in colorGuess, draw circles (in a line) with some spacing in between
        //TODO: currently this gives very tiny circles due to getHeight (getWidth makes it bigger, but too big)
        // probably some settings in card_color_guess.xml or activity_game.xml cause this
        float colorCircleRadius = (float) getHeight() / (colorGuess.getLength() * 2);
        float padding = colorCircleRadius / 2;
        for (int i = 0; i < colorGuess.getLength(); i++) {
            float posX = (i * 2) * (colorCircleRadius + padding) + colorCircleRadius;
            canvas.drawCircle(posX, colorCircleRadius, colorCircleRadius, paintList.get(colorGuess.getColorAtIndex(i)));
        }
        
    }
}

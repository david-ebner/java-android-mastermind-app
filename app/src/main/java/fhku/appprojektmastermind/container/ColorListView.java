package fhku.appprojektmastermind.container;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import fhku.appprojektmastermind.color.ColorBall;
import fhku.appprojektmastermind.color.ColorBallView;

public class ColorListView extends ViewGroup {
    protected ColorList colorList;

    protected int single_spacing;
    protected int padding_top;
    protected int diameter;
    protected final int DEFAULT_DIAMETER = 50;

    public ColorListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setColorList(ColorList colorList) {
        this.colorList = colorList;
        initColorBallViews();
    }

    protected void initColorBallViews() {
        this.removeAllViews();

        for (ColorBall colorBall : colorList.getColorBalls()) {
            ColorBallView newColorBallView = new ColorBallView(getContext());
            newColorBallView.setColorBall(colorBall);
            this.addView(newColorBallView);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            int startPosition = (diameter + single_spacing) * i + single_spacing;
            child.layout(
                    startPosition,
                    padding_top,
                    startPosition + diameter,
                    padding_top + diameter
            );
        }
    }
}

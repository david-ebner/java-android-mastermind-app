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
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int CHILD_COUNT = getChildCount();

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        int spacing = width / 5;
        single_spacing = spacing / (CHILD_COUNT + 1);

        padding_top = height / 10;

        int availableWidth = width - spacing;
        diameter = (CHILD_COUNT == 0 ?
                DEFAULT_DIAMETER : availableWidth / CHILD_COUNT); // avoiding division by zero
//        int diameterHeight = height - (padding_top * 2);
        diameter = Math.min(diameter, height - (padding_top * 2));

        for (int i = 0; i < CHILD_COUNT; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);

            LayoutParams layoutParams = child.getLayoutParams();
            layoutParams.width = diameter;
            layoutParams.height = diameter;
        }

        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
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

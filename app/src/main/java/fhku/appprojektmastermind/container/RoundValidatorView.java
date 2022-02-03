package fhku.appprojektmastermind.container;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public class RoundValidatorView extends ColorListView {

    public RoundValidatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int CHILD_COUNT = getChildCount();

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        //TODO: overthink spacing, sizing and padding calculations (just copied whole onMeasure from ColorGuessView)
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
}

package fhku.appprojektmastermind;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

public class ColorRepertoireView extends ViewGroup {

    private ColorRepertoire colorRepertoire;

    private final int DEFAULT_DIAMETER = 50;
    private int single_spacing;
    private int diameter;
    private int padding_top;

    public ColorRepertoireView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void initColorRepertoire() {
        for (ColorBall colorBall : colorRepertoire.getColorBalls()) {
            ColorBallView newColorBallView = new ColorBallView(getContext());
            newColorBallView.setColorBall(colorBall);
            this.addView(newColorBallView);
        }
    }

    public void setColorRepertoire(ColorRepertoire colorRepertoire) {
        this.colorRepertoire = colorRepertoire;
        initColorRepertoire();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int CHILD_COUNT = getChildCount();

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        //TODO: overthink spacing, sizing and padding calculations
        int spacing = width / 5;
        single_spacing = spacing / (CHILD_COUNT + 1);

        int availableWidth = width - spacing;
        diameter = (CHILD_COUNT == 0 ? DEFAULT_DIAMETER : availableWidth / CHILD_COUNT); //TODO avoid divide by zero

        padding_top = height / 5;

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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("MMIND", "touching color repertoire");
        return super.onTouchEvent(event);
    }
}

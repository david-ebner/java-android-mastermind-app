package fhku.appprojektmastermind.container;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import fhku.appprojektmastermind.color.ColorBall;
import fhku.appprojektmastermind.color.ColorBallView;

public class ColorListView extends ViewGroup {
    protected ColorList colorList;

    public static final double PADDING_LEFT_RIGHT_MINIMUM_FACTOR = .1;
    public static final double PADDING_TOP_BOTTOM_MINIMUM_FACTOR = .15;

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
        int childCount = getChildCount();

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        if (childCount != 0) {
            double widthPerChild = (double) width / childCount;

            int diameter = calculateMaximumDiameter((double) height, widthPerChild);

            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                measureChild(child, widthMeasureSpec, heightMeasureSpec);

                LayoutParams layoutParams = child.getLayoutParams();
                layoutParams.width = diameter;
                layoutParams.height = diameter;
            }
        }

        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    private int calculateMaximumDiameter(double height, double width) {
        double maxChildWidth = width / (1 +  2 * PADDING_LEFT_RIGHT_MINIMUM_FACTOR);
        double maxChildHeight = height / (1 + 2 * PADDING_TOP_BOTTOM_MINIMUM_FACTOR);

        return (int) Math.min(maxChildWidth, maxChildHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        if (childCount == 0) return;

        double paddedChildWidth = (double) getWidth() / childCount;
        int childDiameter = getChildAt(0).getLayoutParams().width;
        double singlePadding = (paddedChildWidth - childDiameter) / 2;
        int topPadding = (getHeight() - childDiameter) / 2;

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            int individualStartLeft = (int) (singlePadding + paddedChildWidth * i);
            child.layout(individualStartLeft, topPadding, individualStartLeft + childDiameter, topPadding + childDiameter);
        }
    }
}
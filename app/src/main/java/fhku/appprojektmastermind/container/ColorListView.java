package fhku.appprojektmastermind.container;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import fhku.appprojektmastermind.color.ColorBall;
import fhku.appprojektmastermind.color.ColorBallView;

public class ColorListView extends ViewGroup {
    protected ColorList colorList;

    protected int ROWS = 1;
    protected double HORIZONTAL_PADDING_FACTOR = .3;
    protected double VERTICAL_PADDING_FACTOR = .2;
    protected double START_PADDING_FACTOR = 2 * HORIZONTAL_PADDING_FACTOR;
    protected double TOP_PADDING_FACTOR = VERTICAL_PADDING_FACTOR;

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
            double maxWidthPerChild = calculateChildWidth(calculateMaxChildrenPerRow(childCount, ROWS), width);
            double maxHeightPerChild = calculateChildHeight(ROWS, height);

            int diameter = (int) Math.min(maxWidthPerChild, maxHeightPerChild);

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

    private double calculateChildWidth(int childCount, int totalWidth) {
        double divisor = (2 * START_PADDING_FACTOR // * childWidth = padding before first and after last child
                        + childCount // * childWidth = space for all children
                        + (childCount - 1) * HORIZONTAL_PADDING_FACTOR); // * childWidth = paddings between children
        return totalWidth / divisor;
    }

    private double calculateChildHeight(int rows, int totalHeight) {
        double divisor = (2 * TOP_PADDING_FACTOR // * childHeight = padding before first and after last line
                        + rows // * childHeight = space for all children
                        + (rows - 1) * VERTICAL_PADDING_FACTOR); // * childHeight = padding between all rows
        return totalHeight / divisor;
    }

    private int calculateMaxChildrenPerRow(int childCount, int rows) {
        return (int) Math.ceil((float) childCount / rows);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        if (childCount == 0) return;

        int childDiameter = getChildAt(0).getLayoutParams().width;
        int childrenInPreviousRows = 0;

        for (int row = 1; row <= ROWS; row++) {
            int childrenLeft = childCount - childrenInPreviousRows;
            int rowsLeft = ROWS - (row - 1);
            int childrenInRow = calculateMaxChildrenPerRow(childrenLeft, rowsLeft);

            double previousRowsOffset = childDiameter * (1 + VERTICAL_PADDING_FACTOR) * (row - 1);
            int topPadding = (int) (calculateRemainingTopPadding(childDiameter, getHeight()) + previousRowsOffset);

            double startPadding = calculateRemainingStartPadding(childDiameter, childrenInRow, getWidth());

            for (int i = 0; i < childrenInRow; i++) {
                View child = getChildAt(i + childrenInPreviousRows);
                double previousChildrenOffset = childDiameter * (1 + HORIZONTAL_PADDING_FACTOR) * i;
                int individualStartLeft = (int) (startPadding + previousChildrenOffset);
                child.layout(individualStartLeft, topPadding, individualStartLeft + childDiameter, topPadding + childDiameter);
            }
            childrenInPreviousRows += childrenInRow;
        }
    }

    private double calculateRemainingTopPadding(double childHeight, int totalHeight) {
        double contentHeight = childHeight * (ROWS + VERTICAL_PADDING_FACTOR * (ROWS - 1));
        return (totalHeight - contentHeight) / 2;
    }

    private double calculateRemainingStartPadding(double childWidth, int childrenPerRow, int totalWidth) {
        double contentWidth = childWidth * (childrenPerRow + HORIZONTAL_PADDING_FACTOR * (childrenPerRow - 1));
        return (totalWidth - contentWidth) / 2;
    }
}
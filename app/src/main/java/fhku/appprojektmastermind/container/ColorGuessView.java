package fhku.appprojektmastermind.container;

import android.content.Context;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import fhku.appprojektmastermind.color.ColorBall;
import fhku.appprojektmastermind.color.ColorBallView;

public class ColorGuessView extends ViewGroup {

    //TODO: make ColorGuessView implement View.OnTouchListener for dragging already placed ColorBalls

    private ColorGuess colorGuess;

    private final int DEFAULT_DIAMETER = 50;
    private int single_spacing;
    private int diameter;
    private int padding_top;

    public ColorGuessView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void initColorBallViews() {
        this.removeAllViews();

        if (colorGuess.getColorBalls().isEmpty()) {
            return;
        }
        for (ColorBall colorBall : colorGuess.getColorBalls()) {
            ColorBallView newColorBallView = new ColorBallView(getContext());
            newColorBallView.setColorBall(colorBall);
            this.addView(newColorBallView);
        }
    }

    private void makeColorBallViewsDragTargets() {
        for (int index = 0; index < this.getChildCount(); index++) {
            this.getChildAt(index).setOnDragListener(new ColorGuessDragListener());
        }
    }

    public void setGrayedOut(boolean grayedOut) {
        if (grayedOut) {
            this.setAlpha(.5f);
        } else {
            this.setAlpha(1f);
        }
    }

    public void setColorGuess(ColorGuess colorGuess) {
        this.colorGuess = colorGuess;
        initColorBallViews();

        if (this.colorGuess.isDone()) {
            setGrayedOut(false);
        } else if (this.colorGuess.isActive()) {
            makeColorBallViewsDragTargets();
            setGrayedOut(false);
        } else {
            setGrayedOut(true);
        }
    }

    public ColorGuess getColorGuess() {
        return colorGuess;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int CHILD_COUNT = getChildCount();

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        //TODO: overthink spacing, sizing and padding calculations
        int spacing = width / 5;
        single_spacing = spacing / (CHILD_COUNT + 1);

        padding_top = height / 10;

        int availableWidth = width - spacing;
        diameter = (CHILD_COUNT == 0 ?
                DEFAULT_DIAMETER : availableWidth / CHILD_COUNT); //TODO avoid divide by zero
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
                    padding_top + diameter);
        }
    }

    private static class ColorGuessDragListener implements OnDragListener {
        @Override
        public boolean onDrag(View dragReceiverView, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                case DragEvent.ACTION_DRAG_EXITED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    System.out.println("MMIND drag entered: " + dragReceiverView.toString());
                    break;
                case DragEvent.ACTION_DROP:
                    ColorBallView draggedView = (ColorBallView) event.getLocalState();
                    System.out.println("MMIND dragged view: " + draggedView.toString() +
                            " dropped in " + dragReceiverView.toString());

                    ColorRepertoireView owner = (ColorRepertoireView) draggedView.getParent();
//                    owner.removeView(draggedView);

                    ColorBallView receiver = (ColorBallView) dragReceiverView;
                    receiver.getColorBall().setColor(draggedView.getColorBall().getColorInt());
                    receiver.setColorBall(draggedView.getColorBall());
                    break;
            }
            return true;
        }
    }
}

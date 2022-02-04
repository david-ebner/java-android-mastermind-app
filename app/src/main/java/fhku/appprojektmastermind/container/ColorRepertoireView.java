package fhku.appprojektmastermind.container;

import android.content.ClipData;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class ColorRepertoireView extends ColorListView implements View.OnTouchListener {

    public ColorRepertoireView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
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
        diameter = (CHILD_COUNT == 0 ? DEFAULT_DIAMETER : availableWidth / CHILD_COUNT); // avoiding division by zero

        padding_top = height / 5;

        for (int i = 0; i < CHILD_COUNT; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);

            LayoutParams layoutParams = child.getLayoutParams();
            layoutParams.width = diameter;
            layoutParams.height = diameter;
            child.setOnTouchListener(this);
        }

        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            ClipData data = ClipData.newPlainText("", "");
            DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data, shadowBuilder, v, 0);
//            v.setVisibility(View.INVISIBLE);
            return true;
        } else {
            return false;
        }
    }
}

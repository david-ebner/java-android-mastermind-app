package fhku.appprojektmastermind.container;

import android.content.Context;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.View;

import androidx.annotation.Nullable;

import fhku.appprojektmastermind.color.ColorBallView;

public class ColorGuessView extends ColorListView {

    public ColorGuessView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
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

    @Override
    public void setColorList(ColorList colorList) {
        super.setColorList(colorList);
        formatAccordingToState();
    }

    private void formatAccordingToState() {
        if (this.colorList.isDone()) {
            setGrayedOut(false);
        } else if (this.colorList.isModifiable()) {
            makeColorBallViewsDragTargets();
            setGrayedOut(false);
        } else {
            setGrayedOut(true);
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
                    break;
                case DragEvent.ACTION_DROP:
                    ColorBallView draggedView = (ColorBallView) event.getLocalState();

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

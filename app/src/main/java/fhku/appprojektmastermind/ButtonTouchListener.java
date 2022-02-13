package fhku.appprojektmastermind;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;

public class ButtonTouchListener implements View.OnTouchListener {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                v.startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.scale_down));
                break;
            case MotionEvent.ACTION_UP:
                v.startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.scale_up));
                break;
        }
        return false;
    }
}

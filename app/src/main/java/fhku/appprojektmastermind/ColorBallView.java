package fhku.appprojektmastermind;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class ColorBallView extends View {
    private ColorBall colorBall;

    public ColorBallView(Context context) {
        super(context);

//        this.setBackgroundColor(0xffaaaaaa); // this was just for testing
    }

    public void setColorBall(ColorBall colorBall) {
        this.colorBall = colorBall;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("MMIND", "am i drawn?");
        Drawable drawable;
        if (colorBall.isEmpty()) {
            drawable = getResources().getDrawable(R.drawable.color_ball_empty);
        } else {
            drawable = getResources().getDrawable(R.drawable.color_ball);
            drawable.setTint(this.colorBall.getColorInt());
        }
        drawable.setBounds(0, 0,
                this.getLayoutParams().width,
                this.getLayoutParams().height);
        drawable.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("MMIND", "touching ColorBall " + this.toString());
        return super.onTouchEvent(event);
    }
}

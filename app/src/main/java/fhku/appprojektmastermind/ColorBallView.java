package fhku.appprojektmastermind;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

public class ColorBallView extends View {
    private ColorBall colorBall;

    public ColorBallView(Context context) {
        super(context);
    }

    public void setColorBall(ColorBall colorBall) {
        this.colorBall = colorBall;
        invalidate();
    }

    public ColorBall getColorBall() {
        return colorBall;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
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
}

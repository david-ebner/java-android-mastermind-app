package fhku.appprojektmastermind.color;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
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

        float radius = (float) getWidth() / 2;

        if (colorBall.isEmpty()) {
            float strokeWidth = radius / 8;
            canvas.drawCircle(radius, radius, radius - strokeWidth/2, emptyPaint(strokeWidth));
        } else {
            canvas.drawCircle(radius, radius, radius, colorFilledPaint(this.colorBall.getColorInt()));
        }
    }

    private Paint colorFilledPaint(int color) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        return paint;
    }

    private Paint emptyPaint(float strokeWidth) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(Color.BLACK);
        return paint;
    }

}

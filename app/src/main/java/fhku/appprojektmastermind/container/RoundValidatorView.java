package fhku.appprojektmastermind.container;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public class RoundValidatorView extends ColorListView {
    public RoundValidatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.ROWS = 2;
        this.VERTICAL_PADDING_FACTOR = .2;
        this.START_PADDING_FACTOR = 2 * HORIZONTAL_PADDING_FACTOR;
        this.TOP_PADDING_FACTOR = VERTICAL_PADDING_FACTOR;
    }
}
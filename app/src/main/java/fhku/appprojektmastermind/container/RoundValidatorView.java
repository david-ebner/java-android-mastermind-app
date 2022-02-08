package fhku.appprojektmastermind.container;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public class RoundValidatorView extends ColorListView {
    public RoundValidatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.ROWS = 2;
    }
}

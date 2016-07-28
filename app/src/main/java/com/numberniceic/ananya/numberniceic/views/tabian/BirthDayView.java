package com.numberniceic.ananya.numberniceic.views.tabian;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.numberniceic.ananya.numberniceic.R;

/**
 * Created by o_ye on 7/23/2016.
 */

public class BirthDayView extends FrameLayout {

    private TextView tvDay;


    public BirthDayView(Context context) {
        super(context);
        initInflate();
        initInstance();
    }

    public BirthDayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstance();
    }

    public BirthDayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstance();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BirthDayView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstance();
    }

    public void setDay(String txt){
        tvDay.setText(txt);
    }

    private void initInstance() {
        tvDay = (TextView) findViewById(R.id.tvBirthDay);

    }

    private void initInflate() {
            inflate(getContext(), R.layout.layout_birth_day, this);

    }
}

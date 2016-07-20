package com.numberniceic.ananya.numberniceic.views.telephone.miracle;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.numberniceic.ananya.numberniceic.R;

/**
 * Created by o_ye on 7/6/2016.
 */

public class MiracleTitleD extends FrameLayout {
    public MiracleTitleD(Context context) {
        super(context);

        initInflate();
        initInstance();

    }

    public MiracleTitleD(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstance();

    }

    public MiracleTitleD(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstance();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MiracleTitleD(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstance();

    }

    private void initInstance() {

    }

    private void initInflate() {
        inflate(getContext(), R.layout.layout_miracle_title_d, this);
    }


}

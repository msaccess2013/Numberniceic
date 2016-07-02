package com.numberniceic.ananya.numberniceic.views.telephone;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.numberniceic.ananya.numberniceic.R;

/**
 * Created by o_ye on 7/3/2016.
 */

public class PairDang extends FrameLayout {
    private TextView tvPairA;
    private TextView tvPairB;

    public PairDang(Context context) {
        super(context);
        innitInflate();
        innitInstance();

    }

    public PairDang(Context context, AttributeSet attrs) {
        super(context, attrs);
        innitInflate();
        innitInstance();

    }

    public PairDang(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        innitInflate();
        innitInstance();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PairDang(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        innitInflate();
        innitInstance();

    }

    private void innitInstance() {
        tvPairA = (TextView) findViewById(R.id.pairDangA);
        tvPairB = (TextView) findViewById(R.id.pairDangB);
    }

    private void innitInflate() {
        inflate(getContext(), R.layout.layout_pair_dang, this);
    }

    public void setPairA(String txt) {
        tvPairA.setText(txt);
    }

    public void setPairB(String txt) {
        tvPairB.setText(txt);
    }

}

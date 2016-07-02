package com.numberniceic.ananya.numberniceic.views.telephone;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.numberniceic.ananya.numberniceic.R;


public class PairPhoneNumber extends FrameLayout{
    RelativeLayout rlPairNumber;
    TextView txtPairNumber;

    public PairPhoneNumber(Context context) {
        super(context);

        initInfate();
        initInstance();

    }

    public PairPhoneNumber(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInfate();
        initInstance();
    }

    public PairPhoneNumber(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInfate();
        initInstance();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PairPhoneNumber(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInfate();
        initInstance();

    }

    private void initInstance() {
        rlPairNumber = (RelativeLayout) findViewById(R.id.rlPairNumber);
        txtPairNumber = (TextView) findViewById(R.id.txtPairNumber);

    }

    private void initInfate() {
        inflate(getContext(), R.layout.layout_pair_phone_number,this);
    }

    public void setBgColor(String color){
        rlPairNumber.setBackgroundColor(Color.parseColor(color));
    }
    public void setTxtPairNumber(String txt){
        txtPairNumber.setText(txt);
    }
}

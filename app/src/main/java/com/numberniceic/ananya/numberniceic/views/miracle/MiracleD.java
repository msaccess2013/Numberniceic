package com.numberniceic.ananya.numberniceic.views.miracle;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.numberniceic.ananya.numberniceic.R;


public class MiracleD extends FrameLayout {

    TextView pairNumber;
    TextView percent;
    TextView description;


    public MiracleD(Context context) {
        super(context);
        initInflate();
        initInstance();
    }

    public MiracleD(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstance();
    }

    public MiracleD(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstance();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MiracleD(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstance();
    }

    private void initInstance() {
        pairNumber = (TextView) findViewById(R.id.tvPairD);
        percent = (TextView) findViewById(R.id.tvPercentD);
        description = (TextView) findViewById(R.id.tvDescription);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.layout_miracle_d,this);
    }

    public void setPairNumber(String pairNumber){

        this.pairNumber.setText(pairNumber);

    }

    public void setPercent(String percent){
        this.percent.setText(percent);
    }
    public void setDescription(String description){
        this.description.setText(description);
    }
}

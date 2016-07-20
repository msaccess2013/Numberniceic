package com.numberniceic.ananya.numberniceic.views.telephone.miracle;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.numberniceic.ananya.numberniceic.R;


public class MiracleHead extends FrameLayout {

    TextView tvScrollD, tvScrollR;
    TextView tvPercentD, tvPercentR;


    public MiracleHead(Context context) {
        super(context);
        initInflate();
        initInstance();
    }

    public MiracleHead(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstance();
    }

    public MiracleHead(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstance();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MiracleHead(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstance();
    }

    private void initInstance() {
        tvScrollD = (TextView) findViewById(R.id.tvScrollD);
        tvScrollR = (TextView) findViewById(R.id.tvScrollR);
        tvPercentD = (TextView) findViewById(R.id.tvPercentD);
        tvPercentR = (TextView) findViewById(R.id.tvPercentR);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.layout_miracle_head, this);
    }

    public void setTvScrollD(String scrollD){
        this.tvScrollD.setText(scrollD);
    }

    public void setTvScrollR(String scrollR) {
        this.tvScrollR.setText(scrollR);
    }

    public void setTvPercentD(String percentD){
        this.tvPercentD.setText(percentD);
    }

    public void setTvPercentR(String percentR) {
        this.tvPercentR.setText(percentR);
    }
}

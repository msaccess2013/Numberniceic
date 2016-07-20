package com.numberniceic.ananya.numberniceic.views.name;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.numberniceic.ananya.numberniceic.R;


public class NameMiracleRView extends FrameLayout {

    private TextView tvPairR;
    private TextView tvDescription;
    private TextView tvDetial;

    public NameMiracleRView(Context context) {
        super(context);
        initInflate();
        intitInstance();
    }

    public NameMiracleRView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        intitInstance();
    }

    public NameMiracleRView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        intitInstance();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public NameMiracleRView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        intitInstance();
    }


    public void setTvPairR(String txt){
        tvPairR.setText(txt);
    }
    public void setTvDescription(String txt){
        tvDescription.setText(txt);
    }
    public void setTvDetial(String txt){
        tvDetial.setText(txt);
    }

    private void intitInstance() {

        tvPairR = (TextView) findViewById(R.id.tvPairR);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        tvDetial = (TextView) findViewById(R.id.tvDetail);

    }

    private void initInflate() {
        inflate(getContext(), R.layout.layout_miracle_name_r, this);
    }


}

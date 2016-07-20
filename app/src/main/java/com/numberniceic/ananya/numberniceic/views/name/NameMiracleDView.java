package com.numberniceic.ananya.numberniceic.views.name;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.numberniceic.ananya.numberniceic.R;

/**
 * Created by o_ye on 7/18/2016.
 */

public class NameMiracleDView extends FrameLayout {

    private TextView tvPairD;
    private TextView tvDescription;
    private TextView tvDetial;

    public NameMiracleDView(Context context) {
        super(context);
        initInflate();
        intitInstance();
    }

    public NameMiracleDView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        intitInstance();
    }

    public NameMiracleDView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        intitInstance();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public NameMiracleDView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        intitInstance();
    }


    public void setTvPairD(String txt){
        tvPairD.setText(txt);
    }
    public void setTvDescription(String txt){
        tvDescription.setText(txt);
    }
    public void setTvDetial(String txt){
        tvDetial.setText(txt);
    }

    private void intitInstance() {

        tvPairD = (TextView) findViewById(R.id.tvPairD);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        tvDetial = (TextView) findViewById(R.id.tvDetail);

    }

    private void initInflate() {
        inflate(getContext(), R.layout.layout_miracle_name_d, this);
    }


}

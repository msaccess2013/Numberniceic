package com.numberniceic.ananya.numberniceic.views.tabian;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.numberniceic.ananya.numberniceic.R;

/**
 * Created by o_ye on 7/22/2016.
 */

public class ProviceView extends FrameLayout {

    private TextView tvProvice;

    public ProviceView(Context context) {
        super(context);

        initInflate();
        initInstance();
    }

    public ProviceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstance();
    }

    public ProviceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstance();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ProviceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstance();
    }

    private void initInstance() {
            tvProvice = (TextView) findViewById(R.id.tvProvineOnLayout);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.layout_tabian_proview, this);
    }


    public void setProvinceText(String txt){
        tvProvice.setText(txt);
    }

}

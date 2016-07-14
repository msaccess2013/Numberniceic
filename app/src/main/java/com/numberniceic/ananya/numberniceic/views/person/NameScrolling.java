package com.numberniceic.ananya.numberniceic.views.person;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by o_ye on 7/11/2016.
 */

public class NameScrolling extends NestedScrollView {


    private boolean enableScrolling = true;

    private void isEnableScrolling(boolean enableScrolling){


        this.enableScrolling = enableScrolling;

    }

    public void setEnableScrolling(boolean enableScrolling){

     this.enableScrolling = enableScrolling;
    }


    public NameScrolling(Context context) {
        super(context);
    }

    public NameScrolling(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NameScrolling(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (enableScrolling){
            return super.onInterceptTouchEvent(ev);
        }else {
            return false;
        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (enableScrolling){
            return super.onTouchEvent(ev);
        }else{
            return false;
        }
    }
}

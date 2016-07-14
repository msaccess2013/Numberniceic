package com.numberniceic.ananya.numberniceic.pojo;

/**
 * Created by o_ye on 7/13/2016.
 */

public class LekKalakinee {

    private String birthDay;
    private char cKalakinee;

    public LekKalakinee(String birthDaychar, char cKalakinee) {

        this.cKalakinee = cKalakinee;
        this.birthDay = birthDaychar;
    }

    public String getBirthDay() {
        return birthDay;
    }


    public char getcKalakinee() {
        return cKalakinee;
    }



}

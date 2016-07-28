package com.numberniceic.ananya.numberniceic.pojo.tabain;

/**
 * Created by o_ye on 7/23/2016.
 */

public class BirthDay {
    private int idDay;
    private String enDay;
    private String thDay;

    public int getIdDay() {
        return idDay;
    }

    public String getEnDay() {
        return enDay;
    }

    public String getThDay() {
        return thDay;
    }

    public BirthDay(int idDay, String enDay, String thDay) {

        this.idDay = idDay;
        this.enDay = enDay;
        this.thDay = thDay;
    }
}

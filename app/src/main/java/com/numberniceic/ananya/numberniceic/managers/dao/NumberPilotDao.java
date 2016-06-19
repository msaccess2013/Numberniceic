package com.numberniceic.ananya.numberniceic.managers.dao;

/**
 * Created by o_ye on 6/19/2016.
 */

public class NumberPilotDao {
    private String number;
    private String type;
    private Integer point;

    public NumberPilotDao(String number, String type, Integer point) {
        this.number = number;
        this.type = type;
        this.point = point;
    }

    public String getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

    public Integer getPoint() {
        return point;
    }

}

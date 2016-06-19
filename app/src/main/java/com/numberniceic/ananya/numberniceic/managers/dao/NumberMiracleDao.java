package com.numberniceic.ananya.numberniceic.managers.dao;

/**
 * Created by o_ye on 6/19/2016.
 */

public class NumberMiracleDao {
    private String number;
    private String type;
    private String title;
    private String detail;

    public String getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public NumberMiracleDao(String number, String type, String title, String detail) {

        this.number = number;
        this.type = type;
        this.title = title;
        this.detail = detail;
    }
}

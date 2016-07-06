package com.numberniceic.ananya.numberniceic.dao.phone;

/**
 * Created by o_ye on 7/5/2016.
 */

public class PhoneMiracleItemDao {
    private String pairNumber;
    private String pairType;
    private String pairPercent;
    private String miracleTitle;
    private String miracleDescription;


    public PhoneMiracleItemDao(String pairNumber, String pairType, String pairPercent, String miracleTitle, String miracleDescription) {
        this.pairNumber = pairNumber;
        this.pairType = pairType;
        this.pairPercent = pairPercent;
        this.miracleTitle = miracleTitle;
        this.miracleDescription = miracleDescription;

    }

    public String getPairNumber() {
        return pairNumber;
    }

    public String getPairType() {
        return pairType;
    }

    public String getPairPercent() {
        return pairPercent;
    }

    public String getMiracleTitle() {
        return miracleTitle;
    }

    public String getMiracleDescription() {
        return miracleDescription;
    }


}

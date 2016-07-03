package com.numberniceic.ananya.numberniceic.managers.telephone;

/**
 * Created by o_ye on 7/3/2016.
 */
public class SummaryScrollManager {


    private Integer scrollD;
    private Integer scrollR;
    private String percentD;
    private String percentR;


    private static SummaryScrollManager ourInstance;

    public static SummaryScrollManager getInstance() {

        if (ourInstance == null)
            ourInstance = new SummaryScrollManager();

        return ourInstance;
    }

    private SummaryScrollManager() {
    }
    public Integer getScrollD() {
        return scrollD;
    }

    public void setScrollD(Integer scrollD) {

        this.scrollD = scrollD;
    }

    public Integer getScrollR() {
        return scrollR;
    }

    public void setScrollR(Integer scrollR) {

        this.scrollR = scrollR;
    }

    public String getPercentD() {
        return percentD;
    }

    public void setPercentD(String percentD) {
        this.percentD = percentD;
    }

    public String getPercentR() {
        return percentR;
    }

    public void setPercentR(String percentR) {
        this.percentR = percentR;
    }
}

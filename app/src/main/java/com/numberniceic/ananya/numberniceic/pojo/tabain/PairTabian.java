package com.numberniceic.ananya.numberniceic.pojo.tabain;

/**
 * Created by o_ye on 7/21/2016.
 */

public class PairTabian {
    private String pair;
    private String type;
    private Integer point;

    public String getPair() {
        return pair;
    }

    public String getType() {
        return type;
    }

    public Integer getPoint() {
        return point;
    }

    public PairTabian(String pair, String type, Integer point) {

        this.pair = pair;
        this.type = type;
        this.point = point;
    }
}

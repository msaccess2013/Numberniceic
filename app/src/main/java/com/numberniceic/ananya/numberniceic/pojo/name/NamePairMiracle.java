package com.numberniceic.ananya.numberniceic.pojo.name;

/**
 * Created by o_ye on 7/18/2016.
 */

public class NamePairMiracle {

    private String pair;
    private String description;
    private String detial;
    private String type;


    public String getPair() {
        return pair;
    }

    public String getDescription() {
        return description;
    }

    public String getDetial() {
        return detial;
    }

    public String getType() {
        return type;
    }

    public NamePairMiracle(String pair, String description, String detial, String type) {

        this.pair = pair;
        this.description = description;
        this.detial = detial;
        this.type = type;
    }
}

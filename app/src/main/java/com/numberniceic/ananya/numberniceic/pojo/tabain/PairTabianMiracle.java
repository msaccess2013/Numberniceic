package com.numberniceic.ananya.numberniceic.pojo.tabain;

/**
 * Created by sandland on 8/6/2016.
 */

public class PairTabianMiracle {

    private String pair;
    private String type;

    private String tile;
    private String description;

    public PairTabianMiracle(String pair, String type, String tile, String description) {
        this.pair = pair;
        this.type = type;

        this.tile = tile;
        this.description = description;
    }

    public String getPair() {

        return pair;
    }

    public String getType() {
        return type;
    }



    public String getTile() {
        return tile;
    }

    public String getDescription() {
        return description;
    }
}

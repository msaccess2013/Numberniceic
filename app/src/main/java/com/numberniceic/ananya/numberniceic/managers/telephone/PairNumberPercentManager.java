package com.numberniceic.ananya.numberniceic.managers.telephone;

import com.numberniceic.ananya.numberniceic.pojo.PairNumberPercent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by o_ye on 7/4/2016.
 */
public class PairNumberPercentManager {

    private List<PairNumberPercent> pairNumberPercents = new ArrayList<>();

    private static PairNumberPercentManager ourInstance;

    public static PairNumberPercentManager getInstance() {


        if (ourInstance == null)
            ourInstance = new PairNumberPercentManager();

        return ourInstance;
    }


    public List<PairNumberPercent> getPairNumberPercents() {
        return pairNumberPercents;
    }

    public void setPairNumberPercents(List<PairNumberPercent> pairNumberPercents) {
        this.pairNumberPercents = pairNumberPercents;
    }

    private PairNumberPercentManager() {
    }
}

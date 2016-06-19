package com.numberniceic.ananya.numberniceic.managers.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by o_ye on 6/19/2016.
 */

public class NumberPilotCollectionDao {
    private List<NumberPilotDao> numberPilot = new ArrayList<>();

    public NumberPilotCollectionDao() {
    }

    public NumberPilotCollectionDao(List<NumberPilotDao> numberPilot) {

        this.numberPilot = numberPilot;
    }

    public List<NumberPilotDao> getNumberPilot() {

        return numberPilot;
    }

    public void setNumberPilot(List<NumberPilotDao> numberPilot) {
        this.numberPilot = numberPilot;
    }
}

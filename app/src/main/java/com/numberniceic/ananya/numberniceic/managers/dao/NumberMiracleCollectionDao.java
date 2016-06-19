package com.numberniceic.ananya.numberniceic.managers.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by o_ye on 6/19/2016.
 */

public class NumberMiracleCollectionDao {

    private List<NumberMiracleDao> miracleDaos = new ArrayList<>();

    public NumberMiracleCollectionDao(List<NumberMiracleDao> miracleDaos) {
        this.miracleDaos = miracleDaos;
    }

    public NumberMiracleCollectionDao() {
    }

    public List<NumberMiracleDao> getMiracleDaos() {
        return miracleDaos;
    }

    public void setMiracleDaos(List<NumberMiracleDao> miracleDaos) {
        this.miracleDaos = miracleDaos;
    }
}

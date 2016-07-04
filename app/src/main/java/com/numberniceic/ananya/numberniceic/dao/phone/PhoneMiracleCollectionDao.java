package com.numberniceic.ananya.numberniceic.dao.phone;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by o_ye on 7/5/2016.
 */

public class PhoneMiracleCollectionDao {

    private List<PhoneMiracleItemDao>  numberMiracleItemDaos = new ArrayList<>();

    public List<PhoneMiracleItemDao> getNumberMiracleItemDaos() {
        return numberMiracleItemDaos;
    }

    public void setNumberMiracleItemDaos(List<PhoneMiracleItemDao> numberMiracleItemDaos) {
        this.numberMiracleItemDaos = numberMiracleItemDaos;
    }
}

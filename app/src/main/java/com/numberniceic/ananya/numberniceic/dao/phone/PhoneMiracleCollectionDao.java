package com.numberniceic.ananya.numberniceic.dao.phone;

import java.util.ArrayList;
import java.util.List;


public class PhoneMiracleCollectionDao {

    private List<PhoneMiracleItemDao>  numberMiracleItemDaos = new ArrayList<>();

    public List<PhoneMiracleItemDao> getNumberMiracleItemDaos() {
        return numberMiracleItemDaos;
    }

    public void setNumberMiracleItemDaos(List<PhoneMiracleItemDao> numberMiracleItemDaos) {
        this.numberMiracleItemDaos = numberMiracleItemDaos;
    }
}

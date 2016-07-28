package com.numberniceic.ananya.numberniceic.pojo.tabain;

/**
 * Created by o_ye on 7/22/2016.
 */

public class Province {

    private String provinceName;
    private String zipCode;

    public String getProvinceName() {
        return provinceName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Province(String provinceName, String zipCode) {

        this.provinceName = provinceName;
        this.zipCode = zipCode;
    }
}

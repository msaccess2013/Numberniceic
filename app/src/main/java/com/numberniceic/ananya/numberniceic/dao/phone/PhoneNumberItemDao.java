package com.numberniceic.ananya.numberniceic.dao.phone;

/**
 * Created by o_ye on 6/16/2016.
 */
public class PhoneNumberItemDao {

    String phoneNumber;
    String type;
    Integer point;

    public PhoneNumberItemDao(String phoneNumber, String type, Integer point) {
        this.phoneNumber = phoneNumber;
        this.type = type;
        this.point = point;
    }

    public PhoneNumberItemDao() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }
}

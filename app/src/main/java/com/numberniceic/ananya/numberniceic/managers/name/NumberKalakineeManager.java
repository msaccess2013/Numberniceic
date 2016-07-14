package com.numberniceic.ananya.numberniceic.managers.name;
import android.util.Log;
import com.numberniceic.ananya.numberniceic.pojo.LekKalakinee;
import java.util.ArrayList;
import java.util.List;

public class NumberKalakineeManager {

    private String lekKalakini;
    private List<LekKalakinee> lekKalakineeList;

    public NumberKalakineeManager() {
        this.addLekKalakinee();
    }

    public String getLekKalakini() {
        return lekKalakini;
    }

    public void setLekKalakinee(char myChar, String dayBirth) {

        for (int i = 0; i < lekKalakineeList.size(); i++) {


            if (lekKalakineeList.get(i).getBirthDay().equals(dayBirth)) {


                char cBord = lekKalakineeList.get(i).getcKalakinee();

                if (myChar == cBord) {
                    this.lekKalakini = String.valueOf(cBord);

                }
            }
        }

    }


    private void addLekKalakinee() {

        List<LekKalakinee> tmList = new ArrayList<>();

        tmList.add(new LekKalakinee("Sunday", 'ศ'));
        tmList.add(new LekKalakinee("Sunday", 'ษ'));
        tmList.add(new LekKalakinee("Sunday", 'โ'));
        tmList.add(new LekKalakinee("Sunday", 'ส'));
        tmList.add(new LekKalakinee("Sunday", 'ห'));
        tmList.add(new LekKalakinee("Sunday", 'ฬ'));
        tmList.add(new LekKalakinee("Sunday", 'ฮ'));

        tmList.add(new LekKalakinee("Monday", 'ะ'));
        tmList.add(new LekKalakinee("Monday", '้'));
        tmList.add(new LekKalakinee("Monday", 'ื'));
        tmList.add(new LekKalakinee("Monday", '์'));
        tmList.add(new LekKalakinee("Monday", 'แ'));
        tmList.add(new LekKalakinee("Monday", '๊'));
        tmList.add(new LekKalakinee("Monday", 'ั'));
        tmList.add(new LekKalakinee("Monday", '็'));
        tmList.add(new LekKalakinee("Monday", 'า'));
        tmList.add(new LekKalakinee("Monday", 'ิ'));
        tmList.add(new LekKalakinee("Monday", 'ึ'));
        tmList.add(new LekKalakinee("Monday", 'ี'));
        tmList.add(new LekKalakinee("Monday", '่'));
        tmList.add(new LekKalakinee("Monday", 'ำ'));
        tmList.add(new LekKalakinee("Monday", 'ุ'));
        tmList.add(new LekKalakinee("Monday", 'ู'));
        tmList.add(new LekKalakinee("Monday", 'เ'));
        tmList.add(new LekKalakinee("Monday", 'ใ'));
        tmList.add(new LekKalakinee("Monday", 'ไ'));
        tmList.add(new LekKalakinee("Monday", 'โ'));

        tmList.add(new LekKalakinee("Tuesday", 'ก'));
        tmList.add(new LekKalakinee("Tuesday", 'ข'));
        tmList.add(new LekKalakinee("Tuesday", 'ค'));
        tmList.add(new LekKalakinee("Tuesday", 'ฆ'));
        tmList.add(new LekKalakinee("Tuesday", 'ง'));

        tmList.add(new LekKalakinee("WednesdayAm", 'จ'));
        tmList.add(new LekKalakinee("WednesdayAm", 'ฉ'));
        tmList.add(new LekKalakinee("WednesdayAm", 'ช'));
        tmList.add(new LekKalakinee("WednesdayAm", 'ซ'));
        tmList.add(new LekKalakinee("WednesdayAm", 'ฌ'));
        tmList.add(new LekKalakinee("WednesdayAm", 'ญ'));

        tmList.add(new LekKalakinee("WednesdayPm", 'บ'));
        tmList.add(new LekKalakinee("WednesdayPm", 'ป'));
        tmList.add(new LekKalakinee("WednesdayPm", 'ผ'));
        tmList.add(new LekKalakinee("WednesdayPm", 'ฝ'));
        tmList.add(new LekKalakinee("WednesdayPm", 'พ'));
        tmList.add(new LekKalakinee("WednesdayPm", 'ฟ'));
        tmList.add(new LekKalakinee("WednesdayPm", 'ภ'));
        tmList.add(new LekKalakinee("WednesdayPm", 'ม'));

        tmList.add(new LekKalakinee("Thursday", 'ด'));
        tmList.add(new LekKalakinee("Thursday", 'ต'));
        tmList.add(new LekKalakinee("Thursday", 'ถ'));
        tmList.add(new LekKalakinee("Thursday", 'ท'));
        tmList.add(new LekKalakinee("Thursday", 'ธ'));
        tmList.add(new LekKalakinee("Thursday", 'น'));

        tmList.add(new LekKalakinee("Friday", 'ย'));
        tmList.add(new LekKalakinee("Friday", 'ร'));
        tmList.add(new LekKalakinee("Friday", 'ล'));
        tmList.add(new LekKalakinee("Friday", 'ว'));

        tmList.add(new LekKalakinee("Saturday", 'ฎ'));
        tmList.add(new LekKalakinee("Saturday", 'ฏ'));
        tmList.add(new LekKalakinee("Saturday", 'ฐ'));
        tmList.add(new LekKalakinee("Saturday", 'ฑ'));
        tmList.add(new LekKalakinee("Saturday", 'ฒ'));
        tmList.add(new LekKalakinee("Saturday", 'ณ'));


        this.lekKalakineeList = tmList;


    }
}

package com.numberniceic.ananya.numberniceic.managers.name;

import com.numberniceic.ananya.numberniceic.pojo.LekSat;
import com.numberniceic.ananya.numberniceic.pojo.LekStar;

import java.util.ArrayList;
import java.util.List;

public class NumberStarManager {

    private String numberStar;
    private List<LekStar> lekStarList;

    public NumberStarManager() {

        addData();
    }


    public String getNumberStar() {
        return numberStar;
    }

    public void setNumberStar(char cName) {

        for (int i = 0; i < lekStarList.size(); i++) {


                for (int j = 0; j < lekStarList.get(i).getcStar().length(); j++) {
                    if (cName == lekStarList.get(i).getcStar().charAt(j)){
                        this.numberStar = lekStarList.get(i).getNumberStar();
                }



            }
        }



    }

    private void addData(){
        List<LekStar> tmList = new ArrayList<>();
        tmList.add(new LekStar("อะาิีุูเโ", "6"));
        tmList.add(new LekStar("กขคฆง", "15"));
        tmList.add(new LekStar("จฉชซฌญ", "8"));
        tmList.add(new LekStar("ฎฏฐฑฒณ", "17"));
        tmList.add(new LekStar("บปผฝพฟภม", "19"));
        tmList.add(new LekStar("ศษสหฬฮ", "21"));
        tmList.add(new LekStar("ดตถทธน", "10"));
        tmList.add(new LekStar("ยรลว", "12"));

        this.lekStarList = tmList;


    }
}

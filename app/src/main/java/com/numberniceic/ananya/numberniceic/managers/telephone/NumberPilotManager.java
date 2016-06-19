package com.numberniceic.ananya.numberniceic.managers.telephone;

import android.util.Log;

import com.numberniceic.ananya.numberniceic.managers.dao.NumberPilotCollectionDao;
import com.numberniceic.ananya.numberniceic.managers.dao.NumberPilotDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by o_ye on 6/19/2016.
 */

public class NumberPilotManager {

    private NumberPilotCollectionDao numberPilotCollectionDao;

    public NumberPilotManager() {

        addNumberPilotDao();
    }

    public void getNumber(){


        for (int i = 0; i < numberPilotCollectionDao.getNumberPilot().size(); i++) {
            String number = numberPilotCollectionDao.getNumberPilot().get(i).getNumber();
            Log.d("number", number);

        }

    }

    public String getType(String pair){

        String type = null;
        for (int i = 0; i < numberPilotCollectionDao.getNumberPilot().size(); i++) {
            if (numberPilotCollectionDao.getNumberPilot().get(i).getNumber().equals(pair)){
                type = numberPilotCollectionDao.getNumberPilot().get(i).getType();
            }
        }
        return type;
    }

    public Integer getPoint(String pair){

        Integer point = null;
        for (int i = 0; i < numberPilotCollectionDao.getNumberPilot().size(); i++) {
            if (numberPilotCollectionDao.getNumberPilot().get(i).getNumber().equals(pair)){
                point = numberPilotCollectionDao.getNumberPilot().get(i).getPoint();
            }
        }
        return point;
    }


    private void setNumberPilotCollectionDao(List<NumberPilotDao> pilotDaoList) {

        numberPilotCollectionDao = new NumberPilotCollectionDao();
        numberPilotCollectionDao.setNumberPilot(pilotDaoList);

    }



    private void addNumberPilotDao(){

        List<NumberPilotDao> numberPilotDaos = new ArrayList<>();

        numberPilotDaos.add(new NumberPilotDao(  "00","R10",-30));
        numberPilotDaos.add(new NumberPilotDao(  "01", "R10",-10));
        numberPilotDaos.add(new NumberPilotDao(  "02", "D8",8));
        numberPilotDaos.add(new NumberPilotDao(  "03", "R10",-10));
        numberPilotDaos.add(new NumberPilotDao(  "04", "D8",25));
        numberPilotDaos.add(new NumberPilotDao(  "05", "D8",25));
        numberPilotDaos.add(new NumberPilotDao(  "06", "D8",10));
        numberPilotDaos.add(new NumberPilotDao(  "07", "R10",-40));
        numberPilotDaos.add(new NumberPilotDao(  "08", "R5",-5));
        numberPilotDaos.add(new NumberPilotDao(  "09", "D10",30));
        numberPilotDaos.add(new NumberPilotDao(  "10", "R10",-30));
        numberPilotDaos.add(new NumberPilotDao(  "11", "R10",-60));
        numberPilotDaos.add(new NumberPilotDao(  "12", "R10",-50));
        numberPilotDaos.add(new NumberPilotDao(  "13", "R10",-70));
        numberPilotDaos.add(new NumberPilotDao(  "14", "D10",58));
        numberPilotDaos.add(new NumberPilotDao(  "15", "D10",65));
        numberPilotDaos.add(new NumberPilotDao(  "16", "R10",-50));
        numberPilotDaos.add(new NumberPilotDao(  "17", "R7",-35));
        numberPilotDaos.add(new NumberPilotDao(  "18", "R10",-50));
        numberPilotDaos.add(new NumberPilotDao(  "19", "D8",14));
        numberPilotDaos.add(new NumberPilotDao(  "20", "R7",-35));
        numberPilotDaos.add(new NumberPilotDao(  "21", "R10",-60));
        numberPilotDaos.add(new NumberPilotDao(  "22", "R7",-25));
        numberPilotDaos.add(new NumberPilotDao(  "23", "D8",20));
        numberPilotDaos.add(new NumberPilotDao(  "24", "D10",70));
        numberPilotDaos.add(new NumberPilotDao(  "25", "R7",-25));
        numberPilotDaos.add(new NumberPilotDao(  "26", "R5",-20));
        numberPilotDaos.add(new NumberPilotDao(  "27", "R10",-65));
        numberPilotDaos.add(new NumberPilotDao(  "28", "R5",-20));
        numberPilotDaos.add(new NumberPilotDao(  "29", "R10",-60));
        numberPilotDaos.add(new NumberPilotDao(  "30", "R10",-70));
        numberPilotDaos.add(new NumberPilotDao(  "31", "R10",-70));
        numberPilotDaos.add(new NumberPilotDao(  "32", "R7",-35));
        numberPilotDaos.add(new NumberPilotDao(  "33", "R10",-70));
        numberPilotDaos.add(new NumberPilotDao(  "34", "R10",-60));
        numberPilotDaos.add(new NumberPilotDao(  "35", "R5",-15));
        numberPilotDaos.add(new NumberPilotDao(  "36", "D10",65));
        numberPilotDaos.add(new NumberPilotDao(  "37", "R10",-80));
        numberPilotDaos.add(new NumberPilotDao(  "38", "R10",-50));
        numberPilotDaos.add(new NumberPilotDao(  "39", "R10",-70));
        numberPilotDaos.add(new NumberPilotDao(  "40", "R5",-20));
        numberPilotDaos.add(new NumberPilotDao(  "41", "D10",65));
        numberPilotDaos.add(new NumberPilotDao(  "42", "D10",65));
        numberPilotDaos.add(new NumberPilotDao(  "43", "R10",-59));
        numberPilotDaos.add(new NumberPilotDao(  "44", "D10",60));
        numberPilotDaos.add(new NumberPilotDao(  "45", "D10",75));
        numberPilotDaos.add(new NumberPilotDao(  "46", "D10",65));
        numberPilotDaos.add(new NumberPilotDao(  "47", "R10",-35));
        numberPilotDaos.add(new NumberPilotDao(  "48", "R10",-75));
        numberPilotDaos.add(new NumberPilotDao(  "49", "R10",-25));
        numberPilotDaos.add(new NumberPilotDao(  "50", "D10",55));
        numberPilotDaos.add(new NumberPilotDao(  "51", "D10",65));
        numberPilotDaos.add(new NumberPilotDao(  "52", "R5",-10));
        numberPilotDaos.add(new NumberPilotDao(  "53", "R5",-10));
        numberPilotDaos.add(new NumberPilotDao(  "54", "D10",65));
        numberPilotDaos.add(new NumberPilotDao(  "55", "D10",60));
        numberPilotDaos.add(new NumberPilotDao(  "56", "D8",60));
        numberPilotDaos.add(new NumberPilotDao(  "57", "R10",-35));
        numberPilotDaos.add(new NumberPilotDao(  "58", "R10",-35));
        numberPilotDaos.add(new NumberPilotDao(  "59", "D10",70));
        numberPilotDaos.add(new NumberPilotDao(  "60", "D5",1));
        numberPilotDaos.add(new NumberPilotDao(  "61", "R10",-60));
        numberPilotDaos.add(new NumberPilotDao(  "62", "R5",-25));
        numberPilotDaos.add(new NumberPilotDao(  "63", "D10",70));
        numberPilotDaos.add(new NumberPilotDao(  "64", "D8",60));
        numberPilotDaos.add(new NumberPilotDao(  "65", "D8",65));
        numberPilotDaos.add(new NumberPilotDao(  "66", "R10",-55));
        numberPilotDaos.add(new NumberPilotDao(  "67", "R10",-75));
        numberPilotDaos.add(new NumberPilotDao(  "68", "R7",-25));
        numberPilotDaos.add(new NumberPilotDao(  "69", "D5",1));
        numberPilotDaos.add(new NumberPilotDao(  "70", "R10",-75));
        numberPilotDaos.add(new NumberPilotDao(  "71", "R7",-30));
        numberPilotDaos.add(new NumberPilotDao(  "72", "R10",-55));
        numberPilotDaos.add(new NumberPilotDao(  "73", "R10",-80));
        numberPilotDaos.add(new NumberPilotDao(  "74", "R7",-20));
        numberPilotDaos.add(new NumberPilotDao(  "75", "R10",-35));
        numberPilotDaos.add(new NumberPilotDao(  "76", "R10",-70));
        numberPilotDaos.add(new NumberPilotDao(  "77", "R10",-70));
        numberPilotDaos.add(new NumberPilotDao(  "78", "R7",-25));
        numberPilotDaos.add(new NumberPilotDao(  "79", "D8",25));
        numberPilotDaos.add(new NumberPilotDao(  "80", "R10",-65));
        numberPilotDaos.add(new NumberPilotDao(  "81", "R10",-45));
        numberPilotDaos.add(new NumberPilotDao(  "82", "R7",-20));
        numberPilotDaos.add(new NumberPilotDao(  "83", "R10",-65));
        numberPilotDaos.add(new NumberPilotDao(  "84", "R10",-75));
        numberPilotDaos.add(new NumberPilotDao(  "85", "R10",-25));
        numberPilotDaos.add(new NumberPilotDao(  "86", "R5",-10));
        numberPilotDaos.add(new NumberPilotDao(  "87", "R7",-25));
        numberPilotDaos.add(new NumberPilotDao(  "88", "R7",-75));
        numberPilotDaos.add(new NumberPilotDao(  "89", "R7",-35));
        numberPilotDaos.add(new NumberPilotDao(  "90", "D10",45));
        numberPilotDaos.add(new NumberPilotDao(  "91", "D8",14));
        numberPilotDaos.add(new NumberPilotDao(  "92", "R7",-15));
        numberPilotDaos.add(new NumberPilotDao(  "93", "R10",-45));
        numberPilotDaos.add(new NumberPilotDao(  "94", "R10",-15));
        numberPilotDaos.add(new NumberPilotDao(  "95", "D10",70));
        numberPilotDaos.add(new NumberPilotDao(  "96", "D5",1));
        numberPilotDaos.add(new NumberPilotDao(  "97", "D8",25));
        numberPilotDaos.add(new NumberPilotDao(  "98", "R10",-35));
        numberPilotDaos.add(new NumberPilotDao(  "99", "D8",60));
        numberPilotDaos.add(new NumberPilotDao(  "100", "D8",30));

        setNumberPilotCollectionDao(numberPilotDaos);

    }
}

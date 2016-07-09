package com.numberniceic.ananya.numberniceic.managers.name;

import com.numberniceic.ananya.numberniceic.pojo.LekSat;

import java.util.ArrayList;
import java.util.List;


public class NumberLekSatManager {

    private String lekSat;

    private List<LekSat> lekSats;

    public NumberLekSatManager() {

        addLekSat();
    }

    public String getLekSat() {
        return lekSat;
    }

    public void setLekSat(char myChar) {

        for (int i = 0; i < lekSats.size(); i++) {
            if (myChar == lekSats.get(i).getMyChar()){
                   this.lekSat = lekSats.get(i).getMyNumber();
            }
        }


    }

    private void addLekSat(){

        List<LekSat> temLeksat = new ArrayList<>();

        temLeksat.add(new LekSat('ก',"1"));
        temLeksat.add(new LekSat('ด',"1"));
        temLeksat.add(new LekSat('ถ',"1"));
        temLeksat.add(new LekSat('ท',"1"));
        temLeksat.add(new LekSat('ภ',"1"));
        temLeksat.add(new LekSat('ฤ',"1"));
        temLeksat.add(new LekSat('ฦ',"1"));
        temLeksat.add(new LekSat('า',"1"));
        temLeksat.add(new LekSat('ำ',"1"));
        temLeksat.add(new LekSat('ุ',"1"));
        temLeksat.add(new LekSat('่',"1"));
        temLeksat.add(new LekSat('ข',"2"));
        temLeksat.add(new LekSat('ง',"2"));
        temLeksat.add(new LekSat('ช',"2"));
        temLeksat.add(new LekSat('บ',"2"));
        temLeksat.add(new LekSat('ป',"2"));
        temLeksat.add(new LekSat('เ',"2"));
        temLeksat.add(new LekSat('แ',"2"));
        temLeksat.add(new LekSat('ู',"2"));
        temLeksat.add(new LekSat('้',"2"));
        temLeksat.add(new LekSat('ฆ',"3"));
        temLeksat.add(new LekSat('ต',"3"));
        temLeksat.add(new LekSat('ฑ',"3"));
        temLeksat.add(new LekSat('ฒ',"3"));
        temLeksat.add(new LekSat('๋',"3"));
        temLeksat.add(new LekSat('ค',"4"));
        temLeksat.add(new LekSat('ธ',"4"));
        temLeksat.add(new LekSat('ญ',"4"));
        temLeksat.add(new LekSat('ร',"4"));
        temLeksat.add(new LekSat('ษ',"4"));
        temLeksat.add(new LekSat('ะ',"4"));
        temLeksat.add(new LekSat('โ',"4"));
        temLeksat.add(new LekSat('ั',"4"));
        temLeksat.add(new LekSat('ิ',"4"));
        temLeksat.add(new LekSat('ฉ',"5"));
        temLeksat.add(new LekSat('ฌ',"5"));
        temLeksat.add(new LekSat('ฌ',"5"));
        temLeksat.add(new LekSat('ณ',"5"));
        temLeksat.add(new LekSat('น',"5"));
        temLeksat.add(new LekSat('ม',"5"));
        temLeksat.add(new LekSat('ห',"5"));
        temLeksat.add(new LekSat('ฎ',"5"));
        temLeksat.add(new LekSat('ฮ',"5"));
        temLeksat.add(new LekSat('ฬ',"5"));
        temLeksat.add(new LekSat('ึ',"5"));
        temLeksat.add(new LekSat('จ',"6"));
        temLeksat.add(new LekSat('ล',"6"));
        temLeksat.add(new LekSat('ว',"6"));
        temLeksat.add(new LekSat('อ',"6"));
        temLeksat.add(new LekSat('ใ',"6"));
        temLeksat.add(new LekSat('ซ',"7"));
        temLeksat.add(new LekSat('ศ',"7"));
        temLeksat.add(new LekSat('ส',"7"));
        temLeksat.add(new LekSat('๊',"7"));
        temLeksat.add(new LekSat('ี',"7"));
        temLeksat.add(new LekSat('ื',"7"));
        temLeksat.add(new LekSat('ผ',"8"));
        temLeksat.add(new LekSat('ฝ',"8"));
        temLeksat.add(new LekSat('พ',"8"));
        temLeksat.add(new LekSat('ฟ',"8"));
        temLeksat.add(new LekSat('ย',"8"));
        temLeksat.add(new LekSat('็',"8"));
        temLeksat.add(new LekSat('ฏ',"9"));
        temLeksat.add(new LekSat('ฐ',"9"));
        temLeksat.add(new LekSat('ไ',"9"));
        temLeksat.add(new LekSat('์',"9"));
        temLeksat.add(new LekSat('a',"1"));
        temLeksat.add(new LekSat('i',"1"));
        temLeksat.add(new LekSat('j',"1"));
        temLeksat.add(new LekSat('q',"1"));
        temLeksat.add(new LekSat('y',"1"));
        temLeksat.add(new LekSat('A',"1"));
        temLeksat.add(new LekSat('I',"1"));
        temLeksat.add(new LekSat('J',"1"));
        temLeksat.add(new LekSat('Q',"1"));
        temLeksat.add(new LekSat('Y',"1"));
        temLeksat.add(new LekSat('b',"2"));
        temLeksat.add(new LekSat('k',"2"));
        temLeksat.add(new LekSat('r',"2"));
        temLeksat.add(new LekSat('B',"2"));
        temLeksat.add(new LekSat('K',"2"));
        temLeksat.add(new LekSat('R',"2"));
        temLeksat.add(new LekSat('c',"3"));
        temLeksat.add(new LekSat('g',"3"));
        temLeksat.add(new LekSat('l',"3"));
        temLeksat.add(new LekSat('s',"3"));
        temLeksat.add(new LekSat('C',"3"));
        temLeksat.add(new LekSat('G',"3"));
        temLeksat.add(new LekSat('L',"3"));
        temLeksat.add(new LekSat('S',"3"));
        temLeksat.add(new LekSat('d',"4"));
        temLeksat.add(new LekSat('m',"4"));
        temLeksat.add(new LekSat('t',"4"));
        temLeksat.add(new LekSat('D',"4"));
        temLeksat.add(new LekSat('M',"4"));
        temLeksat.add(new LekSat('T',"4"));
        temLeksat.add(new LekSat('e',"5"));
        temLeksat.add(new LekSat('h',"5"));
        temLeksat.add(new LekSat('n',"5"));
        temLeksat.add(new LekSat('x',"5"));
        temLeksat.add(new LekSat('E',"5"));
        temLeksat.add(new LekSat('H',"5"));
        temLeksat.add(new LekSat('N',"5"));
        temLeksat.add(new LekSat('X',"5"));
        temLeksat.add(new LekSat('u',"6"));
        temLeksat.add(new LekSat('v',"6"));
        temLeksat.add(new LekSat('w',"6"));
        temLeksat.add(new LekSat('U',"6"));
        temLeksat.add(new LekSat('V',"6"));
        temLeksat.add(new LekSat('W',"6"));
        temLeksat.add(new LekSat('o',"7"));
        temLeksat.add(new LekSat('z',"7"));
        temLeksat.add(new LekSat('O',"7"));
        temLeksat.add(new LekSat('Z',"7"));
        temLeksat.add(new LekSat('f',"8"));
        temLeksat.add(new LekSat('p',"8"));
        temLeksat.add(new LekSat('F',"8"));
        temLeksat.add(new LekSat('P',"8"));

        this.lekSats = temLeksat;

    }

}

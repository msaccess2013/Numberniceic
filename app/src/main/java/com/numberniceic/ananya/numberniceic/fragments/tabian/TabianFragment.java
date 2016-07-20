package com.numberniceic.ananya.numberniceic.fragments.tabian;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.numberniceic.ananya.numberniceic.R;
import com.numberniceic.ananya.numberniceic.managers.NumberPilotManager;
import com.numberniceic.ananya.numberniceic.managers.name.NumberLekSatManager;
import com.numberniceic.ananya.numberniceic.pojo.tabain.PairTabian;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabianFragment extends Fragment {


    private EditText edPrimaryNumber, edSecondNumber;
    private String sPosition1;
    private String sPosition2;
    private String sPosition3;

    private String sPosition1s;
    private String sPosition2s;
    private String sPosition3s;
    private String sPosition4s;

    private Button btnCalTabian;

    private TextView tvPair1A, tvPair2A, tvPair3A;
    private TextView tvPair1B, tvPair2B, tvPair3B;
    private TextView tvSumM, tvSumL;


    private List<String> pairListA;
    private List<String> pairListB;

    private List<PairTabian> pairTabianListA;
    private List<PairTabian> pairTabianListB;



    public static TabianFragment newInstance() {

        Bundle args = new Bundle();

        TabianFragment fragment = new TabianFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tabian, container, false);

        initInstance(rootView);

        btnCalTabian.setOnClickListener(sumFront);


        return rootView;
    }

    /**********************
     * Method
     **********************/
    private void setFrontPosition(List<String> fNumber) {
        this.sPosition1 = null;
        this.sPosition2 = null;
        this.sPosition3 = null;


        NumberLekSatManager satManager = new NumberLekSatManager();
        int countPosition;
        if (fNumber.size() != 1 && fNumber.size() != 2 && fNumber.size() != 3)
            countPosition = 0;
        else
            countPosition = fNumber.size();

        switch (countPosition) {
            case 3:
                if (getStrNumber(fNumber.get(0)) == null) {
                    char position1 = fNumber.get(0).charAt(0);
                    satManager.setLekSat(position1);
                    this.sPosition1 = satManager.getLekSat();
                } else {
                    this.sPosition1 = fNumber.get(0);
                }

                if (getStrNumber(fNumber.get(1)) == null) {
                    char position2 = fNumber.get(1).charAt(0);
                    satManager.setLekSat(position2);
                    this.sPosition2 = satManager.getLekSat();
                } else {
                    this.sPosition2 = fNumber.get(1);
                }

                if (getStrNumber(fNumber.get(2)) == null) {
                    char position3 = fNumber.get(2).charAt(0);
                    satManager.setLekSat(position3);
                    this.sPosition3 = satManager.getLekSat();
                } else {
                    this.sPosition3 = fNumber.get(2);
                }
                break;

            case 2:
                if (getStrNumber(fNumber.get(0)) == null) {
                    char position1 = fNumber.get(0).charAt(0);
                    satManager.setLekSat(position1);
                    this.sPosition1 = satManager.getLekSat();
                } else {
                    this.sPosition1 = fNumber.get(0);
                }

                if (getStrNumber(fNumber.get(1)) == null) {
                    char position2 = fNumber.get(1).charAt(0);
                    satManager.setLekSat(position2);
                    this.sPosition2 = satManager.getLekSat();
                } else {
                    this.sPosition2 = fNumber.get(1);
                }
                break;
            case 1:
                if (getStrNumber(fNumber.get(0)) == null) {
                    char position1 = fNumber.get(0).charAt(0);
                    satManager.setLekSat(position1);
                    this.sPosition1 = satManager.getLekSat();
                } else {
                    this.sPosition1 = fNumber.get(0);
                }
                break;

            case 0:
                this.sPosition1 = null;
                this.sPosition2 = null;
                this.sPosition3 = null;
                break;

        }
    }

    private void setSecondPosition(List<String> sNumbers) {
        this.sPosition1s = null;
        this.sPosition2s = null;
        this.sPosition3s = null;
        this.sPosition4s = null;


        NumberLekSatManager satManager = new NumberLekSatManager();
        int countPosition;
        if (sNumbers.size() != 1 && sNumbers.size() != 2 && sNumbers.size() != 3 && sNumbers.size() != 4)
            countPosition = 0;
        else
            countPosition = sNumbers.size();

        switch (countPosition) {
            case 4:
                if (getStrNumber(sNumbers.get(0)) == null) {
                    char position1 = sNumbers.get(0).charAt(0);
                    satManager.setLekSat(position1);
                    this.sPosition1s = satManager.getLekSat();
                } else {
                    this.sPosition1s = sNumbers.get(0);
                }

                if (getStrNumber(sNumbers.get(1)) == null) {
                    char position2 = sNumbers.get(1).charAt(0);
                    satManager.setLekSat(position2);
                    this.sPosition2s = satManager.getLekSat();
                } else {
                    this.sPosition2s = sNumbers.get(1);
                }

                if (getStrNumber(sNumbers.get(2)) == null) {
                    char position3 = sNumbers.get(2).charAt(0);
                    satManager.setLekSat(position3);
                    this.sPosition3s = satManager.getLekSat();
                } else {
                    this.sPosition3s = sNumbers.get(2);
                }

                if (getStrNumber(sNumbers.get(3)) == null) {
                    char position4 = sNumbers.get(3).charAt(0);
                    satManager.setLekSat(position4);
                    this.sPosition4s = satManager.getLekSat();
                } else {
                    this.sPosition4s = sNumbers.get(3);
                }

                break;


            case 3:
                if (getStrNumber(sNumbers.get(0)) == null) {
                    char position1 = sNumbers.get(0).charAt(0);
                    satManager.setLekSat(position1);
                    this.sPosition1s = satManager.getLekSat();
                } else {
                    this.sPosition1s = sNumbers.get(0);
                }

                if (getStrNumber(sNumbers.get(1)) == null) {
                    char position2 = sNumbers.get(1).charAt(0);
                    satManager.setLekSat(position2);
                    this.sPosition2s = satManager.getLekSat();
                } else {
                    this.sPosition2s = sNumbers.get(1);
                }

                if (getStrNumber(sNumbers.get(2)) == null) {
                    char position3 = sNumbers.get(2).charAt(0);
                    satManager.setLekSat(position3);
                    this.sPosition3s = satManager.getLekSat();
                } else {
                    this.sPosition3s = sNumbers.get(2);
                }
                break;

            case 2:
                if (getStrNumber(sNumbers.get(0)) == null) {
                    char position1 = sNumbers.get(0).charAt(0);
                    satManager.setLekSat(position1);
                    this.sPosition1s = satManager.getLekSat();
                } else {
                    this.sPosition1s = sNumbers.get(0);
                }

                if (getStrNumber(sNumbers.get(1)) == null) {
                    char position2 = sNumbers.get(1).charAt(0);
                    satManager.setLekSat(position2);
                    this.sPosition2s = satManager.getLekSat();
                } else {
                    this.sPosition2s = sNumbers.get(1);
                }
                break;

            case 1:
                if (getStrNumber(sNumbers.get(0)) == null) {
                    char position1 = sNumbers.get(0).charAt(0);
                    satManager.setLekSat(position1);
                    this.sPosition1s = satManager.getLekSat();
                } else {
                    this.sPosition1s = sNumbers.get(0);
                }
                break;

            case 0:
                this.sPosition1s = null;
                this.sPosition2s = null;
                this.sPosition3s = null;
                this.sPosition4s = null;
                break;

        }
    }

    private String getStrNumber(String singleString) {
        String stringNumber = null;
        switch (singleString) {
            case "0":
                stringNumber = "0";
                break;
            case "1":
                stringNumber = "1";
                break;
            case "2":
                stringNumber = "2";
                break;
            case "3":
                stringNumber = "3";
                break;
            case "4":
                stringNumber = "4";
                break;
            case "5":
                stringNumber = "5";
                break;
            case "6":
                stringNumber = "6";
                break;
            case "7":
                stringNumber = "7";
                break;
            case "8":
                stringNumber = "8";
                break;
            case "9":
                stringNumber = "9";
                break;
        }
        return stringNumber;
    }

    private List<String> getFrontStringToList(String primaryNumber) {

        List<String> sSList = new ArrayList<>();
        for (int i = 0; i < primaryNumber.length(); i++) {
            char cPrimaryNumb = primaryNumber.charAt(i);
            if (!Character.isWhitespace(cPrimaryNumb)) {

                String sS = String.valueOf(cPrimaryNumb);
                sSList.add(sS);

            }
        }

        return sSList;
    }

    private List<String> getSecondStringToList(String secondNumber) {

        List<String> sSList = new ArrayList<>();
        for (int i = 0; i < secondNumber.length(); i++) {
            char cSecondNumb = secondNumber.charAt(i);
            if (!Character.isWhitespace(cSecondNumb)) {

                String sS = String.valueOf(cSecondNumb);
                sSList.add(sS);

            }
        }

        return sSList;
    }

    private Integer sumFrontPosition(String sPosition1, String sPosition2, String sPosition3) {

        int p1 = 0;
        int p2 = 0;
        int p3 = 0;


        p1 = (sPosition1 != null) ? Integer.parseInt(sPosition1) : p1;
        p2 = (sPosition2 != null) ? Integer.parseInt(sPosition2) : p2;
        p3 = (sPosition3 != null) ? Integer.parseInt(sPosition3) : p3;


        return p1 + p2 + p3;

    }

    private Integer sumSecondPosition(String sPosition1s, String sPosition2s, String sPosition3s, String sPosition4s) {

        int p1 = 0;
        int p2 = 0;
        int p3 = 0;
        int p4 = 0;


        p1 = (sPosition1s != null) ? Integer.parseInt(sPosition1s) : p1;
        p2 = (sPosition2s != null) ? Integer.parseInt(sPosition2s) : p2;
        p3 = (sPosition3s != null) ? Integer.parseInt(sPosition3s) : p3;
        p4 = (sPosition4s != null) ? Integer.parseInt(sPosition4s) : p4;

        return p1 + p2 + p3 + p4;

    }


    private void setPairTabianList(String sPosition1, String sPosition2, String sPosition3,
                                   String sPosition1s, String sPosition2s, String sPosition3s, String sPosition4s) {

        List<String> pairList = new ArrayList<>();

        if (sPosition1 != null)
            pairList.add(sPosition1);
        if (sPosition2 != null)
            pairList.add(sPosition2);
        if (sPosition3 != null)
            pairList.add(sPosition3);
        if (sPosition1s != null)
            pairList.add(sPosition1s);
        if (sPosition2s != null)
            pairList.add(sPosition2s);
        if (sPosition3s != null)
            pairList.add(sPosition3s);
        if (sPosition4s != null)
            pairList.add(sPosition4s);

        int m = pairList.size() % 2;

        if (pairList.size() >= 2) {
                     pairListA = new ArrayList<>();
            for (int i = 0; i < pairList.size() - m; i++) {
                if (i % 2 == 0) {
                    String numPositionFirst = pairList.get(i);
                    String numPositionSecond = pairList.get(i + 1);
                    String pair = numPositionFirst + numPositionSecond;
                    pairListA.add(pair);
                }
            }
        }

        if (pairList.size() >= 3) {
            pairListB = new ArrayList<>();

            int n = (m == 0) ? 1 : 0;
            for (int i = 1; i < pairList.size() - n; i++) {
                if (i % 2 == 1) {
                    String numPositionFirst = pairList.get(i);
                    String numPositionSecond = pairList.get(i + 1);
                    String pair = numPositionFirst + numPositionSecond;
                    pairListB.add(pair);
                }
            }
        }


    }

    private void setPairListA(List<String> pairListA) {
        if (pairListA != null){
            if (pairListA.size() != 0){
                pairTabianListA = new ArrayList<>();
                NumberPilotManager pilotManager = new NumberPilotManager();
                for (int i = 0; i < pairListA.size(); i++) {
                    String aType = pilotManager.getType(pairListA.get(i));
                    Integer aPoint = pilotManager.getPoint(pairListA.get(i));
                    pairTabianListA.add(new PairTabian(pairListA.get(i), aType, aPoint));

                }
            }
        }
    }
    private void setPairListB(List<String> pairListB) {
        if (pairListB != null){
            if (pairListB.size() != 0){
                pairTabianListB = new ArrayList<>();
                NumberPilotManager pilotManager = new NumberPilotManager();
                for (int i = 0; i < pairListB.size(); i++) {
                    String bType = pilotManager.getType(pairListB.get(i));
                    Integer bPoint = pilotManager.getPoint(pairListB.get(i));
                    pairTabianListB.add(new PairTabian(pairListB.get(i), bType, bPoint));

                }
            }
        }
    }
    private void initInstance(View rootView) {
        edPrimaryNumber = (EditText) rootView.findViewById(R.id.edPrimaryNumber);
        edSecondNumber = (EditText) rootView.findViewById(R.id.edSecondNumber);

        btnCalTabian = (Button) rootView.findViewById(R.id.btnCalTabian);

        tvPair1A = (TextView) rootView.findViewById(R.id.pair1A);
        tvPair2A = (TextView) rootView.findViewById(R.id.pair2A);
        tvPair3A = (TextView) rootView.findViewById(R.id.pair3A);

        tvPair1B = (TextView) rootView.findViewById(R.id.pair1B);
        tvPair2B = (TextView) rootView.findViewById(R.id.pair2B);
        tvPair3B = (TextView) rootView.findViewById(R.id.pair3B);

        tvSumM = (TextView) rootView.findViewById(R.id.tvSumM);
        tvSumL = (TextView) rootView.findViewById(R.id.tvSumL);
    }


    /**********************
     * Listener
     **********************/
    View.OnClickListener sumFront = new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            List<String> fNumber = new ArrayList<>();
            if (edPrimaryNumber.getText().toString().trim().length() >= 1) {
                String primaryNumber = edPrimaryNumber.getText().toString();
                fNumber.addAll(getFrontStringToList(primaryNumber));
            }

            setFrontPosition(fNumber);
            Integer sumFront = sumFrontPosition(sPosition1, sPosition2, sPosition3);
            Log.d("sumFront", String.valueOf(sumFront));
            tvSumM.setText(String.valueOf(sumFront));

            List<String> sNumber = new ArrayList<>();
            if (edSecondNumber.getText().toString().trim().length() != 0) {
                String secondNumber = edSecondNumber.getText().toString();
                sNumber.addAll(getSecondStringToList(secondNumber));
            }
            setSecondPosition(sNumber);
            Integer sumSecond = sumSecondPosition(sPosition1s, sPosition2s, sPosition3s, sPosition4s);
            Log.d("sumSecond", String.valueOf(sumSecond));
            tvSumL.setText(String.valueOf(sumSecond));


            setPairTabianList(sPosition1, sPosition2, sPosition3,
                    sPosition1s, sPosition2s, sPosition3s, sPosition4s);



            setPairListA(pairListA);
            setPairListB(pairListB);

            if (pairTabianListA != null){


                tvPair1A.setText(pairTabianListA.get(0).getPair());
                if (String.valueOf(pairTabianListA.get(0).getType().charAt(0)).equals("D")){
                        tvPair1A.setBackgroundResource(R.drawable.pilot_selector_green);
                }



                tvPair2A.setText(pairTabianListA.get(1).getPair());
                tvPair3A.setText(pairTabianListA.get(2).getPair());


            }

            if (pairTabianListB != null){
                tvPair1B.setText(pairTabianListB.get(0).getPair());
                tvPair2B.setText(pairTabianListB.get(1).getPair());
                tvPair3B.setText(pairTabianListB.get(2).getPair());


            }








        }


    };




}

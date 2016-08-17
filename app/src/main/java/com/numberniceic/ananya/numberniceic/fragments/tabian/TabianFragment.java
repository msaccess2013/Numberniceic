package com.numberniceic.ananya.numberniceic.fragments.tabian;


import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.numberniceic.ananya.numberniceic.R;
import com.numberniceic.ananya.numberniceic.activities.MiracleTabianActivity;
import com.numberniceic.ananya.numberniceic.dao.tabian.TabianMiracleDao;
import com.numberniceic.ananya.numberniceic.managers.NumberPilotManager;
import com.numberniceic.ananya.numberniceic.managers.eventbus.EventBus;
import com.numberniceic.ananya.numberniceic.managers.eventbus.FragMessage;
import com.numberniceic.ananya.numberniceic.managers.name.NumberKalakineeManager;
import com.numberniceic.ananya.numberniceic.managers.name.NumberLekSatManager;
import com.numberniceic.ananya.numberniceic.managers.tabian.TabianListManager;
import com.numberniceic.ananya.numberniceic.pojo.tabain.BirthDay;
import com.numberniceic.ananya.numberniceic.pojo.tabain.PairTabian;
import com.squareup.otto.Subscribe;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabianFragment extends Fragment {
    private TabianMiracleDao dao;
    private InputMethodManager imm;
    private EditText edPrimaryNumber, edSecondNumber;
    private String sPosition1;
    private String sPosition2;
    private String sPosition3;

    private String sPosition1s;
    private String sPosition2s;
    private String sPosition3s;
    private String sPosition4s;

    private FancyButton btnCalTabian;
    private FancyButton btnBirthDay;
    private FancyButton btnTabianReset;
    private FancyButton btnTabianMiracle;

    private TextView tvPair1A, tvPair2A, tvPair3A;
    private TextView tvPair1B, tvPair2B, tvPair3B;
    private TextView tvPairSL, tvPairSM, tvPairKN;
    private TextView tvSumM, tvSumL;
    private TextView tvProvince;
    private TextView tvBirthDay;
    private TextView tvKalakini;


    private TextView tvScrollTabianD, tvScrollTabianR, tvPercentD, tvPercentR;


    private List<String> pairListA;
    private List<String> pairListB;

    private List<PairTabian> pairTabianDoaListA;
    private List<PairTabian> pairTabianDoaListB;

    private Integer sumFront, sumSecond;


    private String fragMessage;
    private String tvbirthDay;
    private String birthDayEn;
    private String string4Kalakini;

    private String pairKn, pairSl, pairSm;

    private boolean kBoardStatus;

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

        KeyboardVisibilityEvent.setEventListener(
                getActivity(),
                new KeyboardVisibilityEventListener() {
                    @Override
                    public void onVisibilityChanged(boolean isOpen) {
                        kBoardStatus = isOpen;

                    }
                });


        btnCalTabian.setOnClickListener(calListener);
        btnTabianReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                edPrimaryNumber.setText("");
                edSecondNumber.setText("");
                if (!kBoardStatus)
                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

            }
        });

        btnTabianMiracle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (dao != null) {
                    Intent intent = new Intent(getContext(), MiracleTabianActivity.class);
                    intent.putExtra("dao", dao);
                    startActivity(intent);
                }


            }
        });

        if (savedInstanceState != null) {

            this.tvbirthDay = savedInstanceState.getString("tvbirthDay");
            tvBirthDay.setText(tvbirthDay);

            this.string4Kalakini = savedInstanceState.getString("string4Kalakini");
            this.birthDayEn = savedInstanceState.getString("birthDayEn");

            this.sPosition1 = savedInstanceState.getString("sPosition1");
            this.sPosition2 = savedInstanceState.getString("sPosition2");
            this.sPosition3 = savedInstanceState.getString("sPosition3");
            this.sPosition1s = savedInstanceState.getString("sPosition1s");
            this.sPosition2s = savedInstanceState.getString("sPosition2s");
            this.sPosition3s = savedInstanceState.getString("sPosition3s");
            this.sPosition4s = savedInstanceState.getString("sPosition4s");


            this.fragMessage = savedInstanceState.getString("fragMessage");

            if (fragMessage != null) {
                this.tvProvince.setText(fragMessage);
            }

            if (sPosition1 != null && sPosition2 != null && sPosition3 != null && sPosition1s != null && sPosition2s != null && sPosition3s != null && sPosition4s != null)
            calculateAllPair();


        }


        btnBirthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BirthDayFragment birthDayFragment = new BirthDayFragment();
                birthDayFragment.show(getFragmentManager(), "BirthDayFragment");
            }
        });

        tvProvince.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getChildFragmentManager().beginTransaction().replace(R.id.provinceContainer, new ProvinceFragment()).commit();

                ProvinceFragment fragment = new ProvinceFragment();
                fragment.show(getFragmentManager(), "ProvinceFragment");


            }
        });


        return rootView;
    }

    /**********************
     * Method
     **********************/
    private void initInstance(View rootView) {
        imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        edPrimaryNumber = (EditText) rootView.findViewById(R.id.edPrimaryNumber);
        edSecondNumber = (EditText) rootView.findViewById(R.id.edSecondNumber);

        btnCalTabian = (FancyButton) rootView.findViewById(R.id.btnCalTabian);
        btnBirthDay = (FancyButton) rootView.findViewById(R.id.btnBirthTabian);
        btnTabianReset = (FancyButton) rootView.findViewById(R.id.btnClearTabian);
        btnTabianMiracle = (FancyButton) rootView.findViewById(R.id.btnMiracleTabian);

        tvProvince = (TextView) rootView.findViewById(R.id.tvProvince);
        tvBirthDay = (TextView) rootView.findViewById(R.id.tvBirthDay);
        tvKalakini = (TextView) rootView.findViewById(R.id.tvKalakini);

        tvPair1A = (TextView) rootView.findViewById(R.id.pair1A);
        tvPair2A = (TextView) rootView.findViewById(R.id.pair2A);
        tvPair3A = (TextView) rootView.findViewById(R.id.pair3A);

        tvPair1B = (TextView) rootView.findViewById(R.id.pair1B);
        tvPair2B = (TextView) rootView.findViewById(R.id.pair2B);
        tvPair3B = (TextView) rootView.findViewById(R.id.pair3B);

        tvSumM = (TextView) rootView.findViewById(R.id.tvSumM);
        tvSumL = (TextView) rootView.findViewById(R.id.tvSumL);
        tvPairSL = (TextView) rootView.findViewById(R.id.pairSL);
        tvPairSM = (TextView) rootView.findViewById(R.id.pairSM);
        tvPairKN = (TextView) rootView.findViewById(R.id.pairKN);


        tvScrollTabianD = (TextView) rootView.findViewById(R.id.tvScrollTabianD);
        tvScrollTabianR = (TextView) rootView.findViewById(R.id.tvScrollTabianR);
        tvPercentD = (TextView) rootView.findViewById(R.id.tvPercentTabianD);
        tvPercentR = (TextView) rootView.findViewById(R.id.tvPercentTabianR);
    }

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

    private void setPairListDaoA(List<String> pairListA) {
        if (pairListA != null) {
            if (pairListA.size() != 0) {
                pairTabianDoaListA = new ArrayList<>();
                NumberPilotManager pilotManager = new NumberPilotManager();
                for (int i = 0; i < pairListA.size(); i++) {
                    String aType = pilotManager.getType(pairListA.get(i));
                    Integer aPoint = pilotManager.getPoint(pairListA.get(i));
                    pairTabianDoaListA.add(new PairTabian(pairListA.get(i), aType, aPoint));

                }
            }
        }
    }

    private void setPairListDaoB(List<String> pairListB) {
        if (pairListB != null) {
            if (pairListB.size() != 0) {
                pairTabianDoaListB = new ArrayList<>();
                NumberPilotManager pilotManager = new NumberPilotManager();
                for (int i = 0; i < pairListB.size(); i++) {
                    String bType = pilotManager.getType(pairListB.get(i));
                    Integer bPoint = pilotManager.getPoint(pairListB.get(i));
                    pairTabianDoaListB.add(new PairTabian(pairListB.get(i), bType, bPoint));

                }
            }
        }
    }

    private void setPairTv() {
        tvPair1A.setText("");
        tvPair1A.setVisibility(View.GONE);
        tvPair2A.setText("");
        tvPair2A.setVisibility(View.GONE);
        tvPair3A.setText("");
        tvPair3A.setVisibility(View.GONE);

        tvPair1B.setText("");
        tvPair1B.setVisibility(View.GONE);
        tvPair2B.setText("");
        tvPair2B.setVisibility(View.GONE);
        tvPair3B.setText("");
        tvPair3B.setVisibility(View.GONE);

        if (pairTabianDoaListA != null) {


            for (int i = 0; i < pairTabianDoaListA.size(); i++) {

                if (i == 0) {
                    String type = String.valueOf(pairTabianDoaListA.get(i).getType().charAt(0));
                    if (type.equals("D")) {
                        tvPair1A.setVisibility(View.VISIBLE);
                        tvPair1A.setBackgroundResource(R.drawable.pilot_selector_green);

                    } else if (type.equals("R")) {
                        tvPair1A.setVisibility(View.VISIBLE);
                        tvPair1A.setBackgroundResource(R.drawable.pilot_selector_red);
                    }

                    String paitReturn = pairTabianDoaListA.get(i).getPair();
                    if (String.valueOf(paitReturn.charAt(0)).equals("0")) {
                        paitReturn = String.valueOf(paitReturn.charAt(1));
                    }
                    tvPair1A.setText(paitReturn);

                }

                if (i == 1) {
                    String type = String.valueOf(pairTabianDoaListA.get(i).getType().charAt(0));
                    if (type.equals("D")) {
                        tvPair2A.setVisibility(View.VISIBLE);
                        tvPair2A.setBackgroundResource(R.drawable.pilot_selector_green);

                    } else if (type.equals("R")) {
                        tvPair2A.setVisibility(View.VISIBLE);
                        tvPair2A.setBackgroundResource(R.drawable.pilot_selector_red);
                    }

                    String paitReturn = pairTabianDoaListA.get(i).getPair();
                    if (String.valueOf(paitReturn.charAt(0)).equals("0")) {
                        paitReturn = String.valueOf(paitReturn.charAt(1));
                    }
                    tvPair2A.setText(paitReturn);

                }
                if (i == 2) {
                    String type = String.valueOf(pairTabianDoaListA.get(i).getType().charAt(0));
                    if (type.equals("D")) {
                        tvPair3A.setVisibility(View.VISIBLE);
                        tvPair3A.setBackgroundResource(R.drawable.pilot_selector_green);

                    } else if (type.equals("R")) {
                        tvPair3A.setVisibility(View.VISIBLE);
                        tvPair3A.setBackgroundResource(R.drawable.pilot_selector_red);
                    }
                    String paitReturn = pairTabianDoaListA.get(i).getPair();
                    if (String.valueOf(paitReturn.charAt(0)).equals("0")) {
                        paitReturn = String.valueOf(paitReturn.charAt(1));
                    }
                    tvPair3A.setText(paitReturn);

                }

            }


        }

        if (pairTabianDoaListB != null) {


            for (int i = 0; i < pairTabianDoaListB.size(); i++) {

                if (i == 0) {
                    String type = String.valueOf(pairTabianDoaListB.get(i).getType().charAt(0));
                    if (type.equals("D")) {
                        tvPair1B.setVisibility(View.VISIBLE);
                        tvPair1B.setBackgroundResource(R.drawable.pilot_selector_green);
                    } else if (type.equals("R")) {
                        tvPair1B.setVisibility(View.VISIBLE);
                        tvPair1B.setBackgroundResource(R.drawable.pilot_selector_red);
                    }
                    String paitReturn = pairTabianDoaListB.get(i).getPair();
                    if (String.valueOf(paitReturn.charAt(0)).equals("0")) {
                        paitReturn = String.valueOf(paitReturn.charAt(1));
                    }
                    tvPair1B.setText(paitReturn);
                }
                if (i == 1) {
                    String type = String.valueOf(pairTabianDoaListB.get(i).getType().charAt(0));
                    if (type.equals("D")) {
                        tvPair2B.setVisibility(View.VISIBLE);
                        tvPair2B.setBackgroundResource(R.drawable.pilot_selector_green);
                    } else if (type.equals("R")) {
                        tvPair2B.setVisibility(View.VISIBLE);
                        tvPair2B.setBackgroundResource(R.drawable.pilot_selector_red);
                    }
                    String paitReturn = pairTabianDoaListB.get(i).getPair();
                    if (String.valueOf(paitReturn.charAt(0)).equals("0")) {
                        paitReturn = String.valueOf(paitReturn.charAt(1));
                    }
                    tvPair2B.setText(paitReturn);

                }
                if (i == 2) {
                    String type = String.valueOf(pairTabianDoaListB.get(i).getType().charAt(0));
                    if (type.equals("D")) {
                        tvPair3B.setVisibility(View.VISIBLE);
                        tvPair3B.setBackgroundResource(R.drawable.pilot_selector_green);
                    } else if (type.equals("R")) {
                        tvPair3B.setVisibility(View.VISIBLE);
                        tvPair3B.setBackgroundResource(R.drawable.pilot_selector_red);
                    }
                    String paitReturn = pairTabianDoaListB.get(i).getPair();
                    if (String.valueOf(paitReturn.charAt(0)).equals("0")) {
                        paitReturn = String.valueOf(paitReturn.charAt(1));
                    }
                    tvPair3B.setText(paitReturn);
                }

            }


        }
    }

    private void setSumPairTv(Integer sumFront, Integer sumSecond) {
        this.sumFront = sumFront;
        this.sumSecond = sumSecond;
        tvSumM.setText(String.valueOf(sumFront));
        tvSumL.setText(String.valueOf(sumSecond));
    }

    private int getScrollDList() {
        int sumScrollD = 0;
        if (pairTabianDoaListA != null) {

            for (int i = 0; i < pairTabianDoaListA.size(); i++) {
                String type = pairTabianDoaListA.get(i).getType();
                Integer point = pairTabianDoaListA.get(i).getPoint();
                if (String.valueOf(type.charAt(0)).equals("D")) {
                    sumScrollD = sumScrollD + point;
                }
            }
        }
        if (pairTabianDoaListB != null) {

            for (int i = 0; i < pairTabianDoaListB.size(); i++) {
                String type = pairTabianDoaListB.get(i).getType();
                Integer point = pairTabianDoaListB.get(i).getPoint();
                if (String.valueOf(type.charAt(0)).equals("D")) {
                    sumScrollD = sumScrollD + point;
                }
            }
        }
        return sumScrollD;

    }

    private int getScrollRList() {
        int sumScrollR = 0;

        if (pairTabianDoaListA != null) {

            for (int i = 0; i < pairTabianDoaListA.size(); i++) {
                String type = pairTabianDoaListA.get(i).getType();
                Integer point = pairTabianDoaListA.get(i).getPoint();
                if (String.valueOf(type.charAt(0)).equals("R")) {
                    sumScrollR = sumScrollR + point;
                }
            }
        }
        if (pairTabianDoaListB != null) {

            for (int i = 0; i < pairTabianDoaListB.size(); i++) {
                String type = pairTabianDoaListB.get(i).getType();
                Integer point = pairTabianDoaListB.get(i).getPoint();
                if (String.valueOf(type.charAt(0)).equals("R")) {
                    sumScrollR = sumScrollR + point;
                }
            }
        }
        return sumScrollR;

    }

    private void setPairTvSumScroll(int scrollDABList, int scrollRABList, int scrollDSm, int scrollRSm,
                                    int scrollDSl, int scrollRSl, int scrollDKn, int scrollRKn) {

        Log.d("scrollD", scrollDABList + " : " + scrollDSm + " : " + scrollDSl + " : " + scrollDKn);
        Log.d("scrollR", scrollRABList + " : " + scrollRSm + " : " + scrollRSl + " : " + scrollRKn);

        int scrollD = scrollDABList + scrollDSm + scrollDSl + scrollDKn;
        int scrollR = scrollRABList + scrollRSm + scrollRSl + scrollRKn;

        this.tvScrollTabianD.setText(String.valueOf(scrollD));
        this.tvScrollTabianR.setText(String.valueOf(scrollR));
    }

    private void setKalakini(String kString) {

        StringBuilder builder = new StringBuilder();
        NumberKalakineeManager manager;

        if (kString != null) {
            for (int i = 0; i < kString.length(); i++) {
                manager = new NumberKalakineeManager();
                manager.setLekKalakinee(kString.charAt(i), birthDayEn);

                if (manager.getLekKalakini() != null) {
                    builder.append(" ");
                    builder.append(manager.getLekKalakini());
                    builder.append(" ");

                    tvKalakini.setText(builder.toString());

                }


            }
        }

    }

    private void setString4Kalakini(String edPf) {
        this.string4Kalakini = edPf;
    }

    private Integer getScrollDSm(String pairSm) {
        int scroll = 0;
        if (pairSm != null) {
            if (pairSm.length() == 1) {
                pairSm = "0" + pairSm;
            }
            NumberPilotManager manager = new NumberPilotManager();
            String t = manager.getType(pairSm);
            if (String.valueOf(t.charAt(0)).equals("D")) {
                scroll = manager.getPoint(pairSm);
            }
        }

        return scroll;
    }

    private Integer getScrollRSm(String pairSm) {
        int scroll = 0;
        if (pairSm != null) {
            if (pairSm.length() == 1) {
                pairSm = "0" + pairSm;
            }
            NumberPilotManager manager = new NumberPilotManager();
            String t = manager.getType(pairSm);
            if (String.valueOf(t.charAt(0)).equals("R")) {
                scroll = manager.getPoint(pairSm);
            }
        }

        return scroll;
    }

    private Integer getScrollDSl(String pairSl) {
        int scroll = 0;
        if (pairSl != null) {
            if (pairSl.length() == 1) {
                pairSl = "0" + pairSl;
            }
            NumberPilotManager manager = new NumberPilotManager();
            String t = manager.getType(pairSl);
            if (String.valueOf(t.charAt(0)).equals("D")) {
                scroll = manager.getPoint(pairSl);
            }
        }

        return scroll;
    }

    private Integer getScrollRSl(String pairSl) {
        int scroll = 0;
        if (pairSl != null) {
            if (pairSl.length() == 1) {
                pairSl = "0" + pairSl;
            }
            NumberPilotManager manager = new NumberPilotManager();
            String t = manager.getType(pairSl);
            if (String.valueOf(t.charAt(0)).equals("R")) {
                scroll = manager.getPoint(pairSl);
            }
        }

        return scroll;
    }

    private Integer getScrollDKn(String pairKn) {
        int scroll = 0;
        if (pairKn != null) {
            if (pairKn.length() == 1) {
                pairKn = "0" + pairKn;
            }
            NumberPilotManager manager = new NumberPilotManager();
            String t = manager.getType(pairKn);
            if (String.valueOf(t.charAt(0)).equals("D")) {
                scroll = manager.getPoint(pairKn);
            }
        }

        return scroll;
    }

    private Integer getScrollRKn(String pairKn) {
        int scroll = 0;
        if (pairKn != null) {
            if (pairKn.length() == 1) {
                pairKn = "0" + pairKn;
            }
            NumberPilotManager manager = new NumberPilotManager();
            String t = manager.getType(pairKn);
            if (String.valueOf(t.charAt(0)).equals("R")) {
                scroll = manager.getPoint(pairKn);
            }
        }

        return scroll;
    }

    private void setPairKN(String sPosition1, String sPosition2, String sPosition3) {
        int p1 = 0;
        int p2 = 0;
        int p3 = 0;
        int kn = 0;


        p1 = (sPosition1 != null) ? Integer.parseInt(sPosition1) : p1;
        p2 = (sPosition2 != null) ? Integer.parseInt(sPosition2) : p2;
        p3 = (sPosition3 != null) ? Integer.parseInt(sPosition3) : p3;

        if (p3 != 0 && p2 != 0) {
            kn = p1 + p3;
        }
        if (p3 == 0 && p2 != 0) {
            kn = p1 + p2;
        }
        if (p3 == 0 && p2 == 0) {
            kn = p1;
        }

        NumberPilotManager manager = new NumberPilotManager();
        String strKM = String.valueOf(kn);
        if (strKM.length() == 1)
            strKM = "0" + strKM;
        String t = manager.getType(strKM);
        if (String.valueOf(t.charAt(0)).equals("D")) {
            this.tvPairKN.setBackgroundResource(R.drawable.bg_green);
        } else if (String.valueOf(t.charAt(0)).equals("R")) {
            this.tvPairKN.setBackgroundResource(R.drawable.bg_red);
        } else {
            this.tvPairKN.setBackgroundResource(R.drawable.bg_pair_tabian);
        }
        String strKn = String.valueOf(kn);
        if (strKn.length() > 1)
            strKn = String.valueOf(strKn.charAt(1));
        this.tvPairKN.setText(strKn);
        this.pairKn = String.valueOf(kn);
    }

    private void setPairSL(Integer sumSecond) {
        String sLSum = String.valueOf(sumSecond);

        if (sLSum.length() == 1) {
            sLSum = "0" + sLSum;
        }

        NumberPilotManager manager = new NumberPilotManager();
        String type = manager.getType(sLSum);

        if (String.valueOf(type.charAt(0)).equals("D")) {
            tvPairSL.setBackgroundResource(R.drawable.bg_green);

        } else {
            tvPairSL.setBackgroundResource(R.drawable.bg_red);
        }
        if (sLSum.length() > 1)

            tvPairSL.setText(sLSum);
        this.pairSl = sLSum;

    }

    private void setPairSM(int mSum) {
        String sMSum = String.valueOf(mSum);

        if (sMSum.length() == 1) {
            sMSum = "0" + sMSum;
        }

        NumberPilotManager manager = new NumberPilotManager();
        String type = manager.getType(sMSum);


        if (String.valueOf(type.charAt(0)).equals("D")) {
            tvPairSM.setBackgroundResource(R.drawable.bg_green);

        } else {
            tvPairSM.setBackgroundResource(R.drawable.bg_red);
        }


        if (sMSum.length() > 1)

            tvPairSM.setText(sMSum);
        this.pairSm = sMSum;
    }

    private void calculateAllPair() {

        setPairTabianList(sPosition1, sPosition2, sPosition3,
                sPosition1s, sPosition2s, sPosition3s, sPosition4s);

        setPairListDaoA(pairListA);
        setPairListDaoB(pairListB);


        sumFront = sumFrontPosition(sPosition1, sPosition2, sPosition3);
        sumSecond = sumSecondPosition(sPosition1s, sPosition2s, sPosition3s, sPosition4s);
        int mSum = sumFront + sumSecond;


        // set to view
        setPairTv();
        setSumPairTv(mSum, sumSecond);

        setPairSM(mSum);
        setPairSL(sumSecond);
        setPairKN(sPosition1, sPosition2, sPosition3);

        setPairTvSumScroll(getScrollDList(), getScrollRList(), getScrollDSm(pairSm), getScrollRSm(pairSm),
                getScrollDSl(pairSl), getScrollRSl(pairSl), getScrollDKn(pairKn), getScrollRKn(pairKn));

        setKalakini(string4Kalakini);

        setPercent(pairTabianDoaListA, pairTabianDoaListB, pairKn, pairSm, pairSl);
        setMiracleDao(pairTabianDoaListA, pairTabianDoaListB, pairKn, pairSm, pairSl);


    }

    private void setMiracleDao(List<PairTabian> pairTabianDoaListA, List<PairTabian> pairTabianDoaListB, String pairKn, String pairSm, String pairSl) {

        if (pairTabianDoaListA != null && pairTabianDoaListB != null && pairKn != null && pairSm != null && pairSl != null) {
            List<PairTabian> pairTabianList = new ArrayList<>();
            pairTabianList.addAll(pairTabianDoaListA);
            pairTabianList.addAll(pairTabianDoaListB);

            NumberPilotManager manager = new NumberPilotManager();

            if (pairKn != null) {
                if (pairKn.length() < 2)
                    pairKn = "0" + pairKn;
                String knType = manager.getType(pairKn);
                Integer knPoint = manager.getPoint(pairKn);
                pairTabianList.add(new PairTabian(pairKn, knType, knPoint));
            }

            if (pairSm != null) {
                if (pairSm.length() < 2)
                    pairSm = "0" + pairSm;
                String smType = manager.getType(pairSm);
                Integer smPoint = manager.getPoint(pairSm);
                pairTabianList.add(new PairTabian(pairSm, smType, smPoint));

            }
            if (pairSl != null) {
                if (pairSl.length() < 2)
                    pairSl = "0" + pairSl;
                String slType = manager.getType(pairSl);
                Integer slPoint = manager.getPoint(pairSl);
                pairTabianList.add(new PairTabian(pairSl, slType, slPoint));
            }

            dao = new TabianMiracleDao();
            dao.setPairTabianList(pairTabianList);
        }

    }







    private void setPercent(List<PairTabian> pairTabianDoaListA, List<PairTabian> pairTabianDoaListB, String pairKn, String pairSm, String pairSl) {
        int percentD = 0;
        int percentR = 0;


        NumberPilotManager manager;

        if (pairTabianDoaListA != null && pairTabianDoaListB != null && pairKn != null && pairSm != null && pairSl != null) {

            if (pairTabianDoaListA.size() == 3 && pairTabianDoaListB.size() == 3) {
                int wA1 = 5;
                int wA2 = 5;
                int wA3 = 10;
                int wB1 = 5;
                int wB2 = 10;
                int wB3 = 25;
                int wKn = 5;
                int wSm = 15;
                int wSl = 20;


                for (int i = 0; i < pairTabianDoaListA.size(); i++) {

                    String type = pairTabianDoaListA.get(i).getType();

                    if (i == 0 && pairTabianDoaListA.get(0).getPair() != null) {

                        if (String.valueOf(type.charAt(0)).equals("D")) {
                            percentD = percentD + wA1;
                        }
                        if (String.valueOf(type.charAt(0)).equals("R")) {
                            percentR = percentR + wA1;
                        }

                    }

                    if (i == 1 && pairTabianDoaListA.get(1).getPair() != null) {

                        if (String.valueOf(type.charAt(0)).equals("D")) {
                            percentD = percentD + wA2;
                        }
                        if (String.valueOf(type.charAt(0)).equals("R")) {
                            percentR = percentR + wA2;
                        }
                    }

                    if (i == 2 && pairTabianDoaListA.get(2).getPair() != null) {

                        if (String.valueOf(type.charAt(0)).equals("D")) {
                            percentD = percentD + wA3;
                        }
                        if (String.valueOf(type.charAt(0)).equals("R")) {
                            percentR = percentR + wA3;
                        }
                    }

                }


                for (int i = 0; i < pairTabianDoaListB.size(); i++) {
                    String type = pairTabianDoaListB.get(i).getType();

                    if (i == 0 && pairTabianDoaListB.get(0).getPair() != null) {

                        if (String.valueOf(type.charAt(0)).equals("D")) {
                            percentD = percentD + wB1;
                        }
                        if (String.valueOf(type.charAt(0)).equals("R")) {
                            percentR = percentR + wB1;
                        }
                    }

                    if (i == 1 && pairTabianDoaListB.get(1).getPair() != null) {

                        if (String.valueOf(type.charAt(0)).equals("D")) {
                            percentD = percentD + wB2;
                        }
                        if (String.valueOf(type.charAt(0)).equals("R")) {
                            percentR = percentR + wB2;
                        }
                    }

                    if (i == 2 && pairTabianDoaListB.get(2).getPair() != null) {

                        if (String.valueOf(type.charAt(0)).equals("D")) {
                            percentD = percentD + wB3;
                        }
                        if (String.valueOf(type.charAt(0)).equals("R")) {
                            percentR = percentR + wB3;
                        }
                    }

                }
                if (pairKn != null) {
                    manager = new NumberPilotManager();
                    if (pairKn.length() < 2)
                        pairKn = "0" + pairKn;
                    String type = manager.getType(pairKn);

                    if (String.valueOf(type.charAt(0)).equals("D")) {
                        percentD = percentD + wKn;
                    }
                    if (String.valueOf(type.charAt(0)).equals("R")) {
                        percentR = percentR + wKn;
                    }
                }

                if (pairSm != null) {
                    manager = new NumberPilotManager();
                    if (pairSm.length() < 2)
                        pairSm = "0" + pairSm;
                    String type = manager.getType(pairKn);

                    if (String.valueOf(type.charAt(0)).equals("D")) {
                        percentD = percentD + wSm;
                    }
                    if (String.valueOf(type.charAt(0)).equals("R")) {
                        percentR = percentR + wSm;
                    }
                }

                if (pairSl != null) {
                    manager = new NumberPilotManager();
                    String type = manager.getType(pairKn);

                    if (String.valueOf(type.charAt(0)).equals("D")) {
                        percentD = percentD + wSl;
                    }
                    if (String.valueOf(type.charAt(0)).equals("R")) {
                        percentR = percentR + wSl;
                    }
                }


            }
            if (pairTabianDoaListA.size() == 3 && pairTabianDoaListB.size() == 2) {

                int wA1;
                int wA2;
                int wA3;
                int wB1;
                int wB2;
                int wB3;
                int wKn;
                int wSm;
                int wSl;

                String firstC = String.valueOf(pairTabianDoaListA.get(0).getPair().charAt(0));
                if (checkNumber(firstC)) {
                    wA1 = 5;
                    wA2 = 10;
                    wA3 = 25;
                    wB1 = 5;
                    wB2 = 15;
                    wB3 = 0;
                    wKn = 5;
                    wSm = 15;
                    wSl = 20;
                } else {
                    wA1 = 5;
                    wA2 = 10;
                    wA3 = 25;
                    wB1 = 5;
                    wB2 = 15;
                    wB3 = 0;
                    wKn = 5;
                    wSm = 15;
                    wSl = 20;
                }


                for (int i = 0; i < pairTabianDoaListA.size(); i++) {

                    String type = pairTabianDoaListA.get(i).getType();

                    if (i == 0 && pairTabianDoaListA.get(0).getPair() != null) {

                        if (String.valueOf(type.charAt(0)).equals("D")) {
                            percentD = percentD + wA1;
                        }
                        if (String.valueOf(type.charAt(0)).equals("R")) {
                            percentR = percentR + wA1;
                        }

                    }

                    if (i == 1 && pairTabianDoaListA.get(1).getPair() != null) {

                        if (String.valueOf(type.charAt(0)).equals("D")) {
                            percentD = percentD + wA2;
                        }
                        if (String.valueOf(type.charAt(0)).equals("R")) {
                            percentR = percentR + wA2;
                        }
                    }

                    if (i == 2 && pairTabianDoaListA.get(2).getPair() != null) {

                        if (String.valueOf(type.charAt(0)).equals("D")) {
                            percentD = percentD + wA3;
                        }
                        if (String.valueOf(type.charAt(0)).equals("R")) {
                            percentR = percentR + wA3;
                        }
                    }

                }


                for (int i = 0; i < pairTabianDoaListB.size(); i++) {
                    String type = pairTabianDoaListB.get(i).getType();

                    if (i == 0 && pairTabianDoaListB.get(0).getPair() != null) {

                        if (String.valueOf(type.charAt(0)).equals("D")) {
                            percentD = percentD + wB1;
                        }
                        if (String.valueOf(type.charAt(0)).equals("R")) {
                            percentR = percentR + wB1;
                        }
                    }

                    if (i == 1 && pairTabianDoaListB.get(1).getPair() != null) {

                        if (String.valueOf(type.charAt(0)).equals("D")) {
                            percentD = percentD + wB2;
                        }
                        if (String.valueOf(type.charAt(0)).equals("R")) {
                            percentR = percentR + wB2;
                        }
                    }

                    if (i == 2 && pairTabianDoaListB.get(2).getPair() != null) {

                        if (String.valueOf(type.charAt(0)).equals("D")) {
                            percentD = percentD + wB3;
                        }
                        if (String.valueOf(type.charAt(0)).equals("R")) {
                            percentR = percentR + wB3;
                        }
                    }

                }
                if (pairKn != null) {
                    manager = new NumberPilotManager();
                    if (pairKn.length() < 2)
                        pairKn = "0" + pairKn;
                    String type = manager.getType(pairKn);

                    if (String.valueOf(type.charAt(0)).equals("D")) {
                        percentD = percentD + wKn;
                    }
                    if (String.valueOf(type.charAt(0)).equals("R")) {
                        percentR = percentR + wKn;
                    }
                }

                if (pairSm != null) {
                    manager = new NumberPilotManager();
                    if (pairSm.length() < 2)
                        pairSm = "0" + pairSm;
                    String type = manager.getType(pairKn);

                    if (String.valueOf(type.charAt(0)).equals("D")) {
                        percentD = percentD + wSm;
                    }
                    if (String.valueOf(type.charAt(0)).equals("R")) {
                        percentR = percentR + wSm;
                    }
                }

                if (pairSl != null) {
                    manager = new NumberPilotManager();
                    String type = manager.getType(pairKn);

                    if (String.valueOf(type.charAt(0)).equals("D")) {
                        percentD = percentD + wSl;
                    }
                    if (String.valueOf(type.charAt(0)).equals("R")) {
                        percentR = percentR + wSl;
                    }
                }


            }
            if (pairTabianDoaListA.size() == 2 && pairTabianDoaListB.size() == 2) {

                int wA1;
                int wA2;
                int wA3;
                int wB1;
                int wB2;
                int wB3;
                int wKn;
                int wSm;
                int wSl;

                String firstC = String.valueOf(pairTabianDoaListA.get(0).getPair().charAt(0));
                if (checkNumber(firstC)) {
                    wA1 = 5;
                    wA2 = 5;
                    wA3 = 0;
                    wB1 = 5;
                    wB2 = 45;
                    wB3 = 0;
                    wKn = 5;
                    wSm = 15;
                    wSl = 20;
                } else {
                    wA1 = 5;
                    wA2 = 20;
                    wA3 = 0;
                    wB1 = 5;
                    wB2 = 30;
                    wB3 = 0;
                    wKn = 5;
                    wSm = 15;
                    wSl = 20;
                }


                for (int i = 0; i < pairTabianDoaListA.size(); i++) {

                    String type = pairTabianDoaListA.get(i).getType();

                    if (i == 0 && pairTabianDoaListA.get(0).getPair() != null) {

                        if (String.valueOf(type.charAt(0)).equals("D")) {
                            percentD = percentD + wA1;
                        }
                        if (String.valueOf(type.charAt(0)).equals("R")) {
                            percentR = percentR + wA1;
                        }

                    }

                    if (i == 1 && pairTabianDoaListA.get(1).getPair() != null) {

                        if (String.valueOf(type.charAt(0)).equals("D")) {
                            percentD = percentD + wA2;
                        }
                        if (String.valueOf(type.charAt(0)).equals("R")) {
                            percentR = percentR + wA2;
                        }
                    }

                    if (i == 2 && pairTabianDoaListA.get(2).getPair() != null) {

                        if (String.valueOf(type.charAt(0)).equals("D")) {
                            percentD = percentD + wA3;
                        }
                        if (String.valueOf(type.charAt(0)).equals("R")) {
                            percentR = percentR + wA3;
                        }
                    }

                }


                for (int i = 0; i < pairTabianDoaListB.size(); i++) {
                    String type = pairTabianDoaListB.get(i).getType();

                    if (i == 0 && pairTabianDoaListB.get(0).getPair() != null) {

                        if (String.valueOf(type.charAt(0)).equals("D")) {
                            percentD = percentD + wB1;
                        }
                        if (String.valueOf(type.charAt(0)).equals("R")) {
                            percentR = percentR + wB1;
                        }
                    }

                    if (i == 1 && pairTabianDoaListB.get(1).getPair() != null) {

                        if (String.valueOf(type.charAt(0)).equals("D")) {
                            percentD = percentD + wB2;
                        }
                        if (String.valueOf(type.charAt(0)).equals("R")) {
                            percentR = percentR + wB2;
                        }
                    }

                    if (i == 2 && pairTabianDoaListB.get(2).getPair() != null) {

                        if (String.valueOf(type.charAt(0)).equals("D")) {
                            percentD = percentD + wB3;
                        }
                        if (String.valueOf(type.charAt(0)).equals("R")) {
                            percentR = percentR + wB3;
                        }
                    }

                }
                if (pairKn != null) {
                    manager = new NumberPilotManager();
                    if (pairKn.length() < 2)
                        pairKn = "0" + pairKn;
                    String type = manager.getType(pairKn);

                    if (String.valueOf(type.charAt(0)).equals("D")) {
                        percentD = percentD + wKn;
                    }
                    if (String.valueOf(type.charAt(0)).equals("R")) {
                        percentR = percentR + wKn;
                    }
                }

                if (pairSm != null) {
                    manager = new NumberPilotManager();
                    if (pairSm.length() < 2)
                        pairSm = "0" + pairSm;
                    String type = manager.getType(pairKn);

                    if (String.valueOf(type.charAt(0)).equals("D")) {
                        percentD = percentD + wSm;
                    }
                    if (String.valueOf(type.charAt(0)).equals("R")) {
                        percentR = percentR + wSm;
                    }
                }

                if (pairSl != null) {
                    manager = new NumberPilotManager();
                    String type = manager.getType(pairKn);

                    if (String.valueOf(type.charAt(0)).equals("D")) {
                        percentD = percentD + wSl;
                    }
                    if (String.valueOf(type.charAt(0)).equals("R")) {
                        percentR = percentR + wSl;
                    }
                }


            }
            if (pairTabianDoaListA.size() == 2 && pairTabianDoaListB.size() == 1) {

                int wA1;
                int wA2;
                int wA3;
                int wB1;
                int wB2;
                int wB3;
                int wKn;
                int wSm;
                int wSl;

                String firstC = String.valueOf(pairTabianDoaListA.get(0).getPair().charAt(0));
                if (checkNumber(firstC)) {
                    wA1 = 10;
                    wA2 = 40;
                    wA3 = 0;
                    wB1 = 10;
                    wB2 = 0;
                    wB3 = 0;
                    wKn = 0;
                    wSm = 20;
                    wSl = 20;
                } else {
                    wA1 = 10;
                    wA2 = 30;
                    wA3 = 0;
                    wB1 = 20;
                    wB2 = 0;
                    wB3 = 0;
                    wKn = 5;
                    wSm = 15;
                    wSl = 20;
                }


                for (int i = 0; i < pairTabianDoaListA.size(); i++) {

                    String type = pairTabianDoaListA.get(i).getType();

                    if (i == 0 && pairTabianDoaListA.get(0).getPair() != null) {

                        if (String.valueOf(type.charAt(0)).equals("D")) {
                            percentD = percentD + wA1;
                        }
                        if (String.valueOf(type.charAt(0)).equals("R")) {
                            percentR = percentR + wA1;
                        }

                    }

                    if (i == 1 && pairTabianDoaListA.get(1).getPair() != null) {

                        if (String.valueOf(type.charAt(0)).equals("D")) {
                            percentD = percentD + wA2;
                        }
                        if (String.valueOf(type.charAt(0)).equals("R")) {
                            percentR = percentR + wA2;
                        }
                    }

                    if (i == 2 && pairTabianDoaListA.get(2).getPair() != null) {

                        if (String.valueOf(type.charAt(0)).equals("D")) {
                            percentD = percentD + wA3;
                        }
                        if (String.valueOf(type.charAt(0)).equals("R")) {
                            percentR = percentR + wA3;
                        }
                    }

                }


                for (int i = 0; i < pairTabianDoaListB.size(); i++) {
                    String type = pairTabianDoaListB.get(i).getType();

                    if (i == 0 && pairTabianDoaListB.get(0).getPair() != null) {

                        if (String.valueOf(type.charAt(0)).equals("D")) {
                            percentD = percentD + wB1;
                        }
                        if (String.valueOf(type.charAt(0)).equals("R")) {
                            percentR = percentR + wB1;
                        }
                    }

                    if (i == 1 && pairTabianDoaListB.get(1).getPair() != null) {

                        if (String.valueOf(type.charAt(0)).equals("D")) {
                            percentD = percentD + wB2;
                        }
                        if (String.valueOf(type.charAt(0)).equals("R")) {
                            percentR = percentR + wB2;
                        }
                    }

                    if (i == 2 && pairTabianDoaListB.get(2).getPair() != null) {

                        if (String.valueOf(type.charAt(0)).equals("D")) {
                            percentD = percentD + wB3;
                        }
                        if (String.valueOf(type.charAt(0)).equals("R")) {
                            percentR = percentR + wB3;
                        }
                    }

                }
                if (pairKn != null) {
                    manager = new NumberPilotManager();
                    if (pairKn.length() < 2)
                        pairKn = "0" + pairKn;
                    String type = manager.getType(pairKn);

                    if (String.valueOf(type.charAt(0)).equals("D")) {
                        percentD = percentD + wKn;
                    }
                    if (String.valueOf(type.charAt(0)).equals("R")) {
                        percentR = percentR + wKn;
                    }
                }

                if (pairSm != null) {
                    manager = new NumberPilotManager();
                    if (pairSm.length() < 2)
                        pairSm = "0" + pairSm;
                    String type = manager.getType(pairKn);

                    if (String.valueOf(type.charAt(0)).equals("D")) {
                        percentD = percentD + wSm;
                    }
                    if (String.valueOf(type.charAt(0)).equals("R")) {
                        percentR = percentR + wSm;
                    }
                }

                if (pairSl != null) {
                    manager = new NumberPilotManager();
                    String type = manager.getType(pairKn);

                    if (String.valueOf(type.charAt(0)).equals("D")) {
                        percentD = percentD + wSl;
                    }
                    if (String.valueOf(type.charAt(0)).equals("R")) {
                        percentR = percentR + wSl;
                    }
                }


            }
            if (pairTabianDoaListA.size() == 1 && pairTabianDoaListB.size() == 1) {

                int wA1;
                int wA2;
                int wA3;
                int wB1;
                int wB2;
                int wB3;
                int wKn;
                int wSm;
                int wSl;

                String firstC = String.valueOf(pairTabianDoaListA.get(0).getPair().charAt(0));
                if (checkNumber(firstC)) {
                    wA1 = 10;
                    wA2 = 0;
                    wA3 = 0;
                    wB1 = 50;
                    wB2 = 0;
                    wB3 = 0;
                    wKn = 0;
                    wSm = 20;
                    wSl = 20;
                } else {
                    wA1 = 10;
                    wA2 = 0;
                    wA3 = 0;
                    wB1 = 40;
                    wB2 = 0;
                    wB3 = 0;
                    wKn = 10;
                    wSm = 20;
                    wSl = 20;
                }


                for (int i = 0; i < pairTabianDoaListA.size(); i++) {

                    String type = pairTabianDoaListA.get(i).getType();

                    if (i == 0 && pairTabianDoaListA.get(0).getPair() != null) {

                        if (String.valueOf(type.charAt(0)).equals("D")) {
                            percentD = percentD + wA1;
                        }
                        if (String.valueOf(type.charAt(0)).equals("R")) {
                            percentR = percentR + wA1;
                        }

                    }

                    if (i == 1 && pairTabianDoaListA.get(1).getPair() != null) {

                        if (String.valueOf(type.charAt(0)).equals("D")) {
                            percentD = percentD + wA2;
                        }
                        if (String.valueOf(type.charAt(0)).equals("R")) {
                            percentR = percentR + wA2;
                        }
                    }

                    if (i == 2 && pairTabianDoaListA.get(2).getPair() != null) {

                        if (String.valueOf(type.charAt(0)).equals("D")) {
                            percentD = percentD + wA3;
                        }
                        if (String.valueOf(type.charAt(0)).equals("R")) {
                            percentR = percentR + wA3;
                        }
                    }

                }


                for (int i = 0; i < pairTabianDoaListB.size(); i++) {
                    String type = pairTabianDoaListB.get(i).getType();

                    if (i == 0 && pairTabianDoaListB.get(0).getPair() != null) {

                        if (String.valueOf(type.charAt(0)).equals("D")) {
                            percentD = percentD + wB1;
                        }
                        if (String.valueOf(type.charAt(0)).equals("R")) {
                            percentR = percentR + wB1;
                        }
                    }

                    if (i == 1 && pairTabianDoaListB.get(1).getPair() != null) {

                        if (String.valueOf(type.charAt(0)).equals("D")) {
                            percentD = percentD + wB2;
                        }
                        if (String.valueOf(type.charAt(0)).equals("R")) {
                            percentR = percentR + wB2;
                        }
                    }

                    if (i == 2 && pairTabianDoaListB.get(2).getPair() != null) {

                        if (String.valueOf(type.charAt(0)).equals("D")) {
                            percentD = percentD + wB3;
                        }
                        if (String.valueOf(type.charAt(0)).equals("R")) {
                            percentR = percentR + wB3;
                        }
                    }

                }
                if (pairKn != null) {
                    manager = new NumberPilotManager();
                    if (pairKn.length() < 2)
                        pairKn = "0" + pairKn;
                    String type = manager.getType(pairKn);

                    if (String.valueOf(type.charAt(0)).equals("D")) {
                        percentD = percentD + wKn;
                    }
                    if (String.valueOf(type.charAt(0)).equals("R")) {
                        percentR = percentR + wKn;
                    }
                }

                if (pairSm != null) {
                    manager = new NumberPilotManager();
                    if (pairSm.length() < 2)
                        pairSm = "0" + pairSm;
                    String type = manager.getType(pairKn);

                    if (String.valueOf(type.charAt(0)).equals("D")) {
                        percentD = percentD + wSm;
                    }
                    if (String.valueOf(type.charAt(0)).equals("R")) {
                        percentR = percentR + wSm;
                    }
                }

                if (pairSl != null) {
                    manager = new NumberPilotManager();
                    String type = manager.getType(pairKn);

                    if (String.valueOf(type.charAt(0)).equals("D")) {
                        percentD = percentD + wSl;
                    }
                    if (String.valueOf(type.charAt(0)).equals("R")) {
                        percentR = percentR + wSl;
                    }
                }


            }
            if (pairTabianDoaListA.size() == 1 && pairTabianDoaListB.size() == 0) {

                int wA1;
                int wA2;
                int wA3;
                int wB1;
                int wB2;
                int wB3;
                int wKn;
                int wSm;
                int wSl;

                wA1 = 50;
                wA2 = 0;
                wA3 = 0;
                wB1 = 0;
                wB2 = 0;
                wB3 = 0;
                wKn = 0;
                wSm = 20;
                wSl = 30;


                for (int i = 0; i < pairTabianDoaListA.size(); i++) {

                    String type = pairTabianDoaListA.get(i).getType();

                    if (i == 0 && pairTabianDoaListA.get(0).getPair() != null) {

                        if (String.valueOf(type.charAt(0)).equals("D")) {
                            percentD = percentD + wA1;
                        }
                        if (String.valueOf(type.charAt(0)).equals("R")) {
                            percentR = percentR + wA1;
                        }

                    }

                    if (i == 1 && pairTabianDoaListA.get(1).getPair() != null) {

                        if (String.valueOf(type.charAt(0)).equals("D")) {
                            percentD = percentD + wA2;
                        }
                        if (String.valueOf(type.charAt(0)).equals("R")) {
                            percentR = percentR + wA2;
                        }
                    }

                    if (i == 2 && pairTabianDoaListA.get(2).getPair() != null) {

                        if (String.valueOf(type.charAt(0)).equals("D")) {
                            percentD = percentD + wA3;
                        }
                        if (String.valueOf(type.charAt(0)).equals("R")) {
                            percentR = percentR + wA3;
                        }
                    }

                }


                for (int i = 0; i < pairTabianDoaListB.size(); i++) {
                    String type = pairTabianDoaListB.get(i).getType();

                    if (i == 0 && pairTabianDoaListB.get(0).getPair() != null) {

                        if (String.valueOf(type.charAt(0)).equals("D")) {
                            percentD = percentD + wB1;
                        }
                        if (String.valueOf(type.charAt(0)).equals("R")) {
                            percentR = percentR + wB1;
                        }
                    }

                    if (i == 1 && pairTabianDoaListB.get(1).getPair() != null) {

                        if (String.valueOf(type.charAt(0)).equals("D")) {
                            percentD = percentD + wB2;
                        }
                        if (String.valueOf(type.charAt(0)).equals("R")) {
                            percentR = percentR + wB2;
                        }
                    }

                    if (i == 2 && pairTabianDoaListB.get(2).getPair() != null) {

                        if (String.valueOf(type.charAt(0)).equals("D")) {
                            percentD = percentD + wB3;
                        }
                        if (String.valueOf(type.charAt(0)).equals("R")) {
                            percentR = percentR + wB3;
                        }
                    }

                }
                if (pairKn != null) {
                    manager = new NumberPilotManager();
                    if (pairKn.length() < 2)
                        pairKn = "0" + pairKn;
                    String type = manager.getType(pairKn);

                    if (String.valueOf(type.charAt(0)).equals("D")) {
                        percentD = percentD + wKn;
                    }
                    if (String.valueOf(type.charAt(0)).equals("R")) {
                        percentR = percentR + wKn;
                    }
                }

                if (pairSm != null) {
                    manager = new NumberPilotManager();
                    if (pairSm.length() < 2)
                        pairSm = "0" + pairSm;
                    String type = manager.getType(pairKn);

                    if (String.valueOf(type.charAt(0)).equals("D")) {
                        percentD = percentD + wSm;
                    }
                    if (String.valueOf(type.charAt(0)).equals("R")) {
                        percentR = percentR + wSm;
                    }
                }

                if (pairSl != null) {
                    manager = new NumberPilotManager();
                    String type = manager.getType(pairKn);

                    if (String.valueOf(type.charAt(0)).equals("D")) {
                        percentD = percentD + wSl;
                    }
                    if (String.valueOf(type.charAt(0)).equals("R")) {
                        percentR = percentR + wSl;
                    }
                }


            }


            Log.d("SumPercent", percentD + " : " + percentR);
            this.tvPercentD.setText(String.valueOf(percentD));
            this.tvPercentR.setText(String.valueOf(percentR));
        }

    }

    private boolean checkNumber(String firstC) {
        boolean number = false;

        List<String> mNumber = new ArrayList<>(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));

        for (int i = 0; i < mNumber.size(); i++) {
            if (firstC.equals(mNumber.get(i))) {
                number = true;
            }
        }


        return number;
    }


    /**********************
     * Listener
     **********************/
    private View.OnClickListener calListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String edPf = edPrimaryNumber.getText().toString();
            String edPs = edSecondNumber.getText().toString();

            int countSpacef = 0;
            int countSpaces = 0;

            for (int i = 0; i < edPf.trim().length(); i++) {
                char c = edPf.charAt(i);
                boolean b = Character.isWhitespace(c);

                if (b) {
                    countSpacef++;
                }
            }

            for (int i = 0; i < edPs.trim().length(); i++) {
                char c = edPs.charAt(i);
                boolean b = Character.isWhitespace(c);

                if (b) {
                    countSpaces++;
                }
            }


            if (edPf.trim().length() >= 1 && edPs.trim().length() >= 1
                    && countSpacef == 0 && countSpaces == 0) {

                List<String> fNumber = new ArrayList<>();
                if (edPf.trim().length() >= 1) {
                    //pairListA
                    fNumber.addAll(getFrontStringToList(edPf));
                    //setKalakini
                    setString4Kalakini(edPf);

                }
                setFrontPosition(fNumber);

                List<String> sNumber = new ArrayList<>();
                if (edPf.trim().length() >= 1) {
                    //pairListB
                    sNumber.addAll(getSecondStringToList(edPs));
                }
                setSecondPosition(sNumber);

                calculateAllPair();

                if (kBoardStatus)
                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);


            } else {
                Toast.makeText(getContext(), "โปรดใส่ข้อมูลที่ถูกต้อง", Toast.LENGTH_SHORT).show();

            }
        }


    };


    @Subscribe
    public void recievedMassage(FragMessage message) {
        Toast.makeText(getContext(), "ทะเบียน " + message.getMessage(), Toast.LENGTH_SHORT).show();

        fragMessage = message.getMessage();
        tvProvince.setText(fragMessage);


    }

    @Subscribe
    public void recievedBirthDay(BirthDay day) {

        this.tvbirthDay = day.getThDay();
        this.tvBirthDay.setText(tvbirthDay);

        this.birthDayEn = day.getEnDay();

        Toast.makeText(getContext(), "คุณเกิดวัน " + tvbirthDay, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (tvBirthDay != null)
        outState.putString("tvbirthDay", tvBirthDay.getText().toString());

        if (string4Kalakini != null)
        outState.putString("string4Kalakini", string4Kalakini);

        if (birthDayEn != null)
        outState.putString("birthDayEn", birthDayEn);


        if (sPosition1 != null) {
            outState.putString("sPosition1", sPosition1);
        }
        if (sPosition2 != null) {
            outState.putString("sPosition2", sPosition2);
        }
        if (sPosition3 != null) {
            outState.putString("sPosition3", sPosition3);
        }

        if (sPosition1s != null) {
            outState.putString("sPosition1s", sPosition1s);
        }
        if (sPosition2s != null) {
            outState.putString("sPosition2s", sPosition2s);
        }
        if (sPosition3s != null) {
            outState.putString("sPosition3s", sPosition3s);
        }
        if (sPosition4s != null) {
            outState.putString("sPosition4s", sPosition4s);
        }

        if (fragMessage != null) {
            outState.putString("fragMessage", fragMessage);
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("FragmentLife", "onCreate");
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d("FragmentLife", "onResume");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("FragmentLife", "onStop");
        EventBus.getInstance().unregister(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("FragmentLife", "onStart");
        EventBus.getInstance().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("FragmentLife", "onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("FragmentLife", "onDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("FragmentLife", "onDestroyView");
    }

}

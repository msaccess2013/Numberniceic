package com.numberniceic.ananya.numberniceic.fragments;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.numberniceic.ananya.numberniceic.R;
import com.numberniceic.ananya.numberniceic.dao.phone.PhoneNumberItemCollectionDao;
import com.numberniceic.ananya.numberniceic.dao.phone.PhoneNumberItemDao;
import com.numberniceic.ananya.numberniceic.dao.phone.ScrollPairNumberDao;
import com.numberniceic.ananya.numberniceic.managers.telephone.NumberPilotManager;
import com.numberniceic.ananya.numberniceic.views.PairPhoneNumber;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TelephoneFragment extends Fragment {
    /****************
     * Member variable
     *****************/

    Button btnOk;
    Button btnReset;
    EditText phone_number;

    TextView pair1, pair2, pair3, pair4, pair5;
    TextView pairB1, pairB2, pairB3, pairB4;
    TextView pairSum;

    TextView txtTotalScrollD, txtTotalScrollR;

    TextView tvConD, tvConR;
    TextView tvPercentD, tvPercentR;


    PhoneNumberItemCollectionDao phoneNumberItemCollectionDao;
    ScrollPairNumberDao scrollPairNumberDao = new ScrollPairNumberDao();
    Integer scrollD = 0;
    Integer scrollR = 0;


    String continueCodeIDD = null;
    String continueCodeIDR = null;

    Integer percentD = 0;
    Integer percentR = 0;
    Integer percentPo1D, percentPo2D, percentPo3D, percentPo4D, percentPo5D, percentSumD;
    Integer percentPo1R, percentPo2R, percentPo3R, percentPo4R, percentPo5R, percentSumR;

    InputMethodManager imm;


    public static TelephoneFragment newInstance() {

        TelephoneFragment telephoneFragment = new TelephoneFragment();
        return telephoneFragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_telephone, container, false);
        initInstance(rootView);
        phone_number.requestFocus();


        checkState(savedInstanceState);
        imm = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);


        return rootView;
    }


    /****************
     * Methods
     *****************/


    private void initInstance(View rootView) {

        phone_number = (EditText) rootView.findViewById(R.id.phone_number);
        btnOk = (Button) rootView.findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOk();
            }
        });


        btnReset = (Button) rootView.findViewById(R.id.btn_reset);
        btnReset.setOnClickListener(onClickListener);

        phone_number.addTextChangedListener(watcher);

        initWidget(rootView);
        imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

        pairSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtSumpair = String.valueOf(pairSum.getText());
                Toast.makeText(getContext(), "txtSumpair" + txtSumpair, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initWidget(View rootView) {
        pair1 = (TextView) rootView.findViewById(R.id.ppnPair01);
        pair2 = (TextView) rootView.findViewById(R.id.ppnPair02);
        pair3 = (TextView) rootView.findViewById(R.id.ppnPair03);
        pair4 = (TextView) rootView.findViewById(R.id.ppnPair04);
        pair5 = (TextView) rootView.findViewById(R.id.ppnPair05);

        pairB1 = (TextView) rootView.findViewById(R.id.ppnPair01B);
        pairB2 = (TextView) rootView.findViewById(R.id.ppnPair02B);
        pairB3 = (TextView) rootView.findViewById(R.id.ppnPair03B);
        pairB4 = (TextView) rootView.findViewById(R.id.ppnPair04B);

        pairSum = (TextView) rootView.findViewById(R.id.ppnPairSum);
        txtTotalScrollD = (TextView) rootView.findViewById(R.id.tvScrollD);
        txtTotalScrollR = (TextView) rootView.findViewById(R.id.tvScrollR);

        tvConD = (TextView) rootView.findViewById(R.id.tvConD);
        tvConR = (TextView) rootView.findViewById(R.id.tvConR);

        tvPercentD = (TextView) rootView.findViewById(R.id.tvPercentD);
        tvPercentR = (TextView) rootView.findViewById(R.id.tvPercentR);
    }

    private void setOk() {
        clearTv();
        clearNumberCencent();


        scrollD = 0;
        scrollR = 0;

        percentD = 0;
        percentR = 0;

        if (phone_number.getText().length() == 10) {


            Toast.makeText(getContext(), "ระบบทำการคำนวณ!!", Toast.LENGTH_SHORT).show();
            setSplitPhoneNumber(phone_number.getText().toString());
            dataAccessDaoA(phoneNumberItemCollectionDao);
            dataAccessDaoB(phoneNumberItemCollectionDao);
            dataAccessDaoSum(phoneNumberItemCollectionDao);

            Integer sumScrollDTotal = scrollPairNumberDao.getPairsAD() + scrollPairNumberDao.getPairsBD() + scrollPairNumberDao.getPairSumD();
            Integer sumScrollRTotal = scrollPairNumberDao.getPairsAR() + scrollPairNumberDao.getPairsBR() + scrollPairNumberDao.getPairSumR();


            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            String scrollD = decimalFormat.format(sumScrollDTotal);
            String scrollR = decimalFormat.format(sumScrollRTotal);

            txtTotalScrollD.setText(scrollD);
            txtTotalScrollR.setText(scrollR);

            setScrollContinueD(phoneNumberItemCollectionDao);
            setScrollContinueR(phoneNumberItemCollectionDao);

            tvConD.setText(continueCodeIDD);
            tvConR.setText(continueCodeIDR);

            setPercentD(phoneNumberItemCollectionDao);
            tvPercentD.setText(String.valueOf(getPercentsD()));
            setPercentR(phoneNumberItemCollectionDao);
            tvPercentR.setText(String.valueOf(getPercentsR()));




            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);




        }
    }

    private void clearPairEdt() {
        phone_number.setText("");

        clearTv();

        phone_number.requestFocus();

        imm.showSoftInput(phone_number, 0);

    }

    private void clearTv() {

        pair1.setText("");
        pair2.setText("");
        pair3.setText("");
        pair4.setText("");
        pair5.setText("");

        pairB1.setText("");
        pairB2.setText("");
        pairB3.setText("");
        pairB4.setText("");

        txtTotalScrollD.setText("");
        txtTotalScrollR.setText("");

        pairSum.setText("");


        continueCodeIDD = null;
        continueCodeIDR = null;
        tvConD.setText("");
        tvConR.setText("");

        tvPercentD.setText("");
        tvPercentR.setText("");
    }

    private void clearNumberCencent() {
        percentPo1D = 0;
        percentPo2D = 0;
        percentPo3D = 0;
        percentPo4D = 0;
        percentPo5D = 0;
        percentSumD = 0;

        percentPo1R = 0;
        percentPo2R = 0;
        percentPo3R = 0;
        percentPo4R = 0;
        percentPo5R = 0;
        percentSumR = 0;
    }
    private Integer getPercentsD(){
     Integer pDs;
        pDs = percentPo1D +   percentPo2D+ percentPo3D +percentPo4D+percentPo5D;
        return pDs;
    }
    private Integer getPercentsR(){
        Integer pRs;
        pRs = percentPo1R +   percentPo2R+ percentPo3R +percentPo4D+percentPo5R;
        return pRs;
    }


    private void checkState(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.getParcelable("phoneNumberItemCollectionDao") != null) {

            phoneNumberItemCollectionDao = savedInstanceState.getParcelable("phoneNumberItemCollectionDao");

            dataAccessDaoA(phoneNumberItemCollectionDao);
            dataAccessDaoB(phoneNumberItemCollectionDao);
            dataAccessDaoSum(phoneNumberItemCollectionDao);

        }
    }

    private void dataAccessDaoSum(PhoneNumberItemCollectionDao dao) {

        String TYPEOfDao = dao.getPhoneNumberItemDaoSum().getType();


        switch (TYPEOfDao) {

            case "D10":
                setMyViewPairD10("#2EFE2E", dao, 0, "S");

            case "D8":
                setMyViewPairD8("#BCF5A9", dao, 0, "S");

            case "D5":
                setMyViewPairD5("#E6F8E0", dao, 0, "S");

            case "R10":
                setMyViewPairR10("#FA5858", dao, 0, "S");

            case "R7":
                setMyViewPairR7("#FA5882", dao, 0, "S");

            case "R5":
                setMyViewPairR5("#F6CECE", dao, 0, "S");
        }

        scrollPairNumberDao.setPairSumD(scrollD);
        scrollPairNumberDao.setPairSumR(scrollR);


    }

    private void dataAccessDaoA(PhoneNumberItemCollectionDao dao) {

        for (int i = 0; i < dao.getPhoneNumberItemDaosA().size(); i++) {
            String TYPEOfADao = String.valueOf(dao.getPhoneNumberItemDaosA().get(i).getType());

            switch (TYPEOfADao) {

                case "D10":
                    setMyViewPairD10("#2EFE2E", dao, i, "A");
                    continue;
                case "D8":
                    setMyViewPairD8("#BCF5A9", dao, i, "A");
                    continue;
                case "D5":
                    setMyViewPairD5("#E6F8E0", dao, i, "A");
                    continue;
                case "R10":
                    setMyViewPairR10("#FA5858", dao, i, "A");
                    continue;
                case "R7":
                    setMyViewPairR7("#FA5882", dao, i, "A");
                    continue;
                case "R5":
                    setMyViewPairR5("#F6CECE", dao, i, "A");
            }
        }
        Toast.makeText(getContext(), "dataAccessDaoA Total ScrollD = " + scrollD + " Total ScrollR = " + scrollR, Toast.LENGTH_SHORT).show();
        scrollPairNumberDao.setPairsAD(scrollD);
        scrollPairNumberDao.setPairsAR(scrollR);

    }

    private void dataAccessDaoB(PhoneNumberItemCollectionDao dao) {

        for (int i = 0; i < dao.getPhoneNumberItemDaosB().size(); i++) {

            String TYPEOfBDao = String.valueOf(dao.getPhoneNumberItemDaosB().get(i).getType());

            switch (TYPEOfBDao) {

                case "D10":
                    setMyViewPairD10("#2EFE2E", dao, i, "B");
                    continue;
                case "D8":
                    setMyViewPairD8("#BCF5A9", dao, i, "B");
                    continue;
                case "D5":
                    setMyViewPairD5("#E6F8E0", dao, i, "B");
                    continue;
                case "R10":
                    setMyViewPairR10("#FA5858", dao, i, "B");
                    continue;
                case "R7":
                    setMyViewPairR7("#FA5882", dao, i, "B");
                    continue;
                case "R5":
                    setMyViewPairR5("#F6CECE", dao, i, "B");
            }
        }
        Toast.makeText(getContext(), "dataAccessDaoB Total ScrollD = " + scrollD + " Total ScrollR = " + scrollR, Toast.LENGTH_SHORT).show();
        scrollPairNumberDao.setPairsBD(scrollD);
        scrollPairNumberDao.setPairsBR(scrollR);

    }

    private void setScrollContinueD(PhoneNumberItemCollectionDao dao) {

        boolean x1 = false;
        boolean x2 = false;
        boolean x3 = false;
        boolean x4 = false;


        for (int position = 4; position > -1; position--) {

            String typeChar = String.valueOf(dao.getPhoneNumberItemDaosA().get(position).getType().charAt(0));

            if (typeChar.equals("D")) {
                if (position == 4) {
                    x1 = true;
                    continueCodeIDD = "x1";
                }
                if (position == 3 && x1) {
                    continueCodeIDD = "x2";
                    x2 = true;
                }
                if (position == 2 && x2) {
                    continueCodeIDD = "x3";
                    x3 = true;
                }
                if (position == 1 && x3) {
                    continueCodeIDD = "x4";
                    x4 = true;
                }
                if (position == 0 && x4) {
                    continueCodeIDD = "x5";
                }
            }

        }


    }

    private void setScrollContinueR(PhoneNumberItemCollectionDao dao) {
        boolean x1 = false;
        boolean x2 = false;
        boolean x3 = false;
        boolean x4 = false;


        for (int position = 4; position > -1; position--) {

            String typeChar = String.valueOf(dao.getPhoneNumberItemDaosA().get(position).getType().charAt(0));

            if (typeChar.equals("R")) {
                if (position == 4) {
                    x1 = true;
                    continueCodeIDR = "x1";
                }
                if (position == 3 && x1) {
                    continueCodeIDR = "x2";
                    x2 = true;
                }
                if (position == 2 && x2) {
                    continueCodeIDR = "x3";
                    x3 = true;
                }
                if (position == 1 && x3) {
                    continueCodeIDR = "x4";
                    x4 = true;
                }
                if (position == 0 && x4) {
                    continueCodeIDR = "x5";
                }
            }
        }
    }

    private void setPercentD(PhoneNumberItemCollectionDao dao) {

        String type = dao.getPhoneNumberItemDaoSum().getType();
        String t = String.valueOf(type.charAt(0));


        for (int i = 0; i < dao.getPhoneNumberItemDaosA().size(); i++) {
            type = dao.getPhoneNumberItemDaosA().get(i).getType();
            t = String.valueOf(type.charAt(0));

            if (t.equals("D")) {

                switch (i) {
                    case 4:  // pair : 24
                        percentPo1D = 30;
                        continue;
                    case 3:
                        percentPo2D = 15;
                        continue;
                    case 2:
                        percentPo3D = 15;
                        continue;
                    case 1:
                        percentPo4D = 15;
                        continue;
                    case 0:
                        percentPo5D = 10;

                }

            }

        }

        if (t.equals("D")) {
            percentD = percentD + 30;
        }


    }

    private void setPercentR(PhoneNumberItemCollectionDao dao) {

        String type = dao.getPhoneNumberItemDaoSum().getType();
        String t = String.valueOf(type.charAt(0));


        for (int i = 0; i < dao.getPhoneNumberItemDaosA().size(); i++) {
            type = dao.getPhoneNumberItemDaosA().get(i).getType();
            t = String.valueOf(type.charAt(0));

            if (t.equals("R")) {

                switch (i) {
                    case 4:  // pair : 24
                        percentPo1R = 30;
                        continue;
                    case 3:
                        percentPo2R = 15;
                        continue;
                    case 2:
                        percentPo3R = 15;
                        continue;
                    case 1:
                        percentPo4R = 15;
                        continue;
                    case 0:
                        percentPo5R = 10;

                }
            }

        }

        if (t.equals("R")) {
            percentSumR = 30;

        }


    }


    private void setMyViewPairD10(String color, PhoneNumberItemCollectionDao dao, int position, String typeOfCollection) {

        if (typeOfCollection.equals("A")) {

            String pairNumberA = dao.getPhoneNumberItemDaosA().get(position).getPhoneNumber();
            Integer pointPairA = dao.getPhoneNumberItemDaosA().get(position).getPoint();

            if (position == 0) {
                pair1.setBackgroundResource(R.drawable.pilot_selector_green);
                pair1.setText(pairNumberA);
                scrollD = scrollD + pointPairA;
            }
            if (position == 1) {
                pair2.setBackgroundResource(R.drawable.pilot_selector_green);
                pair2.setText(pairNumberA);
                scrollD = scrollD + pointPairA;
            }
            if (position == 2) {
                pair3.setBackgroundResource(R.drawable.pilot_selector_green);
                pair3.setText(pairNumberA);
                scrollD = scrollD + pointPairA;
            }
            if (position == 3) {
                pair4.setBackgroundResource(R.drawable.pilot_selector_green);
                pair4.setText(pairNumberA);
                scrollD = scrollD + pointPairA;
            }
            if (position == 4) {
                pair5.setBackgroundResource(R.drawable.pilot_selector_green);
                pair5.setText(pairNumberA);
                scrollD = scrollD + pointPairA;
            }

        } else if (typeOfCollection.equals("B")) {
            String pairNumberB = dao.getPhoneNumberItemDaosB().get(position).getPhoneNumber();
            Integer pointPairB = dao.getPhoneNumberItemDaosB().get(position).getPoint();

            if (position == 0) {
                pairB1.setBackgroundResource(R.drawable.pilot_selector_green);
                pairB1.setText(pairNumberB);
                scrollD = scrollD + pointPairB;
            }
            if (position == 1) {
                pairB2.setBackgroundResource(R.drawable.pilot_selector_green);
                pairB2.setText(pairNumberB);
                scrollD = scrollD + pointPairB;
            }
            if (position == 2) {
                pairB3.setBackgroundResource(R.drawable.pilot_selector_green);
                pairB3.setText(pairNumberB);
                scrollD = scrollD + pointPairB;
            }
            if (position == 3) {
                pairB4.setBackgroundResource(R.drawable.pilot_selector_green);
                pairB4.setText(pairNumberB);
                scrollD = scrollD + pointPairB;
            }

        } else if (typeOfCollection.equals("S")) {

            Integer pointNumberSum = dao.getPhoneNumberItemDaoSum().getPoint();

            if (position == 0) {
                pairSum.setBackgroundResource(R.drawable.pilot_selector_green);
                pairSum.setText(String.valueOf(pointNumberSum));
                scrollD = scrollD + pointNumberSum;
            }


        }

    }

    private void setMyViewPairD8(String color, PhoneNumberItemCollectionDao dao, int position, String typeOfCollection) {

        if (typeOfCollection.equals("A")) {

            String pairNumberA = dao.getPhoneNumberItemDaosA().get(position).getPhoneNumber();
            Integer pointPairA = dao.getPhoneNumberItemDaosA().get(position).getPoint();

            if (position == 0) {
                pair1.setBackgroundResource(R.drawable.pilot_selector_green);
                pair1.setText(pairNumberA);
                scrollD = scrollD + pointPairA;
            }
            if (position == 1) {
                pair2.setBackgroundResource(R.drawable.pilot_selector_green);
                pair2.setText(pairNumberA);
                scrollD = scrollD + pointPairA;
            }
            if (position == 2) {
                pair3.setBackgroundResource(R.drawable.pilot_selector_green);
                pair3.setText(pairNumberA);
                scrollD = scrollD + pointPairA;
            }
            if (position == 3) {
                pair4.setBackgroundResource(R.drawable.pilot_selector_green);
                pair4.setText(pairNumberA);
                scrollD = scrollD + pointPairA;
            }
            if (position == 4) {
                pair5.setBackgroundResource(R.drawable.pilot_selector_green);
                pair5.setText(pairNumberA);
                scrollD = scrollD + pointPairA;
            }

        } else if (typeOfCollection.equals("B")) {
            String pairNumberB = dao.getPhoneNumberItemDaosB().get(position).getPhoneNumber();
            Integer pointPairB = dao.getPhoneNumberItemDaosB().get(position).getPoint();

            if (position == 0) {
                pairB1.setBackgroundResource(R.drawable.pilot_selector_green);
                pairB1.setText(pairNumberB);
                scrollD = scrollD + pointPairB;
            }
            if (position == 1) {
                pairB2.setBackgroundResource(R.drawable.pilot_selector_green);
                pairB2.setText(pairNumberB);
                scrollD = scrollD + pointPairB;
            }
            if (position == 2) {
                pairB3.setBackgroundResource(R.drawable.pilot_selector_green);
                pairB3.setText(pairNumberB);
                scrollD = scrollD + pointPairB;
            }
            if (position == 3) {
                pairB4.setBackgroundResource(R.drawable.pilot_selector_green);
                pairB4.setText(pairNumberB);
                scrollD = scrollD + pointPairB;
            }

        } else if (typeOfCollection.equals("S")) {

            Integer pointNumberSum = dao.getPhoneNumberItemDaoSum().getPoint();

            if (position == 0) {
                pairSum.setBackgroundResource(R.drawable.pilot_selector_green);
                pairSum.setText(String.valueOf(pointNumberSum));
                scrollD = scrollD + pointNumberSum;
            }


        }
    }

    private void setMyViewPairD5(String color, PhoneNumberItemCollectionDao dao, int position, String typeOfCollection) {

        if (typeOfCollection.equals("A")) {

            String pairNumberA = dao.getPhoneNumberItemDaosA().get(position).getPhoneNumber();
            Integer pointPairA = dao.getPhoneNumberItemDaosA().get(position).getPoint();

            if (position == 0) {
                pair1.setBackgroundResource(R.drawable.pilot_selector_green);
                pair1.setText(pairNumberA);
                scrollD = scrollD + pointPairA;
            }
            if (position == 1) {
                pair2.setBackgroundResource(R.drawable.pilot_selector_green);
                pair2.setText(pairNumberA);
                scrollD = scrollD + pointPairA;
            }
            if (position == 2) {
                pair3.setBackgroundResource(R.drawable.pilot_selector_green);
                pair3.setText(pairNumberA);
                scrollD = scrollD + pointPairA;
            }
            if (position == 3) {
                pair4.setBackgroundResource(R.drawable.pilot_selector_green);
                pair4.setText(pairNumberA);
                scrollD = scrollD + pointPairA;
            }
            if (position == 4) {
                pair5.setBackgroundResource(R.drawable.pilot_selector_green);
                pair5.setText(String.valueOf(pairNumberA));
                scrollD = scrollD + pointPairA;
            }

        } else if (typeOfCollection.equals("B")) {
            String pairNumberB = dao.getPhoneNumberItemDaosB().get(position).getPhoneNumber();
            Integer pointPairB = dao.getPhoneNumberItemDaosB().get(position).getPoint();

            if (position == 0) {
                pairB1.setBackgroundResource(R.drawable.pilot_selector_green);
                pairB1.setText(pairNumberB);
                scrollD = scrollD + pointPairB;
            }
            if (position == 1) {
                pairB2.setBackgroundResource(R.drawable.pilot_selector_green);
                pairB2.setText(pairNumberB);
                scrollD = scrollD + pointPairB;
            }
            if (position == 2) {
                pairB3.setBackgroundResource(R.drawable.pilot_selector_green);
                pairB3.setText(pairNumberB);
                scrollD = scrollD + pointPairB;
            }
            if (position == 3) {
                pairB4.setBackgroundResource(R.drawable.pilot_selector_green);
                pairB4.setText(pairNumberB);
                scrollD = scrollD + pointPairB;
            }

        } else if (typeOfCollection.equals("S")) {

            Integer pointNumberSum = dao.getPhoneNumberItemDaoSum().getPoint();

            if (position == 0) {
                pairSum.setBackgroundResource(R.drawable.pilot_selector_green);
                pairSum.setText(String.valueOf(pointNumberSum));
                scrollD = scrollD + pointNumberSum;
            }


        }
    }

    private void setMyViewPairR10(String color, PhoneNumberItemCollectionDao dao, int position, String typeOfCollection) {

        if (typeOfCollection.equals("A")) {

            String pairNumberA = dao.getPhoneNumberItemDaosA().get(position).getPhoneNumber();
            Integer pointPairA = dao.getPhoneNumberItemDaosA().get(position).getPoint();

            if (position == 0) {
                pair1.setBackgroundResource(R.drawable.pilot_selector_red);
                pair1.setText(pairNumberA);
                scrollR = scrollR + pointPairA;
            }
            if (position == 1) {
                pair2.setBackgroundResource(R.drawable.pilot_selector_red);
                pair2.setText(pairNumberA);
                scrollR = scrollR + pointPairA;
            }
            if (position == 2) {
                pair3.setBackgroundResource(R.drawable.pilot_selector_red);
                pair3.setText(pairNumberA);
                scrollR = scrollR + pointPairA;
            }
            if (position == 3) {
                pair4.setBackgroundResource(R.drawable.pilot_selector_red);
                pair4.setText(pairNumberA);
                scrollR = scrollR + pointPairA;
            }
            if (position == 4) {
                pair5.setBackgroundResource(R.drawable.pilot_selector_red);
                pair5.setText(pairNumberA);
                scrollR = scrollR + pointPairA;
            }

        } else if (typeOfCollection.equals("B")) {
            String pairNumberB = dao.getPhoneNumberItemDaosB().get(position).getPhoneNumber();
            Integer pointPairB = dao.getPhoneNumberItemDaosB().get(position).getPoint();

            if (position == 0) {
                pairB1.setBackgroundResource(R.drawable.pilot_selector_red);
                pairB1.setText(pairNumberB);
                scrollR = scrollR + pointPairB;
            }
            if (position == 1) {
                pairB2.setBackgroundResource(R.drawable.pilot_selector_red);
                pairB2.setText(pairNumberB);
                scrollR = scrollR + pointPairB;
            }
            if (position == 2) {
                pairB3.setBackgroundResource(R.drawable.pilot_selector_red);
                pairB3.setText(pairNumberB);
                scrollR = scrollR + pointPairB;
            }
            if (position == 3) {
                pairB4.setBackgroundResource(R.drawable.pilot_selector_red);
                pairB4.setText(pairNumberB);
                scrollR = scrollR + pointPairB;
            }

        } else if (typeOfCollection.equals("S")) {
            String pairNumberSum = dao.getPhoneNumberItemDaoSum().getPhoneNumber();
            Integer pointNumberSum = dao.getPhoneNumberItemDaoSum().getPoint();

            if (position == 0) {
                pairSum.setBackgroundResource(R.drawable.pilot_selector_red);
                pairSum.setText(String.valueOf(pairNumberSum));
                scrollR = scrollR + pointNumberSum;
            }

        }
    }

    private void setMyViewPairR7(String color, PhoneNumberItemCollectionDao dao, int position, String typeOfCollection) {

        if (typeOfCollection.equals("A")) {

            String pairNumberA = dao.getPhoneNumberItemDaosA().get(position).getPhoneNumber();
            Integer pointPairA = dao.getPhoneNumberItemDaosA().get(position).getPoint();

            if (position == 0) {
                pair1.setBackgroundResource(R.drawable.pilot_selector_red);
                pair1.setText(pairNumberA);
                scrollR = scrollR + pointPairA;
            }
            if (position == 1) {
                pair2.setBackgroundResource(R.drawable.pilot_selector_red);
                pair2.setText(pairNumberA);
                scrollR = scrollR + pointPairA;
            }
            if (position == 2) {
                pair3.setBackgroundResource(R.drawable.pilot_selector_red);
                pair3.setText(pairNumberA);
                scrollR = scrollR + pointPairA;
            }
            if (position == 3) {
                pair4.setBackgroundResource(R.drawable.pilot_selector_red);
                pair4.setText(pairNumberA);
                scrollR = scrollR + pointPairA;
            }
            if (position == 4) {
                pair5.setBackgroundResource(R.drawable.pilot_selector_red);
                pair5.setText(pairNumberA);
                scrollR = scrollR + pointPairA;
            }

        } else if (typeOfCollection.equals("B")) {
            String pairNumberB = dao.getPhoneNumberItemDaosB().get(position).getPhoneNumber();
            Integer pointPairB = dao.getPhoneNumberItemDaosB().get(position).getPoint();

            if (position == 0) {
                pairB1.setBackgroundResource(R.drawable.pilot_selector_red);
                pairB1.setText(pairNumberB);
                scrollR = scrollR + pointPairB;
            }
            if (position == 1) {
                pairB2.setBackgroundResource(R.drawable.pilot_selector_red);
                pairB2.setText(pairNumberB);
                scrollR = scrollR + pointPairB;
            }
            if (position == 2) {
                pairB3.setBackgroundResource(R.drawable.pilot_selector_red);
                pairB3.setText(pairNumberB);
                scrollR = scrollR + pointPairB;
            }
            if (position == 3) {
                pairB4.setBackgroundResource(R.drawable.pilot_selector_red);
                pairB4.setText(pairNumberB);
                scrollR = scrollR + pointPairB;
            }

        } else if (typeOfCollection.equals("S")) {
            String pairNumberSum = dao.getPhoneNumberItemDaoSum().getPhoneNumber();
            Integer pointNumberSum = dao.getPhoneNumberItemDaoSum().getPoint();

            if (position == 0) {
                pairSum.setBackgroundResource(R.drawable.pilot_selector_green);
                pairSum.setText(String.valueOf(pairNumberSum));
                scrollR = scrollR + pointNumberSum;
            }

        }
    }

    private void setMyViewPairR5(String color, PhoneNumberItemCollectionDao dao, int position, String typeOfCollection) {

        if (typeOfCollection.equals("A")) {

            String pairNumberA = dao.getPhoneNumberItemDaosA().get(position).getPhoneNumber();
            Integer pointPairA = dao.getPhoneNumberItemDaosA().get(position).getPoint();

            if (position == 0) {
                pair1.setBackgroundResource(R.drawable.pilot_selector_red);
                pair1.setText(pairNumberA);
                scrollR = scrollR + pointPairA;
            }
            if (position == 1) {
                pair2.setBackgroundResource(R.drawable.pilot_selector_red);
                pair2.setText(pairNumberA);
                scrollR = scrollR + pointPairA;
            }
            if (position == 2) {
                pair3.setBackgroundResource(R.drawable.pilot_selector_red);
                pair3.setText(pairNumberA);
                scrollR = scrollR + pointPairA;
            }
            if (position == 3) {
                pair4.setBackgroundResource(R.drawable.pilot_selector_red);
                pair4.setText(pairNumberA);
                scrollR = scrollR + pointPairA;
            }
            if (position == 4) {
                pair5.setBackgroundResource(R.drawable.pilot_selector_red);
                pair5.setText(pairNumberA);
                scrollR = scrollR + pointPairA;
            }

        } else if (typeOfCollection.equals("B")) {
            String pairNumberB = dao.getPhoneNumberItemDaosB().get(position).getPhoneNumber();
            Integer pointPairB = dao.getPhoneNumberItemDaosB().get(position).getPoint();

            if (position == 0) {
                pairB1.setBackgroundResource(R.drawable.pilot_selector_red);
                pairB1.setText(pairNumberB);
                scrollR = scrollR + pointPairB;
            }
            if (position == 1) {
                pairB2.setBackgroundResource(R.drawable.pilot_selector_red);
                pairB2.setText(pairNumberB);
                scrollR = scrollR + pointPairB;
            }
            if (position == 2) {
                pairB3.setBackgroundResource(R.drawable.pilot_selector_red);
                pairB3.setText(pairNumberB);
                scrollR = scrollR + pointPairB;
            }
            if (position == 3) {
                pairB4.setBackgroundResource(R.drawable.pilot_selector_red);
                pairB4.setText(pairNumberB);
                scrollR = scrollR + pointPairB;
            }

        } else if (typeOfCollection.equals("S")) {
            String pairNumberSum = dao.getPhoneNumberItemDaoSum().getPhoneNumber();
            Integer pointNumberSum = dao.getPhoneNumberItemDaoSum().getPoint();

            if (position == 0) {
                pairSum.setBackgroundResource(R.drawable.pilot_selector_red);
                pairSum.setText(String.valueOf(pairNumberSum));
                scrollR = scrollR + pointNumberSum;
            }

        }
    }


    //set Dao
    private void setSplitPhoneNumber(String my_number) {

        String pairPositionA1, pairPositionA2, pairPositionA3, pairPositionA4, pairPositionA5;
        String pairPositionB1, pairPositionB2, pairPositionB3, pairPositionB4;
        String pairPositionSum;

        List<PhoneNumberItemDao> phoneNumberItemDaosA = new ArrayList<>();
        List<PhoneNumberItemDao> phoneNumberItemDaosB = new ArrayList<>();

        pairPositionA1 = String.valueOf(my_number.charAt(0)) + String.valueOf(my_number.charAt(1));
        pairPositionA2 = String.valueOf(my_number.charAt(2)) + String.valueOf(my_number.charAt(3));
        pairPositionA3 = String.valueOf(my_number.charAt(4)) + String.valueOf(my_number.charAt(5));
        pairPositionA4 = String.valueOf(my_number.charAt(6)) + String.valueOf(my_number.charAt(7));
        pairPositionA5 = String.valueOf(my_number.charAt(8)) + String.valueOf(my_number.charAt(9));

        pairPositionB1 = String.valueOf(my_number.charAt(1)) + String.valueOf(my_number.charAt(2));
        pairPositionB2 = String.valueOf(my_number.charAt(3)) + String.valueOf(my_number.charAt(4));
        pairPositionB3 = String.valueOf(my_number.charAt(5)) + String.valueOf(my_number.charAt(6));
        pairPositionB4 = String.valueOf(my_number.charAt(7)) + String.valueOf(my_number.charAt(8));

        Integer n = 0;
        for (int i = 0; i < my_number.length(); i++) {

            n = n + Integer.valueOf(String.valueOf(my_number.charAt(i)));

        }

        pairPositionSum = String.valueOf(n);


        NumberPilotManager manager = new NumberPilotManager();


        Integer pointSum = manager.getPoint(pairPositionSum);
        String typeSum = manager.getType(pairPositionSum);


        Integer pointA1 = manager.getPoint(pairPositionA1);
        String typeA1 = manager.getType(pairPositionA1);
        Integer pointA2 = manager.getPoint(pairPositionA2);
        String typeA2 = manager.getType(pairPositionA2);
        Integer pointA3 = manager.getPoint(pairPositionA3);
        String typeA3 = manager.getType(pairPositionA3);
        Integer pointA4 = manager.getPoint(pairPositionA4);
        String typeA4 = manager.getType(pairPositionA4);
        Integer pointA5 = manager.getPoint(pairPositionA5);
        String typeA5 = manager.getType(pairPositionA5);

        Integer pointB1 = manager.getPoint(pairPositionB1);
        String typeB1 = manager.getType(pairPositionB1);
        Integer pointB2 = manager.getPoint(pairPositionB2);
        String typeB2 = manager.getType(pairPositionB2);
        Integer pointB3 = manager.getPoint(pairPositionB3);
        String typeB3 = manager.getType(pairPositionB3);
        Integer pointB4 = manager.getPoint(pairPositionB4);
        String typeB4 = manager.getType(pairPositionB4);

        phoneNumberItemDaosA.add(new PhoneNumberItemDao(pairPositionA1, typeA1, pointA1));
        phoneNumberItemDaosA.add(new PhoneNumberItemDao(pairPositionA2, typeA2, pointA2));
        phoneNumberItemDaosA.add(new PhoneNumberItemDao(pairPositionA3, typeA3, pointA3));
        phoneNumberItemDaosA.add(new PhoneNumberItemDao(pairPositionA4, typeA4, pointA4));
        phoneNumberItemDaosA.add(new PhoneNumberItemDao(pairPositionA5, typeA5, pointA5));

        phoneNumberItemDaosB.add(new PhoneNumberItemDao(pairPositionB1, typeB1, pointB1));
        phoneNumberItemDaosB.add(new PhoneNumberItemDao(pairPositionB2, typeB2, pointB2));
        phoneNumberItemDaosB.add(new PhoneNumberItemDao(pairPositionB3, typeB3, pointB3));
        phoneNumberItemDaosB.add(new PhoneNumberItemDao(pairPositionB4, typeB4, pointB4));

        phoneNumberItemCollectionDao = new PhoneNumberItemCollectionDao();
        phoneNumberItemCollectionDao.setPhoneNumberItemDaosA(phoneNumberItemDaosA);
        phoneNumberItemCollectionDao.setPhoneNumberItemDaosB(phoneNumberItemDaosB);
        phoneNumberItemCollectionDao.setPhoneNumberItemDaoSum(new PhoneNumberItemDao(pairPositionSum, typeSum, pointSum));


    }


    /****************
     * Listeners
     *****************/

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            clearPairEdt();

        }
    };

    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            if (phone_number.getText().length() <= 10) {
                Log.d("my_number", phone_number.getText().toString());
                Log.d("countx", String.valueOf(phone_number.getText().length()));


            }

        }

        @Override
        public void afterTextChanged(Editable editable) {


        }
    };


    @Override
    public void onStart() {
        super.onStart();
        Log.d("LifeCycle", "onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("LifeCycle", "onStop");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("LifeCycle", "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("LifeCycle", "onDestroy");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("LifeCycle", "onPause");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("phoneNumberItemCollectionDao", phoneNumberItemCollectionDao);

    }
}

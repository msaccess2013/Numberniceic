package com.numberniceic.ananya.numberniceic.fragments.telephone;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.numberniceic.ananya.numberniceic.R;
import com.numberniceic.ananya.numberniceic.adapters.PhonePairDangAdapter;
import com.numberniceic.ananya.numberniceic.dao.phone.PhoneNumberItemCollectionDao;
import com.numberniceic.ananya.numberniceic.dao.phone.PhoneNumberItemDao;
import com.numberniceic.ananya.numberniceic.dao.phone.ScrollPairNumberDao;
import com.numberniceic.ananya.numberniceic.managers.NumberPilotManager;
import com.numberniceic.ananya.numberniceic.managers.telephone.PairNumberPercentManager;
import com.numberniceic.ananya.numberniceic.managers.telephone.SummaryScrollManager;
import com.numberniceic.ananya.numberniceic.pojo.PairNumberDang;
import com.numberniceic.ananya.numberniceic.pojo.PairNumberPercent;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TelephoneFragment extends Fragment {
    /****************
     * Member variable
     *****************/

    PhoneNumberItemCollectionDao phoneNumberItemCollectionDao;

    List<PairNumberPercent> pairNumberPercents = new ArrayList<>();

    Button btnMiracle;
    EditText phone_number;
    String phoneNumber;
    TextView pair1, pair2, pair3, pair4, pair5;
    TextView pairB1, pairB2, pairB3, pairB4;
    TextView pairSum;
    TextView txtTotalScrollD, txtTotalScrollR;
    TextView tvPercentD, tvPercentR;
    TextView tvPhoneNum;
    TextView tvPairDangFirst, tvPairDangSecond, tvPairDangThirdFirst, tvPairDangThirdSecond;


    ScrollPairNumberDao scrollPairNumberDao = new ScrollPairNumberDao();
    Integer scrollD = 0;
    Integer scrollR = 0;
    Integer percentD = 0;
    Integer percentR = 0;
    Integer percentPo1D = 0, percentPo2D = 0, percentPo3D = 0, percentPo4D = 0, percentPo5D = 0, percentSumD = 0;
    Integer percentPo1R = 0, percentPo2R = 0, percentPo3R = 0, percentPo4R = 0, percentPo5R = 0, percentSumR = 0;
    Integer percentPoB1D = 0, percentPoB2D = 0, percentPoB3D = 0, percentPoB4D = 0;
    Integer percentPoB1R = 0, percentPoB2R = 0, percentPoB3R = 0, percentPoB4R = 0;
    InputMethodManager imm;


    public interface FragmentTelePhoneListener {
        void onPairPhoneClick(PhoneNumberItemCollectionDao phoneNumberItemCollectionDao);
    }

    public static TelephoneFragment newInstance() {

        TelephoneFragment telephoneFragment = new TelephoneFragment();
        return telephoneFragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("LifeCycle", "onCreateView");

        View rootView = inflater.inflate(R.layout.fragment_telephone, container, false);

        initInstance(rootView);


        checkState(savedInstanceState);

        if (phoneNumberItemCollectionDao != null)
            setDao(phoneNumberItemCollectionDao);


        return rootView;
    }

    /****************
     * Methods
     *****************/
    private void initInstance(View rootView) {

        phone_number = (EditText) rootView.findViewById(R.id.phone_number);
        phone_number.requestFocus();

        phone_number.addTextChangedListener(watcher);
        imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

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
        tvPercentD = (TextView) rootView.findViewById(R.id.tvPercentD);
        tvPercentR = (TextView) rootView.findViewById(R.id.tvPercentR);

        tvPhoneNum = (TextView) rootView.findViewById(R.id.tvNumphoneCr);

        tvPairDangFirst = (TextView) rootView.findViewById(R.id.tvDangFirst);
        tvPairDangSecond = (TextView) rootView.findViewById(R.id.tvDangSecond);
        tvPairDangThirdFirst = (TextView) rootView.findViewById(R.id.tvDangThirdFist);
        tvPairDangThirdSecond = (TextView) rootView.findViewById(R.id.tvDangThirdSecond);

        btnMiracle = (Button) rootView.findViewById(R.id.btn_ok);
        btnMiracle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (phoneNumberItemCollectionDao != null) {
                    FragmentTelePhoneListener fragmentTelePhoneListener = (FragmentTelePhoneListener) getActivity();
                    fragmentTelePhoneListener.onPairPhoneClick(phoneNumberItemCollectionDao);
                } else {
                    Toast.makeText(getContext(), "กรุณากรอกหมายเลขโทรศัพท์!!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void checkState(Bundle savedInstanceState) {

        if (savedInstanceState != null && savedInstanceState.getParcelable("phoneNumberItemCollectionDao") != null) {

            phoneNumberItemCollectionDao = savedInstanceState.getParcelable("phoneNumberItemCollectionDao");

        }


    }

    private void okDao(String phoneNumberX) {
        clearTv();
        clearNumberCencent();

        this.phoneNumber = phoneNumberX;
        tvPhoneNum.setText(phoneNumber);

        scrollD = 0;
        scrollR = 0;
        percentD = 0;
        percentR = 0;

        if (phoneNumber.length() == 10) {

            if (phoneNumberItemCollectionDao == null) {
                Toast.makeText(getContext(), "ระบบทำการคำนวณ!!", Toast.LENGTH_SHORT).show();
                setSplitPhoneNumber(phoneNumber);
                setDao(phoneNumberItemCollectionDao);
            } else {
                setSplitPhoneNumber(phoneNumber);
                setDao(phoneNumberItemCollectionDao);
            }
        }


        phone_number.setText(null);

        if (phoneNumberItemCollectionDao != null) {
            SummaryScrollManager.getInstance().setScrollD(scrollD);
            SummaryScrollManager.getInstance().setScrollR(scrollR);
            SummaryScrollManager.getInstance().setPercentD(tvPercentD.getText().toString());
            SummaryScrollManager.getInstance().setPercentR(tvPercentR.getText().toString());
        }


    }

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

    private void setDao(PhoneNumberItemCollectionDao phoneNumberItemCollectionDao) {

        dataAccessDaoA(phoneNumberItemCollectionDao);
        dataAccessDaoB(phoneNumberItemCollectionDao);
        dataAccessDaoSum(phoneNumberItemCollectionDao);

        //set continue for A B S
        // 0955456424 : 1045

        setScrollContinueAD(phoneNumberItemCollectionDao);
        setScrollContinueAR(phoneNumberItemCollectionDao);
        setScrollContinueBD(phoneNumberItemCollectionDao);
        setScrollContinueBR(phoneNumberItemCollectionDao);

        setPercentD(phoneNumberItemCollectionDao);
        tvPercentD.setText(String.valueOf(getPercentsD()));
        PairNumberPercentManager.getInstance().setPairNumberPercents(pairNumberPercents);


        setPercentR(phoneNumberItemCollectionDao);
        tvPercentR.setText(String.valueOf(getPercentsR()));

        // 0955456424 : 920

        if (phoneNumberItemCollectionDao != null) {
            setScrollPairBonus(phoneNumberItemCollectionDao);
        }

        setScrollDuplicatePair(phoneNumberItemCollectionDao);
        setScrollCountPair(phoneNumberItemCollectionDao);


        if (phoneNumberItemCollectionDao != null) {
            setScrollDang(phoneNumberItemCollectionDao);
        }

        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String scrollTotalD = decimalFormat.format(scrollD);
        String scrollTotalR = decimalFormat.format(scrollR);

        txtTotalScrollD.setText(scrollTotalD);
        txtTotalScrollR.setText(scrollTotalR);


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

        tvPercentD.setText("");
        tvPercentR.setText("");

        tvPairDangFirst.setText("");
        tvPairDangSecond.setText("");
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

        percentPoB1D = 0;
        percentPoB2D = 0;
        percentPoB3D = 0;
        percentPoB4D = 0;

        percentPoB1R = 0;
        percentPoB2R = 0;
        percentPoB3R = 0;
        percentPoB4R = 0;


    }


    private void setMyViewPairD10(int color, PhoneNumberItemCollectionDao dao, int position, String typeOfCollection) {

        if (typeOfCollection.equals("A")) {

            String pairNumberA = dao.getPhoneNumberItemDaosA().get(position).getPhoneNumber();
            Integer pointPairA = dao.getPhoneNumberItemDaosA().get(position).getPoint();

            if (position == 0) {
                pair1.setBackgroundResource(color);
                pair1.setText(pairNumberA);
                scrollD = scrollD + pointPairA;

            } else if (position == 1) {
                pair2.setBackgroundResource(color);
                pair2.setText(pairNumberA);
                scrollD = scrollD + pointPairA;

            } else if (position == 2) {
                pair3.setBackgroundResource(color);
                pair3.setText(pairNumberA);
                scrollD = scrollD + pointPairA;

            } else if (position == 3) {
                pair4.setBackgroundResource(color);
                pair4.setText(pairNumberA);
                scrollD = scrollD + pointPairA;

            } else if (position == 4) {
                pair5.setBackgroundResource(color);
                pair5.setText(pairNumberA);
                scrollD = scrollD + pointPairA;

            }

        }
        if (typeOfCollection.equals("B")) {
            String pairNumberB = dao.getPhoneNumberItemDaosB().get(position).getPhoneNumber();
            Integer pointPairB = dao.getPhoneNumberItemDaosB().get(position).getPoint();


            if (position == 0) {
                pairB1.setBackgroundResource(color);
                pairB1.setText(pairNumberB);
                scrollD = scrollD + pointPairB;

            } else if (position == 1) {
                pairB2.setBackgroundResource(color);
                pairB2.setText(pairNumberB);
                scrollD = scrollD + pointPairB;

            } else if (position == 2) {
                pairB3.setBackgroundResource(color);
                pairB3.setText(pairNumberB);
                scrollD = scrollD + pointPairB;

            } else if (position == 3) {
                pairB4.setBackgroundResource(color);
                pairB4.setText(pairNumberB);
                scrollD = scrollD + pointPairB;

            }

        }
        if (typeOfCollection.equals("S")) {

            String pairNumberSum = dao.getPhoneNumberItemDaoSum().getPhoneNumber();
            Integer pointNumberSum = dao.getPhoneNumberItemDaoSum().getPoint();

            pairSum.setBackgroundResource(color);
            pairSum.setText(String.valueOf(pairNumberSum));
            scrollD = scrollD + pointNumberSum;


        }

    }

    private void setMyViewPairD8(int color, PhoneNumberItemCollectionDao dao, int position, String typeOfCollection) {

        if (typeOfCollection.equals("A")) {

            String pairNumberA = dao.getPhoneNumberItemDaosA().get(position).getPhoneNumber();
            Integer pointPairA = dao.getPhoneNumberItemDaosA().get(position).getPoint();

            if (position == 0) {
                pair1.setBackgroundResource(color);
                pair1.setText(pairNumberA);
                scrollD = scrollD + pointPairA;

            } else if (position == 1) {
                pair2.setBackgroundResource(color);
                pair2.setText(pairNumberA);
                scrollD = scrollD + pointPairA;

            } else if (position == 2) {
                pair3.setBackgroundResource(color);
                pair3.setText(pairNumberA);

            } else if (position == 3) {
                pair4.setBackgroundResource(color);
                pair4.setText(pairNumberA);
                scrollD = scrollD + pointPairA;

            } else if (position == 4) {
                pair5.setBackgroundResource(color);
                pair5.setText(pairNumberA);
                scrollD = scrollD + pointPairA;

            }

        }
        if (typeOfCollection.equals("B")) {
            String pairNumberB = dao.getPhoneNumberItemDaosB().get(position).getPhoneNumber();
            Integer pointPairB = dao.getPhoneNumberItemDaosB().get(position).getPoint();


            if (position == 0) {
                pairB1.setBackgroundResource(color);
                pairB1.setText(pairNumberB);
                scrollD = scrollD + pointPairB;


            } else if (position == 1) {
                pairB2.setBackgroundResource(color);
                pairB2.setText(pairNumberB);
                scrollD = scrollD + pointPairB;

            } else if (position == 2) {
                pairB3.setBackgroundResource(color);
                pairB3.setText(pairNumberB);
                scrollD = scrollD + pointPairB;

            } else if (position == 3) {
                pairB4.setBackgroundResource(color);
                pairB4.setText(pairNumberB);
                scrollD = scrollD + pointPairB;

            }

        }
        if (typeOfCollection.equals("S")) {

            String pairNumberSum = dao.getPhoneNumberItemDaoSum().getPhoneNumber();
            Integer pointNumberSum = dao.getPhoneNumberItemDaoSum().getPoint();


            pairSum.setBackgroundResource(color);
            pairSum.setText(String.valueOf(pairNumberSum));
            scrollD = scrollD + pointNumberSum;


        }
    }

    private void setMyViewPairD5(int color, PhoneNumberItemCollectionDao dao, int position, String typeOfCollection) {

        if (typeOfCollection.equals("A")) {

            String pairNumberA = dao.getPhoneNumberItemDaosA().get(position).getPhoneNumber();
            Integer pointPairA = dao.getPhoneNumberItemDaosA().get(position).getPoint();


            if (position == 0) {
                pair1.setBackgroundResource(color);
                pair1.setText(pairNumberA);
                scrollD = scrollD + pointPairA;

            } else if (position == 1) {
                pair2.setBackgroundResource(color);
                pair2.setText(pairNumberA);
                scrollD = scrollD + pointPairA;

            } else if (position == 2) {
                pair3.setBackgroundResource(color);
                pair3.setText(pairNumberA);
                scrollD = scrollD + pointPairA;

            } else if (position == 3) {
                pair4.setBackgroundResource(color);
                pair4.setText(pairNumberA);
                scrollD = scrollD + pointPairA;

            } else if (position == 4) {
                pair5.setBackgroundResource(color);
                pair5.setText(String.valueOf(pairNumberA));
                scrollD = scrollD + pointPairA;

            }

        }
        if (typeOfCollection.equals("B")) {
            String pairNumberB = dao.getPhoneNumberItemDaosB().get(position).getPhoneNumber();
            Integer pointPairB = dao.getPhoneNumberItemDaosB().get(position).getPoint();

            if (position == 0) {
                pairB1.setBackgroundResource(color);
                pairB1.setText(pairNumberB);
                scrollD = scrollD + pointPairB;

            } else if (position == 1) {
                pairB2.setBackgroundResource(color);
                pairB2.setText(pairNumberB);
                scrollD = scrollD + pointPairB;

            } else if (position == 2) {
                pairB3.setBackgroundResource(color);
                pairB3.setText(pairNumberB);
                scrollD = scrollD + pointPairB;

            } else if (position == 3) {
                pairB4.setBackgroundResource(color);
                pairB4.setText(pairNumberB);
                scrollD = scrollD + pointPairB;

            }

        }
        if (typeOfCollection.equals("S")) {
            String pairNumberSum = dao.getPhoneNumberItemDaoSum().getPhoneNumber();
            Integer pointNumberSum = dao.getPhoneNumberItemDaoSum().getPoint();

            pairSum.setBackgroundResource(color);
            pairSum.setText(String.valueOf(pairNumberSum));
            scrollD = scrollD + pointNumberSum;

        }
    }

    private void setMyViewPairR10(int color, PhoneNumberItemCollectionDao dao, int position, String typeOfCollection) {

        if (typeOfCollection.equals("A")) {

            String pairNumberA = dao.getPhoneNumberItemDaosA().get(position).getPhoneNumber();
            Integer pointPairA = dao.getPhoneNumberItemDaosA().get(position).getPoint();

            Log.d("pairNumberAR10", pairNumberA);

            if (position == 0) {
                pair1.setBackgroundResource(color);
                pair1.setText(pairNumberA);
                scrollR = scrollR + pointPairA;
            } else if (position == 1) {
                pair2.setBackgroundResource(color);
                pair2.setText(pairNumberA);
                scrollR = scrollR + pointPairA;
            } else if (position == 2) {
                pair3.setBackgroundResource(color);
                pair3.setText(pairNumberA);
                scrollR = scrollR + pointPairA;
            } else if (position == 3) {
                pair4.setBackgroundResource(color);
                pair4.setText(pairNumberA);
                scrollR = scrollR + pointPairA;
            } else if (position == 4) {
                pair5.setBackgroundResource(color);
                pair5.setText(pairNumberA);
                scrollR = scrollR + pointPairA;
            }

        }
        if (typeOfCollection.equals("B")) {
            String pairNumberB = dao.getPhoneNumberItemDaosB().get(position).getPhoneNumber();
            Integer pointPairB = dao.getPhoneNumberItemDaosB().get(position).getPoint();

            if (position == 0) {
                pairB1.setBackgroundResource(color);
                pairB1.setText(pairNumberB);
                scrollR = scrollR + pointPairB;
            } else if (position == 1) {
                pairB2.setBackgroundResource(color);
                pairB2.setText(pairNumberB);
                scrollR = scrollR + pointPairB;
            } else if (position == 2) {
                pairB3.setBackgroundResource(color);
                pairB3.setText(pairNumberB);
                scrollR = scrollR + pointPairB;
            } else if (position == 3) {
                pairB4.setBackgroundResource(color);
                pairB4.setText(pairNumberB);
                scrollR = scrollR + pointPairB;
            }

        }
        if (typeOfCollection.equals("S")) {
            String pairNumberSum = dao.getPhoneNumberItemDaoSum().getPhoneNumber();
            Integer pointNumberSum = dao.getPhoneNumberItemDaoSum().getPoint();

            if (position == 0) {
                pairSum.setBackgroundResource(color);
                pairSum.setText(String.valueOf(pairNumberSum));
                scrollR = scrollR + pointNumberSum;
            }

        }
    }

    private void setMyViewPairR7(int color, PhoneNumberItemCollectionDao dao, int position, String typeOfCollection) {


        if (typeOfCollection.equals("A")) {


            String pairNumberA = dao.getPhoneNumberItemDaosA().get(position).getPhoneNumber();
            Integer pointPairA = dao.getPhoneNumberItemDaosA().get(position).getPoint();


            if (position == 0) {
                pair1.setBackgroundResource(color);
                pair1.setText(pairNumberA);
                scrollR = scrollR + pointPairA;
            } else if (position == 1) {
                pair2.setBackgroundResource(color);
                pair2.setText(pairNumberA);
                scrollR = scrollR + pointPairA;
            } else if (position == 2) {
                pair3.setBackgroundResource(color);
                pair3.setText(pairNumberA);
                scrollR = scrollR + pointPairA;
            } else if (position == 3) {
                pair4.setBackgroundResource(color);
                pair4.setText(pairNumberA);
                scrollR = scrollR + pointPairA;
            } else if (position == 4) {
                pair5.setBackgroundResource(color);
                pair5.setText(pairNumberA);
                scrollR = scrollR + pointPairA;
            }

        }

        if (typeOfCollection.equals("B")) {
            String pairNumberB = dao.getPhoneNumberItemDaosB().get(position).getPhoneNumber();
            Integer pointPairB = dao.getPhoneNumberItemDaosB().get(position).getPoint();

            if (position == 0) {
                pairB1.setBackgroundResource(color);
                pairB1.setText(pairNumberB);
                scrollR = scrollR + pointPairB;
            } else if (position == 1) {
                pairB2.setBackgroundResource(color);
                pairB2.setText(pairNumberB);
                scrollR = scrollR + pointPairB;
            } else if (position == 2) {
                pairB3.setBackgroundResource(color);
                pairB3.setText(pairNumberB);
                scrollR = scrollR + pointPairB;
            } else if (position == 3) {
                pairB4.setBackgroundResource(color);
                pairB4.setText(pairNumberB);
                scrollR = scrollR + pointPairB;
            }

        }
        if (typeOfCollection.equals("S")) {
            String pairNumberSum = dao.getPhoneNumberItemDaoSum().getPhoneNumber();
            Integer pointNumberSum = dao.getPhoneNumberItemDaoSum().getPoint();

            if (position == 0) {
                pairSum.setBackgroundResource(color);
                pairSum.setText(String.valueOf(pairNumberSum));
                scrollR = scrollR + pointNumberSum;
            }

        }
    }

    private void setMyViewPairR5(int color, PhoneNumberItemCollectionDao dao, int position, String typeOfCollection) {

        if (typeOfCollection.equals("A")) {

            String pairNumberA = dao.getPhoneNumberItemDaosA().get(position).getPhoneNumber();
            Integer pointPairA = dao.getPhoneNumberItemDaosA().get(position).getPoint();


            if (position == 0) {
                pair1.setBackgroundResource(color);
                pair1.setText(pairNumberA);
                scrollR = scrollR + pointPairA;
            } else if (position == 1) {
                pair2.setBackgroundResource(color);
                pair2.setText(pairNumberA);
                scrollR = scrollR + pointPairA;
            } else if (position == 2) {
                pair3.setBackgroundResource(color);
                pair3.setText(pairNumberA);
                scrollR = scrollR + pointPairA;
            } else if (position == 3) {
                pair4.setBackgroundResource(color);
                pair4.setText(pairNumberA);
                scrollR = scrollR + pointPairA;
            } else if (position == 4) {
                pair5.setBackgroundResource(color);
                pair5.setText(pairNumberA);
                scrollR = scrollR + pointPairA;
            }

        }

        if (typeOfCollection.equals("B")) {
            String pairNumberB = dao.getPhoneNumberItemDaosB().get(position).getPhoneNumber();
            Integer pointPairB = dao.getPhoneNumberItemDaosB().get(position).getPoint();

            if (position == 0) {
                pairB1.setBackgroundResource(color);
                pairB1.setText(pairNumberB);
                scrollR = scrollR + pointPairB;
            } else if (position == 1) {
                pairB2.setBackgroundResource(color);
                pairB2.setText(pairNumberB);
                scrollR = scrollR + pointPairB;
            } else if (position == 2) {
                pairB3.setBackgroundResource(color);
                pairB3.setText(pairNumberB);
                scrollR = scrollR + pointPairB;
            } else if (position == 3) {
                pairB4.setBackgroundResource(color);
                pairB4.setText(pairNumberB);
                scrollR = scrollR + pointPairB;
            }

        }
        if (typeOfCollection.equals("S")) {
            String pairNumberSum = dao.getPhoneNumberItemDaoSum().getPhoneNumber();
            Integer pointNumberSum = dao.getPhoneNumberItemDaoSum().getPoint();

            if (position == 0) {
                pairSum.setBackgroundResource(color);
                pairSum.setText(String.valueOf(pairNumberSum));
                scrollR = scrollR + pointNumberSum;
            }

        }
    }


    private void dataAccessDaoA(PhoneNumberItemCollectionDao dao) {

        for (int i = 0; i < dao.getPhoneNumberItemDaosA().size(); i++) {
            String TYPEOfADao = String.valueOf(dao.getPhoneNumberItemDaosA().get(i).getType());

            switch (TYPEOfADao) {

                case "D10":
                    setMyViewPairD10(R.drawable.pilot_selector_green, dao, i, "A");
                    break;
                case "D8":
                    setMyViewPairD8(R.drawable.pilot_selector_green, dao, i, "A");
                    break;
                case "D5":
                    setMyViewPairD5(R.drawable.pilot_selector_green, dao, i, "A");
                    break;
                case "R10":
                    setMyViewPairR10(R.drawable.pilot_selector_red, dao, i, "A");
                    break;
                case "R7":
                    setMyViewPairR7(R.drawable.pilot_selector_red, dao, i, "A");
                    break;
                case "R5":
                    setMyViewPairR5(R.drawable.pilot_selector_red, dao, i, "A");
                    break;
            }
        }

        scrollPairNumberDao.setPairsAD(scrollD);
        scrollPairNumberDao.setPairsAR(scrollR);

    }

    private void dataAccessDaoB(PhoneNumberItemCollectionDao dao) {

        for (int i = 0; i < dao.getPhoneNumberItemDaosB().size(); i++) {

            String TYPEOfBDao = String.valueOf(dao.getPhoneNumberItemDaosB().get(i).getType());

            switch (TYPEOfBDao) {

                case "D10":
                    setMyViewPairD10(R.drawable.pilot_selector_green, dao, i, "B");
                    break;
                case "D8":
                    setMyViewPairD8(R.drawable.pilot_selector_green, dao, i, "B");
                    break;
                case "D5":
                    setMyViewPairD5(R.drawable.pilot_selector_green, dao, i, "B");
                    break;
                case "R10":
                    setMyViewPairR10(R.drawable.pilot_selector_red, dao, i, "B");
                    break;
                case "R7":
                    setMyViewPairR7(R.drawable.pilot_selector_red, dao, i, "B");
                    break;
                case "R5":
                    setMyViewPairR5(R.drawable.pilot_selector_red, dao, i, "B");
                    break;
            }
        }

        scrollPairNumberDao.setPairsBD(scrollD);
        scrollPairNumberDao.setPairsBR(scrollR);

    }

    private void dataAccessDaoSum(PhoneNumberItemCollectionDao dao) {

        String TYPEOfDao = dao.getPhoneNumberItemDaoSum().getType();


        switch (TYPEOfDao) {

            case "D10":
                setMyViewPairD10(R.drawable.pilot_selector_green, dao, 0, "S");
                break;
            case "D8":
                setMyViewPairD8(R.drawable.pilot_selector_green, dao, 0, "S");
                break;
            case "D5":
                setMyViewPairD5(R.drawable.pilot_selector_green, dao, 0, "S");
                break;
            case "R10":
                setMyViewPairR10(R.drawable.pilot_selector_red, dao, 0, "S");
                break;
            case "R7":
                setMyViewPairR7(R.drawable.pilot_selector_red, dao, 0, "S");
                break;
            case "R5":
                setMyViewPairR5(R.drawable.pilot_selector_red, dao, 0, "S");
                break;
        }

        scrollPairNumberDao.setPairSumD(scrollD);
        scrollPairNumberDao.setPairSumR(scrollR);


    }


    private void setScrollContinueAD(PhoneNumberItemCollectionDao dao) {

        boolean x1 = false;
        boolean x2 = false;
        boolean x3 = false;
        boolean x4 = false;


        for (int position = 4; position > -1; position--) {

            String typeChar = String.valueOf(dao.getPhoneNumberItemDaosA().get(position).getType().charAt(0));

            if (typeChar.equals("D")) {

                if (position == 4) {
                    x1 = true;

                    setConSortScrollD(25);
                }
                if (position == 3 && x1) {

                    x2 = true;
                    setConSortScrollD(50);
                }
                if (position == 2 && x2) {

                    x3 = true;
                    setConSortScrollD(75);

                }
                if (position == 1 && x3) {

                    x4 = true;
                    setConSortScrollD(100);

                }
                if (position == 0 && x4) {

                    setConSortScrollD(125);

                }
            }

        }


    }

    private void setScrollContinueAR(PhoneNumberItemCollectionDao dao) {
        boolean x1 = false;
        boolean x2 = false;
        boolean x3 = false;
        boolean x4 = false;


        for (int position = 4; position > -1; position--) {

            String typeChar = String.valueOf(dao.getPhoneNumberItemDaosA().get(position).getType().charAt(0));

            if (typeChar.equals("R")) {
                if (position == 4) {
                    x1 = true;

                    setConSortScrollR(25);
                }
                if (position == 3 && x1) {

                    x2 = true;
                    setConSortScrollR(50);
                }
                if (position == 2 && x2) {

                    x3 = true;
                    setConSortScrollR(75);
                }
                if (position == 1 && x3) {

                    x4 = true;
                    setConSortScrollR(100);
                }
                if (position == 0 && x4) {

                    setConSortScrollR(125);
                }
            }
        }
    }

    private void setScrollContinueBD(PhoneNumberItemCollectionDao dao) {

        boolean x1 = false;
        boolean x2 = false;
        boolean x3 = false;

        for (int position = 3; position > -1; position--) {

            String typeChar = String.valueOf(dao.getPhoneNumberItemDaosB().get(position).getType().charAt(0));

            if (typeChar.equals("D")) {
                if (position == 3) {

                    x1 = true;
                    setConSortScrollD(25);
                }
                if (position == 2 && x1) {

                    x2 = true;
                    setConSortScrollD(50);

                }
                if (position == 1 && x2) {

                    x3 = true;
                    setConSortScrollD(75);

                }
                if (position == 0 && x3) {
                    setConSortScrollD(100);

                }
            }

        }


    }

    private void setScrollContinueBR(PhoneNumberItemCollectionDao dao) {

        boolean x1 = false;
        boolean x2 = false;
        boolean x3 = false;

        for (int position = 3; position > -1; position--) {

            String typeChar = String.valueOf(dao.getPhoneNumberItemDaosB().get(position).getType().charAt(0));

            if (typeChar.equals("R")) {
                if (position == 3) {

                    x1 = true;
                    setConSortScrollR(25);
                }
                if (position == 2 && x1) {

                    x2 = true;
                    setConSortScrollR(50);

                }
                if (position == 1 && x2) {

                    x3 = true;
                    setConSortScrollR(75);

                }
                if (position == 0 && x3) {
                    setConSortScrollR(100);

                }
            }

        }


    }


    private void setConSortScrollR(Integer scroll) {

        scrollR = scrollR + scroll;

    }

    private void setConSortScrollD(Integer scroll) {

        scrollD = scrollD + scroll;

    }


    private void setScrollPairBonus(PhoneNumberItemCollectionDao dao) {

        String firstLastPairType = dao.getPhoneNumberItemDaosA().get(4).getType();
        String secondLastPairType = dao.getPhoneNumberItemDaosA().get(3).getType();
        String sumType = dao.getPhoneNumberItemDaoSum().getType();

        Integer scrollDFirstPair = 0;
        Integer scrollRFirstPair = 0;

        Integer scrollDSecondPair = 0;
        Integer scrollRSecondPair = 0;

        Integer scrollDSum = 0;
        Integer scrollRSum = 0;


        //start last pair scroll


        if (firstLastPairType.equals("D10")) {

            scrollDFirstPair = 100;

        } else if (firstLastPairType.equals("D8")) {

            scrollDFirstPair = 75;

        } else if (firstLastPairType.equals("D5")) {

            scrollDFirstPair = 35;
        }


        if (firstLastPairType.equals("R10")) {

            scrollRFirstPair = 100;

        } else if (firstLastPairType.equals("R7")) {

            scrollRFirstPair = 75;

        } else if (firstLastPairType.equals("R5")) {

            scrollRFirstPair = 35;
        }


        // Start second last pair


        if (secondLastPairType.equals("D10")) {

            scrollDSecondPair = 75;

        } else if (secondLastPairType.equals("D8")) {

            scrollDSecondPair = 50;

        } else if (secondLastPairType.equals("D5")) {

            scrollDSecondPair = 25;
        }


        if (secondLastPairType.equals("R10")) {

            scrollRSecondPair = 75;

        } else if (secondLastPairType.equals("R7")) {

            scrollRSecondPair = 50;

        } else if (secondLastPairType.equals("R5")) {

            scrollRSecondPair = 25;
        }


        // Start sum pair


        if (sumType.equals("D10")) {

            scrollDSum = 150;

        } else if (sumType.equals("D8")) {

            scrollDSum = 100;

        } else if (sumType.equals("D5")) {

            scrollDSum = 50;
        }


        if (sumType.equals("R10")) {

            scrollRSum = 150;

        } else if (sumType.equals("R7")) {

            scrollRSum = 100;

        } else if (sumType.equals("R5")) {

            scrollRSum = 50;
        }

        scrollD = scrollD + scrollDFirstPair + scrollDSecondPair + scrollDSum;
        scrollR = scrollR + scrollRFirstPair + scrollRSecondPair + scrollRSum;


    }

    private void setScrollDuplicatePair(PhoneNumberItemCollectionDao dao) {

        List<String> myNumber = new ArrayList<>();
        NumberPilotManager manager = new NumberPilotManager();

        Integer scrollDupD = 0;
        Integer scrollDupR = 0;


        for (int n = 0; n < dao.getPhoneNumberItemDaosA().size(); n++) {
            myNumber.add(dao.getPhoneNumberItemDaosA().get(n).getPhoneNumber());
        }
        for (int m = 0; m < dao.getPhoneNumberItemDaosB().size(); m++) {
            myNumber.add(dao.getPhoneNumberItemDaosB().get(m).getPhoneNumber());
        }

        myNumber.add(dao.getPhoneNumberItemDaoSum().getPhoneNumber());

        Map<String, Integer> myMap = new HashMap<>();
        for (int i = 0; i < dao.getPhoneNumberItemDaosA().size(); i++) {
            String key = dao.getPhoneNumberItemDaosA().get(i).getPhoneNumber();
            Integer value = Collections.frequency(myNumber, dao.getPhoneNumberItemDaosA().get(i).getPhoneNumber());

            myMap.put(key, value);

        }
        for (int i = 0; i < dao.getPhoneNumberItemDaosB().size(); i++) {
            String key = dao.getPhoneNumberItemDaosB().get(i).getPhoneNumber();
            Integer value = Collections.frequency(myNumber, dao.getPhoneNumberItemDaosB().get(i).getPhoneNumber());

            myMap.put(key, value);

        }
        String mySumNumber = dao.getPhoneNumberItemDaoSum().getPhoneNumber();
        Integer value = Collections.frequency(myNumber, mySumNumber);
        myMap.put(mySumNumber, value);


        for (Map.Entry<String, Integer> entry : myMap.entrySet()) {

            String myKey = entry.getKey();
            Integer myValue = entry.getValue();
            if (String.valueOf(manager.getType(myKey).charAt(0)).equals("D")) {

                switch (myValue) {
                    case 2:
                        scrollDupD = scrollDupD + 50;
                        break;
                    case 3:
                        scrollDupD = scrollDupD + 100;
                        break;
                    case 4:
                        scrollDupD = scrollDupD + 150;
                        break;
                    case 5:
                        scrollDupD = scrollDupD + 250;
                        break;
                    case 6:
                        scrollDupD = scrollDupD + 300;
                        break;
                    case 7:
                        scrollDupD = scrollDupD + 350;
                        break;
                    case 8:
                        scrollDupD = scrollDupD + 400;
                        break;


                }
            }

            if (String.valueOf(manager.getType(myKey).charAt(0)).equals("R")) {

                switch (myValue) {
                    case 2:
                        scrollDupR = scrollDupR + 50;
                        break;
                    case 3:
                        scrollDupR = scrollDupR + 100;
                        break;
                    case 4:
                        scrollDupR = scrollDupR + 150;
                        break;
                    case 5:
                        scrollDupR = scrollDupR + 250;
                        break;
                    case 6:
                        scrollDupR = scrollDupR + 300;
                        break;
                    case 7:
                        scrollDupR = scrollDupR + 350;
                        break;
                    case 8:
                        scrollDupR = scrollDupR + 400;
                        break;


                }
            }


        }


        scrollD = scrollD + scrollDupD;
        scrollR = scrollR + scrollDupR;


    }

    private void setScrollCountPair(PhoneNumberItemCollectionDao dao) {
        Integer countD = 0;
        Integer countR = 0;
        Integer scrollCountD = 0;
        Integer scrollCountR = 0;

        for (int i = 0; i < dao.getPhoneNumberItemDaosA().size(); i++) {
            if (String.valueOf(dao.getPhoneNumberItemDaosA().get(i).getType().charAt(0)).equals("D")) {
                countD++;
            }
            if (String.valueOf(dao.getPhoneNumberItemDaosA().get(i).getType().charAt(0)).equals("R")) {
                countR++;
            }
        }

        switch (countD) {
            case 1:
                scrollCountD = scrollCountD + 5;
                break;
            case 2:
                scrollCountD = scrollCountD + 10;
                break;
            case 3:
                scrollCountD = scrollCountD + 15;
                break;
            case 4:
                scrollCountD = scrollCountD + 20;
                break;
            case 5:
                scrollCountD = scrollCountD + 25;
                break;
        }
        switch (countR) {
            case 1:
                scrollCountR = scrollCountR + 5;
                break;
            case 2:
                scrollCountR = scrollCountR + 10;
                break;
            case 3:
                scrollCountR = scrollCountR + 15;
                break;
            case 4:
                scrollCountR = scrollCountR + 20;
                break;
            case 5:
                scrollCountR = scrollCountR + 25;
                break;
        }

        scrollD = scrollD + scrollCountD;
        scrollR = scrollR + scrollCountR;

    }


    private Integer getPercentsD() {
        Integer pDs;
        pDs = percentPo1D + percentPo2D + percentPo3D + percentPo4D + percentPo5D + percentSumD
                + percentPoB1D + percentPoB2D + percentPoB3D + percentPoB4D;
        return pDs;
    }

    private Integer getPercentsR() {
        Integer pRs;
        pRs = percentPo1R + percentPo2R + percentPo3R + percentPo4R + percentPo5R + percentSumR
                + percentPoB1R + percentPoB2R + percentPoB3R + percentPoB4R;
        return pRs;
    }

    private void setPercentD(PhoneNumberItemCollectionDao dao) {


        for (int i = 0; i < dao.getPhoneNumberItemDaosA().size(); i++) {
            String type;
            String t;
            type = dao.getPhoneNumberItemDaosA().get(i).getType();
            t = String.valueOf(type.charAt(0));

            if (t.equals("D")) {

                switch (i) {
                    case 4:  // pair : 24 position 4
                        percentPo1D = 17;
                        pairNumberPercents.add(new PairNumberPercent(dao.getPhoneNumberItemDaosA().get(i).getPhoneNumber(), 17));
                        break;
                    case 3:
                        percentPo2D = 15;
                        pairNumberPercents.add(new PairNumberPercent(dao.getPhoneNumberItemDaosA().get(i).getPhoneNumber(), 15));
                        break;
                    case 2:
                        percentPo3D = 10;
                        pairNumberPercents.add(new PairNumberPercent(dao.getPhoneNumberItemDaosA().get(i).getPhoneNumber(), 10));
                        break;
                    case 1:
                        percentPo4D = 7;
                        pairNumberPercents.add(new PairNumberPercent(dao.getPhoneNumberItemDaosA().get(i).getPhoneNumber(), 7));
                        break;
                    case 0:
                        percentPo5D = 5;
                        pairNumberPercents.add(new PairNumberPercent(dao.getPhoneNumberItemDaosA().get(i).getPhoneNumber(), 5));
                        break;


                }

            }

        }

        for (int i = 0; i < dao.getPhoneNumberItemDaosB().size(); i++) {
            String type;
            String t;
            type = dao.getPhoneNumberItemDaosB().get(i).getType();
            t = String.valueOf(type.charAt(0));

            if (t.equals("D")) {

                switch (i) {

                    case 3: //92
                        percentPoB1D = 10;
                        pairNumberPercents.add(new PairNumberPercent(dao.getPhoneNumberItemDaosB().get(i).getPhoneNumber(), 10));
                        break;
                    case 2:
                        percentPoB2D = 10;
                        pairNumberPercents.add(new PairNumberPercent(dao.getPhoneNumberItemDaosB().get(i).getPhoneNumber(), 10));
                        break;
                    case 1:
                        percentPoB3D = 5;
                        pairNumberPercents.add(new PairNumberPercent(dao.getPhoneNumberItemDaosB().get(i).getPhoneNumber(), 5));
                        break;
                    case 0:
                        percentPoB4D = 3;
                        pairNumberPercents.add(new PairNumberPercent(dao.getPhoneNumberItemDaosB().get(i).getPhoneNumber(), 3));
                        break;

                }

            }

        }

        String type;
        String t;
        type = dao.getPhoneNumberItemDaoSum().getType();
        t = String.valueOf(type.charAt(0));
        if (t.equals("D")) {
            percentSumD = 18;
            pairNumberPercents.add(new PairNumberPercent(dao.getPhoneNumberItemDaoSum().getPhoneNumber(), 18));
        }


    }

    private void setPercentR(PhoneNumberItemCollectionDao dao) {


        for (int i = 0; i < dao.getPhoneNumberItemDaosA().size(); i++) {
            String type;
            String t;
            type = dao.getPhoneNumberItemDaosA().get(i).getType();
            t = String.valueOf(type.charAt(0));

            if (t.equals("R")) {

                switch (i) {
                    case 4:  // pair : 24
                        percentPo1R = 17;
                        pairNumberPercents.add(new PairNumberPercent(dao.getPhoneNumberItemDaosA().get(i).getPhoneNumber(), 17));
                        break;
                    case 3:
                        percentPo2R = 15;
                        pairNumberPercents.add(new PairNumberPercent(dao.getPhoneNumberItemDaosA().get(i).getPhoneNumber(), 15));
                        break;
                    case 2:
                        percentPo3R = 10;
                        pairNumberPercents.add(new PairNumberPercent(dao.getPhoneNumberItemDaosA().get(i).getPhoneNumber(), 10));
                        break;
                    case 1:
                        percentPo4R = 7;
                        pairNumberPercents.add(new PairNumberPercent(dao.getPhoneNumberItemDaosA().get(i).getPhoneNumber(), 7));
                        break;
                    case 0:
                        percentPo5R = 5;
                        pairNumberPercents.add(new PairNumberPercent(dao.getPhoneNumberItemDaosA().get(i).getPhoneNumber(), 5));
                        break;


                }
            }

        }


        for (int i = 0; i < dao.getPhoneNumberItemDaosB().size(); i++) {
            String type;
            String t;
            type = dao.getPhoneNumberItemDaosB().get(i).getType();
            t = String.valueOf(type.charAt(0));

            if (t.equals("R")) {

                switch (i) {

                    case 3:
                        percentPoB4R = 10;
                        pairNumberPercents.add(new PairNumberPercent(dao.getPhoneNumberItemDaosB().get(i).getPhoneNumber(), 10));
                        break;
                    case 2:
                        percentPoB3R = 10;
                        pairNumberPercents.add(new PairNumberPercent(dao.getPhoneNumberItemDaosB().get(i).getPhoneNumber(), 10));
                        break;
                    case 1:
                        percentPoB2R = 5;
                        pairNumberPercents.add(new PairNumberPercent(dao.getPhoneNumberItemDaosB().get(i).getPhoneNumber(), 5));
                        break;
                    case 0:
                        percentPoB1R = 3;
                        pairNumberPercents.add(new PairNumberPercent(dao.getPhoneNumberItemDaosB().get(i).getPhoneNumber(), 3));
                        break;

                }
            }

        }

        String type;
        String t;
        type = dao.getPhoneNumberItemDaoSum().getType();
        t = String.valueOf(type.charAt(0));
        if (t.equals("R")) {
            percentSumR = 18;
            pairNumberPercents.add(new PairNumberPercent(dao.getPhoneNumberItemDaoSum().getPhoneNumber(), 18));


        }


    }


    private List<PairNumberDang> getScrollDangPairAB(PhoneNumberItemCollectionDao dao) {

        List<PairNumberDang> numberDangs = new ArrayList<>();

        NumberPilotManager manager = new NumberPilotManager();


        for (int i = 0; i < dao.getPhoneNumberItemDaosA().size(); i++) {

            String mNumber = dao.getPhoneNumberItemDaosA().get(i).getPhoneNumber();
            char firstC = mNumber.charAt(0);
            char secondC = mNumber.charAt(1);
            String pairDang = String.valueOf(secondC) + String.valueOf(firstC);
            String pairDangLike = String.valueOf(firstC) + String.valueOf(secondC);

            for (int j = 0; j < dao.getPhoneNumberItemDaosB().size(); j++) {
                String dNumber = dao.getPhoneNumberItemDaosB().get(j).getPhoneNumber();
                if (pairDang.equals(dNumber) && !pairDang.equals(pairDangLike)) {
                    Integer mPoint = manager.getPoint(mNumber) + manager.getPoint(dNumber);
                    numberDangs.add(new PairNumberDang("มีคู่เด้ง AB ", mNumber, dNumber, mPoint));
                    Log.d("pairOfdangAB", mNumber + " : " + dNumber);

                }
            }
        }
        return numberDangs;
    }

    private List<PairNumberDang> getScrollDangPairAA(PhoneNumberItemCollectionDao dao) {

        List<PairNumberDang> numberDangsAA = new ArrayList<>();
        NumberPilotManager manager = new NumberPilotManager();

        for (int i = 0; i < dao.getPhoneNumberItemDaosA().size(); i++) {

            String mNumber = dao.getPhoneNumberItemDaosA().get(i).getPhoneNumber();
            char firstC = mNumber.charAt(0);
            char secondC = mNumber.charAt(1);
            String pairDang = String.valueOf(secondC) + String.valueOf(firstC);
            String pairDangLike = String.valueOf(firstC) + String.valueOf(secondC);


            for (int j = 0; j < dao.getPhoneNumberItemDaosA().size(); j++) {
                String dNumber = dao.getPhoneNumberItemDaosA().get(j).getPhoneNumber();
                if (pairDang.equals(dNumber) && !pairDang.equals(pairDangLike)) {

                    Integer mPoint = manager.getPoint(mNumber) + manager.getPoint(dNumber);
                    numberDangsAA.add(new PairNumberDang("มีคู่เด้ง AA ", mNumber, dNumber, mPoint));
                    Log.d("pairOfdangAA", mNumber + " : " + dNumber);


                }
            }
        }
        return numberDangsAA;

    }

    private List<PairNumberDang> getScrollDangPairBB(PhoneNumberItemCollectionDao dao) {

        List<PairNumberDang> numberDangsBB = new ArrayList<>();
        NumberPilotManager manager = new NumberPilotManager();


        for (int i = 0; i < dao.getPhoneNumberItemDaosB().size(); i++) {

            String mNumber = dao.getPhoneNumberItemDaosB().get(i).getPhoneNumber();
            char firstC = mNumber.charAt(0);
            char secondC = mNumber.charAt(1);
            String pairDang = String.valueOf(secondC) + String.valueOf(firstC);
            String pairDangLike = String.valueOf(firstC) + String.valueOf(secondC);


            for (int j = 0; j < dao.getPhoneNumberItemDaosB().size(); j++) {
                String dNumber = dao.getPhoneNumberItemDaosB().get(j).getPhoneNumber();
                if (pairDang.equals(dNumber) && !pairDang.equals(pairDangLike)) {

                    Integer mPoint = manager.getPoint(mNumber) + manager.getPoint(dNumber);
                    numberDangsBB.add(new PairNumberDang("มีคู่เด้ง BB ", mNumber, dNumber, mPoint));
                    Log.d("pairOfdangBB", mNumber + " : " + dNumber);


                }
            }
        }
        return numberDangsBB;
    }

    private PairNumberDang getScrollDangPairDang(PhoneNumberItemCollectionDao dao) {

        NumberPilotManager manager = new NumberPilotManager();


        String mNumber = dao.getPhoneNumberItemDaosA().get(4).getPhoneNumber();
        char firstC = mNumber.charAt(0);
        char secondC = mNumber.charAt(1);
        String pairDang = String.valueOf(secondC) + String.valueOf(firstC);
        String pairDangLike = String.valueOf(firstC) + String.valueOf(secondC);


        String dNumber = dao.getPhoneNumberItemDaosB().get(3).getPhoneNumber();
        if (pairDang.equals(dNumber) && !pairDang.equals(pairDangLike)) {

            Integer mPoint = manager.getPoint(mNumber) + manager.getPoint(dNumber);

            return new PairNumberDang("มีคู่ 2 เด้ง", mNumber, dNumber, mPoint);

        }


        return null;
    }

    private PairNumberDang getScrollDangPairSumFirst(PhoneNumberItemCollectionDao dao) {

        NumberPilotManager manager = new NumberPilotManager();
        String mNumber = dao.getPhoneNumberItemDaosA().get(4).getPhoneNumber();
        char firstC = mNumber.charAt(0);
        char secondC = mNumber.charAt(1);
        String pairDang = String.valueOf(secondC) + String.valueOf(firstC);
        String dNumber = dao.getPhoneNumberItemDaosB().get(3).getPhoneNumber();

        if (pairDang.equals(dNumber) && !pairDang.equals(mNumber)) {

            String dSum = dao.getPhoneNumberItemDaoSum().getPhoneNumber();
            if (mNumber.equals(dSum)) {
                Integer mPoint = manager.getPoint(mNumber) + manager.getPoint(dSum);
                return new PairNumberDang("มีคู่ 3 เด้ง", pairDang, dSum, mPoint);

            }
        }
        return null;
    }

    private PairNumberDang getScrollDangPairSumSecond(PhoneNumberItemCollectionDao dao) {

        NumberPilotManager manager = new NumberPilotManager();
        String mNumber = dao.getPhoneNumberItemDaosA().get(4).getPhoneNumber();
        char firstC = mNumber.charAt(0);
        char secondC = mNumber.charAt(1);
        String pairDang = String.valueOf(secondC) + String.valueOf(firstC);
        String dNumber = dao.getPhoneNumberItemDaosB().get(3).getPhoneNumber();

        if (pairDang.equals(dNumber) && !pairDang.equals(mNumber)) {

            String dSum = dao.getPhoneNumberItemDaoSum().getPhoneNumber();

            if (dNumber.equals(dSum)) {
                Integer mPoint = manager.getPoint(dNumber) + manager.getPoint(dSum);
                return new PairNumberDang("มีคู่ 3 เด้ง", dNumber, dSum, mPoint);
            }
        }


        return null;

    }

    private void setScrollDang(PhoneNumberItemCollectionDao dao) {

        NumberPilotManager manager = new NumberPilotManager();

        List<PairNumberDang> allPairNumberDangs = new ArrayList<>();

        //allPairNumberDangs.addAll(getScrollDangPairAA(dao));
        allPairNumberDangs.addAll(getScrollDangPairAB(dao));
        //allPairNumberDangs.addAll(getScrollDangPairBB(dao));

        PairNumberDang dangDao = getScrollDangPairDang(dao);
        PairNumberDang tripleDangDaoFirst = getScrollDangPairSumFirst(dao);



        if (tripleDangDaoFirst != null) {

            String first = tripleDangDaoFirst.pairNumberFirst;
            String second = tripleDangDaoFirst.pairNumberSecond;
            String dangTypeFirst = manager.getType(first);


            if (String.valueOf(dangTypeFirst.charAt(0)).equals("R")) {
                tvPairDangThirdFirst.setBackgroundResource(R.drawable.pilot_selector_red);
                tvPairDangThirdSecond.setBackgroundResource(R.drawable.pilot_selector_red);

            } else {
                tvPairDangThirdFirst.setBackgroundResource(R.drawable.pilot_selector_green);
                tvPairDangThirdSecond.setBackgroundResource(R.drawable.pilot_selector_green);

            }
            tvPairDangThirdFirst.setText(first);
            tvPairDangThirdSecond.setText(second);


        } else {
            tvPairDangThirdFirst.setText("");
            tvPairDangThirdFirst.setBackgroundResource(R.drawable.pilot_selector_null);

            tvPairDangThirdSecond.setText("");
            tvPairDangThirdSecond.setBackgroundResource(R.drawable.pilot_selector_null);

        }



        if (dangDao != null) {

            if (dangDao.pairNumberFirst != null) {
                String first = dangDao.pairNumberFirst;
                String dTypeFirst = manager.getType(first);
                if (String.valueOf(dTypeFirst.charAt(0)).equals("R")) {
                    tvPairDangFirst.setBackgroundResource(R.drawable.pilot_selector_red);
                    tvPairDangFirst.setText(first);
                } else {
                    tvPairDangFirst.setBackgroundResource(R.drawable.pilot_selector_green);
                    tvPairDangFirst.setText(first);
                }

            }

            if (dangDao.pairNumberSecond != null) {
                String second = dangDao.pairNumberSecond;
                String dTypeSecond = manager.getType(second);
                if (String.valueOf(dTypeSecond.charAt(0)).equals("R")) {
                    tvPairDangSecond.setBackgroundResource(R.drawable.pilot_selector_red);
                    tvPairDangSecond.setText(second);
                } else {
                    tvPairDangSecond.setBackgroundResource(R.drawable.pilot_selector_green);
                    tvPairDangSecond.setText(second);
                }
            }

        } else {

            tvPairDangFirst.setText("");
            tvPairDangFirst.setBackgroundResource(R.drawable.pilot_selector_null);
            tvPairDangSecond.setText("");
            tvPairDangSecond.setBackgroundResource(R.drawable.pilot_selector_null);

        }


/*        mPhonePairDangAdapter = new PhonePairDangAdapter();
        aGridView.setAdapter(mPhonePairDangAdapter);
        mPhonePairDangAdapter.setPairDang(allPairNumberDangs);*/

    }


    /****************
     * Listeners
     *****************/

    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            if (phone_number.getText().length() == 10) {
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                okDao(phone_number.getText().toString());

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
        checkState(savedInstanceState);

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
        Log.d("LifeCycle", "onSaveInstanceState");
        outState.putParcelable("phoneNumberItemCollectionDao", phoneNumberItemCollectionDao);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("LifeCycle", "onDestroyView");
    }
}

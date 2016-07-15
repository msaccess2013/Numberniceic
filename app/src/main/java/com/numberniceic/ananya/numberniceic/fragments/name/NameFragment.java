package com.numberniceic.ananya.numberniceic.fragments.name;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.numberniceic.ananya.numberniceic.dao.name.LekSatItemCollectionDao;
import com.numberniceic.ananya.numberniceic.dao.name.LekSatNameItemDao;
import com.numberniceic.ananya.numberniceic.dao.name.LekSatNickNameItemDao;
import com.numberniceic.ananya.numberniceic.dao.name.LekSatSurNameItemDao;
import com.numberniceic.ananya.numberniceic.dao.name.LekShaItemCollectionDao;
import com.numberniceic.ananya.numberniceic.dao.name.LekShaNameDao;
import com.numberniceic.ananya.numberniceic.dao.name.LekShaNickNameDao;
import com.numberniceic.ananya.numberniceic.dao.name.LekShaSurNameDao;
import com.numberniceic.ananya.numberniceic.managers.name.NumberKalakineeManager;
import com.numberniceic.ananya.numberniceic.managers.name.NumberLekSatManager;
import com.numberniceic.ananya.numberniceic.managers.name.NumberStarManager;
import com.numberniceic.ananya.numberniceic.views.person.NameScrolling;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NameFragment extends Fragment {

    private String theDay;
    private InputMethodManager imm;
    private LekSatItemCollectionDao satDao = new LekSatItemCollectionDao();
    private LekShaItemCollectionDao shaDao = new LekShaItemCollectionDao();
    private NameScrolling nestedScrollView;
    private Button btnBirthDay;
    private TextView tvBirthDay;
    private EditText edName, edSurname, edNickname;
    private Button btnCal, btnReset;
    private TextView tvNameLeksat, tvSurNameLekSat, tvNickNameLekSat;
    private TextView tvNameSumSat, tvSurnameSumSat, tvNicknameSumSat, tvNamePlusSur;
    private TextView tvNameSumSatFak, tvSurnameSumSatFak, tvNicknameSumSatFak, tvNamePlusSurFak;
    private TextView tvNameSumSha, tvSurnameSumSha, tvNicknameSumSha, tvNamePlusSurSha;
    private TextView tvNameSumShaFak, tvSurnameSumShaFak, tvNicknameSumShaFak, tvNamePlusSurShaFak;
    private TextView tvKalakinee;
    private TextView tvNameShadow, tvSurNameShadow, tvNickNameShadow;
    private TextView tvNameAyatana, tvSurNameAyatana, tvNickNameAyatana, tvNamePlusAyatana;


    View rootView;

    public static NameFragment newInstance() {

        NameFragment nameFragment = new NameFragment();
        return nameFragment;

    }

    public NameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {

        Log.d("NameFragLife", "onCreateView");

        rootView = inflater.inflate(R.layout.fragment_name, container, false);


        initInstance(rootView);


        btnBirthDay.setOnClickListener(bornFragment);
        tvBirthDay.setOnClickListener(bornFragment);
        btnCal.setOnClickListener(deCodeListener);
        btnReset.setOnClickListener(resetListener);


        return rootView;
    }


    /*****************
     * method
     *****************/

    public void setBirth(String msg) {
        this.theDay = msg;
        switch (theDay) {
            case "Sunday":
                tvBirthDay.setText("เกิดวันอาทิตย์");
                nestedScrollView.setEnableScrolling(true);
                Toast.makeText(getContext(), "คุณเ" + tvBirthDay.getText(), Toast.LENGTH_SHORT).show();
                break;
            case "Monday":
                tvBirthDay.setText("เกิดวันจันทร์");
                nestedScrollView.setEnableScrolling(true);
                Toast.makeText(getContext(), "คุณเ" + tvBirthDay.getText(), Toast.LENGTH_SHORT).show();
                break;
            case "Tuesday":
                tvBirthDay.setText("เกิดวันอังคาร");
                nestedScrollView.setEnableScrolling(true);
                Toast.makeText(getContext(), "คุณเ" + tvBirthDay.getText(), Toast.LENGTH_SHORT).show();
                break;
            case "WednesdayPm":
                tvBirthDay.setText("เกิดวันพุธ กลางคืน");
                nestedScrollView.setEnableScrolling(true);
                Toast.makeText(getContext(), "คุณเ" + tvBirthDay.getText(), Toast.LENGTH_SHORT).show();
                break;
            case "WednesdayAm":
                tvBirthDay.setText("เกิดวันพุธ กลางวัน");
                nestedScrollView.setEnableScrolling(true);
                Toast.makeText(getContext(), "คุณเ" + tvBirthDay.getText(), Toast.LENGTH_SHORT).show();
                break;
            case "Thursday":
                tvBirthDay.setText("เกิดวันพฤหัสบดี");
                nestedScrollView.setEnableScrolling(true);
                Toast.makeText(getContext(), "คุณเ" + tvBirthDay.getText(), Toast.LENGTH_SHORT).show();
                break;
            case "Friday":
                tvBirthDay.setText("เกิดวันศุกร์");
                nestedScrollView.setEnableScrolling(true);
                Toast.makeText(getContext(), "คุณเ" + tvBirthDay.getText(), Toast.LENGTH_SHORT).show();
                break;
            case "Saturday":
                tvBirthDay.setText("เกิดวันเสาร์");
                nestedScrollView.setEnableScrolling(true);
                Toast.makeText(getContext(), "คุณเ" + tvBirthDay.getText(), Toast.LENGTH_SHORT).show();
                break;

        }


    }

    private void decodeName() {

        tvNameLeksat.setText("");

        if (satDao.getLekSatNameItemDaos() != null) {

            satDao.getLekSatNameItemDaos().clear();
        }


        if (edName.getText().length() > 0) {

            List<LekSatNameItemDao> satNameItemDaos = new ArrayList<>();

            String name = edName.getText().toString();
            StringBuilder sb = new StringBuilder();

            NumberLekSatManager manager = new NumberLekSatManager();

            if (name.length() > 0) {

                for (int i = 0; i < name.length(); i++) {
                    manager.setLekSat(name.charAt(i));
                    if (manager.getLekSat() != null) {
                        String sNumber = manager.getLekSat();

                        satNameItemDaos.add(new LekSatNameItemDao(sNumber, name.charAt(i)));


                        String cNameSat = String.valueOf(name.charAt(i));

                        sb.append(cNameSat + ":" + sNumber + "  ");

                    }
                }
                tvNameLeksat.setText(sb);
                satDao.setLekSatNameItemDaos(satNameItemDaos);


            }

        }
    }

    private void decodeSurName() {

        tvSurNameLekSat.setText("");


        if (satDao.getLekSatSurNameItemDaos() != null) {
            satDao.getLekSatSurNameItemDaos().clear();
        }

        List<LekSatSurNameItemDao> satSurNameItemDaos = new ArrayList<>();

        String surname = edSurname.getText().toString();

        NumberLekSatManager manager = new NumberLekSatManager();
        StringBuilder sb = new StringBuilder();
        if (surname.length() > 0) {
            for (int i = 0; i < surname.length(); i++) {
                manager.setLekSat(surname.charAt(i));
                if (manager.getLekSat() != null) {
                    String sNumber = manager.getLekSat();
                    String cSurname = String.valueOf(surname.charAt(i));
                    sb.append(" " + cSurname + ":" + sNumber);
                    satSurNameItemDaos.add(new LekSatSurNameItemDao(surname.charAt(i), sNumber));
                }
            }
        }

        satDao.setLekSatSurNameItemDaos(satSurNameItemDaos);
        tvSurNameLekSat.setText(sb);


    }

    private void decodeNickName() {


        tvNickNameLekSat.setText("");


        if (satDao.getLekSatNickNameItemDaos() != null) {
            satDao.getLekSatNickNameItemDaos().clear();
        }

        List<LekSatNickNameItemDao> satNickNameItemDaos = new ArrayList<>();

        String nickname = edNickname.getText().toString();

        NumberLekSatManager manager = new NumberLekSatManager();

        StringBuilder sb = new StringBuilder();

        if (edNickname.getText().toString().length() > 0) {

            for (int i = 0; i < nickname.length(); i++) {
                manager.setLekSat(nickname.charAt(i));
                if (manager.getLekSat() != null) {
                    String sNumber = manager.getLekSat();
                    String cNickName = String.valueOf(nickname.charAt(i));

                    sb.append(cNickName + ":" + sNumber + "   ");

                    satNickNameItemDaos.add(new LekSatNickNameItemDao(nickname.charAt(i), sNumber));
                }
            }

            satDao.setLekSatNickNameItemDaos(satNickNameItemDaos);
            tvNickNameLekSat.setText(sb);


        }
    }

    private void sumSat() {

        Integer sumSatName = 0;
        Integer sumSatSurName = 0;
        Integer sumSatNickName = 0;
        Integer sumSatNamePlusSur = 0;


        if (satDao.getLekSatNameItemDaos() != null) {

            if (satDao.getLekSatNameItemDaos().size() > 0) {

                for (int i = 0; i < satDao.getLekSatNameItemDaos().size(); i++) {

                    Integer mNumb = Integer.valueOf(satDao.getLekSatNameItemDaos().get(i).getNumber());

                    sumSatName = sumSatName + mNumb;

                }
            }
        }

        if (satDao.getLekSatSurNameItemDaos() != null) {

            for (int i = 0; i < satDao.getLekSatSurNameItemDaos().size(); i++) {

                sumSatSurName = sumSatSurName + Integer.valueOf(satDao.getLekSatSurNameItemDaos().get(i).getNumber());

            }
        }
        if (satDao.getLekSatNickNameItemDaos() != null && edNickname.getText().length() > 0) {
            for (int i = 0; i < satDao.getLekSatNickNameItemDaos().size(); i++) {

                sumSatNickName = sumSatNickName + Integer.valueOf(satDao.getLekSatNickNameItemDaos().get(i).getNumber());
            }
        }


        tvNameSumSat.setText("");
        tvSurnameSumSat.setText("");
        tvNicknameSumSat.setText("");
        tvNamePlusSur.setText("");

        if (sumSatName.toString().length() == 3) {
            char c1 = sumSatName.toString().charAt(0);
            char c2 = sumSatName.toString().charAt(1);
            char c3 = sumSatName.toString().charAt(2);

            String fPair = String.valueOf(c1) + String.valueOf(c2);
            String sPair = String.valueOf(c2) + String.valueOf(c3);

            if (!fPair.equals(sPair)) {
                tvNameSumSat.setText(fPair);
                tvNameSumSatFak.setText(sPair);
            }

        } else {
            if (sumSatName != 0)
            tvNameSumSat.setText(String.valueOf(sumSatName));
        }


        if (sumSatSurName.toString().length() == 3) {
            char c1 = sumSatSurName.toString().charAt(0);
            char c2 = sumSatSurName.toString().charAt(1);
            char c3 = sumSatSurName.toString().charAt(2);

            String fPair = String.valueOf(c1) + String.valueOf(c2);
            String sPair = String.valueOf(c2) + String.valueOf(c3);

            if (!fPair.equals(sPair)) {
                tvSurnameSumSat.setText(fPair);
                tvSurnameSumSatFak.setText(sPair);
            }

        } else {
            if (sumSatSurName != 0)
            tvSurnameSumSat.setText(String.valueOf(sumSatSurName));
        }


        if (sumSatNickName.toString().length() == 3) {
            char c1 = sumSatNickName.toString().charAt(0);
            char c2 = sumSatNickName.toString().charAt(1);
            char c3 = sumSatNickName.toString().charAt(2);

            String fPair = String.valueOf(c1) + String.valueOf(c2);
            String sPair = String.valueOf(c2) + String.valueOf(c3);

            if (!fPair.equals(sPair)) {
                tvNicknameSumSat.setText(fPair);
                tvNicknameSumSatFak.setText(sPair);
            }

        } else {
            if (sumSatNickName != 0)
            tvNicknameSumSat.setText(String.valueOf(sumSatNickName));
        }


        sumSatNamePlusSur = sumSatName + sumSatSurName;
        if (sumSatNamePlusSur.toString().length() == 3) {
            char c1 = sumSatNamePlusSur.toString().charAt(0);
            char c2 = sumSatNamePlusSur.toString().charAt(1);
            char c3 = sumSatNamePlusSur.toString().charAt(2);

            String fPair = String.valueOf(c1) + String.valueOf(c2);
            String sPair = String.valueOf(c2) + String.valueOf(c3);

            if (!fPair.equals(sPair)) {
                tvNamePlusSur.setText(fPair);
                tvNamePlusSurFak.setText(sPair);
            }

        } else {

            if (sumSatNamePlusSur != 0)
            tvNamePlusSur.setText(String.valueOf(sumSatNamePlusSur));
        }


    }

    private void sumShadow() {
        Integer sumShaName = 0;
        Integer sumShaSurname = 0;
        Integer sumShaNickname = 0;

        if (shaDao.getLekShaNameDaoList() != null) {

            if (shaDao.getLekShaNameDaoList().size() > 0) {
                for (int i = 0; i < shaDao.getLekShaNameDaoList().size(); i++) {
                    String sNumb = shaDao.getLekShaNameDaoList().get(i).getcNumber();

                    if (sNumb != null) {

                        sumShaName = sumShaName + Integer.valueOf(sNumb);
                    }
                }

                if (sumShaName.toString().length() == 3) {

                    String fPair = String.valueOf(sumShaName.toString().charAt(0)) + String.valueOf(sumShaName.toString().charAt(1));
                    String sPair = String.valueOf(sumShaName.toString().charAt(1)) + String.valueOf(sumShaName.toString().charAt(2));

                    if (!fPair.equals(sPair)) {
                        tvNameSumSha.setText(fPair);
                        tvNameSumShaFak.setText(sPair);
                    }


                } else {
                    if (sumShaName != 0)
                    tvNameSumSha.setText(String.valueOf(sumShaName));
                }
            }
        }


        if (shaDao.getLekShaSurNameDaoList() != null) {

            if (shaDao.getLekShaSurNameDaoList().size() > 0) {
                for (int i = 0; i < shaDao.getLekShaSurNameDaoList().size(); i++) {
                    String sNumb = shaDao.getLekShaSurNameDaoList().get(i).getcNumber();

                    if (sNumb != null) {

                        sumShaSurname = sumShaSurname + Integer.valueOf(sNumb);
                    }
                }
                if (sumShaSurname.toString().length() == 3) {

                    String fPair = String.valueOf(sumShaSurname.toString().charAt(0)) + String.valueOf(sumShaSurname.toString().charAt(1));
                    String sPair = String.valueOf(sumShaSurname.toString().charAt(1)) + String.valueOf(sumShaSurname.toString().charAt(2));

                    if (!fPair.equals(sPair)) {
                        tvSurnameSumSha.setText(fPair);
                        tvSurnameSumShaFak.setText(sPair);
                    }


                } else {
                    if (sumShaSurname != 0)
                    tvSurnameSumSha.setText(String.valueOf(sumShaSurname));
                }
            }
        }


        Integer shaPlue = sumShaName + sumShaSurname;

        if (shaPlue.toString().length() == 3) {

            String fPair = String.valueOf(shaPlue.toString().charAt(0)) + String.valueOf(shaPlue.toString().charAt(1));
            String sPair = String.valueOf(shaPlue.toString().charAt(1)) + String.valueOf(shaPlue.toString().charAt(2));

            if (!fPair.equals(sPair)) {
                tvNamePlusSurSha.setText(fPair);
                tvNamePlusSurShaFak.setText(sPair);
            }


        } else {
            if (shaPlue != 0)
            tvNamePlusSurSha.setText(String.valueOf(shaPlue));
        }


        if (shaDao.getLekShaNickNameDaoList() != null) {

            if (shaDao.getLekShaNickNameDaoList().size() > 0) {
                for (int i = 0; i < shaDao.getLekShaNickNameDaoList().size(); i++) {
                    String sNumb = shaDao.getLekShaNickNameDaoList().get(i).getcNumber();

                    if (sNumb != null) {

                        sumShaNickname = sumShaNickname + Integer.valueOf(sNumb);
                    }
                }
                if (sumShaNickname.toString().length() == 3) {

                    String fPair = String.valueOf(sumShaNickname.toString().charAt(0)) + String.valueOf(sumShaNickname.toString().charAt(1));
                    String sPair = String.valueOf(sumShaNickname.toString().charAt(1)) + String.valueOf(sumShaNickname.toString().charAt(2));

                    if (!fPair.equals(sPair)) {
                        tvNicknameSumSha.setText(fPair);
                        tvNicknameSumShaFak.setText(sPair);
                    }


                } else {
                    if (sumShaNickname != 0)
                    tvNicknameSumSha.setText(String.valueOf(sumShaNickname));
                }
            }
        }


    }

    private void deKalakinee(String theDay) {
        tvKalakinee.setText("");

        int mName = edName.getText().toString().length();
        String sName = edName.getText().toString();
        int mSurname = edSurname.getText().toString().length();
        String sSurname = edSurname.getText().toString();
        int mNickname = edNickname.getText().toString().length();
        String sNickname = edNickname.getText().toString();


        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < mName; i++) {

            char cName = sName.charAt(i);

            NumberKalakineeManager kManager = new NumberKalakineeManager();

            kManager.setLekKalakinee(cName, theDay);

            String kNumber = kManager.getLekKalakini();

            if (kNumber != null)
                sb.append("  " + kNumber + "  ");

        }

        for (int i = 0; i < mSurname; i++) {

            char cSurName = sSurname.charAt(i);

            NumberKalakineeManager kManager = new NumberKalakineeManager();

            kManager.setLekKalakinee(cSurName, theDay);

            String kNumber = kManager.getLekKalakini();

            if (kNumber != null)
                sb.append("  " + kNumber + "  ");

        }

        for (int i = 0; i < mNickname; i++) {

            char cNickName = sNickname.charAt(i);

            NumberKalakineeManager kManager = new NumberKalakineeManager();

            kManager.setLekKalakinee(cNickName, theDay);

            String kNumber = kManager.getLekKalakini();

            if (kNumber != null)
                sb.append("  " + kNumber + "  ");

        }

        tvKalakinee.setText(sb.toString());


    }

    private void deStarName() {

        tvNameShadow.setText("");

        if (shaDao.getLekShaNameDaoList() != null) {
            shaDao.getLekShaNameDaoList().clear();
        }

        List<LekShaNameDao> lekShaNameDaos = new ArrayList<>();


        int mName = edName.getText().toString().length();
        String sName = edName.getText().toString();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < mName; i++) {

            char cName = sName.charAt(i);

            NumberStarManager sManager = new NumberStarManager();

            sManager.setNumberStar(cName);

            String sNumber = sManager.getNumberStar();
            //เก็บอักษรแต่ตัวเลขจะเป็น null
            if (sNumber != null) {
                sb.append(cName + ":" + sNumber + "  ");
                lekShaNameDaos.add(new LekShaNameDao(cName, sNumber));
            }

        }

        tvNameShadow.setText("  " + sb.toString() + "  ");
        shaDao.setLekShaNameDaoList(lekShaNameDaos);


    }

    private void deStarSurName() {
        tvSurNameShadow.setText("");

        List<LekShaSurNameDao> lekShaSurNameDaos = new ArrayList<>();

        int mSurName = edSurname.getText().toString().length();
        String sSurName = edSurname.getText().toString();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < mSurName; i++) {

            char cSurName = sSurName.charAt(i);

            NumberStarManager sManager = new NumberStarManager();

            sManager.setNumberStar(cSurName);

            String sNumber = sManager.getNumberStar();

            if (sNumber != null) {
                sb.append(cSurName + ":" + sNumber + "  ");
                lekShaSurNameDaos.add(new LekShaSurNameDao(cSurName, sNumber));
            }

        }

        shaDao.setLekShaSurNameDaoList(lekShaSurNameDaos);

        tvSurNameShadow.setText(sb);


    }

    private void deStarNickName() {

        tvNickNameShadow.setText("");

        List<LekShaNickNameDao> lekShaNickNameDaos = new ArrayList<>();

        int mNickName = edNickname.getText().toString().length();
        String sNickName = edNickname.getText().toString();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < mNickName; i++) {

            char cNickName = sNickName.charAt(i);

            NumberStarManager sManager = new NumberStarManager();

            sManager.setNumberStar(cNickName);

            String sNumber = sManager.getNumberStar();

            if (sNumber != null) {
                sb.append(cNickName + ":" + sNumber + "  ");
                lekShaNickNameDaos.add(new LekShaNickNameDao(cNickName, sNumber));
            }

        }

        shaDao.setLekShaNickNameDaoList(lekShaNickNameDaos);
        tvNickNameShadow.setText(sb);


    }


    private void ayatanaName() {

        if (tvNameSumSha.getText().length() > 0 && tvNameSumShaFak.getText().length() == 0) {

            String cName = tvNameSumSha.getText().toString();


            if (cName.length() == 1) {
                Integer c1 = Integer.valueOf(String.valueOf(cName.charAt(0)));
                tvNameAyatana.setText(String.valueOf(c1));
            }

            if (cName.length() == 2) {
                Integer c1 = Integer.valueOf(String.valueOf(cName.charAt(0)));
                Integer c2 = Integer.valueOf(String.valueOf(cName.charAt(1)));

                if (c2 > 5) {
                    tvNameAyatana.setText(String.valueOf(c2));
                } else {
                    int x = c1 + c2;
                    if (String.valueOf(x).length() == 2) {

                        char x1 = String.valueOf(x).charAt(0);
                        char x2 = String.valueOf(x).charAt(1);

                        int z = Integer.valueOf(String.valueOf(x1)) + Integer.valueOf(String.valueOf(x2));
                        tvNameAyatana.setText(String.valueOf(z));

                    } else {
                        tvNameAyatana.setText(String.valueOf(x));
                    }
                }

            }
        } else if (tvNameSumShaFak.getText().length() > 0) {

            String cNameSha = tvNameSumSha.getText().toString();
            String cNameShaFak = tvNameSumShaFak.getText().toString();

            Integer c1 = Integer.valueOf(String.valueOf(cNameSha.charAt(0)));
            Integer c2 = Integer.valueOf(String.valueOf(cNameSha.charAt(1)));
            Integer c3 = Integer.valueOf(String.valueOf(cNameShaFak.charAt(1)));


            int x = c1 + c2 + c3;

            if (x >= 10){
                int x1 = Integer.parseInt(String.valueOf(String.valueOf(x).charAt(0)));
                int x2 = Integer.parseInt(String.valueOf(String.valueOf(x).charAt(1)));
                int z = x1 + x2;
                tvNameAyatana.setText(String.valueOf(z));
            }else {
                tvNameAyatana.setText(String.valueOf(x));

            }
        }




    }
    private void ayatanaSurName() {

        if (tvSurnameSumSha.getText().length() > 0 && tvSurnameSumShaFak.getText().length() == 0) {

            String cSurName = tvSurnameSumSha.getText().toString();


            if (cSurName.length() == 1) {
                Integer c1 = Integer.valueOf(String.valueOf(cSurName.charAt(0)));
                tvSurNameAyatana.setText(String.valueOf(c1));
            }

            if (cSurName.length() == 2) {
                Integer c1 = Integer.valueOf(String.valueOf(cSurName.charAt(0)));
                Integer c2 = Integer.valueOf(String.valueOf(cSurName.charAt(1)));

                if (c2 > 5) {
                    tvSurNameAyatana.setText(String.valueOf(c2));
                } else {
                    int x = c1 + c2;
                    if (String.valueOf(x).length() == 2) {

                        char x1 = String.valueOf(x).charAt(0);
                        char x2 = String.valueOf(x).charAt(1);

                        int z = Integer.valueOf(String.valueOf(x1)) + Integer.valueOf(String.valueOf(x2));
                        tvSurNameAyatana.setText(String.valueOf(z));

                    } else {
                        tvSurNameAyatana.setText(String.valueOf(x));
                    }
                }

            }
        } else if (tvSurnameSumShaFak.getText().length() > 0) {

            String cSurNameSha = tvSurnameSumSha.getText().toString();
            String cSurNameShaFak = tvSurnameSumShaFak.getText().toString();

            Integer c1 = Integer.valueOf(String.valueOf(cSurNameSha.charAt(0)));
            Integer c2 = Integer.valueOf(String.valueOf(cSurNameSha.charAt(1)));
            Integer c3 = Integer.valueOf(String.valueOf(cSurNameShaFak.charAt(1)));


            int x = c1 + c2 + c3;

            if (x >= 10){
                int x1 = Integer.parseInt(String.valueOf(String.valueOf(x).charAt(0)));
                int x2 = Integer.parseInt(String.valueOf(String.valueOf(x).charAt(1)));
                int z = x1 + x2;
                tvSurNameAyatana.setText(String.valueOf(z));
            }else {
                tvSurNameAyatana.setText(String.valueOf(x));

            }
        }




    }
    private void ayatanaNickName() {

        if (tvNicknameSumSha.getText().length() > 0 && tvNicknameSumShaFak.getText().length() == 0) {

            String cNickName = tvNicknameSumSha.getText().toString();


            if (cNickName.length() == 1) {
                Integer c1 = Integer.valueOf(String.valueOf(cNickName.charAt(0)));
                tvNickNameAyatana.setText(String.valueOf(c1));
            }

            if (cNickName.length() == 2) {
                Integer c1 = Integer.valueOf(String.valueOf(cNickName.charAt(0)));
                Integer c2 = Integer.valueOf(String.valueOf(cNickName.charAt(1)));

                if (c2 > 5) {
                    tvNickNameAyatana.setText(String.valueOf(c2));
                } else {
                    int x = c1 + c2;
                    if (String.valueOf(x).length() == 2) {

                        char x1 = String.valueOf(x).charAt(0);
                        char x2 = String.valueOf(x).charAt(1);

                        int z = Integer.valueOf(String.valueOf(x1)) + Integer.valueOf(String.valueOf(x2));
                        tvNickNameAyatana.setText(String.valueOf(z));

                    } else {
                        tvNickNameAyatana.setText(String.valueOf(x));
                    }
                }

            }
        } else if (tvNicknameSumShaFak.getText().length() > 0) {

            String cNickNameSha = tvNicknameSumSha.getText().toString();
            String cNickNameShaFak = tvNicknameSumShaFak.getText().toString();

            Integer c1 = Integer.valueOf(String.valueOf(cNickNameSha.charAt(0)));
            Integer c2 = Integer.valueOf(String.valueOf(cNickNameSha.charAt(1)));
            Integer c3 = Integer.valueOf(String.valueOf(cNickNameShaFak.charAt(1)));


            int x = c1 + c2 + c3;

            if (x >= 10){
                int x1 = Integer.parseInt(String.valueOf(String.valueOf(x).charAt(0)));
                int x2 = Integer.parseInt(String.valueOf(String.valueOf(x).charAt(1)));
                int z = x1 + x2;
                tvNickNameAyatana.setText(String.valueOf(z));
            }else {
                tvNickNameAyatana.setText(String.valueOf(x));

            }
        }




    }
    private void ayatanaNamePlus() {

        if (tvNamePlusSurSha.getText().length() > 0 && tvNamePlusSurShaFak.getText().length() == 0) {

            String cNamePlus = tvNamePlusSurSha.getText().toString();


            if (cNamePlus.length() == 1) {
                Integer c1 = Integer.valueOf(String.valueOf(cNamePlus.charAt(0)));
                tvNamePlusAyatana.setText(String.valueOf(c1));
            }

            if (cNamePlus.length() == 2) {
                Integer c1 = Integer.valueOf(String.valueOf(cNamePlus.charAt(0)));
                Integer c2 = Integer.valueOf(String.valueOf(cNamePlus.charAt(1)));

                if (c2 > 5) {
                    tvNamePlusAyatana.setText(String.valueOf(c2));
                } else {
                    int x = c1 + c2;
                    if (String.valueOf(x).length() == 2) {

                        char x1 = String.valueOf(x).charAt(0);
                        char x2 = String.valueOf(x).charAt(1);

                        int z = Integer.valueOf(String.valueOf(x1)) + Integer.valueOf(String.valueOf(x2));
                        tvNamePlusAyatana.setText(String.valueOf(z));

                    } else {
                        tvNamePlusAyatana.setText(String.valueOf(x));
                    }
                }

            }
        } else if (tvNamePlusSurSha.getText().length() > 1) {

            String cNickNameSha = tvNamePlusSurSha.getText().toString();
            String cNickNameShaFak = tvNamePlusSurShaFak.getText().toString();

            Integer c1 = Integer.valueOf(String.valueOf(cNickNameSha.charAt(0)));
            Integer c2 = Integer.valueOf(String.valueOf(cNickNameSha.charAt(1)));
            Integer c3 = Integer.valueOf(String.valueOf(cNickNameShaFak.charAt(1)));


            int x = c1 + c2 + c3;

            if (x >= 10){
                int x1 = Integer.parseInt(String.valueOf(String.valueOf(x).charAt(0)));
                int x2 = Integer.parseInt(String.valueOf(String.valueOf(x).charAt(1)));
                int z = x1 + x2;
                tvNamePlusAyatana.setText(String.valueOf(z));
            }else {
                tvNamePlusAyatana.setText(String.valueOf(x));

            }
        }




    }


    private void initInstance(View rootView) {
        imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        nestedScrollView = (NameScrolling) rootView.findViewById(R.id.nsvName);

        btnBirthDay = (Button) rootView.findViewById(R.id.btnBirthDay);
        tvBirthDay = (TextView) rootView.findViewById(R.id.tvBirthDay);

        edName = (EditText) rootView.findViewById(R.id.edName);
        edSurname = (EditText) rootView.findViewById(R.id.edSurname);
        edNickname = (EditText) rootView.findViewById(R.id.edNiceName);
        btnCal = (Button) rootView.findViewById(R.id.btnCal);
        btnReset = (Button) rootView.findViewById(R.id.btnReset);

        tvNameLeksat = (TextView) rootView.findViewById(R.id.tvNameLeksat);
        tvSurNameLekSat = (TextView) rootView.findViewById(R.id.tvSurNameLeksat);
        tvNickNameLekSat = (TextView) rootView.findViewById(R.id.tvNickNameLeksat);

        tvNameSumSat = (TextView) rootView.findViewById(R.id.tvNameSumSat);
        tvSurnameSumSat = (TextView) rootView.findViewById(R.id.tvSurnameSumSat);
        tvNicknameSumSat = (TextView) rootView.findViewById(R.id.tvNickNameSumSat);
        tvNamePlusSur = (TextView) rootView.findViewById(R.id.tvNamePlusSumSat);

        tvNameSumSatFak = (TextView) rootView.findViewById(R.id.tvNameSumSatFak);
        tvSurnameSumSatFak = (TextView) rootView.findViewById(R.id.tvSurNameSumSatFak);
        tvNicknameSumSatFak = (TextView) rootView.findViewById(R.id.tvNickNameSumSatFak);
        tvNamePlusSurFak = (TextView) rootView.findViewById(R.id.tvNamePlusSumSatFak);

        tvNameSumSha = (TextView) rootView.findViewById(R.id.tvNameSumSha);
        tvSurnameSumSha = (TextView) rootView.findViewById(R.id.tvSurnameSumSha);
        tvNicknameSumSha = (TextView) rootView.findViewById(R.id.tvNickNameSumSha);
        tvNamePlusSurSha = (TextView) rootView.findViewById(R.id.tvNamePlusSumSha);


        tvNameSumShaFak = (TextView) rootView.findViewById(R.id.tvNameSumShaFak);
        tvSurnameSumShaFak = (TextView) rootView.findViewById(R.id.tvSurnameSumShaFak);
        tvNicknameSumShaFak = (TextView) rootView.findViewById(R.id.tvNickNameSumShaFak);
        tvNamePlusSurShaFak = (TextView) rootView.findViewById(R.id.tvNamePlusSumShaFak);

        tvKalakinee = (TextView) rootView.findViewById(R.id.tvKalakinee);

        tvNameShadow = (TextView) rootView.findViewById(R.id.tvNameShadow);
        tvSurNameShadow = (TextView) rootView.findViewById(R.id.tvSurNameShadow);
        tvNickNameShadow = (TextView) rootView.findViewById(R.id.tvNickNameShadow);

        tvNameAyatana = (TextView) rootView.findViewById(R.id.tvNameAyatana);
        tvSurNameAyatana = (TextView) rootView.findViewById(R.id.tvSurnameAyatana);
        tvNickNameAyatana = (TextView) rootView.findViewById(R.id.tvNickNameAyatana);
        tvNamePlusAyatana = (TextView) rootView.findViewById(R.id.tvNamePlusAyatana);

    }

    /*****************
     * Listener
     *****************/

    private View.OnClickListener bornFragment = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            nestedScrollView.setEnableScrolling(false);
            getChildFragmentManager()
                    .beginTransaction().add(R.id.radFragmentContainer,
                    new BornFragment(), "BornFragment").addToBackStack(null).commit();

        }
    };

    private View.OnClickListener deCodeListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            decodeName();
            decodeSurName();
            decodeNickName();


            deStarName();
            deStarSurName();
            deStarNickName();

            deKalakinee(theDay);


            sumSat();
            sumShadow();

            ayatanaName();
            ayatanaSurName();
            ayatanaNickName();
            ayatanaNamePlus();


            if (imm.isAcceptingText()) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    };


    private View.OnClickListener resetListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            clearEdt();
            clearTv();

            if (imm.isAcceptingText()) {
                Log.d("imm", "true");

            } else {
                Log.d("imm", "false");
                imm.showSoftInput(edName, InputMethodManager.SHOW_IMPLICIT);
            }

        }
    };

    private void clearEdt() {
        edName.setText("");
        edSurname.setText("");
        edNickname.setText("");
    }

    private void clearTv(){
        tvNameLeksat.setText("");
        tvSurNameLekSat.setText("");
        tvNickNameLekSat.setText("");
        tvNamePlusSur.setText("");

        tvNameLeksat.setText("");
        tvSurNameLekSat.setText("");
        tvNickNameLekSat.setText("");
        tvNamePlusSur.setText("");

        tvNameShadow.setText("");
        tvSurNameShadow.setText("");
        tvNickNameShadow.setText("");
        tvNamePlusSurSha.setText("");


        tvNameSumSha.setText("");
        tvSurnameSumSha.setText("");
        tvNicknameSumSha.setText("");
        tvNamePlusSurSha.setText("");
        tvNameSumShaFak.setText("");
        tvSurnameSumShaFak.setText("");
        tvNicknameSumShaFak.setText("");
        tvNamePlusSurShaFak.setText("");


        tvNameSumSat.setText("");
        tvNameSumSatFak.setText("");
        tvSurnameSumSat.setText("");
        tvSurnameSumSatFak.setText("");
        tvNicknameSumSat.setText("");
        tvNicknameSumSatFak.setText("");
        tvNamePlusSur.setText("");
        tvNamePlusSurFak.setText("");

        tvNameAyatana.setText("");
        tvSurNameAyatana.setText("");
        tvNickNameAyatana.setText("");
        tvNamePlusAyatana.setText("");


        tvKalakinee.setText("");
    }


    @Override
    public void onPause() {
        super.onPause();

        Log.d("NameFragLife", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("NameFragLife", "onStop");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("NameFragLife", "onStart");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("NameFragLife", "onCreate");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("NameFragLife", "onResume");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("NameFragLife", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("NameFragLife", "onDestroy");
    }
}



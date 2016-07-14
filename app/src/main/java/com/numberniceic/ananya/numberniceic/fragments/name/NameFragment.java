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
    private TextView tvNameSumSha, tvSurnameSumSha, tvNicknameSumSha, tvNamePlusSurSha;
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

        if (edNickname.getText().toString().length() > 1) {

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

        tvNameSumSat.setText(String.valueOf(sumSatName));
        tvSurnameSumSat.setText(String.valueOf(sumSatSurName));
        tvNicknameSumSat.setText(String.valueOf(sumSatNickName));

        sumSatNamePlusSur = sumSatName + sumSatSurName;
        tvNamePlusSur.setText(String.valueOf(sumSatNamePlusSur));

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
                tvNameSumSha.setText(String.valueOf(sumShaName));
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
                tvSurnameSumSha.setText(String.valueOf(sumShaSurname));
            }
        }


        Integer shaPlue = sumShaName + sumShaSurname;
        tvNamePlusSurSha.setText(String.valueOf(shaPlue));

        if (shaDao.getLekShaNickNameDaoList() != null) {

            if (shaDao.getLekShaNickNameDaoList().size() > 0) {
                for (int i = 0; i < shaDao.getLekShaNickNameDaoList().size(); i++) {
                    String sNumb = shaDao.getLekShaNickNameDaoList().get(i).getcNumber();

                    if (sNumb != null) {

                        sumShaNickname = sumShaNickname + Integer.valueOf(sNumb);
                    }
                }
                tvNicknameSumSha.setText(String.valueOf(sumShaNickname));
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
            if (sNumber != null)
                sb.append(cName + ":" + sNumber + "  ");
            lekShaNameDaos.add(new LekShaNameDao(cName, sNumber));

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

            if (sNumber != null)
                lekShaSurNameDaos.add(new LekShaSurNameDao(cSurName, sNumber));
                sb.append(cSurName + ":" + sNumber + "  ");

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


    private void ayatana(){

        if (tvNameSumSha.getText().length() > 0){

            String cName = tvNameSumSha.getText().toString();

            if (cName.length() == 2){
                Integer c1 = Integer.valueOf(String.valueOf(cName.charAt(0)));
                Integer c2 = Integer.valueOf(String.valueOf(cName.charAt(1)));

                if (c2 > 5){
                    tvNameAyatana.setText(String.valueOf(c2));
                }else{
                    int x = c1 + c2;
                    tvNameAyatana.setText(String.valueOf(x));
                }

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

        tvNameSumSha = (TextView) rootView.findViewById(R.id.tvNameSumSha);
        tvSurnameSumSha = (TextView) rootView.findViewById(R.id.tvSurnameSumSha);
        tvNicknameSumSha = (TextView) rootView.findViewById(R.id.tvNickNameSumSha);
        tvNamePlusSurSha = (TextView) rootView.findViewById(R.id.tvNamePlusSumSha);
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
            sumSat();
            deKalakinee(theDay);
            deStarName();
            deStarSurName();
            deStarNickName();
            sumShadow();

            ayatana();


            if (imm.isAcceptingText()) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    };


    private View.OnClickListener resetListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            clearEdt();

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



package com.numberniceic.ananya.numberniceic.fragments.name;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.numberniceic.ananya.numberniceic.R;
import com.numberniceic.ananya.numberniceic.dao.name.LekSatItemCollectionDao;
import com.numberniceic.ananya.numberniceic.dao.name.LekSatNameItemDao;
import com.numberniceic.ananya.numberniceic.dao.name.LekSatNickNameItemDao;
import com.numberniceic.ananya.numberniceic.dao.name.LekSatSurNameItemDao;
import com.numberniceic.ananya.numberniceic.managers.name.NumberLekSatManager;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NameFragment extends Fragment {

    private LekSatItemCollectionDao dao = new LekSatItemCollectionDao();

    private FloatingActionButton fabCallRad;
    private TextView tvBirthDay;
    private EditText edName, edSurname, edNickname;
    private Button btnCal, btnReset;
    private TextView tvNameLeksat, tvSurNameLekSat, tvNickNameLekSat;
    private TextView tvNameSumSat, tvSurnameSumSat, tvNicknameSumSat;
    private TextView tvNameSumSha, tvSurnameSumSha, tvNicknameSumSha, tvNamePlusSur;



    public static NameFragment newInstance(){

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

        View rootView = inflater.inflate(R.layout.fragment_name, container, false);


        initInstance(rootView);



            fabCallRad.setOnClickListener(bornFragment);
            tvBirthDay.setOnClickListener(bornFragment);
            btnCal.setOnClickListener(deCodeListener);
            btnReset.setOnClickListener(resetListener);









        return rootView;
    }



    /*****************
     *  method
     *****************/

    public void setBirth(String msg){

        switch (msg) {
            case "Sunday" :
                tvBirthDay.setText("เกิดวันอาทิตย์");
                break;
            case "Monday" :
                tvBirthDay.setText("เกิดวันจันทร์");
                break;
            case "Tuesday" :
                tvBirthDay.setText("เกิดวันอังคาร");
                break;
            case "WednesdayPm" :
                tvBirthDay.setText("เกิดวันพุธ กลางคืน");
                break;
            case "WednesdayAm" :
                tvBirthDay.setText("เกิดวันพุธ กลางวัน");
                break;
            case "Thursday" :
                tvBirthDay.setText("เกิดวันพฤหัสบดี");
                break;
            case "Friday" :
                tvBirthDay.setText("เกิดวันศุกร์");
                break;
            case "Saturday" :
                tvBirthDay.setText("เกิดวันเสาร์");
                break;
        }




    }

    private void decodeName() {

        List<LekSatNameItemDao> satNameItemDaos = new ArrayList<>();

        String name = edName.getText().toString();

        NumberLekSatManager manager = new NumberLekSatManager();

        if (name.length() > 0) {

            for (int i = 0; i < name.length(); i++) {
                manager.setLekSat(name.charAt(i));
                if (manager.getLekSat() != null) {
                    String sNumber = manager.getLekSat();
                    Log.d("sNumber", sNumber);

                    satNameItemDaos.add(new LekSatNameItemDao(sNumber, name.charAt(i)));
                }
            }

            dao.setLekSatNameItemDaos(satNameItemDaos);

            if (dao.getLekSatNameItemDaos() != null){

                tvNameLeksat.setText("");

                for (int q = 0; q < dao.getLekSatNameItemDaos().size(); q++) {
                    String sname = String.valueOf(dao.getLekSatNameItemDaos().get(q).getName());
                    String numb = dao.getLekSatNameItemDaos().get(q).getNumber();

                    Log.d("nameNumb" , sname  + " : " + numb);

                    tvNameLeksat.append(sname + ":" + numb + "   ");
                }

            }


        }else {
            Toast.makeText(getContext(), "โปรดกรอกชื่อ!!", Toast.LENGTH_SHORT).show();
            edName.setFocusable(true);
        }




    }
    private void decodeSurName() {

        List<LekSatSurNameItemDao> satSurNameItemDaos = new ArrayList<>();

        String surname = edSurname.getText().toString();

        NumberLekSatManager manager = new NumberLekSatManager();

        if (surname.length() > 0) {

            for (int i = 0; i < surname.length(); i++) {
                manager.setLekSat(surname.charAt(i));
                if (manager.getLekSat() != null) {
                    String sNumber = manager.getLekSat();


                    satSurNameItemDaos.add(new LekSatSurNameItemDao(surname.charAt(i), sNumber));
                }
            }

            dao.setLekSatSurNameItemDaos(satSurNameItemDaos);

            if (dao.getLekSatSurNameItemDaos() != null){

                tvSurNameLekSat.setText("");

                for (int q = 0; q < dao.getLekSatSurNameItemDaos().size(); q++) {
                    String sname = String.valueOf(dao.getLekSatSurNameItemDaos().get(q).getSurName());
                    String numb = dao.getLekSatSurNameItemDaos().get(q).getNumber();

                    Log.d("nameNumb" , sname  + " : " + numb);

                    tvSurNameLekSat.append(sname + ":" + numb + "   ");
                }

            }


        }else {
            Toast.makeText(getContext(), "โปรดกรอกชื่อ!!", Toast.LENGTH_SHORT).show();
            edSurname.setFocusable(true);
        }




    }
    private void decodeNickName() {

        List<LekSatNickNameItemDao> satNickNameItemDaos = new ArrayList<>();

        String nickname = edNickname.getText().toString();

        NumberLekSatManager manager = new NumberLekSatManager();

        if (nickname.length() > 0) {

            for (int i = 0; i < nickname.length(); i++) {
                manager.setLekSat(nickname.charAt(i));
                if (manager.getLekSat() != null) {
                    String sNumber = manager.getLekSat();


                    satNickNameItemDaos.add(new LekSatNickNameItemDao(nickname.charAt(i), sNumber));
                }
            }

            dao.setLekSatNickNameItemDaos(satNickNameItemDaos);

            if (dao.getLekSatNickNameItemDaos() != null){

                tvNickNameLekSat.setText("");

                for (int q = 0; q < dao.getLekSatNickNameItemDaos().size(); q++) {
                    String sname = String.valueOf(dao.getLekSatNickNameItemDaos().get(q).getNickName());
                    String numb = dao.getLekSatNickNameItemDaos().get(q).getNumber();

                    Log.d("nameNumb" , sname  + " : " + numb);

                    tvNickNameLekSat.append(sname + ":" + numb + "   ");
                }

            }


        }else {
            Toast.makeText(getContext(), "โปรดกรอกชื่อ!!", Toast.LENGTH_SHORT).show();
            edNickname.setFocusable(true);
        }




    }

    private void sumSat() {

        if (dao.getLekSatNameItemDaos() != null){

            Integer sumSatName = 0;
            Integer sumSatSurName = 0;
            Integer sumSatNickName = 0;
            Integer sumSatNamePlusSur = 0;

            for (int i = 0; i < dao.getLekSatNameItemDaos().size(); i++) {

                sumSatName = sumSatName + Integer.valueOf(dao.getLekSatNameItemDaos().get(i).getNumber());

            }

            for (int i = 0; i < dao.getLekSatSurNameItemDaos().size(); i++) {

                sumSatSurName = sumSatName + Integer.valueOf(dao.getLekSatSurNameItemDaos().get(i).getNumber());

            }

            for (int i = 0; i < dao.getLekSatNickNameItemDaos().size(); i++) {

                sumSatNickName = sumSatName + Integer.valueOf(dao.getLekSatNickNameItemDaos().get(i).getNumber());
            }


            tvNameSumSat.setText("");
            tvSurnameSumSat.setText("");
            tvNicknameSumSat.setText("");
            tvNamePlusSur.setText("");

            tvNameSumSat.setText(String.valueOf(sumSatName));
            tvSurnameSumSat.setText(String.valueOf(sumSatSurName));
            tvNicknameSumSat.setText(String.valueOf(sumSatNickName));

            sumSatNamePlusSur = sumSatNamePlusSur + sumSatName + sumSatSurName;
            tvNamePlusSur.setText(String.valueOf(sumSatNamePlusSur));
        }
    }


    private void initInstance(View rootView) {
        fabCallRad = (FloatingActionButton) rootView.findViewById(R.id.fabCallRg);
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

        tvNameSumSha = (TextView) rootView.findViewById(R.id.tvNameSumSha);
        tvSurnameSumSha = (TextView) rootView.findViewById(R.id.tvSurnameSumSha);
        tvNicknameSumSha = (TextView) rootView.findViewById(R.id.tvNickNameSumSha);
        tvNamePlusSur = (TextView) rootView.findViewById(R.id.tvNamePlusSumSat);
    }

    /*****************
     *  Listener
     *****************/

    private View.OnClickListener bornFragment = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.radFragmentContainer, new BornFragment(), "BornFragment")
                    .addToBackStack(null).commit();

        }
    };

    private View.OnClickListener deCodeListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            decodeName();
            decodeSurName();
            decodeNickName();
            sumSat();
        }
    };


    private View.OnClickListener resetListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            edName.setText("");
            edSurname.setText("");
            edNickname.setText("");
        }
    };


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



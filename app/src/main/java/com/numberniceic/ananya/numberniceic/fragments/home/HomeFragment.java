package com.numberniceic.ananya.numberniceic.fragments.home;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.numberniceic.ananya.numberniceic.R;
import com.numberniceic.ananya.numberniceic.activities.MiracleHomeActivity;
import com.numberniceic.ananya.numberniceic.adapters.home.PairHomeAdapter;
import com.numberniceic.ananya.numberniceic.managers.NumberPilotManager;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

import java.util.ArrayList;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;

public class HomeFragment extends Fragment {


    private InputMethodManager imm;
    private TextView tvPointD, tvPointR;
    private EditText edHomeNumber;
    private FancyButton btnHomeNumberCal;
    private FancyButton btnHomeNumberClr;
    private FancyButton btnHomeNumberMiracle;
    private ArrayList<String> charStrList;
    private ArrayList<String> pairHomeNumberList;
    private GridView gvPairHome;
    private boolean kBoardStatus;


    public static HomeFragment newInstance() {

        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;

    }


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        initInstance(rootView);

        if (savedInstanceState != null) {
            charStrList = savedInstanceState.getStringArrayList("charStrList");
            calPair();
        }

        KeyboardVisibilityEvent.setEventListener(
                getActivity(),
                new KeyboardVisibilityEventListener() {
                    @Override
                    public void onVisibilityChanged(boolean isOpen) {
                        kBoardStatus = isOpen;

                    }
                });
        btnHomeNumberCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calPair();

                if (kBoardStatus)
                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            }
        });

        btnHomeNumberClr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edHomeNumber.setText("");
                tvPointD.setText("");
                tvPointR.setText("");
                gvPairHome.setAdapter(null);
                if (charStrList != null) {
                    charStrList.clear();
                }
                if (pairHomeNumberList != null){
                pairHomeNumberList.clear();}
            }
        });

        btnHomeNumberMiracle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pairHomeNumberList != null) {
                    if (pairHomeNumberList.size() != 0) {
                        Intent intent = new Intent(getContext(), MiracleHomeActivity.class);
                        intent.putStringArrayListExtra("pairHomeNumberList", pairHomeNumberList);
                        startActivity(intent);
                    }
                }
            }
        });


        return rootView;
    }

    private void calPair() {
        setStrHomeNumber();
        setPairHomeNumberList(charStrList);
        setToGrid(pairHomeNumberList);
        setPoint(pairHomeNumberList);
    }

    private void setPoint(List<String> pairHomeNumberList) {

        Integer pointD = 0;
        Integer pointR = 0;


        if (pairHomeNumberList != null) {
            NumberPilotManager manager = new NumberPilotManager();
            for (int i = 0; i < pairHomeNumberList.size(); i++) {
                String type = manager.getType(pairHomeNumberList.get(i));
                Integer point = manager.getPoint(pairHomeNumberList.get(i));
                if (String.valueOf(type.charAt(0)).equals("D")) {
                    pointD = pointD + point;
                }
                if (String.valueOf(type.charAt(0)).equals("R")) {
                    pointR = pointR + point;
                }
            }
        }

        tvPointD.setText(String.valueOf(pointD));
        tvPointR.setText(String.valueOf(pointR));

    }

    private void setToGrid(List<String> pairHomeNumberList) {
        if (pairHomeNumberList != null) {

            PairHomeAdapter adapter = new PairHomeAdapter();
            adapter.addPairHome(pairHomeNumberList);
            gvPairHome.setAdapter(adapter);

        }




    }

    private void setStrHomeNumber() {
        String strHomeNumber = edHomeNumber.getText().toString().trim();
        if (strHomeNumber.length() > 0) {
            charStrList = new ArrayList<>();
            for (int i = 0; i < strHomeNumber.length(); i++) {
                char cHomeNumber = strHomeNumber.charAt(i);

                if (!Character.isWhitespace(cHomeNumber)) {
                    if (checkNumber(cHomeNumber)) {
                        charStrList.add(String.valueOf(cHomeNumber));
                    }
                }

            }
        }
    }

    private boolean checkNumber(char cHomeNumber) {

        boolean isNumber = false;
        String strChar = String.valueOf(cHomeNumber);
        switch (strChar) {
            case "0":
                isNumber = true;
                break;
            case "1":
                isNumber = true;
                break;
            case "2":
                isNumber = true;
                break;
            case "3":
                isNumber = true;
                break;
            case "4":
                isNumber = true;
                break;
            case "5":
                isNumber = true;
                break;
            case "6":
                isNumber = true;
                break;
            case "7":
                isNumber = true;
                break;
            case "8":
                isNumber = true;
                break;
            case "9":
                isNumber = true;
                break;
        }
        return isNumber;
    }


    public void setPairHomeNumberList(List<String> charStrList) {

        if (charStrList != null) {
            pairHomeNumberList = new ArrayList<>();
            for (int i = 0; i < charStrList.size(); i++) {
                if (i != charStrList.size() - 1) {
                    String pair = charStrList.get(i) + charStrList.get(i + 1);
                    pairHomeNumberList.add(pair);
                }
                if (charStrList.size() == 1) {

                    String pair = "0" + charStrList.get(i);
                    pairHomeNumberList.add(pair);
                }

            }
        }


    }

    private void initInstance(View rootView) {
        imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        edHomeNumber = (EditText) rootView.findViewById(R.id.edHomeNumber);
        btnHomeNumberCal = (FancyButton) rootView.findViewById(R.id.btnHomeNumberCal);
        btnHomeNumberClr = (FancyButton) rootView.findViewById(R.id.btnHomeNumberClr);
        btnHomeNumberMiracle = (FancyButton) rootView.findViewById(R.id.btnHomeNumberMiracle);
        gvPairHome = (GridView) rootView.findViewById(R.id.gvPairHome);
        tvPointD = (TextView) rootView.findViewById(R.id.tvPointD);
        tvPointR = (TextView) rootView.findViewById(R.id.tvPointR);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (charStrList != null) {
            outState.putStringArrayList("charStrList", charStrList);
        }
    }
}

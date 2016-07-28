package com.numberniceic.ananya.numberniceic.fragments.tabian;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.numberniceic.ananya.numberniceic.R;
import com.numberniceic.ananya.numberniceic.adapters.tabian.BirthDayAdapter;
import com.numberniceic.ananya.numberniceic.managers.eventbus.EventBus;
import com.numberniceic.ananya.numberniceic.pojo.tabain.BirthDay;

import java.util.ArrayList;
import java.util.List;

import static android.os.Build.VERSION.PREVIEW_SDK_INT;
import static android.os.Build.VERSION.SDK;
import static android.os.Build.VERSION.SDK_INT;

public class BirthDayFragment extends DialogFragment {

    private List<BirthDay> dayList;
    private ListView lvDayView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.layout_birthday_fragment, container, false);

        getDialog().setTitle("เลือกวันเกิด");



        initInstance(rootView);

        dayList = new ArrayList<>();
        dayList.add(new BirthDay(1, "Sunday", "อาทิตย์"));
        dayList.add(new BirthDay(2, "Monday", "จันทร์"));
        dayList.add(new BirthDay(3, "Tuesday", "อังคาร"));
        dayList.add(new BirthDay(4, "WednesdayAm", "พุธ กลางวัน"));
        dayList.add(new BirthDay(5, "WednesdayPm", "พุธ กลางคืน"));
        dayList.add(new BirthDay(6, "Thursday", "พฤหัสบดี"));
        dayList.add(new BirthDay(7, "Friday", "ศุกร์"));
        dayList.add(new BirthDay(8, "Monday", "เสาร์"));


        BirthDayAdapter adapter = new BirthDayAdapter();
        adapter.setDayList(dayList);
        lvDayView.setAdapter(adapter);
        lvDayView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                BirthDay birthDay = dayList.get(i);
                EventBus.getInstance().post(birthDay);
                getDialog().cancel();

            }
        });




        return rootView;
    }

    private void initInstance(View rootView) {
        lvDayView = (ListView) rootView.findViewById(R.id.lvDay);
    }
}

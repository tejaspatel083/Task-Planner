package com.example.taskplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    private CalendarView calender;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view =  inflater.inflate(R.layout.fragment_home,container,false);
        getActivity().setTitle("Home");

        calender = view.findViewById(R.id.calenderView);

        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                String s = dayOfMonth + " - " + (month + 1) + " - " + year;

                Intent intent = new Intent(getActivity(),AddTaskPage.class);
                intent.putExtra("Key",s);
                startActivity(intent);
            }
        });

        return view;

    }
}

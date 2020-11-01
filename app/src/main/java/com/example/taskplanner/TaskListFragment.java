package com.example.taskplanner;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class TaskListFragment extends Fragment {

    ListView listView;
    ArrayList<TaskInfo> arrayList;
    CustomAdapterList arrayAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view =  inflater.inflate(R.layout.fragment_task_list,container,false);
        getActivity().setTitle("My Task List");

        listView = view.findViewById(R.id.task_listview);

        arrayList = new ArrayList<TaskInfo>();



        arrayList.add( new TaskInfo("Gym","21-10-2020"));
        arrayList.add(new TaskInfo("College","22-11-2020"));
        arrayList.add(new TaskInfo("Birthday Party","5-11-2020"));
        arrayList.add(new TaskInfo("Christmas","25-12-2020"));
        arrayList.add( new TaskInfo("Gym","21-10-2020"));
        arrayList.add(new TaskInfo("College","22-11-2020"));
        arrayList.add(new TaskInfo("Birthday Party","5-11-2020"));
        arrayList.add(new TaskInfo("Christmas","25-12-2020"));

        arrayAdapter = new CustomAdapterList(arrayList);

        listView.setAdapter(arrayAdapter);

        return view;

    }
}

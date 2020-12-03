package com.example.taskplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class FavouriteTaskListFragment extends Fragment {

    ListView listView;
    ArrayList<TaskInfo> arrayList;
    CustomAdapterList arrayAdapter;
    FirebaseFirestore db;
    FirebaseAuth firebaseAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_task_list,container,false);
        getActivity().setTitle("Favourite Task List");

        listView = view.findViewById(R.id.task_listview);

        db = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        arrayList = new ArrayList<TaskInfo>();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TaskInfo taskInfo = (TaskInfo) parent.getAdapter().getItem(position);
                String title = taskInfo.getTitle();

                Intent intent = new Intent(getContext(),TaskDetails.class);
                intent.putExtra("TitleName",title);
                startActivity(intent);

            }
        });

        return view;
    }
}

package com.example.taskplanner;


import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class TaskListFragment extends Fragment {

    ListView listView;
    ArrayList<TaskInfo> arrayList;
    CustomAdapterList arrayAdapter;
    FirebaseFirestore db;
    FirebaseAuth firebaseAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view =  inflater.inflate(R.layout.fragment_task_list,container,false);
        getActivity().setTitle("My Task List");

        listView = view.findViewById(R.id.task_listview);

        db = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        arrayList = new ArrayList<TaskInfo>();


        db.collection("Collection-1")
                .document("User Task List")
                .collection(firebaseAuth.getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful())
                        {

                            for (DocumentSnapshot documentSnapshot: task.getResult())
                            {
                                TaskInfo obj = documentSnapshot.toObject(TaskInfo.class);
                                arrayList.add(obj);
                            }
                            arrayAdapter = new CustomAdapterList(arrayList);

                            listView.setAdapter(arrayAdapter);
                        }
                        else
                            {
                            Log.d("MissionActivity", "Error getting documents: ", task.getException());
                        }


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast toast = Toast.makeText(getActivity(),"Error"+e.getMessage(),Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                        toast.show();
                    }
                });

        return view;

    }
}

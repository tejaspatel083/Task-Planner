package com.example.taskplanner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.taskplanner.api_interfaces.JsonPlaceHolderApi;
import com.example.taskplanner.models.TaskInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.constraintlayout.widget.Constraints.TAG;

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
                intent.putExtra("key","fav");
                intent.putExtra("TitleName",title);
                startActivity(intent);

            }
        });


        /*
        db.collection("Collection-2")
                .document("Favorite Task List")
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

         */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<ArrayList<TaskInfo>> call = jsonPlaceHolderApi.getFavTask();

        call.enqueue(new Callback<ArrayList<TaskInfo>>() {
            @Override
            public void onResponse(Call<ArrayList<TaskInfo>> call, Response<ArrayList<TaskInfo>> response) {
                if (!response.isSuccessful())
                {
                    //textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<TaskInfo> posts = response.body();

                try {
                    arrayList.addAll(response.body());
                    Log.v(TAG,"LOGS"+arrayList.size());
                    ArrayList<TaskInfo> list = new ArrayList<TaskInfo>();
                    for (int i = 0; i < arrayList.size(); i++) {
                        String title = arrayList.get(i).getTitle();
                        list.add(new TaskInfo(title));
                    }
                    arrayAdapter = new CustomAdapterList(list);

                    listView.setAdapter(arrayAdapter);
                }
                catch (Exception e)
                {

                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();

                }
            }

            @Override
            public void onFailure(Call<ArrayList<TaskInfo>> call, Throwable t) {

            }
        });

        return view;
    }
}

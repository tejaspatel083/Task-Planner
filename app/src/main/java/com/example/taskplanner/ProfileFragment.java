package com.example.taskplanner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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
import com.google.firebase.firestore.auth.User;

public class ProfileFragment  extends Fragment {

    private TextView name,email;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view =  inflater.inflate(R.layout.fragment_profile,container,false);
        getActivity().setTitle("Profile");

        name = view.findViewById(R.id.ProfileName);
        email = view.findViewById(R.id.ProfileEmail);
        db = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        db.collection("Collection-2")
                .document("User Information")
                .collection(firebaseAuth.getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if(task.isSuccessful())
                        {
                            for (DocumentSnapshot documentSnapshot: task.getResult())
                            {
                                UserInfo obj = documentSnapshot.toObject(UserInfo.class);
                                name.setText(obj.getName());
                                email.setText(obj.getEmail());
                            }
                        }
                        else
                        {
                            Toast.makeText(getContext(), "Something went wrong!!!", Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(getContext(), "Error:"+e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

        return view;
    }


}

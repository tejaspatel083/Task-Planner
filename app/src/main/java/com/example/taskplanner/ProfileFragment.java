package com.example.taskplanner;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ProfileFragment  extends Fragment {

    private TextView name,email;
    private Button button;
    private EditText edit_name;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view =  inflater.inflate(R.layout.fragment_profile,container,false);
        getActivity().setTitle("Profile");

        button = view.findViewById(R.id.ProfileUpdateButton);
        name = view.findViewById(R.id.ProfileName);
        email = view.findViewById(R.id.ProfileEmail);
        db = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        db.collection("Collection-2")
                .document(firebaseAuth.getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        if(task.isSuccessful())
                        {


                            DocumentSnapshot documentSnapshot = task.getResult();

                            if (documentSnapshot.exists())
                            {
                                UserInfo obj = documentSnapshot.toObject(UserInfo.class);
                                name.setText(obj.getName());
                                email.setText(obj.getEmail());

                            }
                            else {
                                Log.d(TAG, "No such document");
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

                        Toast.makeText(getContext(), "Error : "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                LayoutInflater layoutInflater = getActivity().getLayoutInflater();
                View view = layoutInflater.inflate(R.layout.custom_dialog,null);

                edit_name = view.findViewById(R.id.updateName);

                builder.setView(view).setTitle("Update Profile");

                builder.setView(view).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });



                builder.setView(view).setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String name = edit_name.getText().toString().trim();

                        UserInfo userInfo = new UserInfo(name);


                        db.collection("Collection-2")
                                .document(firebaseAuth.getUid())
                                .update("name",name)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        Toast toast = Toast.makeText(getActivity(),"Profile Updated",Toast.LENGTH_LONG);
                                        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                                        toast.show();
                                        
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                        Toast toast = Toast.makeText(getActivity(),"Error : "+e.getMessage(),Toast.LENGTH_LONG);
                                        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                                        toast.show();
                                    }
                                });





                    }
                });

                builder.show();
            }
        });

        return view;
    }


}

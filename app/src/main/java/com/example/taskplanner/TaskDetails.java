package com.example.taskplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taskplanner.models.TaskInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class TaskDetails extends AppCompatActivity {

    FirebaseFirestore db;
    FirebaseAuth firebaseAuth;
    TextView title,date,notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        title = findViewById(R.id.titleTextView);
        date = findViewById(R.id.dateTextView);
        notes = findViewById(R.id.noteScrollTextView);

        db = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        String matchTitle = getIntent().getExtras().get("TitleName").toString();
        String check = getIntent().getExtras().get("key").toString();

        if(check.equalsIgnoreCase("fav"))
        {
            db.collection("Collection-2")
                    .document("Favorite Task List")
                    .collection(firebaseAuth.getUid())
                    .whereEqualTo("title",matchTitle)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if(task.isSuccessful())
                            {

                                for(DocumentSnapshot documentSnapshot:task.getResult())
                                {
                                    TaskInfo taskInfo = documentSnapshot.toObject(TaskInfo.class);
                                    title.setText(taskInfo.getTitle());
                                    date.setText(taskInfo.getDate());
                                    notes.setText(taskInfo.getNote());
                                }

                            }
                            else
                            {
                                Toast.makeText(TaskDetails.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                            }

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(TaskDetails.this, "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
        }
        else if(check.equalsIgnoreCase("normal"))
        {
            db.collection("Collection-1")
                    .document("User Task List")
                    .collection(firebaseAuth.getUid())
                    .whereEqualTo("title",matchTitle)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if(task.isSuccessful())
                            {

                                for(DocumentSnapshot documentSnapshot:task.getResult())
                                {
                                    TaskInfo taskInfo = documentSnapshot.toObject(TaskInfo.class);
                                    title.setText(taskInfo.getTitle());
                                    date.setText(taskInfo.getDate());
                                    notes.setText(taskInfo.getNote());
                                }

                            }
                            else
                            {
                                Toast.makeText(TaskDetails.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                            }

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(TaskDetails.this, "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
        }
        else
        {
            Toast toast = Toast.makeText(TaskDetails.this,"Something went wrong",Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
        }




    }
}

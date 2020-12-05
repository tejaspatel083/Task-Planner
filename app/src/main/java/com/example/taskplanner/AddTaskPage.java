package com.example.taskplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddTaskPage extends AppCompatActivity {

    private TextView date;
    private EditText NoteScroll,NoteTitle;
    private CheckBox favourite;
    private Button addBtn;
    private FirebaseFirestore db;
    private FirebaseAuth firebaseAuth;
    private int n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task_page);

        NoteTitle = findViewById(R.id.titleEditText);
        NoteScroll = findViewById(R.id.noteEditText);
        date = findViewById(R.id.dateTextView);
        favourite = findViewById(R.id.checkbox);
        addBtn = findViewById(R.id.btnAddTask);

        db = FirebaseFirestore.getInstance();

        firebaseAuth = FirebaseAuth.getInstance();

        NoteScroll.setMovementMethod(new ScrollingMovementMethod());

        final String strDate = getIntent().getExtras().getString("Key");

        date.setText(strDate);



        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strTitle = NoteTitle.getText().toString().trim();
                String strNote = NoteScroll.getText().toString().trim();

                TaskInfo obj = new TaskInfo(strTitle,strDate,strNote);

                if(favourite.isChecked()){
                    db.collection("Collection-2")
                            .document("Favorite Task List")
                            .collection(firebaseAuth.getUid())
                            .add(obj)
                            .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                    Toast toast = Toast.makeText(AddTaskPage.this,"Task Added",Toast.LENGTH_LONG);
                                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                                    toast.show();
                                    startActivity(new Intent(AddTaskPage.this,HomePage.class));
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Toast toast = Toast.makeText(AddTaskPage.this,"Error"+e.getMessage(),Toast.LENGTH_LONG);
                                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                                    toast.show();
                                }
                            });

                }

                else
                {
                    db.collection("Collection-1")
                            .document("User Task List")
                            .collection(firebaseAuth.getUid())
                            .add(obj)
                            .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                    Toast toast = Toast.makeText(AddTaskPage.this,"Task Added",Toast.LENGTH_LONG);
                                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                                    toast.show();
                                    startActivity(new Intent(AddTaskPage.this,HomePage.class));
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Toast toast = Toast.makeText(AddTaskPage.this,"Error"+e.getMessage(),Toast.LENGTH_LONG);
                                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                                    toast.show();
                                }
                            });

                }




            }
        });

    }
}

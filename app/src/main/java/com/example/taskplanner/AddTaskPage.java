package com.example.taskplanner;

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

import com.example.taskplanner.api_interfaces.JsonPlaceHolderApi;
import com.example.taskplanner.models.TaskInfo;
import com.example.taskplanner.models.UserInfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddTaskPage extends AppCompatActivity {

    private TextView date;
    private EditText NoteScroll,NoteTitle;
    private CheckBox favourite;
    private Button addBtn;
    private FirebaseFirestore db;
    private FirebaseAuth firebaseAuth;
    private int n;
    private String strDate;

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

        strDate = getIntent().getExtras().getString("Key");

        date.setText(strDate);



        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*

                String strTitle = NoteTitle.getText().toString().trim();
                String strNote = NoteScroll.getText().toString().trim();

                TaskInfo obj = new TaskInfo(strTitle,strDate,strNote);

                 */

                if(favourite.isChecked())
                {

                    addFavTask();

                    /*

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

                     */

                }

                else
                {
                    addTask();
                    /*
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

                     */

                }




            }
        });

    }

    void addTask()
    {
        String strTitle = NoteTitle.getText().toString().trim();
        String strNote = NoteScroll.getText().toString().trim();

        TaskInfo taskInfo = new TaskInfo(strTitle,strDate,strNote);

        Gson gson = new Gson();
        String json = gson.toJson(taskInfo);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<TaskInfo> call = jsonPlaceHolderApi.addTask(taskInfo);



        call.enqueue(new Callback<TaskInfo>() {
            @Override
            public void onResponse(Call<TaskInfo> call, Response<TaskInfo> response) {

                Toast toast = Toast.makeText(AddTaskPage.this,"Task Added", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM| Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                startActivity(new Intent(AddTaskPage.this,HomePage.class));

            }

            @Override
            public void onFailure(Call<TaskInfo> call, Throwable t) {

                Toast toast = Toast.makeText(AddTaskPage.this,t.getMessage(),Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
            }
        });

    }

    void addFavTask()
    {
        String title = NoteTitle.getText().toString().trim();
        String note = NoteScroll.getText().toString().trim();

        TaskInfo task = new TaskInfo(title,strDate,note);


        UserInfo user = new UserInfo();
        Gson gson = new Gson();
        String json = gson.toJson(task);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

       Call<TaskInfo> call = jsonPlaceHolderApi.addFavTask(task);

       call.enqueue(new Callback<TaskInfo>() {
           @Override
           public void onResponse(Call<TaskInfo> call, Response<TaskInfo> response) {

               Toast toast = Toast.makeText(AddTaskPage.this,"Task Added", Toast.LENGTH_LONG);
               toast.setGravity(Gravity.BOTTOM| Gravity.CENTER_HORIZONTAL, 0, 0);
               toast.show();
               startActivity(new Intent(AddTaskPage.this,HomePage.class));
           }

           @Override
           public void onFailure(Call<TaskInfo> call, Throwable t) {

               Toast toast = Toast.makeText(AddTaskPage.this,t.getMessage(),Toast.LENGTH_LONG);
               toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
               toast.show();
           }
       });

    }
}

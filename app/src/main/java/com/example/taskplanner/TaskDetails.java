package com.example.taskplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class TaskDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        TextView noteScrollable = (TextView)findViewById(R.id.noteScroll);

        noteScrollable.setMovementMethod(new ScrollingMovementMethod());
    }
}

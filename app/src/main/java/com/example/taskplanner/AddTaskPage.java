package com.example.taskplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class AddTaskPage extends AppCompatActivity {

    private TextView NoteScroll,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task_page);

        NoteScroll = findViewById(R.id.noteEditText);
        date = findViewById(R.id.dateTextView);


        NoteScroll.setMovementMethod(new ScrollingMovementMethod());

        String str = getIntent().getExtras().getString("Key");
        date.setText(str);

    }
}

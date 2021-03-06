package com.example.test;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;

public class CreateClassPage extends AppCompatActivity {
    EditText className;
    EditText section;
    EditText room;
    EditText subject;
    String className_input;
    String room_input;
    String subject_input;
    String section_input;
    String classCode;
    final String TAG = "fuq";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class_page);
        android.support.v7.widget.Toolbar myToolbar = findViewById(R.id.createClassToolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        className = findViewById(R.id.className_et);
        section = findViewById(R.id.sectionName_et);
        room = findViewById(R.id.roomName_et);
        subject = findViewById(R.id.subjectName_et);
        className.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    className();
                }
            }
        });
        room.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    room();
                }
            }
        });
    }

    private boolean className() {
        className_input = className.getText().toString();
        if (className_input.matches("")) {
            className.setError("this field is required!");
            return false;
        } else {
            return true;
        }
    }

    private boolean room() {
        room_input = room.getText().toString();
        if (room_input.matches("")) {
            room.setError("this field is required!");
            return false;
        } else {
            return true;
        }
    }

    public void createButton(View v) {
        if (className() && room()) {
            try {
                section_input = section.getText().toString();
                room_input = room.getText().toString();
                className_input = className.getText().toString();
                subject_input = subject.getText().toString();
                new WriterThread(getApplication()).execute("createClass");
                new WriterThread(getApplication()).execute(className_input);
                new WriterThread(getApplication()).execute(room_input);
                new WriterThread(getApplication()).execute(section_input);
                new WriterThread(getApplication()).execute(subject_input);
                classCode = new ReaderThread().execute().get();
                Intent in = new Intent(CreateClassPage.this, ClassPage.class);
                String stat="teacher";
                in.putExtra("classCode", classCode);
                in.putExtra("status" ,stat);
                in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

package com.example.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class CreateClassPage extends AppCompatActivity {
    EditText className;
    EditText section;
    EditText room;
    EditText subject;
    String className_input;
    String room_input;
    String subject_input;
    String section_input;
final String TAG="fuq";
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
        Log.d(TAG, "className: "+ className_input);
        if (className_input.matches("")) {
            className.setError("this field is required!");
            return false;
        } else {
            return true;
        }
    }

    private boolean room() {
        room_input = room.getText().toString();
        Log.d(TAG, "room: "+ room);
        if (room_input.matches("")) {
            room.setError("this field is required!");
            return false;
        } else {
            return true;
        }
    }

    public void createButton(View v) {
//        Log.d(TAG, "createButton: in fucking if");
        if (className() && room()) {
            section_input = section.getText().toString();
            new WriterThread(getApplication()).execute("createClass",subject_input,room_input,section_input);
            Intent in=new Intent(CreateClassPage.this,ClassPage.class);
            startActivity(in);
        }
    }
}

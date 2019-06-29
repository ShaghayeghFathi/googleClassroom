package com.example.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class_page);
        android.support.v7.widget.Toolbar myToolbar=findViewById(R.id.createClassToolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        className=findViewById(R.id.className_et);
        section=findViewById(R.id.sectionName_et);
        room=findViewById(R.id.roomName_et);
        subject=findViewById(R.id.subjectName_et);
    }
    public void className(View view){
        room_input=room.getText().toString();
        if(room_input.matches("")){
            room.setError("this field is required!");
        }

    }
    public void section(View view){
        room_input=room.getText().toString();
        if(room_input.matches("")){
            room.setError("this field is required!");
        }
        className_input=className.getText().toString();
        if(className_input.matches("")){
            className.setError("this field is required!");
        }
    }
    public void room(View view){
        className_input=className.getText().toString();
        if(className_input.matches("")){
            className.setError("this field is required!");
        }

    }
    public void subject(View view){
        room_input=room.getText().toString();
        if(room_input.matches("")){
            room.setError("this field is required!");
        }
        className_input=className.getText().toString();
        if(className_input.matches("")){
            className.setError("this field is required!");
        }
    }
}

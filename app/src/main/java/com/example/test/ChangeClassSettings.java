package com.example.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class ChangeClassSettings extends AppCompatActivity {
    EditText title;
    EditText Description;
    EditText section;
    EditText room;
    EditText subject;
    String classCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_class_settings);
        android.support.v7.widget.Toolbar myToolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent i = getIntent();
        classCode = i.getExtras().getCharSequence("classCode").toString();
        Log.d("shitthisfuck", "onCreate: " + classCode);
        title = findViewById(R.id.ClassTitle_et);
        Description = findViewById(R.id.Description_et);
        section = findViewById(R.id.Section_et);
        room = findViewById(R.id.Room_et);
        subject = findViewById(R.id.Subject_et);
    }

    public void changeInfo(View v) {
        String title_input = title.getText().toString();
        String description_input = Description.getText().toString();
        String section_input = section.getText().toString();
        String room_input = room.getText().toString();
        String subject_input = subject.getText().toString();
        new WriterThread(getApplication()).execute("changeInfo");
        new WriterThread(getApplication()).execute(classCode);

        new WriterThread(getApplication()).execute(title_input);
        new WriterThread(getApplication()).execute(description_input);
        new WriterThread(getApplication()).execute(section_input);
        new WriterThread(getApplication()).execute(room_input);
        new WriterThread(getApplication()).execute(subject_input);
        Intent i = new Intent(ChangeClassSettings.this, MainPage.class);
        startActivity(i);
    }
}
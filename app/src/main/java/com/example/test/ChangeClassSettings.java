package com.example.test;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class ChangeClassSettings extends AppCompatActivity {
    EditText title;
    EditText Description;
    EditText section;
    EditText room;
    EditText subject;
    TextView classCode_tv;
    String classCode;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        classCode=i.getStringExtra("classCode");
        setContentView(R.layout.activity_change_class_settings);
        android.support.v7.widget.Toolbar myToolbar = findViewById(R.id.changeClassToolbar);
        setSupportActionBar(myToolbar);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        title = findViewById(R.id.ClassTitle_et);
        Description = findViewById(R.id.Description_et);
        section = findViewById(R.id.Section_et);
        room = findViewById(R.id.Room_et);
        subject = findViewById(R.id.Subject_et);
        classCode_tv=findViewById(R.id.classCode_tv);
        classCode_tv.setText("Class code: " + classCode);
        new WriterThread(getApplication()).execute("getClassInfo");
        new WriterThread(getApplication()).execute(classCode);
        try {
            String t=new ReaderThread().execute().get();
            String s=new ReaderThread().execute().get();
            String r=new ReaderThread().execute().get();
            String su= new ReaderThread().execute().get();
            title.setText(t);
//            Description.setText(new ReaderThread().execute().get());
            section.setText(s);
            room.setText(r);
            subject.setText(su);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
//        new WriterThread(getApplication()).execute(description_input);
        new WriterThread(getApplication()).execute(section_input);
        new WriterThread(getApplication()).execute(room_input);
        new WriterThread(getApplication()).execute(subject_input);
        Intent i = new Intent(ChangeClassSettings.this, MainPage.class);
        startActivity(i);
    }
}
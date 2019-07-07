package com.example.test;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.concurrent.ExecutionException;

public class ClassInfoPage extends AppCompatActivity {
    String classCode;
    TextView className;
    TextView description;
    TextView room;
    TextView section;
    TextView subject;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        classCode = i.getStringExtra("classCode");
        setContentView(R.layout.activity_class_info_page);
        android.support.v7.widget.Toolbar myToolbar = findViewById(R.id.classInfoToolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        className = findViewById(R.id.name_info);
        description = findViewById(R.id.description_info);
        room = findViewById(R.id.room_info);
        section = findViewById(R.id.section_info);
        subject = findViewById(R.id.subject_info);
        new WriterThread(getApplication()).execute("getClassInfo");
        new WriterThread(getApplication()).execute(classCode);
        try {
            String t = new ReaderThread().execute().get();
            String s = new ReaderThread().execute().get();
            String r = new ReaderThread().execute().get();
            String su = new ReaderThread().execute().get();
            className.setText("Class Name: "+t);
            section.setText("Section: "+s);
            room.setText("Room: "+r);
            subject.setText("Subject: "+su);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

package com.example.test;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

public class CreateHomework extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    TextView datetext;
    TextView timetext;
    Dialog datePicker;
    Dialog timePicker;
    TextInputEditText title;
    TextInputEditText description;
    EditText point;
    EditText topic;
    final String TAG = "ftsio";
    String datetext_tx;
    String timetxt_tx;
    String title_tx;
    String description_tx;
    String point_tx;
    String classCode;
    String topic_tx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_homework);
        android.support.v7.widget.Toolbar myToolbar = findViewById(R.id.createHomeworkToolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        title=findViewById(R.id.title_hw);
        description=findViewById(R.id.Description_hw);
        point=findViewById(R.id.point_assignment);
        datetext = findViewById(R.id.textView4);
        timetext = findViewById(R.id.textView5);
        topic=findViewById(R.id.topic_hw);
        Intent i = getIntent();
        classCode=i.getStringExtra("classCode");
        Calendar c = Calendar.getInstance();
        datePicker = new DatePickerDialog(this, this, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        timePicker = new TimePickerDialog(this, this, 0, 0, true);

        datetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: date");
                datePicker.show();
            }
        });
        timetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: time");
                timePicker.show();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Log.d(TAG, "onDateSet: " + year + "/" + month + "/" + dayOfMonth);
        datetext.setText(new StringBuilder().append(year).append("/").append(month)
                .append("/").append(dayOfMonth).toString());
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Log.d(TAG, "onDateSet: " + hourOfDay + ":" + minute);
        timetext.setText(new StringBuilder().append(hourOfDay).append(":")
                .append(minute).toString());
    }
    public void clickSend(View v){
        datetext_tx=datetext.getText().toString();
        timetxt_tx=timetext.getText().toString();
        title_tx=title.getText().toString();
        description_tx=description.getText().toString();
        point_tx=point.getText().toString();
        topic_tx=topic.getText().toString();
        datetext_tx=datetext.getText().toString();
        timetxt_tx=timetext.getText().toString();
        new WriterThread(getApplication()).execute("createAssignment");
        new WriterThread(getApplication()).execute(classCode);
        new WriterThread(getApplication()).execute(title_tx);
        new WriterThread(getApplication()).execute(description_tx);
        new WriterThread(getApplication()).execute(point_tx);
        new WriterThread(getApplication()).execute(topic_tx);
        new WriterThread(getApplication()).execute(datetext_tx);
        new WriterThread(getApplication()).execute(timetxt_tx);
        Intent i=new Intent(CreateHomework.this,ClassWorkPage.class);
        i.putExtra("classCode", classCode);
        i.putExtra("status" , "teacher");
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}

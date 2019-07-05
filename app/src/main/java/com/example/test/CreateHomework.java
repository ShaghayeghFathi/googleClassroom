package com.example.test;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

public class CreateHomework extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    TextView datetext;
    TextView timetext;
    Dialog datePicker;
    Dialog timePicker;
    final String TAG = "ftsio";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_homework);
        datetext = findViewById(R.id.textView4);
        timetext = findViewById(R.id.textView5);
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
}

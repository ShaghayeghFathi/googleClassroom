package com.example.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ClassInfoPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_info_page);
        android.support.v7.widget.Toolbar myToolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}

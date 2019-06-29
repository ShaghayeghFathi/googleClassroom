package com.example.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class JoinClassPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_class_page);
        android.support.v7.widget.Toolbar myToolbar=findViewById(R.id.joinClassToolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}

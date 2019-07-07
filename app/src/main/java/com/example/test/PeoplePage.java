package com.example.test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class PeoplePage extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    TextView teacherName;
    ImageView teacherImage;
    String classCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_page);
        Intent i = getIntent();
        classCode = i.getStringExtra("classCode");
        teacherName = findViewById(R.id.teacherName);
        teacherImage = findViewById(R.id.teacherPic);
        ArrayList<PeopleItem> peopleList = new ArrayList<>();
        new WriterThread(getApplication()).execute("getPeopleInfo");
        new WriterThread(getApplication()).execute(classCode);
        try {
            String str=new ReaderThread().execute().get();
            File teacherPic = new ImageReader(str,getApplicationContext()).get();
            teacherImage.setImageBitmap(BitmapFactory.decodeFile(teacherPic.getPath()));
            Log.d("peoplep", "onCreate: " + str);
            teacherName.setText(str);
            int n = Integer.parseInt(new ReaderThread().execute().get());
            for (int j = 0; j < n; j++) {
                String s = new ReaderThread().execute().get();
                peopleList.add(new PeopleItem(s));
            }
            recyclerView = findViewById(R.id.recyclerView_ppl);
            recyclerView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(this);
            adapter = new PeopleAdapter(peopleList, getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

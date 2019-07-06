package com.example.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainPage extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ImageView ib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        android.support.v7.widget.Toolbar myToolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(myToolbar);
        try {
            new WriterThread(getApplication()).execute("numOfClasses");
            String numOfClassesSt = new ReaderThread().execute().get();
            int numOfClasses = Integer.parseInt(numOfClassesSt);
            ArrayList<ExampleItem> exampleList = new ArrayList<>();
            new WriterThread(getApplication()).execute("classInfo");
            for (int i = 0; i < numOfClasses; i++) {
                String code=new ReaderThread().execute().get();
                String subject = new ReaderThread().execute().get();
                String section = new ReaderThread().execute().get();
                String info = new ReaderThread().execute().get();
                if (!section.equals("empty"))
                    exampleList.add(new ExampleItem((R.drawable.cl3), subject, section, info, code));
                else
                    exampleList.add(new ExampleItem((R.drawable.cl3), subject, "", info, code));

            }
            mRecyclerView = findViewById(R.id.recyclerView);
            mRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(this);
            mAdapter = new ExampleAdapter(exampleList, getApplicationContext());
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mAdapter);
            ib = findViewById(R.id.class_click_btn);
//            ib.setOnClickListener(this);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainpagemenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.AboutUs) {
            Intent i = new Intent(MainPage.this, AboutUsPage.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.Refresh) {
            OnRestart();
        } else if (item.getItemId() == R.id.joinClass) {
            Intent i = new Intent(MainPage.this, JoinClassPage.class);
            startActivity(i);

        } else if (item.getItemId() == R.id.createClass) {
            Intent i = new Intent(MainPage.this, CreateClassPage.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    protected void OnRestart() {
        super.onRestart();
        Intent i = new Intent(MainPage.this, MainPage.class);
        startActivity(i);
        finish();
    }

    public void onClick(View v) {

    }


}

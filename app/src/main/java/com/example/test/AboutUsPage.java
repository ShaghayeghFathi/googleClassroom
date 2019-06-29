package com.example.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

public class AboutUsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us_page);
        android.support.v7.widget.Toolbar myToolbar=findViewById(R.id.aboutUsToolbar);
        setSupportActionBar(myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.aboutusmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.Refresh)
        {
            OnRestart();
        }
        return super.onOptionsItemSelected(item);
    }
    protected void OnRestart()
    {
        super.onRestart();
        Intent i=new Intent(AboutUsPage.this,AboutUsPage.class);
        startActivity(i);
        finish();
    }
}

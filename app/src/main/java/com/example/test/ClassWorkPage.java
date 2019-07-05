package com.example.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ClassWorkPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_work_page);
        android.support.v7.widget.Toolbar myToolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(myToolbar);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.classworkmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.AboutUs) {
            Intent i = new Intent(ClassWorkPage.this, AboutUsPage.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.Refresh) {
            OnRestart();
        }
        else if (item.getItemId()==R.id.Classes)
        {
            Intent i=new Intent(ClassWorkPage.this,MainPage.class);
            startActivity(i);
        }
        else if (item.getItemId()==R.id.setting_btn)
        {
            Intent i=new Intent(ClassWorkPage.this,ChangeClassSettings.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    protected void OnRestart() {
        super.onRestart();
        Intent i = new Intent(ClassWorkPage.this, ClassWorkPage.class);
        startActivity(i);
        finish();
    }
}

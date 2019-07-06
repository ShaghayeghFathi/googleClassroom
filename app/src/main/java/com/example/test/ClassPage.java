package com.example.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ClassPage extends AppCompatActivity {
    String classCode;
    String stat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        classCode=i.getStringExtra("classCode");
        stat=i.getStringExtra("status");
        setContentView(R.layout.activity_class_page);
        android.support.v7.widget.Toolbar myToolbar = findViewById(R.id.classToolbar);
        setSupportActionBar(myToolbar);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.classmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.AboutUs) {
            Intent i = new Intent(ClassPage.this, AboutUsPage.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.Refresh) {
            OnRestart();
        }
        else if (item.getItemId()==R.id.Classes)
        {
            Intent i=new Intent(ClassPage.this,MainPage.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    protected void OnRestart() {
        super.onRestart();
        Intent i = new Intent(ClassPage.this, ClassPage.class);
        startActivity(i);
        finish();
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Log.d(this.getClass().getName(), "back button pressed");
        }
        return super.onKeyDown(keyCode, event);
    }
    public void clickClassWork (View v)
    {
        Intent i=new Intent(ClassPage.this,ClassWorkPage.class);
        i.putExtra("classCode" , classCode);
        i.putExtra("status" , stat);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}

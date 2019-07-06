package com.example.test;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ClassWorkPage extends AppCompatActivity {
String classCode;
String stat;
FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        classCode=i.getStringExtra("classCode");
        stat=i.getStringExtra("status");
        Log.d("classWorkPage", "onCreate: beginning");
        setContentView(R.layout.activity_class_work_page);
        android.support.v7.widget.Toolbar myToolbar = findViewById(R.id.classWorkToolbar);
        setSupportActionBar(myToolbar);
        Log.d("classWorkPage", "onCreate: end");
        fab=findViewById(R.id.floatingActionButton);
        if(!stat.equals("teacher")){
            fab.hide();
        }
        else{
            fab.show();
        }
    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        Log.d("classWorkPage", "onCreate: menu" +
                "");
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
            if(stat.equals("teacher")) {
                Intent i = new Intent(ClassWorkPage.this, ChangeClassSettings.class);
                i.putExtra("classCode" , classCode);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }else if(stat.equals("student")){
                Intent i = new Intent(ClassWorkPage.this, ClassInfoPage.class);
                i.putExtra("classCode" , classCode);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }

        }

        return super.onOptionsItemSelected(item);
    }

    protected void OnRestart() {
        super.onRestart();
        Intent i = new Intent(ClassWorkPage.this, ClassWorkPage.class);
        startActivity(i);
        finish();
    }
    public void FABClick(View v){
        Intent i =new Intent(ClassWorkPage.this,CreateHomework.class);
        i.putExtra("classCode", classCode);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}

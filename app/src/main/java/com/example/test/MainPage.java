package com.example.test;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        android.support.v7.widget.Toolbar myToolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(myToolbar);
        
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
        }
 else if (item.getItemId() == R.id.joinClass) {
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

}

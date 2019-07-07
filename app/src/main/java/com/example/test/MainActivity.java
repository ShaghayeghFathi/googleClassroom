package com.example.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.net.ServerSocket;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new SocketThread().execute();
        findViewById(R.id.signUp_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp(v);
            }
        });

    }
    public void signUp (View v)
    {
        new WriterThread(getApplicationContext()).execute("signUp");
        Intent GoSignUpActivity = new Intent (MainActivity.this,SignUpActivity.class);
        startActivity(GoSignUpActivity);

    }
    public void signIn (View v)
    {
        new WriterThread(getApplicationContext()).execute("signIn");
        Intent GoSignInActivity = new Intent (MainActivity.this,SignInActivity.class);
        startActivity(GoSignInActivity);

    }
}

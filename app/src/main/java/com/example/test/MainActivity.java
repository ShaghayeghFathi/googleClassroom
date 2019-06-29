package com.example.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new SocketThread().execute();

    }
    public void signUp (View v)
    {
        Intent GoSignUpActivity = new Intent (MainActivity.this,SignUpActivity.class);
        startActivity(GoSignUpActivity);

    }
    public void signIn (View v)
    {
        Intent GoSignInActivity = new Intent (MainActivity.this,SignInActivity.class);
        startActivity(GoSignInActivity);

    }
}

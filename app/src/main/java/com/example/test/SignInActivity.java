package com.example.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class SignInActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    Button signIn;
    String username_input;
    String password_input;
    boolean correctUsername = false;
    boolean correctPassword = false;
    final String TAG = "asdf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        username = findViewById(R.id.username_signin_et);
        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    isCorrectUsername();
                }
            }
        });
        password = findViewById(R.id.password_signin_et);
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    clickPassword_in();
                }
            }
        });
        signIn = findViewById(R.id.signIn_btn);
    }

    public void signIn_enter(View v) {
        try {
            Log.d(TAG, "signIn_enter: ");
            if (clickPassword_in() && isCorrectUsername()) {
                new WriterThread(getApplicationContext()).execute("password", password_input);
                String ans = new ReaderThread().execute().get();
                if (ans.equals("match")) {
                    correctPassword = true;
                } else if (ans.equals("notMatch")) {
                    correctPassword = false;
                    Toast.makeText(this, "Username and password don't match", Toast.LENGTH_LONG).show();
                    return;
                }
            }
            if (correctPassword && correctUsername) {
                new WriterThread(getApplicationContext()).execute("done");
                String msg = null;
                try {
                    msg = new ReaderThread().execute().get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (msg.equals("loggedIn")) {
                    Intent GoMainPage = new Intent(SignInActivity.this, MainPage.class);
                    startActivity(GoMainPage);
                }

            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean clickPassword_in() {
        password_input = password.getText().toString();
        if (password_input.matches("")) {
            password.setError("This field must be filled!");
            return false;
        }
        return true;
    }

    private boolean isCorrectUsername() {
        try {
            username_input = username.getText().toString();
            if (username_input.matches("")) {
                username.setError("This field must be filled!");
                correctUsername = false;
            } else {
                new WriterThread(getApplicationContext()).execute("username", username_input);
                String ans = new ReaderThread().execute().get();
                if (ans.equals("notExist")) {
                    correctUsername = false;
                    username.setError("Username doesn't exist");
                    username.setText("");
                } else if (ans.equals("exist")) {
                    correctUsername = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return correctUsername;
    }
}

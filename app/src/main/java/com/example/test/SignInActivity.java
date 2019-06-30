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
    boolean correctUsername=false;
    boolean correctPassword=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        username=findViewById(R.id.username_signin_et);
        password=findViewById(R.id.password_signin_et);
        signIn=findViewById(R.id.signIn_btn);
    }
    public void signIn_enter (View v) {
        username_input=username.getText().toString();
        password_input=password.getText().toString();
        if(username_input.matches("")){
            username.setError("This field must be filled!");
            correctUsername=false;
        }else {
            correctUsername=true;
        }
        if(password_input.matches("")){
            password.setError("This field must be filled!");
            correctPassword=false;
        }else{
            correctPassword=true;
        }
        if(correctPassword && correctUsername){
            new WriterThread().execute(username_input+ " " + password_input);
            try {
                String answer = new ReaderThread().execute().get();
                if( answer.equals("match")){
                    Log.d("hello", "signIn_enter: its matched");
                    Intent goMainPage = new Intent(SignInActivity.this, MainActivity.class);
                    startActivity(goMainPage);
                }else{
                    Toast.makeText(this,"Wrong username or password",Toast.LENGTH_SHORT).show();
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void clickPassword_in(View v){
        username_input=username.getText().toString();
        if(username_input.matches("")){
            username.setError("This field must be filled!");
        }
    }
    public void clickUsername_in(View view){
        password_input=password.getText().toString();
        if(password_input.matches("")){
            password.setError("This field must be filled!");
        }
    }
}

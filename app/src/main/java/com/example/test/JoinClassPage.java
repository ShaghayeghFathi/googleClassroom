package com.example.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class JoinClassPage extends AppCompatActivity {
    EditText classCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_class_page);
        android.support.v7.widget.Toolbar myToolbar=findViewById(R.id.joinClassToolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        classCode=findViewById(R.id.classCode);
    }
    public void selectJoin(View v){
        String code=classCode.getText().toString();
        new WriterThread(getApplication()).execute("joinClass");
        new WriterThread(getApplication()).execute(code);
        try {
            String ans=new ReaderThread().execute().get();
            Log.d("ans", "selectJoin: "+ ans);
            if(ans.equals("correctCode")){
                Intent i=new Intent(JoinClassPage.this,ClassPage.class);
                startActivity(i);
            }else if(ans.equals("wrongCode")){
                Toast.makeText(getApplicationContext(),"Wrong code!",Toast.LENGTH_LONG).show();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}

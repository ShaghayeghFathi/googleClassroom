package com.example.test;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.OpenableColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.concurrent.ExecutionException;


public class SignUpActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    Button signUp;
    String input_username;
    String input_password;
    ImageSocket imageSocket = null;
    boolean correctUsername = false;
    boolean correctPassword = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        username = findViewById(R.id.username_signup_et);
        password = findViewById(R.id.password_signup_et);
        signUp = findViewById(R.id.signUp_enter_btn);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp_enter(v);
            }
        });
        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    isCorrectUsername();
                }
            }
        });
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    isCorrectPassword();
                }
            }
        });

    }

    private boolean isCorrectPassword() {
        input_password = password.getText().toString();
        if (input_password.matches("")) {
            password.setError("password is required");
        } else if (input_password.length() <= 5) {
            password.setError("Must be longer than 5 letters!");
        } else {
            return true;
        }
        return false;
    }

    private boolean isCorrectUsername() {
        input_username = username.getText().toString();
        if (!input_username.equals("")) {
            new WriterThread(getApplicationContext()).execute("username", input_username);
            try {
                String response = new ReaderThread().execute().get();
                if (response.equals("repetitiveUsername")) {
                    username.setError("Username is Taken");
                    password.clearFocus();
                    username.requestFocus();
                } else if (response.equals("uniqueUsername")) {
                    return true;
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } else {
            username.setError("Username is required");
        }
        return false;
    }

    public void selectImage(View v)                           //nemidanim
    {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/jpeg");
        startActivityForResult(intent, 112);
    }

    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }

        return result;
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        if (resultCode == RESULT_OK && reqCode == 112) {
            final Uri imageUri = data.getData();
            ((TextView) findViewById(R.id.fileName)).setText(getFileName(imageUri));
            imageSocket = new ImageSocket(imageUri);
        }
    }

    public void signUp_enter(View v) {

        if (isCorrectPassword() && isCorrectUsername()) {
            correctPassword = true;
            if(imageSocket != null){
                imageSocket.execute();
                try {
                    String s = new ReaderThread().execute().get();
                    if(s.equals("error")){
                        Toast.makeText(getApplicationContext(),"sdfklasjdlfasdjflkajb",Toast.LENGTH_LONG).show();
                    }else if(s.equals("received")){
                        Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            new WriterThread(getApplicationContext()).execute("password", input_password, "done");

            String msg = null;
            try {
                msg = new ReaderThread().execute().get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (msg.equals("registered")) {
                Intent GoMainPage = new Intent(SignUpActivity.this, MainPage.class);
                startActivity(GoMainPage);
            }
        }
    }

    class ImageSocket extends AsyncTask<Void, Void, Void> {
        Uri image;

        public ImageSocket(Uri img) {
            image = img;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                InputStream is = getContentResolver().openInputStream(image);
                DataInputStream fis = new DataInputStream(is);
                SocketThread.dos.writeUTF("image");
                SocketThread.dos.writeInt(fis.available());
                while (fis.available() > 0) {
                    byte b = fis.readByte();
                    SocketThread.dos.writeByte(b);
                    SocketThread.dos.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}

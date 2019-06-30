package com.example.test;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;


public class SignUpActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    Button signUp;
    String input_username;
    String input_password;
    boolean correctUsername=false;
    boolean correctPassword=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        username=findViewById(R.id.username_signup_et);
        password=findViewById(R.id.password_signup_et);
        signUp=findViewById(R.id.signUp_btn);

    }
    public void selectImage(View v)                           //nemidanim
    {
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setType("image/*");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
//        BufferedImage bimage= ImageIO.read(new File("images.png"));
//        ByteArrayOutputStream bos=new ByteArrayOutputStream();
//        ImageIO.write(bimage,"png",bos);
//        byte[] bytes=bos.toByteArray();
    }
    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                //imageStream.read()
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(SignUpActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        }
    }
    public void signUp_enter(View v)
    {
        input_password=password.getText().toString();
        if (input_password.equals(""))
        {
            password.setError("password is required");
            correctPassword=false;
        }
        else if (input_password.length()<6)
        {
            password.setError("password should be more than 5 letters");
            correctPassword=false;
        }
        else {
            correctPassword = true;
//                SocketThread.dos.writeUTF("password "+input_password);
                new WriterThread().execute("password " + input_password);

        }
        input_username=username.getText().toString();
        try {
            if (input_username.matches(""))
            {
                username.setError("username is required");
                correctUsername=false;
            }
            else {
               // SocketThread.dos.writeUTF("username "+input_username);
                new WriterThread().execute("username " + input_username);
//                String answer=SocketThread.dis.readUTF();
                String answer = new ReaderThread().execute().get();
                if (answer.equals("repetitiveUsername")) {
                    username.setError("username is taken");
                    correctUsername = false;
                } else
                    correctUsername = true;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (correctUsername&&correctPassword) {
            Intent GoMainPage = new Intent(SignUpActivity.this, MainPage.class);
            startActivity(GoMainPage);
        }
    }
    public void clickUsername(View v)
    {
        input_password=password.getText().toString();
        if (input_password.matches(""))
        {
            password.setError("password is required");
        }
        else if (input_password.length()<6)
        {
            password.setError("password should be more than 5 letters");
        }
    }
    public void clickPassword(View v)
    {
        input_username=username.getText().toString();
        if (input_username.matches(""))
        {
            username.setError("username is required");
        }
        else {
            try {
//                SocketThread.dos.writeUTF("username " + input_username);
                new WriterThread().execute("username " + input_username);
//                String answer = SocketThread.dis.readUTF();
                String answer = new ReaderThread().execute().get();
                if (answer.equals("repetitiveUsername")) {
                    username.setError("username is taken");
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}

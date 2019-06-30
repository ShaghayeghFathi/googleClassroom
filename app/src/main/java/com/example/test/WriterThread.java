package com.example.test;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

public class WriterThread extends AsyncTask<String, Void, Void> {
    @Override
    protected Void doInBackground(String... strings) {
        try {
            SocketThread.dos.writeUTF(strings[0]);
            Log.d("wtf", "writing to client");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
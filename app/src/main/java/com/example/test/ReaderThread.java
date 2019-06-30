package com.example.test;
import android.os.AsyncTask;

import com.example.test.SocketThread;

import java.io.IOException;


public class ReaderThread extends AsyncTask<Void, SocketThread, String> {
    @Override
    protected String doInBackground(Void... voids) {
        String read = null;
        try {
            read = SocketThread.dis.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            return read;
        }
    }
}


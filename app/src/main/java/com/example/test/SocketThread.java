package com.example.test;

import android.os.AsyncTask;
import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;



public class SocketThread extends AsyncTask<Void,Void,Void> {
    static Socket socket;
    static DataInputStream dis;
    static DataOutputStream dos;
    protected Void doInBackground(Void... voids)
    {
        try {
            Log.d("connect", "doInBackground: trying to make a socket");
            socket=new Socket(" 192.168.1.106",12345);
            Log.d("bye","world");

            System.out.println("after socket");
            dis=new DataInputStream(socket.getInputStream());
            dos=new DataOutputStream(socket.getOutputStream());
            dos.writeUTF("signUp");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    protected void onPostExecute(Void v)
    {

    }
    protected void onPreExecute()
    {

    }

}



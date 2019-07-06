package com.example.test;
import android.os.AsyncTask;
import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
public class SocketThread extends AsyncTask<Void,Void,Void> {
    static Socket socket;
    static DataInputStream dis=null;
    static DataOutputStream dos=null;
    protected Void doInBackground(Void... voids){
        try {
            Log.d("connect", "doInBackground: trying to make a socket");
            socket=new Socket("172.20.109.83",12345);
            dis=new DataInputStream(socket.getInputStream());
            dos=new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}



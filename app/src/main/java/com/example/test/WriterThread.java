package com.example.test;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

public class WriterThread extends AsyncTask<String, Void, Void> {
    ProgressDialog pd;
    Context ctx;

    public WriterThread(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        pd = new ProgressDialog(ctx);
        pd.setCancelable(false);
        pd.setTitle("Please Wait...");
        pd.setMessage("Managing Data Transfer");
//       pd.show();
    }

    @Override
    protected Void doInBackground(String... strings) {
        try {
            for (String str : strings) {
                SocketThread.dos.writeUTF(str);
                SocketThread.dos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        pd.dismiss();
    }
}
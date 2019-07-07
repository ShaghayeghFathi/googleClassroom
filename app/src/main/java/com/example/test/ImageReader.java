package com.example.test;

import android.content.Context;
import android.os.AsyncTask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageReader extends AsyncTask<Void, Void, File> {
    String uname;
    File f;
    Context ctx;
    public ImageReader(String uname,Context ctx) {
        this.uname = uname;
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        f = new File(ctx.getFilesDir(), "profile.jpg");

    }

    @Override
    protected File doInBackground(Void... voids) {
        try {
            SocketThread.dos.writeUTF("getProfileImage");
            SocketThread.dos.writeUTF(uname);
            int av = SocketThread.dis.readInt();
            FileOutputStream fos = new FileOutputStream(f);
            for (int i = 0; i < av; i++) {
                fos.write(SocketThread.dis.readByte());
                fos.flush();
            }
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return f;
    }
}

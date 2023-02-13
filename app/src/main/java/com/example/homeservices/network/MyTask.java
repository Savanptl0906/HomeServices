package com.example.homeservices.network;

import android.os.AsyncTask;

import com.example.homeservices.utilitypackage.UtilityClass;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyTask extends AsyncTask<String,Void,Void> {

    NetworkInterface network;
    String msg="";
    public MyTask(NetworkInterface network)
    {
        this.network = network;
    }

    @Override
    protected Void doInBackground(String... d) {

        try
        {
            URL url = new URL(UtilityClass.domain+d[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("POST");
            OutputStream os = con.getOutputStream();
            os.write(d[1].getBytes());

            InputStream is = con.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            msg = br.readLine();
        }
        catch(Exception e)
        {
            msg = e.toString();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        // TODO Auto-generated method stub
        super.onProgressUpdate(values);
    }
    @Override
    protected void onPostExecute(Void result) {
        network.networkResult(msg);
        super.onPostExecute(result);
    }

}

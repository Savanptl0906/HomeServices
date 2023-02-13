package com.example.homeservices;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends Activity implements Runnable {

    Handler h;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        h=new Handler();
        h.postDelayed(this,2000);
    }

    @Override
    public void run() {
        Intent i;
        i=new Intent(this,LoginActivity.class);
        startActivity(i);
        finish();
    }
}

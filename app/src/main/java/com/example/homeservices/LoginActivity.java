package com.example.homeservices;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences spf =  getSharedPreferences("customer", Context.MODE_PRIVATE);
        boolean cstatus = spf.getBoolean("customerlogin", false);
        if(cstatus == true)
        {
            Intent i = new Intent(this,CustomerHomeActivitty.class);
            startActivity(i);
            finish();
        }


        spf =  getSharedPreferences("provider", Context.MODE_PRIVATE);
        boolean pstatus = spf.getBoolean("providerlogin", false);
        if(pstatus == true)
        {
            Intent i = new Intent(this,ProviderHomeActivity.class);
            startActivity(i);
            finish();
        }

    }

    void customerData()
    {
        SharedPreferences spf = getSharedPreferences("customer",Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = spf.edit();
        ed.putBoolean("customerlogin", true);
        ed.commit();
    }
    void providerData()
    {
        SharedPreferences spf = getSharedPreferences("provider",Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = spf.edit();
        ed.putBoolean("providerlogin", true);
        ed.commit();
    }
    public void customer(View v)
    {
        Intent i=new Intent(this,CustomerLoginActivity.class);
        startActivity(i);
        finish();
    }
    public void provider(View v)
    {
        Intent i=new Intent(this,ProviderLoginActivity.class);
        startActivity(i);
        finish();
    }
    public void serviceMan(View v)
    {
        Intent i=new Intent(this,ServiceManLogin.class);
        startActivity(i);
        finish();
    }
}

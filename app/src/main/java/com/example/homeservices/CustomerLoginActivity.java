package com.example.homeservices;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.homeservices.network.MyTask;
import com.example.homeservices.network.NetworkInterface;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class CustomerLoginActivity extends Activity implements NetworkInterface {

    EditText mobile, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);


        mobile = findViewById(R.id.mobile);
        password = findViewById(R.id.password);

        SharedPreferences spf =  getSharedPreferences("customer",Context.MODE_PRIVATE);
        boolean status = spf.getBoolean("customerlogin", false);
        if(status == true)
        {
            Intent i = new Intent(this,CustomerHomeActivitty.class);
            startActivity(i);
            finish();
        }

    }
        public void signup (View view)
        {
            Intent i = new Intent(this, CustomerMobileSignup.class);
            startActivity(i);
            finish();
        }

        public void reset (View view)
        {
            mobile.setText("");
            password.setText("");
            mobile.requestFocus();
        }

    public void login(View v)
    {
  //      Toast.makeText(this, "login clicked", Toast.LENGTH_SHORT).show();
        String a = mobile.getText().toString();
        String b = password.getText().toString();

        if(a.contentEquals(""))
        {
            mobile.setError("Please Enter The Mobile No. ");
        }
        else if (b.contentEquals(""))
        {
            password.setError("Please Enter The Password ");
        }
        else{
            try{
                String data;
                data = URLEncoder.encode("mobile","UTF-8")+"="+
                        URLEncoder.encode(a,"UTF-8")+"&"+
                        URLEncoder.encode("password","UTF-8")+"="+
                        URLEncoder.encode(b,"UTF-8");

//                Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
                MyTask task = new MyTask(this);
                task.execute("customerlogin.php",data);

            }
            catch(Exception e)
            {
                Toast.makeText(this, "Url problem",Toast.LENGTH_LONG).show();
            }
        }
    }

    void customerData()
    {
        String customershared=mobile.getText().toString();

        SharedPreferences spf = getSharedPreferences("customer",Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = spf.edit();
        ed.putBoolean("customerlogin", true);
        ed.putString("customershared",customershared);
        ed.commit();
    }



    @Override
    public void networkResult(String msg) {
        if(msg.equalsIgnoreCase("fail"))
        {
            Toast.makeText(CustomerLoginActivity.this, "Mobile No. or password is Incorrect...",Toast.LENGTH_LONG).show();
        }
        else if(msg.equalsIgnoreCase("success"))
        {
            Intent i;
           i = new Intent(CustomerLoginActivity.this,MapLocation.class);
//            i = new Intent(CustomerLoginActivity.this,CustomerHomeActivitty.class);
            startActivity(i);

            customerData();

            finish();
        }
        else
        {
             Toast.makeText(CustomerLoginActivity.this, msg ,Toast.LENGTH_LONG).show();
        }
    }

}

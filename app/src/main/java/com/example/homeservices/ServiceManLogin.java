package com.example.homeservices;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.homeservices.network.MyTask;
import com.example.homeservices.network.NetworkInterface;

import java.net.URLEncoder;

public class ServiceManLogin extends AppCompatActivity implements NetworkInterface {

    EditText mobile, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_man_login);


        mobile = findViewById(R.id.mobile);
        password = findViewById(R.id.password);

        SharedPreferences spf =  getSharedPreferences("serviceman", Context.MODE_PRIVATE);
        boolean status = spf.getBoolean("servicemanlogin", false);
        if(status == true)
        {
            Intent i = new Intent(this,ServiceManHome.class);
            startActivity(i);
            finish();
        }


    }

    public void reset (View view)
    {
        mobile.setText("");
        password.setText("");
        mobile.requestFocus();
    }

    public void login(View v)
    {

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
                task.execute("servicemanLogin.php",data);

            }
            catch(Exception e)
            {
                Toast.makeText(this, "Url problem",Toast.LENGTH_LONG).show();
            }
        }
    }

    void servicemanData()
    {
        String servicemanshared=mobile.getText().toString();

        SharedPreferences spf = getSharedPreferences("serviceman",Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = spf.edit();
        ed.putBoolean("servicemanlogin", true);
        ed.putString("servicemanshared",servicemanshared);
        ed.commit();
    }


    @Override
    public void networkResult(String msg) {

        if(msg.equalsIgnoreCase("fail"))
        {
            Toast.makeText(ServiceManLogin.this, "Mobile No. or password is Incorrect...",Toast.LENGTH_LONG).show();
        }
        else if(msg.equalsIgnoreCase("success"))
        {
            Intent i;
            i = new Intent(ServiceManLogin.this,ServiceManHome.class);
//            i = new Intent(CustomerLoginActivity.this,CustomerHomeActivitty.class);
            startActivity(i);

            servicemanData();

            finish();
        }
        else
        {
            Toast.makeText(ServiceManLogin.this, msg ,Toast.LENGTH_LONG).show();
        }
    }
}

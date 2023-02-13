package com.example.homeservices;

import android.app.Activity;
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

public class ProviderLoginActivity extends Activity implements NetworkInterface {

    EditText mobile, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_login);

        mobile = findViewById(R.id.mobile);
        password = findViewById(R.id.password);

        SharedPreferences spf =  getSharedPreferences("provider", Context.MODE_PRIVATE);
        boolean status = spf.getBoolean("providerlogin", false);
        if(status == true)
        {
            Intent i = new Intent(this,ProviderHomeActivity.class);
            startActivity(i);
            finish();
        }
    }
    public void signup(View v)
    {
        Intent i = new Intent(this, ProviderMobileSignup.class);
        startActivity(i);
        finish();
    }
    public void reset (View view)
    {
        mobile.setText("");
        password.setText("");
        mobile.requestFocus();
    }

    public void login(View view)
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

                MyTask task = new MyTask(this);
                task.execute("providerlogin.php",data);

            }
            catch(Exception e)
            {
                Toast.makeText(this, "Url problem",Toast.LENGTH_LONG).show();
            }

        }
    }

    void providerData()
    {
        String sharedmobile=mobile.getText().toString();

        SharedPreferences spf = getSharedPreferences("provider",Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = spf.edit();
        ed.putBoolean("providerlogin", true);
        ed.putString("sharedmobile",sharedmobile);
        ed.commit();
    }


    @Override
    public void networkResult(String msg) {
        if(msg.equalsIgnoreCase("fail"))
        {
            Toast.makeText(ProviderLoginActivity.this, "Mobile No. or Password is Incorrect...",Toast.LENGTH_LONG).show();
        }
        else if(msg.equalsIgnoreCase("success"))
        {
            Intent i;
            i = new Intent(ProviderLoginActivity.this,ProviderHomeActivity.class);
            startActivity(i);

            providerData();

            finish();
        }
        else
        {
            Toast.makeText(ProviderLoginActivity.this, msg ,Toast.LENGTH_LONG).show();
        }
    }

}

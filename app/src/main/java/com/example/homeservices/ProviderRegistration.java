package com.example.homeservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homeservices.network.MyTask;
import com.example.homeservices.network.NetworkInterface;

import java.net.URLEncoder;

public class ProviderRegistration extends AppCompatActivity implements NetworkInterface {

    EditText registration;
    TextView mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_registration);

        registration=findViewById(R.id.registration);
        mobile=findViewById(R.id.mobile);

        Bundle bundle=getIntent().getExtras();
        String phone=bundle.getString("phn");

        mobile.setText(phone);
    }

    public void check(View v)
    {
        String a = registration.getText().toString();

        try{
            String data;
            data = URLEncoder.encode("registration","UTF-8")+"="+
                    URLEncoder.encode(a,"UTF-8");

            MyTask task = new MyTask(this);
            task.execute("registration.php",data);

        }
        catch(Exception e)
        {
            Toast.makeText(this, "Url problem",Toast.LENGTH_LONG).show();
        }

    }

    public void reset(View v)
    {
        registration.setText("");
    }
    @Override
    public void networkResult(String msg) {

        if(msg.equalsIgnoreCase("fail"))
        {
            Toast.makeText(ProviderRegistration.this, "Your Registration Number Is Not Available ...",Toast.LENGTH_LONG).show();
        }
        else if(msg.equalsIgnoreCase("success"))
        {

            String reg=registration.getText().toString();
            String mob= mobile.getText().toString();

            Intent i;
            //  i = new Intent(CustomerLoginActivity.this,MapLocation.class);
            i = new Intent(ProviderRegistration.this,ProviderSignupActivity.class);
            i.putExtra("reg",reg);
            i.putExtra("mob",mob);
            startActivity(i);
            finish();

        }
        else
        {
            Toast.makeText(ProviderRegistration.this, msg ,Toast.LENGTH_LONG).show();
        }
    }
}


package com.example.homeservices;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homeservices.network.MyTask;
import com.example.homeservices.network.NetworkInterface;

import java.net.URLEncoder;

public class CustomerDescription2 extends AppCompatActivity implements NetworkInterface {

    TextView cname,caddress,cmobile,charge,modelno,issues,providerMobile,shopname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_description2);


        cname=findViewById(R.id.cname);
        caddress=findViewById(R.id.caddress);
        cmobile=findViewById(R.id.cmobile);
        charge=findViewById(R.id.charge);
        modelno=findViewById(R.id.modelno);
        issues=findViewById(R.id.issues);
        providerMobile=findViewById(R.id.pr_mobile);
        shopname=findViewById(R.id.pr_name);

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("cname");
        String mobile = bundle.getString("cmobile");
        String address = bundle.getString("caddress");
        String model = bundle.getString("model");
        String issue = bundle.getString("issue");
        String visitcharge = bundle.getString("visitcharge");
        String p_mobile = bundle.getString("pr_mobile");
        String p_name = bundle.getString("pr_name");

        cname.setText(name);
        cmobile.setText(mobile);
        caddress.setText(address);
        modelno.setText(model);
        issues.setText(issue);
        charge.setText(visitcharge);
        providerMobile.setText(p_mobile);
        shopname.setText(p_name);
//        Toast.makeText(this,"name :"+name+" mobile :"+mobile+" address :"+address+" model :"+model+" issue :"+issue+" visit charge :"+visitcharge,Toast.LENGTH_LONG).show();
    }

    String pr_mobile,custname;
    public void completed(View view)
    {
        pr_mobile=providerMobile.getText().toString();
        custname=cname.getText().toString();
        try{
            String data;
            data = URLEncoder.encode("providermobile","UTF-8")+"="+
                    URLEncoder.encode(""+pr_mobile,"UTF-8")+"&"+
                    URLEncoder.encode("custname","UTF-8")+"="+
                    URLEncoder.encode(""+custname,"UTF-8");

            MyTask task = new MyTask(this);
            task.execute("status.php",data);

        }
        catch(Exception e)
        {
            Toast.makeText(this, "Url problem"+e.toString(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.servicemanmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout)
        {
            SharedPreferences spf = getSharedPreferences("serviceman", Context.MODE_PRIVATE);
            SharedPreferences.Editor ed;
            ed = spf.edit();
            ed.putBoolean("servicemanlogin", false);
            ed.commit();

            Toast.makeText(this, "Signout done!!!", Toast.LENGTH_LONG).show();
            finish();

            Intent intent;
            intent = new Intent(CustomerDescription2.this,LoginActivity.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void networkResult(String msg) {
        try{
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();

            if (msg.equals("success"))
            {
                Intent intent=new Intent(CustomerDescription2.this,ServiceManHome.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
            }
        }
        catch(Exception e)
        {
            Toast.makeText(CustomerDescription2.this, "json prob"+e.toString(),Toast.LENGTH_LONG).show();
        }
    }
}

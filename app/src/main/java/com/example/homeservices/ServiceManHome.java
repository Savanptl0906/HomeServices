package com.example.homeservices;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homeservices.network.MyTask;
import com.example.homeservices.network.NetworkInterface;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class ServiceManHome extends AppCompatActivity implements NetworkInterface {


    Context c;
    String a,svalue,shopname;
    TextView sname,smobile;
    ListView lv1;
    List<String> cname,cmobile,caddress,model,issue,visitcharge,pr_mobile,pr_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_man_home);

        c=this;


        lv1=findViewById(R.id.lv1);
        smobile=findViewById(R.id.smobile);
        sname=findViewById(R.id.sname);

        cname = new ArrayList<String>();
        cmobile = new ArrayList<String>();
        caddress = new ArrayList<String>();
        model = new ArrayList<String>();
        issue = new ArrayList<String>();
        visitcharge = new ArrayList<String>();
        pr_mobile = new ArrayList<String>();
        pr_name = new ArrayList<String>();


        SharedPreferences spf = getSharedPreferences("serviceman",Context.MODE_PRIVATE);
        String value=spf.getString("servicemanshared","servicemanshared");

        smobile.setText(value);

        svalue=smobile.getText().toString();


        servicemanRequest();
    }



    void servicemanRequest()
    {
        try{
            String data;
            data = URLEncoder.encode("smobile","UTF-8")+"="+
                    URLEncoder.encode(""+svalue,"UTF-8");

            MyTask task = new MyTask(this);
            task.execute("servicemanShow.php",data);

        }
        catch(Exception e)
        {
            Toast.makeText(this, "Url problem"+e.toString(),Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void networkResult(String msg) {


        try{
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
            JSONArray arr = new JSONArray(msg);

            for(int i=0;i<arr.length();i++)
            {
                JSONObject obj = arr.getJSONObject(i);

                String cn = obj.getString("cname");
                String cm = obj.getString("cmobile");
                String ca = obj.getString("caddress");
                String m = obj.getString("model");
                String is = obj.getString("issue");
                String pc = obj.getString("visitcharge");

                String provider = obj.getString("pr_mobile");
                String extra=obj.getString("pr_name");

                cname.add(cn);
                cmobile.add(cm);
                caddress.add(ca);
                model.add(m);
                issue.add(is);
                visitcharge.add(pc);

                pr_mobile.add(provider);
                pr_name.add(extra);

            }
        }
        catch(Exception e)
        {
            Toast.makeText(ServiceManHome.this, "json prob"+e.toString(),Toast.LENGTH_LONG).show();
        }
        ServicemanAdapter ad = new ServicemanAdapter(ServiceManHome.this,cname,cmobile,caddress,model,issue,visitcharge,pr_mobile,pr_name);
        lv1.setAdapter(ad);
    }
}

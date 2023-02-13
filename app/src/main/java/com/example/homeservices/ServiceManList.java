package com.example.homeservices;

import android.content.Context;
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

public class ServiceManList extends AppCompatActivity implements NetworkInterface {

    TextView pr_mobile;
    ListView lv1;

    String svalue;
    String c_name,c_mobile,c_address,c_model,c_issue,c_visitcharge,shopname,pmobile;
    Context c;
    List<String> sname,smobile,cname,cmobile,caddress,model,issue,visitcharge,pro_name,pro_mobile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_man_list);

        pr_mobile=findViewById(R.id.pr_mobile);
        lv1=findViewById(R.id.lv1);

        Bundle bundle=getIntent().getExtras();

        pmobile=bundle.getString("pr_mobile");
        shopname=bundle.getString("pr_name");

        c_name=bundle.getString("c_name");
        c_mobile=bundle.getString("c_mobile");
        c_address=bundle.getString("c_address");
        c_model=bundle.getString("c_model");
        c_issue=bundle.getString("c_issue");
        c_visitcharge=bundle.getString("c_visitcharge");



        pr_mobile.setText(pmobile);
        svalue=pr_mobile.getText().toString();

        c=this;

        sname = new ArrayList<String>();
        smobile = new ArrayList<String>();

        cname = new ArrayList<String>();
        cmobile = new ArrayList<String>();
        caddress = new ArrayList<String>();
        model = new ArrayList<String>();
        issue = new ArrayList<String>();
        visitcharge = new ArrayList<String>();

        pro_mobile= new ArrayList<String>();
        pro_name= new ArrayList<String>();





        serviceMan();
    }

    void serviceMan()
    {
        try{
            String data;
            data = URLEncoder.encode("providermobile","UTF-8")+"="+
                    URLEncoder.encode(""+svalue,"UTF-8");

            MyTask task = new MyTask(this);
            task.execute("serviceManList.php",data);

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

                String name = obj.getString("name");
                String mobile = obj.getString("mobile");


                sname.add(name);
                smobile.add(mobile);

                cname.add(c_name);
                cmobile.add(c_mobile);
                caddress.add(c_address);
                model.add(c_model);
                issue.add(c_issue);
                visitcharge.add(c_visitcharge);

                pro_name.add(shopname);
                pro_mobile.add(pmobile);

            }
        }
        catch(Exception e)
        {
            Toast.makeText(ServiceManList.this, "json prob"+e.toString(),Toast.LENGTH_LONG).show();
        }

        ServiceManDesignList ad = new ServiceManDesignList(ServiceManList.this,sname,smobile,cname,cmobile,caddress,model,issue,visitcharge,pro_name,pro_mobile);
        lv1.setAdapter(ad);
    }
}

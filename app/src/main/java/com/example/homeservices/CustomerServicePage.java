package com.example.homeservices;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
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

public class CustomerServicePage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, NetworkInterface {


    Context c;
    String a,cvalue;
    TextView c_name,c_mobile;
    ListView lv1;
    List<String> cname,cmobile,caddress,model,issue,visitcharge,pr_mobile,pr_name,sname,smobile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_service_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);



        c=this;


        lv1=findViewById(R.id.lv1);
        c_mobile=findViewById(R.id.c_mobile);
        c_name=findViewById(R.id.c_name);

        cname = new ArrayList<String>();
        cmobile = new ArrayList<String>();
        caddress = new ArrayList<String>();
        model = new ArrayList<String>();
        issue = new ArrayList<String>();
        visitcharge = new ArrayList<String>();

        pr_mobile = new ArrayList<String>();
        pr_name = new ArrayList<String>();

        sname = new ArrayList<String>();
        smobile = new ArrayList<String>();



        Bundle bundle=getIntent().getExtras();
        String value=bundle.getString("c_mobile");

        c_mobile.setText(value);

        cvalue=c_mobile.getText().toString();


        servicemanRequest();


    }

    void servicemanRequest()
    {
        try{
            String data;
            data = URLEncoder.encode("cmobile","UTF-8")+"="+
                    URLEncoder.encode(""+cvalue,"UTF-8");

            MyTask task = new MyTask(this);
            task.execute("customerServiceShow.php",data);

        }
        catch(Exception e)
        {
            Toast.makeText(this, "Url problem"+e.toString(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.customer_service_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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

                String sn=obj.getString("sname");
                String sm=obj.getString("smobile");

                cname.add(cn);
                cmobile.add(cm);
                caddress.add(ca);
                model.add(m);
                issue.add(is);
                visitcharge.add(pc);

                pr_mobile.add(provider);
                pr_name.add(extra);

                sname.add(sn);
                smobile.add(sm);


            }
        }
        catch(Exception e)
        {
            Toast.makeText(CustomerServicePage.this, "json prob"+e.toString(),Toast.LENGTH_LONG).show();
        }
        CustomerAdapter ad = new CustomerAdapter(CustomerServicePage.this,cname,cmobile,caddress,model,issue,visitcharge,pr_mobile,pr_name,sname,smobile);
        lv1.setAdapter(ad);
    }
}

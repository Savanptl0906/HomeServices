package com.example.homeservices;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
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

public class ProviderListForCustomer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, NetworkInterface, AdapterView.OnItemClickListener {

    Context c;
    TextView list;
    String a;
    ListView lv1;
    List<String> shopname,mobile,address,visitcharge,pname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_list_for_customer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        c=this;

        list=findViewById(R.id.list);
        lv1=findViewById(R.id.lv1);
        Bundle bundle = getIntent().getExtras();
        a = bundle.getString("name");

        shopname = new ArrayList<String>();
        mobile = new ArrayList<String>();
        address = new ArrayList<String>();
        visitcharge = new ArrayList<String>();
        pname=new ArrayList<String>();

        if (a.equals("Electricians"))
        {
            list();
        }
        else if (a.equals("Plumbers"))
        {
            list();
        }
        else if (a.equals("Geyser"))
        {
            list();
        }
        else if (a.equals("Carpenters"))
        {
            list();
        }
        else if (a.equals("AC Service"))
        {
            list();
        }
        else if (a.equals("Refregerator Repair"))
        {
            list();
        }
        else if (a.equals("Washing Machine Repair"))
        {
            list();
        }
        else if (a.equals("Water Purifier Repair"))
        {
            list();
        }
        else if (a.equals("Microwave Repair"))
        {
            list();
        }
        else if (a.equals("Kitchen Deep cleaning"))
        {
            list();
        }
        else if (a.equals("Home Deep cleaning"))
        {
            list();
        }
        else if (a.equals("Carpet Cleaning"))
        {
            list();
        }
        else if (a.equals("Bathroom Deep Cleaning"))
        {
            list();
        }
        else if (a.equals("Garden Cleaning"))
        {
            list();
        }
        else if (a.equals("Sofa Cleaning"))
        {
            list();
        }
        else if (a.equals("Home Painting"))
        {
            list();
        }
        else if (a.equals("Architect"))
        {
            list();
        }
        else if (a.equals("Modular Kitchen"))
        {
            list();
        }
        else if (a.equals("Bathroom Renovation"))
        {
            list();
        }
        else if (a.equals("Furniture"))
        {
            list();
        }
        else {

            Toast.makeText(this,"Problem",Toast.LENGTH_LONG).show();
        }
    }

    void list()
    {
        list.setText("Your Near By "+a+" Services");
        try{
            String data;
            data = URLEncoder.encode("category","UTF-8")+"="+
                    URLEncoder.encode(""+a,"UTF-8");

            MyTask task = new MyTask(this);
            task.execute("category.php",data);

        }
        catch(Exception e)
        {
            Toast.makeText(this, "Url problem"+e.toString(),Toast.LENGTH_LONG).show();
        }

        Toast.makeText(this,"Services :"+a,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.provider_list_for_customer, menu);
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


        if (id == R.id.home)
        {
            Intent i=new Intent(this,CustomerHomeActivitty.class);
            startActivity(i);
        }
        else if (id == R.id.profile) {

            Intent i=new Intent(this,CustomerProfile.class);
            startActivity(i);
        } else if (id == R.id.history) {

        } else if (id == R.id.settings) {

        }
        else if (id == R.id.logout)
        {
            SharedPreferences spf = getSharedPreferences("customer", Context.MODE_PRIVATE);
            SharedPreferences.Editor ed;
            ed = spf.edit();
            ed.putBoolean("customerlogin", false);
            ed.commit();

            Toast.makeText(this, "Signout done!!!", Toast.LENGTH_LONG).show();
            finish();

            Intent intent;
            intent = new Intent(this,LoginActivity.class);
            startActivity(intent);

        }else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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

                String n = obj.getString("shopname");
                String m= obj.getString("mobile");
                String ad= obj.getString("address");
                String ch= obj.getString("visitcharge");
                String p= obj.getString("name");

                shopname.add(n);
                mobile.add(m);
                address.add(ad);
                visitcharge.add(ch);
                pname.add(p);
            }
        }
        catch(Exception e)
        {
            Toast.makeText(ProviderListForCustomer.this, "json prob"+e.toString(),Toast.LENGTH_LONG).show();
        }
        CustomAdapter ad = new CustomAdapter(ProviderListForCustomer.this,shopname,mobile,address,visitcharge,pname);
        lv1.setAdapter(ad);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        TextView a=view.findViewById(R.id.sname);
        TextView b=view.findViewById(R.id.smobile);

        String sname=a.getText().toString();
        String smobile=b.getText().toString();

        Toast.makeText(this,"sname :"+sname+" smobile :"+smobile,Toast.LENGTH_LONG).show();
    }
}

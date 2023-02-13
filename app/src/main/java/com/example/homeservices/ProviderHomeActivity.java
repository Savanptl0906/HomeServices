package com.example.homeservices;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homeservices.network.MyTask;
import com.example.homeservices.network.NetworkInterface;
import com.example.homeservices.notification.RegisterToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class ProviderHomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, NetworkInterface {


    Context c;
    String a,svalue,shopname;
    TextView pmobile,shop_name;
    ListView lv1;
    List<String> cname,cmobile,caddress,model,issue,visitcharge,pr_mobile,pr_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_home);
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


        lv1=findViewById(R.id.lv1);
        pmobile=findViewById(R.id.pmobile);
        shop_name=findViewById(R.id.pr_name);

        cname = new ArrayList<String>();
        cmobile = new ArrayList<String>();
        caddress = new ArrayList<String>();
        model = new ArrayList<String>();
        issue = new ArrayList<String>();
        visitcharge = new ArrayList<String>();
        pr_mobile = new ArrayList<String>();
        pr_name = new ArrayList<String>();


        SharedPreferences spf = getSharedPreferences("provider",Context.MODE_PRIVATE);
        String value=spf.getString("sharedmobile","sharedmobile");

        pmobile.setText(value);

        svalue=pmobile.getText().toString();
        //after login add these lines
        RegisterToken token_reg = new RegisterToken(this,"provider",svalue);
        token_reg.set();
        //upto this

        providerRequest();
    }
    void providerRequest()
    {
        try{
            String data;
            data = URLEncoder.encode("providermobile","UTF-8")+"="+
                    URLEncoder.encode(""+svalue,"UTF-8");

            MyTask task = new MyTask(this);
            task.execute("providerShow.php",data);

        }
        catch(Exception e)
        {
            Toast.makeText(this, "Url problem"+e.toString(),Toast.LENGTH_LONG).show();
        }
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
        getMenuInflater().inflate(R.menu.provider_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            shopname=shop_name.getText().toString();

            Intent i=new Intent(this,ServiceMan.class);
            i.putExtra("pmobile",svalue);
            i.putExtra("shopname",shopname);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.profile)
        {

            Intent i=new Intent(this,ProviderProfileActivity.class);
            i.putExtra("pvalue",svalue);
            startActivity(i);
        } else if (id == R.id.history) {

        } else if (id == R.id.settings) {

        }
        else if (id == R.id.logout)
        {
            SharedPreferences spf = getSharedPreferences("provider", Context.MODE_PRIVATE);
            SharedPreferences.Editor ed;
            ed = spf.edit();
            ed.putBoolean("providerlogin", false);
            ed.commit();

            Toast.makeText(this, "Signout done!!!", Toast.LENGTH_LONG).show();
            finish();

            Intent intent;
            intent = new Intent(ProviderHomeActivity.this,LoginActivity.class);
            startActivity(intent);

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

                String cn = obj.getString("customername");
                String cm = obj.getString("customermobile");
                String ca = obj.getString("customeraddress");
                String m = obj.getString("model");
                String is = obj.getString("issue");
                String pc = obj.getString("providercharge");
                String provider = obj.getString("prmobile");


                String extra=obj.getString("pname");

                cname.add(cn);
                cmobile.add(cm);
                caddress.add(ca);
                model.add(m);
                issue.add(is);
                visitcharge.add(pc);
                pr_mobile.add(provider);
                pr_name.add(extra);

                shop_name.setText(extra);
            }
        }
        catch(Exception e)
        {
            Toast.makeText(ProviderHomeActivity.this, "json prob"+e.toString(),Toast.LENGTH_LONG).show();
        }
        ProviderAdapter ad = new ProviderAdapter(ProviderHomeActivity.this,cname,cmobile,caddress,model,issue,visitcharge,pr_mobile,pr_name);
        lv1.setAdapter(ad);

    }
}

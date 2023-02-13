package com.example.homeservices;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.homeservices.network.NetworkInterface;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class AssignServiceMan extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    TextView pr_name,pr_mobile,cname,cmobile,caddress,model,issue,visitcharge,sname,smobile;
    Context context;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_service_man);
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

        context=this;

        pr_name=findViewById(R.id.pr_name);
        pr_mobile=findViewById(R.id.pr_mobile);

        cname=findViewById(R.id.cname);
        cmobile=findViewById(R.id.cmobile);
        caddress=findViewById(R.id.caddress);
        model=findViewById(R.id.model);
        issue=findViewById(R.id.issue);
        visitcharge=findViewById(R.id.visitcharge);

        sname=findViewById(R.id.sname);
        smobile=findViewById(R.id.smobile);

        Bundle bundle=getIntent().getExtras();
        String a=bundle.getString("pr_name");
        String b=bundle.getString("pr_mobile");

        String cc=bundle.getString("cname");
        String d=bundle.getString("cmobile");
        String e=bundle.getString("caddress");
        String f=bundle.getString("model");
        String g=bundle.getString("issue");
        String h=bundle.getString("visitcharge");

        String ii=bundle.getString("sname");
        String j=bundle.getString("smobile");

        pr_name.setText(a);
        pr_mobile.setText(b);

        cname.setText(cc);
        cmobile.setText(d);
        caddress.setText(e);
        model.setText(f);
        issue.setText(g);
        visitcharge.setText(h);

        sname.setText(ii);
        smobile.setText(j);

    }

    String a,b,c,d,e,f,g,h,ii,j;

    void get()
    {
        a=pr_name.getText().toString();
        b=pr_mobile.getText().toString();

        c=cname.getText().toString();
        d=cmobile.getText().toString();
        e=caddress.getText().toString();
        f=model.getText().toString();
        g=issue.getText().toString();
        h=visitcharge.getText().toString();

        ii=sname.getText().toString();
        j=smobile.getText().toString();
    }

    public void done(View view)
    {
        String data="";
        get();

        try {
            data = URLEncoder.encode("pr_name", "UTF-8") + "=" + URLEncoder.encode(a, "UTF-8") + "&" +
                    URLEncoder.encode("pr_mobile", "UTF-8") + "=" + URLEncoder.encode(b, "UTF-8") + "&" +
                    URLEncoder.encode("cname", "UTF-8") + "=" + URLEncoder.encode(c, "UTF-8") + "&" +
                    URLEncoder.encode("cmobile", "UTF-8") + "=" + URLEncoder.encode(d, "UTF-8") + "&" +
                    URLEncoder.encode("caddress", "UTF-8") + "=" + URLEncoder.encode(e, "UTF-8") + "&" +
                    URLEncoder.encode("model", "UTF-8") + "=" + URLEncoder.encode(f, "UTF-8") + "&" +
                    URLEncoder.encode("issue", "UTF-8") + "=" + URLEncoder.encode(g, "UTF-8") + "&" +
                    URLEncoder.encode("visitcharge", "UTF-8") + "=" + URLEncoder.encode(h, "UTF-8") + "&" +
                    URLEncoder.encode("sname", "UTF-8") + "=" + URLEncoder.encode(ii, "UTF-8") + "&" +
                    URLEncoder.encode("smobile", "UTF-8") + "=" + URLEncoder.encode(j, "UTF-8");
        } catch (Exception e) {
            Toast.makeText(AssignServiceMan.this, "Signup Problem" + e.toString(), Toast.LENGTH_LONG).show();
        }
        MyTask task = new MyTask();
        task.execute("record.php", data);

    }

    class MyTask extends AsyncTask<String,String,String>
    {

        ProgressDialog pd;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(context);
            pd.setMessage("Connection with database...");
            pd.show();
        }

        @Override
        protected String doInBackground(String... arg) {

            try{
                String ip=getBaseContext().getResources().getString(R.string.ip);
                String msg="",temp;
                URL url=new URL(ip+"/home_services/"+arg[0]);
                HttpURLConnection con=(HttpURLConnection)url.openConnection();

                con.setRequestMethod("POST");
                if(arg.length >1)
                {
                    OutputStream os = con.getOutputStream();
                    os.write(arg[1].getBytes());
                }

                InputStream is = con.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                temp = br.readLine();
                while(temp != null)
                {
                    msg += temp + "\n";
                    temp = br.readLine();
                }
                return msg;

            }
            catch (Exception e){

                return e.toString();
            }
        }

        @Override
        protected void onPostExecute(String result)
        {
            pd.dismiss();

            //  Toast.makeText(context, result+ "", Toast.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(),"Signup Done Successfully",Toast.LENGTH_LONG).show();
            if(result.equals("insertion failed"))
            {
                Toast.makeText(getApplicationContext(),"Recored failed"+result,Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(AssignServiceMan.this,"Recored done Successfully",Toast.LENGTH_LONG).show();
                Intent i=new Intent(AssignServiceMan.this,ProviderHomeActivity.class);
                startActivity(i);
                finish();

            }
/*            else{
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }*/
            super.onPostExecute(result);
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
        getMenuInflater().inflate(R.menu.assign_service_man, menu);
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


}

package com.example.homeservices;

import android.accounts.NetworkErrorException;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homeservices.network.MyTask;
import com.example.homeservices.network.NetworkInterface;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class ServiceMan extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{


    TextView pmobile,shopname;
    EditText name,mobile,email,password,repassword;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_man);
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

        context=this;


        pmobile=findViewById(R.id.pmobile);

        shopname=findViewById(R.id.shopname);
        name=findViewById(R.id.name);
        mobile=findViewById(R.id.mobile);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        repassword=findViewById(R.id.repassword);

        Bundle bundle=getIntent().getExtras();
        String value=bundle.getString("pmobile");
        String shop=bundle.getString("shopname");
        pmobile.setText(value);
        shopname.setText(shop);

    }



    public void reset(View view)
    {
        shopname.setText("");
        name.setText("");
        mobile.setText("");
        password.setText("");
        repassword.setText("");
        email.setText("");

        name.requestFocus();

    }

    String a,b,c,d,e,f,pm;
    void get()
    {
        pm=pmobile.getText().toString();
        a=shopname.getText().toString();
        b=name.getText().toString();
        c=mobile.getText().toString();
        d=email.getText().toString();
        e=password.getText().toString();
        f=repassword.getText().toString();
    }

    public void signup(View view)
    {
        String data="";

        get();

        if(e.contentEquals("") && f.contentEquals(""))/* && a.contentEquals("") && b.contentEquals("") && c.contentEquals("") && e.contentEquals("") && f.contentEquals(""))*/
        {
            password.setError("Please Enter Password");
            repassword.setError("Please Enter Repassword");
            return;

        }
        else if(!e.equals(f))
        {
            repassword.setError("Password & Repassword is not Same");
            return;
        }
        else {
            try {
                data = URLEncoder.encode("pmobile", "UTF-8") + "=" + URLEncoder.encode(""+pm, "UTF-8") + "&" +
                        URLEncoder.encode("shopname", "UTF-8") + "=" + URLEncoder.encode(""+a, "UTF-8") + "&" +
                        URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(""+b, "UTF-8") + "&" +
                        URLEncoder.encode("mobile", "UTF-8") + "=" + URLEncoder.encode(""+c, "UTF-8") + "&" +
                        URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(""+d, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(""+e, "UTF-8");
            } catch (Exception e) {
                Toast.makeText(ServiceMan.this, "Signup Problem" + e.toString(), Toast.LENGTH_LONG).show();
            }
            MyTask task = new MyTask();
            task.execute("serviceMan.php", data);
        }
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
                Toast.makeText(getApplicationContext(),"Signup failed"+result,Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(ServiceMan.this,"Service Man Added Successfully",Toast.LENGTH_LONG).show();
                Intent i=new Intent(ServiceMan.this,ProviderHomeActivity.class);
                startActivity(i);
                finish();

            }
/*            else{
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }*/
            super.onPostExecute(result);        }
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
        getMenuInflater().inflate(R.menu.service_man, menu);
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}



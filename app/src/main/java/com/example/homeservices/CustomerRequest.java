package com.example.homeservices;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class CustomerRequest extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, NetworkInterface, View.OnClickListener {

    TextView pname,pmobile,paddress,pcharge,pr_name,ratting;
    EditText cname,cmobile,caddress,modelno,issues;
    Context context;

    String a,b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_request);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab =
                (FloatingActionButton) findViewById(R.id.fab);
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
        pname=findViewById(R.id.pname);
        pmobile=findViewById(R.id.pmobile);
        pmobile.setOnClickListener(this);
        paddress=findViewById(R.id.paddress);
        pcharge=findViewById(R.id.pcharge);
        pr_name=findViewById(R.id.pr_name);

        ratting=findViewById(R.id.ratting);

        cname=findViewById(R.id.cname);
        cmobile=findViewById(R.id.cmobile);
        caddress=findViewById(R.id.caddress);

        modelno=findViewById(R.id.modelno);
        issues=findViewById(R.id.issues);

        Bundle bundle = getIntent().getExtras();
        String sname = bundle.getString("sname");
        String smobile = bundle.getString("smobile");
        String saddress = bundle.getString("saddress");
        String visitcharge = bundle.getString("visitcharge");
        String provider = bundle.getString("pname");

        pname.setText(sname);
        pmobile.setText(smobile);
        paddress.setText(saddress);
        pcharge.setText(visitcharge);
        pr_name.setText(provider);
        Toast.makeText(this,"name :"+sname+" mobile :"+smobile+" address :"+saddress+" visit charge :"+visitcharge+ "Provider Name :"+provider,Toast.LENGTH_LONG).show();

        SharedPreferences spf = getSharedPreferences("customer",Context.MODE_PRIVATE);
        String value=spf.getString("customershared","customershared");

        cmobile.setText(value);
        a=cmobile.getText().toString();
        b=pmobile.getText().toString();
        request();

//        ratting();
    }
    void request()
    {
        try{
            String data;
            data = URLEncoder.encode("mobile","UTF-8")+"="+
                    URLEncoder.encode(""+a,"UTF-8")+"&"+
                    URLEncoder.encode("pmobile","UTF-8")+"="+
                    URLEncoder.encode(""+b,"UTF-8");

            MyTask task = new MyTask(this);
            task.execute("customerShow.php",data);

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

        }
 else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

        public void requestForser(View v)
        {
            String providershop=pname.getText().toString();
            String providername=pr_name.getText().toString();
            String providermobile=pmobile.getText().toString();
            String provideraddress=paddress.getText().toString();
            String providercharge=pcharge.getText().toString();
            String customername=cname.getText().toString();
            String customermobile=cmobile.getText().toString();
            String customeraddress=caddress.getText().toString();
            String model=modelno.getText().toString();
            String issue=issues.getText().toString();


            String data="";
            try
            {
                data = URLEncoder.encode("providershop", "UTF-8") + "=" + URLEncoder.encode(""+providershop, "UTF-8") + "&" +
                        URLEncoder.encode("providername", "UTF-8") + "=" + URLEncoder.encode(""+providername, "UTF-8") + "&" +
                        URLEncoder.encode("providermobile", "UTF-8") + "=" + URLEncoder.encode(""+providermobile, "UTF-8") + "&" +
                        URLEncoder.encode("provideraddress", "UTF-8") + "=" + URLEncoder.encode(""+provideraddress, "UTF-8") + "&" +
                        URLEncoder.encode("providercharge", "UTF-8") + "=" + URLEncoder.encode(""+providercharge, "UTF-8") + "&" +
                        URLEncoder.encode("customername", "UTF-8") + "=" + URLEncoder.encode(""+customername, "UTF-8") + "&" +
                        URLEncoder.encode("customermobile", "UTF-8") + "=" + URLEncoder.encode(""+customermobile, "UTF-8")+ "&" +
                        URLEncoder.encode("customeraddress", "UTF-8") + "=" + URLEncoder.encode(""+customeraddress, "UTF-8")+ "&" +
                        URLEncoder.encode("model", "UTF-8") + "=" + URLEncoder.encode(""+model, "UTF-8")+ "&" +
                        URLEncoder.encode("issue", "UTF-8") + "=" + URLEncoder.encode(""+issue, "UTF-8");
            }
            catch (Exception e)
            {
                Toast.makeText(CustomerRequest.this, "Problem" + e.toString(), Toast.LENGTH_LONG).show();
            }
            ParentTask task = new ParentTask();
            task.execute("request.php", data);

        }

    @Override
    public void onClick(View view) {
        String mobile_num = pmobile.getText().toString();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+mobile_num));
        startActivity(intent);
    }

    class ParentTask extends AsyncTask<String,String,String>
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

              Toast.makeText(context, result+ "", Toast.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(),"Signup Done Successfully",Toast.LENGTH_LONG).show();
            if(result.equals("insertion failed"))
            {
                Toast.makeText(getApplicationContext(),"Request failed"+result,Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(CustomerRequest.this,"Request Sent Successfully",Toast.LENGTH_LONG).show();
                Intent i=new Intent(CustomerRequest.this,CustomerServicePage.class);
                i.putExtra("c_mobile",a);
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
    public void networkResult(String msg) {

        try{
//            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
            JSONArray arr = new JSONArray(msg);

            for(int i=0;i<arr.length();i++)
            {
                JSONObject obj = arr.getJSONObject(i);

                String n = obj.getString("name");
                String ad= obj.getString("address");
                String rate=obj.getString("rate");

                cname.setText(n);
                caddress.setText(ad);

                ratting.setText(rate);
//                Toast.makeText(context, rate, Toast.LENGTH_SHORT).show();
            }
        }
        catch(Exception e)
        {
            Toast.makeText(CustomerRequest.this, "json prob"+e.toString(),Toast.LENGTH_LONG).show();
        }
    }
}

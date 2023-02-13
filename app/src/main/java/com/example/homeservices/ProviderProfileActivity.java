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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.homeservices.network.MyTask;
import com.example.homeservices.network.NetworkInterface;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;

public class ProviderProfileActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, NetworkInterface {

    EditText shopname,pname,pmobile,pemail,paddress,ppincode,charge,pcategory;
    String providerMobile,profileMobile,value;

    Button update,reset;

    Spinner category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                shopname.setEnabled(true);
                pname.setEnabled(true);
                pmobile.setEnabled(true);
                pemail.setEnabled(true);
                paddress.setEnabled(true);
                ppincode.setEnabled(true);
                charge.setEnabled(true);
                pcategory.setEnabled(true);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        category=findViewById(R.id.category);

        shopname=findViewById(R.id.shopname);
        pname=findViewById(R.id.pname);
        pmobile=findViewById(R.id.pmobile);
        pemail=findViewById(R.id.pemail);
        paddress=findViewById(R.id.paddress);
        ppincode=findViewById(R.id.ppincode);
        charge=findViewById(R.id.charge);
        pcategory=findViewById(R.id.pcategory);

        Bundle bundle=getIntent().getExtras();
        providerMobile=bundle.getString("pvalue");

        pmobile.setText(providerMobile);
        profileMobile=pmobile.getText().toString();

/*        value=category.getSelectedItem().toString();
        pcategory.setText(value);
*/
        providerProfile();


    }


    void providerProfile()
    {
        try{
            String data;
            data = URLEncoder.encode("providermobile","UTF-8")+"="+
                    URLEncoder.encode(""+profileMobile,"UTF-8");

            MyTask task = new MyTask(this);
            task.execute("providerProfile.php",data);

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
        getMenuInflater().inflate(R.menu.provider_profile, menu);

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

            shopname.setEnabled(true);
            pname.setEnabled(true);
            pmobile.setEnabled(true);
            pemail.setEnabled(true);
            paddress.setEnabled(true);
            ppincode.setEnabled(true);
            charge.setEnabled(true);
            pcategory.setEnabled(true);

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {

            Intent i=new Intent(this,ProviderHomeActivity.class);
            startActivity(i);
        } else if (id == R.id.history) {

        } else if (id == R.id.settings) {

        } else if (id == R.id.logout) {
            SharedPreferences spf = getSharedPreferences("provider", Context.MODE_PRIVATE);
            SharedPreferences.Editor ed;
            ed = spf.edit();
            ed.putBoolean("providerlogin", false);
            ed.commit();

            Toast.makeText(this, "Signout done!!!", Toast.LENGTH_LONG).show();
            finish();

            Intent intent;
            intent = new Intent(ProviderProfileActivity.this,LoginActivity.class);
            startActivity(intent);


        } else if (id == R.id.nav_share) {

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

                String shop_name = obj.getString("shopname");
                String name = obj.getString("name");
                String email = obj.getString("email");
                String address = obj.getString("address");
                String pincode= obj.getString("pincode");
                String category = obj.getString("category");
                String visitcharge = obj.getString("visitcharge");

                shopname.setText(shop_name);
                pname.setText(name);
                pemail.setText(email);
                paddress.setText(address);
                ppincode.setText(pincode);
                pcategory.setText(category);
                charge.setText(visitcharge);
            }
        }
        catch(Exception e)
        {
            Toast.makeText(ProviderProfileActivity.this, "json prob"+e.toString(),Toast.LENGTH_LONG).show();
        }
    }
}

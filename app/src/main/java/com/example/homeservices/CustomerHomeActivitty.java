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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homeservices.notification.RegisterToken;

public class CustomerHomeActivitty extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{


    String value;
    ImageView plumbers,electricians,pestcontrol,geyser,carpenters,ac,refregerator,washing,waterpurifier,microwave,kitchencleaning,homedeepcleaning,carpet,bathroomcleaning,garden,sofa,homepainting,architect,modularkitchen,bathroomrenovation,furniture;
    TextView nav_hed_user_name,nav_hed_mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home_activitty);


        SharedPreferences spf = getSharedPreferences("customer",Context.MODE_PRIVATE);
        value=spf.getString("customershared","customershared");


        //after login
        RegisterToken token_reg = new RegisterToken(this,"customer",value);
        token_reg.set();


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




        electricians=findViewById(R.id.electricians);
        plumbers=findViewById(R.id.plumbers);
        pestcontrol=findViewById(R.id.pestcontrol);
        geyser=findViewById(R.id.geyser);
        carpenters=findViewById(R.id.carpenters);
        ac=findViewById(R.id.ac);
        refregerator=findViewById(R.id.refregerator);
        washing=findViewById(R.id.washing);
        waterpurifier=findViewById(R.id.waterpurifier);
        microwave=findViewById(R.id.microwave);
        kitchencleaning=findViewById(R.id.kitchencleaning);
        homedeepcleaning=findViewById(R.id.homedeepcleaning);
        carpet=findViewById(R.id.carpet);
        bathroomcleaning=findViewById(R.id.bathroomcleaning);
        garden=findViewById(R.id.garden);
        sofa=findViewById(R.id.sofa);
        homepainting=findViewById(R.id.homepainting);
        architect=findViewById(R.id.architect);
        modularkitchen=findViewById(R.id.modularkitchen);
        bathroomrenovation=findViewById(R.id.bathroomrenovation);
        furniture=findViewById(R.id.furniture);
        nav_hed_user_name=findViewById(R.id.nav_hed_user_name);
        nav_hed_mobile=findViewById(R.id.nav_hed_mobile);

    }

    String data="";
    void intent()
    {
        Intent i=new Intent(CustomerHomeActivitty.this,ProviderListForCustomer.class);
        i.putExtra("name",data);
        startActivity(i);
    }
    public void same(View v)
    {

        int id=v.getId();
        if (id==R.id.electricians)
        {
            data="Electricians";
            intent();
        }
        else if (id==R.id.plumbers)
        {
            data="Plumbers";
            intent();
        }
        else if (id==R.id.pestcontrol)
        {
            data="Pest Control";
            intent();
        }
        else if (id==R.id.geyser)
        {
            data="Geyser";
            intent();
        }
        else if (id==R.id.carpenters)
        {
            data="Carpenters";
            intent();
        }
        else if (id==R.id.ac)
        {
            data="AC Service";
            intent();
        }
        else if (id==R.id.refregerator)
        {
            data="Refregerator Repair";
            intent();
        }
        else if (id==R.id.washing)
        {
            data="Washing Machine Repair";
            intent();
        }
        else if (id==R.id.waterpurifier)
        {
            data="Water Purifier Repair";
            intent();
        }
        else if (id==R.id.microwave)
        {
            data="Microwave Repair";
            intent();
        }
        else if (id==R.id.kitchencleaning)
        {
            data="Kitchen Deep cleaning";
            intent();
        }
        else if (id==R.id.homedeepcleaning)
        {
            data="Home Deep cleaning";
            intent();
        }
        else if (id==R.id.carpet)
        {
            data="Carpet Cleaning";
            intent();
        }
        else if (id==R.id.bathroomcleaning)
        {
            data="Bathroom Deep Cleaning";
            intent();
        }
        else if (id==R.id.garden)
        {
            data="Garden Cleaning";
            intent();
        }
        else if (id==R.id.sofa)
        {
            data="Sofa Cleaning";
            intent();
        }
        else if (id==R.id.homepainting)
        {
            data="Home Painting";
            intent();
        }
        else if (id==R.id.architect)
        {
            data="Architect";
            intent();
        }
        else if (id==R.id.modularkitchen)
        {
            data="Modular Kitchen";
            intent();
        }
        else if (id==R.id.bathroomrenovation)
        {
            data="Bathroom Renovation";
            intent();
        }
        else if (id==R.id.furniture)
        {
            data="Furniture";
            intent();
        }
        else
        {
            Toast.makeText(this,"Problem",Toast.LENGTH_LONG).show();
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
        getMenuInflater().inflate(R.menu.customer_home_activitty, menu);
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

        if (id == R.id.profile) {
            Intent i=new Intent(this,CustomerProfile.class);
            startActivity(i);
        } else if (id == R.id.history) {
            Intent i=new Intent(this,CustomerServicePage.class);
            startActivity(i);
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
        else if(id == R.id.nav_hed_user_name)
        {
            nav_hed_user_name.setText("");
        }
        else if(id == R.id.nav_hed_mobile)
        {

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}

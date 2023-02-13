package com.example.homeservices;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.homeservices.network.MyTask;
import com.example.homeservices.network.NetworkInterface;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.net.URLEncoder;

public class MapLocation extends FragmentActivity implements OnMapReadyCallback, NetworkInterface {

    String permissions[] = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    boolean perFlag = false;
    int perCode = 1234;
    SupportMapFragment frag;
    GoogleMap gmap;
    FusedLocationProviderClient fusedclient;
    ImageView iv;
    EditText ed1;
    double d1,d2;
    Button b;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        b = findViewById(R.id.confirm);
        b.setVisibility(View.VISIBLE);
        //SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fr1);
        //mapFragment.getMapAsync(this);
        getPermission();
    }

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)
    void getPermission() {
        if ((checkSelfPermission(permissions[0]) == PackageManager.PERMISSION_GRANTED) && (checkSelfPermission(permissions[1]) == PackageManager.PERMISSION_GRANTED)) {
            perFlag = true;
            init();
        } else {
            ActivityCompat.requestPermissions(this, permissions, perCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == perCode) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    perFlag = false;
                    Toast.makeText(this, permissions[i], Toast.LENGTH_SHORT).show();
                    return;
                }
                perFlag = true;
                init();

            }
        }
    }

    public void init() {
        FragmentManager fm;
        fm = getSupportFragmentManager();
        frag = (SupportMapFragment) fm.findFragmentById(R.id.map);
        frag.getMapAsync(this);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;
        getDeviceLocation();
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    void getDeviceLocation() {
        fusedclient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            return;
        }
        Task<Location> t = fusedclient.getLastLocation();
        t.addOnCompleteListener(new OnCompleteListener<Location>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if (task.isSuccessful() == true) {
                    Location t = task.getResult();
                    d1 = t.getLatitude();
                    d2 = t.getLongitude();
                    LatLng lt = new LatLng(d1, d2);
                    moveCamera(lt, 15, "my loc");
                } else {
                    Toast.makeText(MapLocation.this, "Location not found", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    void moveCamera(LatLng ll, float zoom, String title) {
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, zoom);
        gmap.moveCamera(update);
        MarkerOptions m = new MarkerOptions();
        m.title(title);
        m.position(ll);
        gmap.addMarker(m);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        gmap.setMyLocationEnabled(true);

        gmap.getUiSettings();
    }
    SharedPreferences spf;
    String mobile;
    public void confirmReq(View v){
        String data="";
        spf = getSharedPreferences("customer",Context.MODE_PRIVATE);
        mobile=spf.getString("customershared","customershared");
        try{
            data = URLEncoder.encode("mobile","UTF-8")+"="+URLEncoder.encode(mobile,"UTF-8")+"&"+
                    URLEncoder.encode("lat","UTF-8")+"="+URLEncoder.encode(""+d1,"UTF-8")+"&"+
                    URLEncoder.encode("lng","UTF-8")+"="+URLEncoder.encode(""+d2,"UTF-8");
            MyTask task= new MyTask(this);
            task.execute("req.php",data);

        }catch (Exception e){
            Toast.makeText(this, e+"", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void networkResult(String msg) {

        if(msg.equals("insertion failed"))
        {
            Toast.makeText(getApplicationContext(),"Signup failed"+msg,Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(MapLocation.this,"Signup Done Successfully",Toast.LENGTH_LONG).show();
            SharedPreferences.Editor ed = spf.edit();
            ed.putString("cust_mobile",mobile);
            Intent i =new Intent(this,CustomerHomeActivitty.class);
            startActivity(i);
            finish();

        }

    }
}

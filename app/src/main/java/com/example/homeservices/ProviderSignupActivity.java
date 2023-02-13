package com.example.homeservices;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class ProviderSignupActivity extends Activity {

    String text="";
    EditText shopname,name,password,repassword,email,address,pincode,visitcharge;
    TextView preg,mobile;
    Spinner category;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_signup);

        context=this;

        shopname=findViewById(R.id.shopname);
        name=findViewById(R.id.name);
        password=findViewById(R.id.password);
        repassword=findViewById(R.id.repassword);
        email=findViewById(R.id.email);
        preg=findViewById(R.id.preg);
        mobile=findViewById(R.id.mobile);
        address=findViewById(R.id.address);
        pincode=findViewById(R.id.pincode);
        visitcharge=findViewById(R.id.visitcharge);

        category=findViewById(R.id.category);

        Bundle bundle = getIntent().getExtras();
        String reg = bundle.getString("reg");
        String phone = bundle.getString("mob");


        preg.setText(reg);
        mobile.setText(phone);
    }

    public void login(View v)
    {
        Intent i=new Intent(this,ProviderLoginActivity.class);
        startActivity(i);
        finish();
    }
    public void reset(View v)
    {
        shopname.setText("");
        name.setText("");
        password.setText("");
        repassword.setText("");
        email.setText("");
        address.setText("");
        visitcharge.setText("");

        name.requestFocus();
    }

    String sname,a,b,c,d,e,f,repass,charge,regs;
    void get()
    {
        sname=shopname.getText().toString();
        a=name.getText().toString();
        b=mobile.getText().toString();
        regs=preg.getText().toString();
        c=email.getText().toString();
        d=password.getText().toString();
        repass=repassword.getText().toString();
        e=address.getText().toString();
        f=pincode.getText().toString();

        text=category.getSelectedItem().toString();

        charge=visitcharge.getText().toString();

    }

    public void signup(View v)
    {
        String data="";

        get();



        if(d.contentEquals("") && repass.contentEquals(""))/* && a.contentEquals("") && b.contentEquals("") && c.contentEquals("") && e.contentEquals("") && f.contentEquals(""))*/
        {
            /*name.setError("Please Enter Your Name");
            mobile.setError("Please Enter Your Mobile Number");
            email.setError("Please Enter Your Email Id");
            address.setError("Please Enter Your Address");
            pincode.setError("Please Enter Your pincode");*/
            password.setError("Please Enter Password");
            repassword.setError("Please Enter Repassword");
            return;

        }
        else if(!d.equals(repass))
        {
            repassword.setError("Password & Repassword is not Same");
            return;
        }
        else {
            try {
                data = URLEncoder.encode("shopname", "UTF-8") + "=" + URLEncoder.encode(""+sname, "UTF-8") + "&" +
                        URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(""+a, "UTF-8") + "&" +
                        URLEncoder.encode("mobile", "UTF-8") + "=" + URLEncoder.encode(""+b, "UTF-8") + "&" +
                        URLEncoder.encode("registration", "UTF-8") + "=" + URLEncoder.encode(""+regs, "UTF-8") + "&" +
                        URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(""+c, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(""+d, "UTF-8") + "&" +
                        URLEncoder.encode("address", "UTF-8") + "=" + URLEncoder.encode(""+e, "UTF-8") + "&" +
                        URLEncoder.encode("pincode", "UTF-8") + "=" + URLEncoder.encode(""+f, "UTF-8")+"&"+
                        URLEncoder.encode("category","UTF-8")+ "=" + URLEncoder.encode(""+text,"UTF-8")+"&"+
                        URLEncoder.encode("visitcharge","UTF-8")+ "=" + URLEncoder.encode(""+charge,"UTF-8");
            } catch (Exception e) {
                Toast.makeText(ProviderSignupActivity.this, "Signup Problem" + e.toString(), Toast.LENGTH_LONG).show();
            }
            MyTask task = new MyTask();
            task.execute("providersignup.php", data);
        }
    }

    void providerData()
    {
        String sharedmobile=mobile.getText().toString();

        SharedPreferences spf = getSharedPreferences("provider",Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = spf.edit();
        ed.putBoolean("providerlogin", true);
        ed.putString("sharedmobile",sharedmobile);
        ed.commit();
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
            else {
                Toast.makeText(ProviderSignupActivity.this,"Signup Done Successfully",Toast.LENGTH_LONG).show();
                //Intent i=new Intent(ProviderSignupActivity.this,ProviderLoginActivity.class);
                Intent i=new Intent(ProviderSignupActivity.this,ProviderMapLocation.class);
                startActivity(i);
                providerData();
                finish();

            }
/*            else{
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }*/
            super.onPostExecute(result);        }
    }
}

package com.example.homeservices;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class CustomerSignupActivity extends Activity {

    EditText name,password,repassword,email,address,pincode;
    TextView mobile;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_signup);

        context=this;

        name=findViewById(R.id.name);
        password=findViewById(R.id.password);
        repassword=findViewById(R.id.repassword);
        email=findViewById(R.id.email);
        mobile=findViewById(R.id.mobile);
        address=findViewById(R.id.address);
        pincode=findViewById(R.id.pincode);

        Bundle bundle = getIntent().getExtras();
        String phone = bundle.getString("phn");


        mobile.setText(phone);
    }
    public void login(View v)
    {
        Intent i=new Intent(this,CustomerLoginActivity.class);
        startActivity(i);
        finish();
    }
    public void reset(View v)
    {
        name.setText("");
        email.setText("");
        password.setText("");
        repassword.setText("");
        address.setText("");
        pincode.setText("");

        name.requestFocus();
    }

    String a,b,c,d,e,f,repass;
    void get()
    {
        a=name.getText().toString();
        b=mobile.getText().toString();
        c=email.getText().toString();
        d=password.getText().toString();
        repass=repassword.getText().toString();
        e=address.getText().toString();
        f=pincode.getText().toString();
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

        }
        else if(!d.equals(repass))
        {
            repassword.setError("Password & Repassword is not Same");
        }
        else {
            try {
                data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(a, "UTF-8") + "&" +
                        URLEncoder.encode("mobile", "UTF-8") + "=" + URLEncoder.encode(b, "UTF-8") + "&" +
                        URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(c, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(d, "UTF-8") + "&" +
                        URLEncoder.encode("address", "UTF-8") + "=" + URLEncoder.encode(e, "UTF-8") + "&" +
                        URLEncoder.encode("pincode", "UTF-8") + "=" + URLEncoder.encode(f, "UTF-8");
            } catch (Exception e) {
                Toast.makeText(CustomerSignupActivity.this, "Signup Problem" + e.toString(), Toast.LENGTH_LONG).show();
            }
            MyTask task = new MyTask();
            task.execute("customersignup.php", data);
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
            if(result.equals("insertion done"))
            {
                Toast.makeText(CustomerSignupActivity.this,"Signup Done Successfully",Toast.LENGTH_LONG).show();
                Intent i=new Intent(CustomerSignupActivity.this,CustomerLoginActivity.class);
                startActivity(i);
                finish();

//                Toast.makeText(getApplicationContext(),"Signup failed"+result,Toast.LENGTH_LONG).show();
            }
            else{
  /*                  Toast.makeText(CustomerSignupActivity.this,"Signup Done Successfully",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(CustomerSignupActivity.this,CustomerLoginActivity.class);
                    startActivity(i);
                    finish();*/
                Toast.makeText(getApplicationContext(),"Signup failed"+result,Toast.LENGTH_LONG).show();

            }
/*            else{
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }*/
            super.onPostExecute(result);
        }
    }

}

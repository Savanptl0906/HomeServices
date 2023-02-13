package com.example.homeservices;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CustomerDescription extends Activity {

    TextView cname,caddress,cmobile,charge,modelno,issues,providerMobile,shopname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_description);


        cname=findViewById(R.id.cname);
        caddress=findViewById(R.id.caddress);
        cmobile=findViewById(R.id.cmobile);
        charge=findViewById(R.id.charge);
        modelno=findViewById(R.id.modelno);
        issues=findViewById(R.id.issues);
        providerMobile=findViewById(R.id.pr_mobile);
        shopname=findViewById(R.id.pr_name);

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("cname");
        String mobile = bundle.getString("cmobile");
        String address = bundle.getString("caddress");
        String model = bundle.getString("model");
        String issue = bundle.getString("issue");
        String visitcharge = bundle.getString("visitcharge");
        String p_mobile = bundle.getString("pr_mobile");
        String p_name = bundle.getString("pr_name");

        cname.setText(name);
        cmobile.setText(mobile);
        caddress.setText(address);
        modelno.setText(model);
        issues.setText(issue);
        charge.setText(visitcharge);
        providerMobile.setText(p_mobile);
        shopname.setText(p_name);
        Toast.makeText(this,"name :"+name+" mobile :"+mobile+" address :"+address+" model :"+model+" issue :"+issue+" visit charge :"+visitcharge,Toast.LENGTH_LONG).show();

    }

    public void accept(View view)
    {
        String pr_mobile=providerMobile.getText().toString();
        String pr_name=shopname.getText().toString();


        String c_name=cname.getText().toString();
        String c_mobile=cmobile.getText().toString();
        String c_address=caddress.getText().toString();
        String c_model=modelno.getText().toString();
        String c_issue=issues.getText().toString();
        String c_visitcharge=charge.getText().toString();


        Intent i=new Intent(this,ServiceManList.class);

        i.putExtra("pr_mobile",pr_mobile);
        i.putExtra("pr_name",pr_name);


        i.putExtra("c_name",c_name);
        i.putExtra("c_mobile",c_mobile);
        i.putExtra("c_address",c_address);
        i.putExtra("c_model",c_model);
        i.putExtra("c_issue",c_issue);
        i.putExtra("c_visitcharge",c_visitcharge);
        startActivity(i);
    }
}


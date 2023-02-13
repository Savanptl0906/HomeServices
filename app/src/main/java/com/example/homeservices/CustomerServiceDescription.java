package com.example.homeservices;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.homeservices.paytm.PaymentClass;

public class CustomerServiceDescription extends AppCompatActivity implements View.OnClickListener {

    Button pay;
    TextView cname, caddress, cmobile, charge, modelno, issues, providerMobile, shopname, sname, smobile;
    String p_mobile, mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_service_description);

        cname = findViewById(R.id.cname);
        caddress = findViewById(R.id.caddress);
        cmobile = findViewById(R.id.cmobile);
        charge = findViewById(R.id.visitcharge);
        modelno = findViewById(R.id.model);
        issues = findViewById(R.id.issue);

        pay = findViewById(R.id.pay);
        pay.setOnClickListener(this);

        providerMobile = findViewById(R.id.pr_mobile);
        shopname = findViewById(R.id.pr_name);

        sname = findViewById(R.id.sname);
        smobile = findViewById(R.id.smobile);


        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("cname");
        mobile = bundle.getString("cmobile");
        String address = bundle.getString("caddress");
        String model = bundle.getString("model");
        String issue = bundle.getString("issue");
        String visitcharge = bundle.getString("visitcharge");

        p_mobile = bundle.getString("pr_mobile");
        String p_name = bundle.getString("pr_name");

        String s_name = bundle.getString("sname");
        String s_mobile = bundle.getString("smobile");

        cname.setText(name);
        cmobile.setText(mobile);
        caddress.setText(address);
        modelno.setText(model);
        issues.setText(issue);
        charge.setText(visitcharge);

        providerMobile.setText(p_mobile);
        shopname.setText(p_name);

        sname.setText(s_name);
        smobile.setText(s_mobile);

        smobile.setOnClickListener(this);
//        Toast.makeText(this,"name :"+name+" mobile :"+mobile+" address :"+address+" model :"+model+" issue :"+issue+" visit charge :"+visitcharge,Toast.LENGTH_LONG).show();

    }


    public void ratting(View view) {
        Intent i = new Intent(this, Ratting.class);
        i.putExtra("pr_mobile", p_mobile);
        i.putExtra("c_mobile", mobile);
        startActivity(i);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.smobile) {
            String mobile_num = smobile.getText().toString();
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + mobile_num));
            startActivity(intent);
        } else {
            PaymentClass paytmclass = new PaymentClass(this);
            paytmclass.processPaytm(charge.getText().toString());
        }
    }
}

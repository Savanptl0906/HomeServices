package com.example.homeservices;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomerAdapter extends BaseAdapter {


    String a = "";
    String b = "";
    Context c;
    List<String> cname, cmobile, caddress, model, issue, visitcharge, pr_mobile, pr_name, sname, smobile;


    public CustomerAdapter(Context c, List<String> cname, List<String> cmobile, List<String> caddress, List<String> model, List<String> issue, List<String> visitcharge, List<String> pr_mobile, List<String> pr_name, List<String> sname, List<String> smobile) {
        this.c = c;
        this.cname = cname;
        this.cmobile = cmobile;
        this.caddress = caddress;
        this.model = model;
        this.issue = issue;
        this.visitcharge = visitcharge;

        this.pr_mobile = pr_mobile;
        this.pr_name = pr_name;

        this.sname = sname;
        this.smobile = smobile;
    }


    @Override
    public int getCount() {
        return cname.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater li = LayoutInflater.from(c);
        View v = li.inflate(R.layout.cutomeradapter, null);
        TextView t1 = (TextView) v.findViewById(R.id.cname);
        TextView t2 = (TextView) v.findViewById(R.id.cmobile);
        TextView t3 = (TextView) v.findViewById(R.id.caddress);
        TextView t4 = (TextView) v.findViewById(R.id.model);
        TextView t5 = (TextView) v.findViewById(R.id.issue);
        TextView t6 = (TextView) v.findViewById(R.id.visitcharge);
        TextView t7 = (TextView) v.findViewById(R.id.pr_mobile);
        TextView t8 = (TextView) v.findViewById(R.id.pr_name);
        TextView t9 = (TextView) v.findViewById(R.id.sname);
        TextView t10 = (TextView) v.findViewById(R.id.smobile);


        t1.setText(cname.get(position));
        t2.setText(cmobile.get(position));
        t3.setText(caddress.get(position));
        t4.setText(model.get(position));
        t5.setText(issue.get(position));
        t6.setText(visitcharge.get(position));
        t7.setText(pr_mobile.get(position));
        t8.setText(pr_name.get(position));
        t9.setText(sname.get(position));
        t10.setText(smobile.get(position));

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(),"name :"+a+" mobile :"+b,Toast.LENGTH_LONG).show();
                Intent i = new Intent(v.getContext(), CustomerServiceDescription.class);
                i.putExtra("cname", cname.get(position));
                i.putExtra("cmobile", cmobile.get(position));
                i.putExtra("caddress", caddress.get(position));
                i.putExtra("model", model.get(position));
                i.putExtra("issue", issue.get(position));
                i.putExtra("visitcharge", visitcharge.get(position));
                i.putExtra("pr_mobile", pr_mobile.get(position));
                i.putExtra("pr_name", pr_name.get(position));
                i.putExtra("sname",sname.get(position));
                i.putExtra("smobile", smobile.get(position));
                c.startActivity(i);
            }
        });
        return v;
    }
}
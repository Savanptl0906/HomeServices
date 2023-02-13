package com.example.homeservices;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ServiceManDesignList extends BaseAdapter {

    String a="";
    String b="";
    Context c;
    List<String> sname, smobile, cname, cmobile, caddress, model, issue, visitcharge,pr_name,pr_mobile;

    public ServiceManDesignList(Context c, List<String> sname, List<String> smobile, List<String> cname, List<String> cmobile, List<String> caddress, List<String> model, List<String> issue, List<String> visitcharge, List<String> pr_name, List<String> pr_mobile )
    {

        this.c = c;

        this.sname=sname;
        this.smobile=smobile;

        this.cname=cname;
        this.cmobile=cmobile;
        this.caddress=caddress;
        this.model=model;
        this.issue=issue;
        this.visitcharge=visitcharge;

        this.pr_name=pr_name;
        this.pr_mobile=pr_mobile;

    }




    @Override
    public int getCount() { return sname.size(); }

    @Override
    public Object getItem(int position) { return null; }

    @Override
    public long getItemId(int position) { return 0; }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater li=LayoutInflater.from(c);
        View v=li.inflate(R.layout.servicemanlist, null);
        TextView t1=(TextView)v.findViewById(R.id.sname);
        TextView t2=(TextView)v.findViewById(R.id.smobile);
        TextView t3=(TextView)v.findViewById(R.id.cname);
        TextView t4=(TextView)v.findViewById(R.id.cmobile);
        TextView t5=(TextView)v.findViewById(R.id.caddress);
        TextView t6=(TextView)v.findViewById(R.id.model);
        TextView t7=(TextView)v.findViewById(R.id.issue);
        TextView t8=(TextView)v.findViewById(R.id.visitcharge);
        TextView t9=(TextView)v.findViewById(R.id.pr_name);
        TextView t10=(TextView)v.findViewById(R.id.pr_mobile);


        t1.setText(sname.get(position));
        t2.setText(smobile.get(position));
        t3.setText(cname.get(position));
        t4.setText(cmobile.get(position));
        t5.setText(caddress.get(position));
        t6.setText(model.get(position));
        t7.setText(issue.get(position));
        t8.setText(visitcharge.get(position));
        t9.setText(pr_name.get(position));
        t10.setText(pr_mobile.get(position));

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(),"name :"+a+" mobile :"+b,Toast.LENGTH_LONG).show();
                Intent i=new Intent(v.getContext(),AssignServiceMan.class);

                i.putExtra("sname",sname.get(position));
                i.putExtra("smobile",smobile.get(position));

                i.putExtra("cname",cname.get(position));
                i.putExtra("cmobile",cmobile.get(position));
                i.putExtra("caddress",caddress.get(position));
                i.putExtra("model",model.get(position));
                i.putExtra("issue",issue.get(position));
                i.putExtra("visitcharge",visitcharge.get(position));

                i.putExtra("pr_name",pr_name.get(position));
                i.putExtra("pr_mobile",pr_mobile.get(position));

                c.startActivity(i);
            }
        });
        return v;
    }
}

package com.example.homeservices;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CustomAdapter extends BaseAdapter{

    String a="";
    String b="";
    Context c;
    List<String> sname,smobile,saddress,visitcharge,pname;

    public CustomAdapter(Context c, List<String> sname, List<String> smobile, List<String> saddress, List<String> visitcharge, List<String> pname)
    {

        this.c = c;
        this.sname=sname;
        this.smobile=smobile;
        this.saddress=saddress;
        this.visitcharge=visitcharge;
        this.pname=pname;

    }


    @Override
    public int getCount() {
        return sname.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater li=LayoutInflater.from(c);
        View v=li.inflate(R.layout.design, null);

        TextView t1=(TextView)v.findViewById(R.id.sname);
        TextView t2=(TextView)v.findViewById(R.id.smobile);
        TextView t3=(TextView)v.findViewById(R.id.saddress);
        TextView t4=(TextView)v.findViewById(R.id.visitcharge);
        TextView t5=(TextView)v.findViewById(R.id.pname);



//        TextView t1=(TextView)v.findViewById(R.id.sname);
  //      TextView t2=(TextView)v.findViewById(R.id.smobile);

        t1.setText(sname.get(position));
        t2.setText(smobile.get(position));
        t3.setText(saddress.get(position));
        t4.setText(visitcharge.get(position));
        t5.setText(pname.get(position));


        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(),"name :"+a+" mobile :"+b,Toast.LENGTH_LONG).show();
                Intent i=new Intent(v.getContext(),CustomerRequest.class);
                i.putExtra("sname",sname.get(position));
                i.putExtra("smobile",smobile.get(position));
                i.putExtra("saddress",saddress.get(position));
                i.putExtra("visitcharge",visitcharge.get(position));
                i.putExtra("pname",pname.get(position));
                c.startActivity(i);
            }
        });

        return v;
    }

}

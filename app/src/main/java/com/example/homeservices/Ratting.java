package com.example.homeservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homeservices.network.MyTask;
import com.example.homeservices.network.NetworkInterface;

import java.net.URLEncoder;

public class Ratting extends AppCompatActivity implements NetworkInterface {


     RatingBar ratingBar;
     TextView tvRateCount,tvRateMessage;
     float ratedValue;
     String pr_mobile,c_mobile,rating_msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratting);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        tvRateCount = (TextView) findViewById(R.id.tvRateCount);
        tvRateMessage = (TextView) findViewById(R.id.tvRateMessage);

        Bundle bundle = getIntent().getExtras();
         pr_mobile=bundle.getString("pr_mobile");
        c_mobile=bundle.getString("c_mobile");



        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                ratedValue = ratingBar.getRating();
                tvRateCount.setText("Your Rating : " + ratedValue + "/5.");

                if(ratedValue<1){
                    tvRateMessage.setText("ohh ho...");
                }else if(ratedValue<2){
                    tvRateMessage.setText("Ok.");
                }else if(ratedValue<3){
                    tvRateMessage.setText("Not bad.");
                }else if(ratedValue<4){
                    tvRateMessage.setText("Nice");
                }else if(ratedValue<5){
                    tvRateMessage.setText("Very Nice");
                }else if(ratedValue==5){
                    tvRateMessage.setText("Thank you..!!!");
                }

            }
        });


    }

    public void submit(View view)
    {

        rating_msg=tvRateMessage.getText().toString();
        //Toast.makeText(this, ""+ratedValue+" "+rating_msg+" "+pr_mobile+" "+c_mobile, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, ""+rating_msg, Toast.LENGTH_SHORT).show();
        try{
            String data;
            data = URLEncoder.encode("ratting","UTF-8")+"="+ URLEncoder.encode(""+ratedValue,"UTF-8")+"&"+
                    URLEncoder.encode("pr_mobile","UTF-8")+"="+ URLEncoder.encode(""+pr_mobile,"UTF-8")+"&"+
                    URLEncoder.encode("c_mobile","UTF-8")+"="+ URLEncoder.encode(""+c_mobile,"UTF-8")+"&"+
                    URLEncoder.encode("review","UTF-8")+"="+ URLEncoder.encode(""+rating_msg,"UTF-8");

            MyTask task = new MyTask(this);
            task.execute("ratting.php",data);

        }
        catch(Exception e)
        {
            Toast.makeText(this, "Url problem"+e.toString(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void networkResult(String msg) {
        try {
            if (msg.equalsIgnoreCase("success")) {
                Intent intent = new Intent(Ratting.this, CustomerHomeActivitty.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Failed"+msg, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(Ratting.this, "json prob" + e.toString(), Toast.LENGTH_LONG).show();
        }

    }
}

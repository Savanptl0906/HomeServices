package com.example.homeservices.notification;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.homeservices.network.MyTask;
import com.example.homeservices.network.NetworkInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class RegisterToken implements NetworkInterface {
    Context context;

    String TAG = "token page";
    String type,mobile;
    public RegisterToken(Context context,String type,String mobile) {
        this.context = context;
        this.type = type;
        this.mobile = mobile;
    }

    public void set() {
//        Log.d(TAG,"set method");
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();
                        Log.d(TAG, token);
                        MyTask ntask = new MyTask(RegisterToken.this);
                        String data = convert("token",token)+"&"+convert("mobile",mobile);
                     //   Toast.makeText(context, data, Toast.LENGTH_SHORT).show();
                        if(type.equals("customer"))
                        {
                            Log.d(TAG,"customer");
                            ntask.execute("insertcutomertoken.php",data);
                        }
                        else {
                            ntask.execute("insertproviertoken.php",data);
                        }
                    }
                });
    }
    String convert(String key,String data)
    {
        String msg;
        msg="";

        try {
            msg = URLEncoder.encode(key,"UTF-8")+"="+URLEncoder.encode(data,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return msg;
    }

    @Override
    public void networkResult(String msg) {

        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}

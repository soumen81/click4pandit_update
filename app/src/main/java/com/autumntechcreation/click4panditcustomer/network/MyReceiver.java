package com.autumntechcreation.click4panditcustomer.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String status = NetworkUtil.getConnectivityStatusString(context);
       /* if(status.isEmpty()){
            status="No Internet Connection";
           // status="";
        }*/
        if(status.equals("Wifi enabled")){
            Log.e("WIFI","Wifi enable");
            Toast.makeText(context, "Your Wifi is enable", Toast.LENGTH_SHORT).show();
        }else if(status.equals("Mobile data enabled")){
            Log.e("MOBILEDATA","Mobile data enabled");
            Toast.makeText(context, "Your Mobile data is enabled", Toast.LENGTH_SHORT).show();
        }else if(status.equals("No internet is available")) {
            Toast.makeText(context, "Please Check your Internet Connection", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(context, status, Toast.LENGTH_LONG).show();
        }


    }
}

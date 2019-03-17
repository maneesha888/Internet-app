    package com.example.lavanya.internet;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.sql.BatchUpdateException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!isConnected(MainActivity.this))buildDialog(MainActivity.this).show();
        else
        {
            Toast.makeText(this, "WELCOME", Toast.LENGTH_SHORT).show();
            setContentView(R.layout.activity_main);

        }

    }

  public boolean isConnected(Context context)
  {
      ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
      NetworkInfo net = connectivityManager.getActiveNetworkInfo();
      if (net!=null && net.isConnectedOrConnecting())
      {
         android.net.NetworkInfo wifi=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
         android.net.NetworkInfo mobile =connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
         if ((mobile!=null && mobile.isConnectedOrConnecting()) || (wifi!=null && wifi.isConnectedOrConnecting()))
         return true;
         else return false;
      }else
     return false;
  }

public AlertDialog.Builder buildDialog(Context context)
{
    AlertDialog.Builder builder=new AlertDialog.Builder(context);
    builder.setTitle("NO INTERNET CONNECTION");
    builder.setMessage("YOU NEED TO HAVE MOBILE DATA OR WIFI TO ACCESS THIS. PRESS OK TO EXIT");
    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            finish();
        }
    });
    return builder;
}
}

package com.android.phonebook;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class callog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callog);
        if(ContextCompat.checkSelfPermission(callog.this,
                Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(callog.this,Manifest.permission.READ_CALL_LOG)){
                ActivityCompat.requestPermissions (callog.this,
                        new String[]{Manifest.permission.READ_CALL_LOG},1);
            }
            else {
                ActivityCompat.requestPermissions(callog.this,
                        new String[]{Manifest.permission.READ_CALL_LOG},1 );
            }}
        else
            {
                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText(getCallDetails());
            }
        }
    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults)
    {
        switch(requestCode)
        {
            case 1:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    if(ContextCompat.checkSelfPermission(callog.this,Manifest.permission.READ_CALL_LOG)==PackageManager.PERMISSION_GRANTED)
                    {
                        Toast.makeText(this,"permission granted",Toast.LENGTH_SHORT).show();
                    }}
                else
                {
                    Toast.makeText(this,"No permission granted",Toast.LENGTH_SHORT).show();
                }

            }

        }}

    private String getCallDetails(){
        StringBuffer sb= new StringBuffer();
        Cursor managedCursor = getContentResolver().query(CallLog.Calls.CONTENT_URI,null,null,null,null);
        int number1= managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type= managedCursor.getColumnIndex(CallLog.Calls.TYPE);
        int date= managedCursor.getColumnIndex(CallLog.Calls.DATE);
        int duration= managedCursor.getColumnIndex(CallLog.Calls.DURATION);
        sb.append("Call details : \n\n");
        while(managedCursor.moveToNext())
        {
            String phnumber = managedCursor.getString(number1);
            String callType = managedCursor.getString(type);
            String callDate = managedCursor.getString(date);
            Date callDayTime = new Date(Long.valueOf(callDate));
            SimpleDateFormat formatter =  new SimpleDateFormat("dd-MM-yy MM:mm");
            String dateString = formatter.format(callDayTime);
            String callDuration = managedCursor.getString(duration);
            String dir = null;
            int dircode = Integer.parseInt(callType);
            switch(dircode)
            {
                case CallLog.Calls.OUTGOING_TYPE:
                    dir="OUTGOING";
                    break;
                case CallLog.Calls.INCOMING_TYPE:
                    dir="INCOMING";
                    break;
                case CallLog.Calls.MISSED_TYPE:
                    dir = "MISSED";
                    break;
            }
            sb.append("\nPhone NUmber: "+ phnumber + "\nCallType: " + dir + "\nCall Date: " + dateString + "\nCall Duration: "+callDuration);
            sb.append("\n--------------------");
        }
        managedCursor.close();
        return sb.toString();
    }
}


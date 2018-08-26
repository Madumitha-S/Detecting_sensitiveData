package com.android.phonebook;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.widget.TextView;
import android.view.View;

import java.util.ArrayList;

public class InterceptCall extends BroadcastReceiver {
    TextView txt;
    ArrayList<Integer> number;
    
    @Override
    public void onReceive(Context context, Intent intent) {
        ArrayList<Integer> number = new ArrayList<>();
        try
        {
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
                        if(state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_RINGING))
                        {
                                txt = (TextView) txt.findViewById(R.id.textView2);
                                txt.setText("incoming");
                                number.add(1);

                        }
                        if(state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_OFFHOOK))
            {
                    txt = (TextView) txt.findViewById(R.id.textView2);
                    txt.setText("outgoing");
                    number.add(2);

            }
            if(state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_IDLE))
            {
                txt = (TextView) txt.findViewById(R.id.textView2);
                txt.setText("outgoing");
                number.add(3);
            }}
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }


package com.android.phonebook;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class phncall extends AppCompatActivity {

    Button btn_call;
    ListView lt_call;
    int pid=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phncall);
        btn_call=(Button)findViewById(R.id.button1);
        lt_call=(ListView)findViewById(R.id.listView);
        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callatruntimepermission();
            }
        });
    }
    private void callatruntimepermission() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},pid);
        }
        else
        {
            String smobile = lt_call.toString();
            Intent call = new Intent(Intent.ACTION_CALL);
            call.setData(Uri.parse("tel:"+smobile));
            startActivity(call);
        }
    }
    @Override
    public void onRequestPermissionsResult (int requestCode, @NonNull String[] permissions,@NonNull int[] grantResult)
    {
        super.onRequestPermissionsResult(requestCode,permissions,grantResult);
        if(requestCode==pid)
        {
            if(grantResult[0]==PackageManager.PERMISSION_GRANTED)
            {
                callatruntimepermission();
            }
        }
    }
}
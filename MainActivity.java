package com.android.phonebook;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    Button btn1,btn4;
    ArrayList<String> al = new ArrayList<>();
    String smobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.READ_CALL_LOG)){
                ActivityCompat.requestPermissions (MainActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE},1);
            }
            else {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE},1 );
            }}

        lv=(ListView)findViewById(R.id.listView);
        btn1=(Button)findViewById(R.id.button1);
        btn4=(Button)findViewById(R.id.button4);
        btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
        {
            Intent intent = new Intent(MainActivity.this, phncall.class);
            startActivity(intent);
            finish();
        }
        });
        btn4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, callog.class);
                startActivity(intent);
                finish();
            }
        });
        MyDatabase myDatabase = new MyDatabase(this);
        al=myDatabase.getAllContacts();
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,al);
        lv.setAdapter(adp);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                smobile = lv.getItemAtPosition(i).toString();
                callatruntimepermission(smobile);
                Intent intent=new Intent(getApplicationContext(),InterceptCall.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults)
    {
        switch(requestCode)
        {
            case 1:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CALL_PHONE)==PackageManager.PERMISSION_GRANTED)
                    {
                        Toast.makeText(this,"permission granted",Toast.LENGTH_SHORT).show();
                        callatruntimepermission(smobile);

                    }}
                else
                {
                    Toast.makeText(this,"No permission granted",Toast.LENGTH_SHORT).show();
                }

            }

        }}
    private void callatruntimepermission( String i) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},1);
        }
        else
        {
            Intent call = new Intent(Intent.ACTION_CALL);
            call.setData(Uri.parse("tel:"+i));
            startActivity(call);
        }
    }
    public void loadAddActivity(View v)
    {
         startActivity(new Intent(this,AddActivity.class));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
    }

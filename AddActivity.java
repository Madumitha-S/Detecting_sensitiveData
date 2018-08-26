package com.android.phonebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    MyDatabase myDatabase;
    EditText etName,etMobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        myDatabase = new MyDatabase(this);
        etName = (EditText)findViewById(R.id.editText);
        etMobile = (EditText) findViewById(R.id.editText2);
    }
    public void saveData(View v)
    {
        String name = etName.getText().toString();
        String mobile = etMobile.getText().toString();
        etName.setText("");
        etMobile.setText("");
        myDatabase.addContact(name,mobile);
        Toast.makeText(this,"contact saved",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,MainActivity.class));
    }
    public void goBack(View v)
    {
        startActivity(new Intent(this,MainActivity.class));
    }
}

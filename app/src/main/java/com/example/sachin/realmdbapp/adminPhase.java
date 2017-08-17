package com.example.sachin.realmdbapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import io.realm.Realm;

import static java.security.AccessController.getContext;

public class adminPhase extends AppCompatActivity {
    EditText et1,et2,et3,et4;
    Button btn1;
    Spinner mySpinner1;
    String[] productList={"Phones","Computers"};
    Realm realm;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_phase);
        database = FirebaseDatabase.getInstance();

        et1= (EditText) findViewById(R.id.edit1);
        et2= (EditText) findViewById(R.id.edit2);
        et3= (EditText) findViewById(R.id.edit3);
        et4= (EditText) findViewById(R.id.edit4);
        btn1= (Button) findViewById(R.id.button6);
        mySpinner1= (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> myAdapter=new ArrayAdapter<String>(adminPhase.this,android.R.layout.simple_spinner_dropdown_item,productList);
        mySpinner1.setAdapter(myAdapter);
        mySpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0)
                {
                    btn1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (et1.getText().toString().equalsIgnoreCase("") || et2.getText().toString().equalsIgnoreCase("") || et3.getText().toString().equalsIgnoreCase("") || et4.getText().toString().equalsIgnoreCase(""))
                            {
                                new AlertDialog.Builder(adminPhase.this)
                                        .setTitle("Error")
                                        .setMessage("Please provide required information")
                                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                // continue with delete

                                            }
                                        })
                                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                // do nothing
                                            }
                                        })
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .show();
                            }
                            else {
                                Realm.init(adminPhase.this);
                                realm = Realm.getDefaultInstance();
                                mobileData m1 = new mobileData();
                                m1.productName1 = et1.getText().toString();
                                m1.price1 = Double.parseDouble(et2.getText().toString());
                                m1.imgID1 = R.drawable.m;
                                realm.beginTransaction();
                                realm.copyToRealm(m1);
                                realm.commitTransaction();
                                Random r = new Random();
                                int i1 = (r.nextInt(1000));
                                DatabaseReference myRef = database.getReference("Phones");
                                Map<String, String> data= new HashMap<String, String>();
                                data.put(et1.getText().toString(),"Product Name");
                                data.put(et2.getText().toString(),"Product Price");
                                data.put(et3.getText().toString(),"Product Color");
                                data.put(et4.getText().toString(),"Product Quantity");
                                myRef.child(et1.getText().toString()+i1).setValue(data);

                                Toast.makeText(adminPhase.this, "1 Phone Added", Toast.LENGTH_SHORT).show();
                                et1.setText("");
                                et2.setText("");
                                et3.setText("");
                                et4.setText("");
                            }
                        }
                    });
                }
                else if(position==1)
                {
                    btn1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (et1.getText().toString().equalsIgnoreCase("") || et2.getText().toString().equalsIgnoreCase("") || et3.getText().toString().equalsIgnoreCase("") || et4.getText().toString().equalsIgnoreCase(""))
                            {
                                new AlertDialog.Builder(adminPhase.this)
                                        .setTitle("Error")
                                        .setMessage("Please provide required information")
                                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                // continue with delete

                                            }
                                        })
                                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                // do nothing
                                            }
                                        })
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .show();
                            }
                            else {
                                Realm.init(adminPhase.this);
                                realm = Realm.getDefaultInstance();
                                computerData m2 = new computerData();
                                m2.productName2 = et1.getText().toString();
                                m2.price2 = Double.parseDouble(et2.getText().toString());
                                m2.imgID2 = R.drawable.c;
                                realm.beginTransaction();
                                realm.copyToRealm(m2);
                                realm.commitTransaction();
                                Random r1 = new Random();
                                int i11 = (r1.nextInt(1000));
                                DatabaseReference myRef1 = database.getReference("Computers");
                                Map<String, String> data= new HashMap<String, String>();
                                data.put(et1.getText().toString(),"Product Name");
                                data.put(et2.getText().toString(),"Product Price");
                                data.put(et3.getText().toString(),"Product Color");
                                data.put(et4.getText().toString(),"Product Quantity");
                                myRef1.child(et1.getText().toString()+i11).setValue(data);



                                Toast.makeText(adminPhase.this, "1 Computer Added", Toast.LENGTH_SHORT).show();
                                et1.setText("");
                                et2.setText("");
                                et3.setText("");
                                et4.setText("");
                            }
                        }
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}

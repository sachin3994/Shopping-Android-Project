package com.example.sachin.realmdbapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.realm.Realm;
import io.realm.RealmResults;


public class Register extends AppCompatActivity {
    EditText et1,et2,et3,et4;
    Button btn1,btn2;
    Realm realm;
    String email1,password1;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        et1 = (EditText) findViewById(R.id.editText2);
        et2 = (EditText) findViewById(R.id.editText3);
        et3 = (EditText) findViewById(R.id.editText);
        et4= (EditText) findViewById(R.id.editTextEmail);
        btn1 = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button2);
        final View parentLayout=findViewById(R.id.rootView);
        Realm.init(Register.this);
        realm = Realm.getDefaultInstance();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (et1.getText().toString().equalsIgnoreCase("") || et2.getText().toString().equalsIgnoreCase("") || et3.getText().toString().equalsIgnoreCase("") || et4.getText().toString().equalsIgnoreCase(""))
                {
                    new AlertDialog.Builder(Register.this)
                            .setTitle("Error")
                            .setMessage("Provide required information")
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

                    /*form myform = new form();
                    myform.username = et1.getText().toString();
                    myform.password = et2.getText().toString();
                    myform.age = Integer.parseInt(et3.getText().toString());
                    myform.email = et4.getText().toString();
                    realm.beginTransaction();
                    realm.copyToRealm(myform);
                    realm.commitTransaction();*/
                    email1=et4.getText().toString();
                    password1=et2.getText().toString();
                    createAccount(email1,password1);


                    //Toast.makeText(Register.this, realmData.username, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(Register.this, "Successful", Toast.LENGTH_SHORT).show();
                  /* new AlertDialog.Builder(Register.this)
                            .setTitle("Successful")
                            .setMessage("press OK to continue")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                    login l = new login();
                                    l.sharedPrefValue = 2;
                                    Intent myintent = new Intent(Register.this, login.class);
                                    startActivity(myintent);
                                    finish();
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                }
                            })
                            .setIcon(android.R.drawable.ic_input_add)
                            .show();*/
                }



            }
        });
    btn2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RealmResults<form> test = realm.where(form.class).findAll();
           /* for(int i=0;i<test.size();i++) {

                if (test != null) {
                    Toast.makeText(Register.this, test.get(i).username + " " + test.get(i).password, Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(Register.this, "not exist", Toast.LENGTH_SHORT).show();
                }
            }*/
            Intent myintent=new Intent(Register.this,userList.class);
            startActivity(myintent);
        }
    });
       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, values);



    }
    private void createAccount(String email, String password) {

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Success message", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(Register.this, "Authentication success.",
                                    Toast.LENGTH_SHORT).show();
                            form myform = new form();
                            myform.username = et1.getText().toString();
                            myform.password = et2.getText().toString();
                            myform.age = Integer.parseInt(et3.getText().toString());
                            myform.email = et4.getText().toString();
                            realm.beginTransaction();
                            realm.copyToRealm(myform);
                            realm.commitTransaction();


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Error message", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Register.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // [START_EXCLUDE]
                        // [END_EXCLUDE]
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Register.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                });
        // [END create_user_with_email]
    }


}

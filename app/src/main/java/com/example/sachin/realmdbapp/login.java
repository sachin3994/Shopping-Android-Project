package com.example.sachin.realmdbapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.realm.Realm;
import io.realm.RealmResults;

public class login extends AppCompatActivity {
    String email1;
    EditText et1,et2;
    Button btn1,btn2;
    Realm realm;
    boolean b=false;
    String uName;
    String pass1;
    private FirebaseAuth mAuth;

    int status;
    int sharedPrefValue;

    //uName=et1.getText().toString();
   // pass=et2.getText().toString();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        b=false;
        mAuth = FirebaseAuth.getInstance();
        et1= (EditText) findViewById(R.id.editText4);
        et2= (EditText) findViewById(R.id.editText5);
        btn1= (Button) findViewById(R.id.button3);
        btn2= (Button) findViewById(R.id.button4);
       /* if(getIntent().getExtras()!=null)
        {
             uName=getIntent().getExtras().getString("username");
        }
        else
        {
            Toast.makeText(this, "not getting in login", Toast.LENGTH_SHORT).show();
        }*/
        email1=et1.getText().toString();
        pass1=et2.getText().toString();
        Realm.init(this);
        realm=Realm.getDefaultInstance();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (et1.getText().toString().equalsIgnoreCase("admin") && et2.getText().toString().equalsIgnoreCase("password"))
                {
                    new AlertDialog.Builder(login.this)
                            .setTitle("Welcome")
                            .setMessage("Opening admin phase")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                    Intent myintent = new Intent(login.this, adminPhase.class);
                                    startActivity(myintent);
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                }
                            })
                            .setIcon(android.R.drawable.btn_star)
                            .show();
                }
                else if(et1.getText().toString().equalsIgnoreCase("") && et2.getText().toString().equalsIgnoreCase(""))
                {
                    new AlertDialog.Builder(login.this)
                            .setTitle("Eror")
                            .setMessage("Fields cannot be empty")
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
                            .setIcon(android.R.drawable.btn_star)
                            .show();
                }
                else {

                    signIn(et1.getText().toString(),et2.getText().toString());

                }

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent=new Intent(login.this,Register.class);
                startActivity(myintent);
            }
        });




        }
    private void signIn(final String email, String password) {



        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("onSuccess", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(login.this, "Success", Toast.LENGTH_SHORT).show();
                            b=true;
                            Realm.init(login.this);
                            realm=Realm.getDefaultInstance();
                            RealmResults<form> test1 = realm.where(form.class).findAll();
                            for(int i=0;i<test1.size();i++)
                            {
                                if(test1.get(i).email.equalsIgnoreCase(email))
                                {
                                    uName=test1.get(i).username;
                                }
                            }
                            Intent myintent = new Intent(login.this, MainFragmentActivity.class);
                            myintent.putExtra("UNAME",uName);
                            startActivity(myintent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("onError", "signInWithEmail:failure", task.getException());
                            Toast.makeText(login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            new AlertDialog.Builder(login.this)
                                    .setTitle("Error")
                                    .setMessage("User not found, press OK to register")
                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            // continue with delete
                                            Intent myintent = new Intent(login.this, Register.class);
                                            startActivity(myintent);
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

                        // [START_EXCLUDE]

                    }
                });
        // [END sign_in_with_email]
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        b=false;
        return super.onCreateView(name, context, attrs);
    }
}

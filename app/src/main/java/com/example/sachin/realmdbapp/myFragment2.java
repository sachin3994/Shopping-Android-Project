package com.example.sachin.realmdbapp;

/**
 * Created by Sachin on 22-04-2017.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Sachin on 22-04-2017.
 */

public class myFragment2 extends Fragment {
    TextView t1,t2,t3;
    String uName,uEmail;
    int uAge;
    EditText et1,et2,et3;
    Button btn1;
    Realm realm;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment2layout,container,false);
        t1= (TextView) v.findViewById(R.id.profileName);
        t2= (TextView) v.findViewById(R.id.profileEmail);
        t3= (TextView) v.findViewById(R.id.profileAge);
        t1.setText(uName);
        t2.setText(uEmail);
        t3.setText(""+uAge);
        et1= (EditText) v.findViewById(R.id.editText6);
        et2= (EditText) v.findViewById(R.id.editText7);
        et3= (EditText) v.findViewById(R.id.editText8);
        btn1= (Button) v.findViewById(R.id.button7);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Realm.init(getActivity());
                realm=Realm.getDefaultInstance();
                RealmResults<form> test = realm.where(form.class).findAll();
                if (et1.getText().toString().equalsIgnoreCase("") || et2.getText().toString().equalsIgnoreCase("") || et1.getText().toString().equalsIgnoreCase(""))
                {
                    Snackbar.make(getView(), "Fields cannot be empty", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else {
                    for (int i = 0; i < test.size(); i++) {
                        if (et1.getText().toString().equalsIgnoreCase(test.get(i).password) && uName.equalsIgnoreCase(test.get(i).username)) {
                            if (et2.getText().toString().equalsIgnoreCase(et3.getText().toString())) {
                                form myF = new form();
                                myF.username = uName;
                                myF.password = et2.getText().toString();
                                myF.email = uEmail;
                                myF.age = uAge;
                                realm.beginTransaction();
                                realm.insertOrUpdate(myF);
                                realm.commitTransaction();
                                Snackbar.make(getView(), "Password changed successfully", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                            }
                        }
                    }
                }
            }
        });
        return v;
    }
}
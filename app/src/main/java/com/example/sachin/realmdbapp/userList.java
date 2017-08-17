package com.example.sachin.realmdbapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;

public class userList extends AppCompatActivity {
    ListView myList;
    Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_userlist);
        myList= (ListView) findViewById(R.id.list);
        Realm.init(this);
        realm = Realm.getDefaultInstance();
        RealmResults<form> test = realm.where(form.class).findAll();
        String[] names=new String[test.size()];
        String[] pass=new String[test.size()];

        for(int i=0;i<test.size();i++)
        {
            names[i]=test.get(i).username;
            pass[i]=test.get(i).password;
        }
        ArrayAdapter<String> myAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);
        myList.setAdapter(myAdapter);
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(userList.this, ""+position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

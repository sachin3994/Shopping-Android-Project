package com.example.sachin.realmdbapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainFragmentActivity extends AppCompatActivity {
    String name,emailID;
    int age;
    Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment);

        /*Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        if (getIntent().getExtras()!=null)
        {
            name=getIntent().getExtras().getString("UNAME");
        }
        else
        {
            Toast.makeText(this, "not getting in Main Fragment", Toast.LENGTH_SHORT).show();
        }
        Realm.init(MainFragmentActivity.this);
        realm=Realm.getDefaultInstance();
        RealmResults<form> test = realm.where(form.class).findAll();
        for(int i=0;i<test.size();i++) {
            if (name.equalsIgnoreCase(test.get(i).username)) {
                age=test.get(i).age;
                emailID=test.get(i).email;
            }
        }

        TabLayout tablayout= (TabLayout) findViewById(R.id.tab_layout);
        tablayout.addTab(tablayout.newTab().setText("welcome"));
        tablayout.addTab(tablayout.newTab().setText("Profile"));
        tablayout.addTab(tablayout.newTab().setText("Cart"));
        tablayout.setTabGravity(tablayout.GRAVITY_FILL);

        final ViewPager viewPager= (ViewPager) findViewById(R.id.pager);
        pagerAdapter myAdapter=new pagerAdapter(getSupportFragmentManager(),tablayout.getTabCount(),name,age,emailID);
        viewPager.setAdapter(myAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
}

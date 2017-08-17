package com.example.sachin.realmdbapp;

/**
 * Created by Sachin on 22-04-2017.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Sachin on 22-04-2017.
 */

public class pagerAdapter extends FragmentStatePagerAdapter {
    int numOfTabs;
    String nn,EE;
    int aa;
    public pagerAdapter(FragmentManager fm,int num,String n,int a,String eID) {
        super(fm);
        numOfTabs=num;
        nn=n;
        aa=a;
        EE=eID;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                myFragment1 tab1=new myFragment1();
                tab1.name1=nn;
                return tab1;



            case 1:
                myFragment2 tab2=new myFragment2();
                tab2.uName=nn;
                tab2.uAge=aa;
                tab2.uEmail=EE;
                return tab2;

            case 2:
                myFragment3 tab3=new myFragment3();
                return tab3;

            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}

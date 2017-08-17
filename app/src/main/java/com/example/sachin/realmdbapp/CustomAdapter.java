package com.example.sachin.realmdbapp;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import io.realm.RealmResults;


/**
 * Created by Sachin on 22-04-2017.
 */

public class CustomAdapter extends ArrayAdapter {
    Context mcontext;
    RealmResults <mobileData> data;
    RealmResults <computerData> data1;
    int myID;
    public CustomAdapter(@NonNull Context context, RealmResults pList, int ID) {
        super(context,R.layout.my_cell,pList);
        this.myID=ID;
        mcontext=context;
        if (myID==1) {
            this.data = pList;
        }
        else if(myID==2)
        {
            this.data1=pList;
        }


    }
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater inflater=LayoutInflater.from(parent.getContext());
            convertView=inflater.inflate(R.layout.my_cell,parent,false);


        TextView t1= (TextView) convertView.findViewById(R.id.textView);
        ImageView img= (ImageView) convertView.findViewById(R.id.pImage);
        TextView t2= (TextView) convertView.findViewById(R.id.priceTag);


        /*productList myhelper=new productList();
        myhelper=data.get(position);*/
        if(myID==1) {
            t1.setText(data.get(position).productName1);
            img.setImageResource(data.get(position).imgID1);
            t2.setText(""+data.get(position).price1);
        }
        else if(myID==2)
        {
            t1.setText(data1.get(position).productName2);
            img.setImageResource(data1.get(position).imgID2);
            t2.setText(""+data1.get(position).price2);
        }

        return convertView;
    }
}

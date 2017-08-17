package com.example.sachin.realmdbapp;

import android.content.Context;
import android.support.annotation.IdRes;
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
 * Created by Sachin on 26-04-2017.
 */

public class CustomAdapterCart extends ArrayAdapter {
    RealmResults<myCart> cartList;
    public CustomAdapterCart(@NonNull Context context, RealmResults<myCart> pList) {
        super(context,R.layout.cart_cell,pList);
        cartList=pList;
    }
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        convertView=inflater.inflate(R.layout.cart_cell,parent,false);


        TextView t1= (TextView) convertView.findViewById(R.id.textViewCart);
        ImageView img= (ImageView) convertView.findViewById(R.id.cartImage);


        t1.setText(cartList.get(position).productName);
        img.setImageResource(cartList.get(position).imgID);

        /*productList myhelper=new productList();
        myhelper=data.get(position);*/


        return convertView;
    }
}

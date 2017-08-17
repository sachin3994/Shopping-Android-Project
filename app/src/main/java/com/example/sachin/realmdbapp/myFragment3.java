package com.example.sachin.realmdbapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.zip.Inflater;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Sachin on 26-04-2017.
 */

public class myFragment3 extends Fragment {
    ListView lv;
    Realm realm;
    TextView t1;
    double total;
    Button btn1;
    FirebaseDatabase database;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       View v=inflater.inflate(R.layout.fragment3layout,container,false);
        lv= (ListView) v.findViewById(R.id.myCartList);
        t1= (TextView) v.findViewById(R.id.textView13);
        btn1= (Button) v.findViewById(R.id.button5);
        database = FirebaseDatabase.getInstance();
        Realm.init(getActivity());
        realm=Realm.getDefaultInstance();
        RealmResults<myCart> test = realm.where(myCart.class).findAll();
        final CustomAdapterCart myListAdapter=new CustomAdapterCart(getActivity(),test);
        lv.setAdapter(myListAdapter);
        for (int i=0;i<test.size();i++)
        {
            total=total+test.get(i).price;
        }
        t1.setText(""+total);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final RealmResults<myCart> rows = realm.where(myCart.class).findAll();

                DatabaseReference myRef = database.getReference("Orders");
                Map<String, String> data= new HashMap<String, String>();
                for (int i=0;i<rows.size();i++)
                {
                    data.put(rows.get(i).productName,"Product Name");
                    int temp=(int)(rows.get(i).price);
                    data.put(temp+"","Product Name");
                    myRef.child(rows.get(i).productName).setValue(data);
                }
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        rows.deleteAllFromRealm();
                        myListAdapter.notifyDataSetChanged();
                        t1.setText(""+0.0);

                    }
                });
            }
        });
        return v;
    }
}

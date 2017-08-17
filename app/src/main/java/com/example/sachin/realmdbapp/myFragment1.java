package com.example.sachin.realmdbapp;

/**
 * Created by Sachin on 22-04-2017.
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by Sachin on 22-04-2017.
 */

public class myFragment1 extends Fragment {
    Spinner mySpinner;
    String name1;
    ListView myList;
    String[] product={"Phones","Computers"};
    ArrayList<mobileData> listOfProducts1=new ArrayList<mobileData>();
    ArrayList<computerData> listOfProducts2=new ArrayList<computerData>();
    TextView t1;
    int srno=0;
    Realm realm;

   /* public void addDataForPhone()
    {
        mobileData obj1=new mobileData();
        obj1.productName1="Iphone 7";
        obj1.price1=999;
        obj1.imgID1=R.drawable.iphone;
        listOfProducts1.add(obj1);

        mobileData obj2=new mobileData();
        obj2.productName1="Iphone 7 plus";
        obj2.price1=1099;
        obj2.imgID1=R.drawable.iphone;
        listOfProducts1.add(obj2);

        mobileData obj3=new mobileData();
        obj3.productName1="Vivo V5";
        obj3.price1=549;
        obj3.imgID1=R.drawable.vivo;
        listOfProducts1.add(obj3);

        mobileData obj4=new mobileData();
        obj4.productName1="Google Pixel";
        obj4.price1=1099;
        obj4.imgID1=R.drawable.pixel;
        listOfProducts1.add(obj4);
    }*/
    /*public void addDataForComputers()
    {
        computerData obj1=new computerData();
        obj1.productName2="HP Notebook pro i5";
        obj1.price2=1019;
        obj1.imgID2=R.drawable.laptop1;
        listOfProducts2.add(obj1);

        computerData obj2=new computerData();
        obj2.productName2="Dell Inspiron i5 PC";
        obj2.price2=1199;
        obj2.imgID2=R.drawable.dell1;
        listOfProducts2.add(obj2);

        computerData obj3=new computerData();
        obj3.productName2="MacBook  Pro";
        obj3.price2=1549;
        obj3.imgID2=R.drawable.apple1;
        listOfProducts2.add(obj3);

        computerData obj4=new computerData();
        obj4.productName2="Toshiba Satellite i7";
        obj4.price2=1099;
        obj4.imgID2=R.drawable.laptop2;
        listOfProducts2.add(obj4);
    }*/
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       // addDataForPhone();
       // addDataForComputers();
        View v= inflater.inflate(R.layout.fragment1layout, container, false);
        t1= (TextView) v.findViewById(R.id.textView9);
        /*login myLogin=new login();
        String wname=myLogin.setMyData();
        Toast.makeText(getActivity(), ""+wname, Toast.LENGTH_SHORT).show();
        t1.setText(wname);*/
       // String t = this.getArguments().getString("name");
        //String t=this.getArguments().getString("name");
        t1.setText(name1);

        mySpinner= (Spinner) v.findViewById(R.id.spinner);
        myList= (ListView) v.findViewById(R.id.listView);
        ArrayAdapter<String> myAdapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,product);
        this.registerForContextMenu(myList);

        myList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        mySpinner.setAdapter(myAdapter);
        mySpinner.setGravity(Gravity.CENTER);
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0)
                {   Realm.init(getActivity());
                    realm=Realm.getDefaultInstance();
                    RealmResults<mobileData> test = realm.where(mobileData.class).findAll();
                    CustomAdapter myListAdapter=new CustomAdapter(getActivity(),test,1);
                    myList.setAdapter(myListAdapter);
                    srno=0;
                }
                else if(position==1)
                {
                    Realm.init(getActivity());
                    realm=Realm.getDefaultInstance();
                    RealmResults<computerData> test1 = realm.where(computerData.class).findAll();
                    CustomAdapter mylistAdapter1=new CustomAdapter(getActivity(),test1,2);
                    myList.setAdapter(mylistAdapter1);
                    srno=1;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        return v;
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int pos=info.position;
        if(item.getItemId()==R.id.addToCart)
        {
            Realm.init(getActivity());
            realm=Realm.getDefaultInstance();
            if(srno==0)
            {
                Realm.init(getActivity());
                realm=Realm.getDefaultInstance();
                RealmResults<mobileData> test = realm.where(mobileData.class).findAll();
                myCart m1=new myCart();
                m1.productName=test.get(pos).productName1;
                m1.price=test.get(pos).price1;
                m1.imgID=test.get(pos).imgID1;
                realm.beginTransaction();
                realm.copyToRealm(m1);
                realm.commitTransaction();
            }
            else if (srno==1)
            {
                RealmResults<computerData> test1 = realm.where(computerData.class).findAll();
                myCart m2=new myCart();
                m2.productName=test1.get(pos).productName2;
                m2.price=test1.get(pos).price2;
                m2.imgID=test1.get(pos).imgID2;
                realm.beginTransaction();
                realm.copyToRealm(m2);
                realm.commitTransaction();
            }
            Snackbar.make(getView(), "1 Item Added", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();        }

        else if(item.getItemId()==R.id.description)
        {
            if(srno==0) {
                if (pos == 0) {
                    String url = "http://www.gsmarena.com/apple_iphone_7-8064.php";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                } else if (pos == 1) {
                    String url = "http://www.gsmarena.com/apple_iphone_7_plus-8065.php";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                } else if (pos == 2) {
                    String url = "http://www.gsmarena.com/vivo_v5-8430.php";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                } else if (pos == 3) {
                    String url = "http://www.gsmarena.com/google_pixel-8346.php";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
                else if (pos == 4) {
                    String url = "http://www.gsmarena.com";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
                else if (pos == 5) {
                    String url = "http://www.gsmarena.com";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
            }
            else if (srno==1)
            {
                if (pos == 0) {
                    String url = "https://www.cnet.com/products/hp-probook-6360b-core-i5-2410m-2-3ghz-13-3/specs/";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                } else if (pos == 1) {
                    String url = "https://www.cnet.com/products/dell-inspiron-15-5547-15-6-core-i7-4510u-8-gb-ram-1-tb-hdd-english/specs/";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                } else if (pos == 2) {
                    String url = "https://www.apple.com/ca/macbook-pro/specs/";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                } else if (pos == 3) {
                    String url = "https://www.cnet.com/products/toshiba-satellite-s55-c5247-15-6-core-i7-4720hq-8-gb-ram-1-tb-hdd/specs/";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
                else if (pos == 4) {
                    String url = "https://www.google.ca/search?q=computer+specs&rlz=1C1AVNA_enIN677IN677&oq=computer+specs&aqs=chrome..69i57j0l5.2891j0j9&sourceid=chrome&ie=UTF-8#q=all+laptops+specs";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
                else if (pos == 5) {
                    String url = "https://www.google.ca/search?q=computer+specs&rlz=1C1AVNA_enIN677IN677&oq=computer+specs&aqs=chrome..69i57j0l5.2891j0j9&sourceid=chrome&ie=UTF-8#q=all+laptops+specs";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
            }

        }






        return super.onContextItemSelected(item);


    }
}

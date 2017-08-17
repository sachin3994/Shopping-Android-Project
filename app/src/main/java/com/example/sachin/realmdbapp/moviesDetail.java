package com.example.sachin.realmdbapp;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

public class moviesDetail extends AppCompatActivity {

    ImageView img1;
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_detail);
        img1= (ImageView) findViewById(R.id.imageView);
        t1= (TextView) findViewById(R.id.textView6);
       /* if (getIntent().getExtras()!=null) {
            int position = getIntent().getExtras().getInt("listIndex");
            if(position==0)
            {
                img1.setImageResource(R.drawable.idiots);
                t1.setText("3 Idiots");
            }
            else if(position==1)
            {
                img1.setImageResource(R.drawable.prestige);
                t1.setText("Prestige");
            }
            else if(position==2)
            {
                img1.setImageResource(R.drawable.constantine);
                t1.setText("Constantine");
            }
            else if(position==3)
            {
                img1.setImageResource(R.drawable.skyfall);
                t1.setText("Sky Fall");
            }
            else if(position==4)
            {
                img1.setImageResource(R.drawable.avengers);
                t1.setText("Avengers-Age of ultron");
            }
        }*/

    }
}

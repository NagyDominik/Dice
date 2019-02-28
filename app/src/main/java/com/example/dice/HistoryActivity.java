package com.example.dice;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HistoryActivity extends AppCompatActivity {

    Button btn_Back;
    Button btn_Clear;
    ListView lv_History;
    ImageView imageView;
    
    int IMAGES [] ={
            R.drawable.one,
            R.drawable.two,
            R.drawable.three,
            R.drawable.four,
            R.drawable.five,
            R.drawable.six,
    };

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        btn_Back = this.findViewById(R.id.btn_Back);
        btn_Clear = this.findViewById(R.id.bnt_Clear);
        lv_History = this.findViewById(R.id.lv_History);


        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        CustomAdapter customAdapter = new CustomAdapter();
        lv_History.setAdapter(customAdapter);

    }




    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return IMAGES.length ;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup parent) {

            View view = getLayoutInflater().inflate(R.layout.cell, null);
            ImageView mimage = (ImageView) view.findViewById(R.id.image);
            TextView mtext = (TextView)view.findViewById(R.id.rolledTime);

            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            Date date = new Date();
            dateFormat.format(date);

            mimage.setImageResource(IMAGES[i]);
            mtext.setText(dateFormat.format(date));


            return view;
        }
    }
}

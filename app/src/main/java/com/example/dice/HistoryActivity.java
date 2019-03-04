package com.example.dice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dice.Model.BEDiceRoll;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import static java.lang.String.format;

public class HistoryActivity extends AppCompatActivity {

    Button btn_Back;
    Button btn_Clear;
    ListView lv_History;

    RollAdapter rollAdapter;
    ArrayList<BEDiceRoll> rollHistory = new ArrayList<>();
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
                rollHistory.clear();
                lv_History.setAdapter(null);
                Intent returnIntent = new Intent();
                setResult(2,returnIntent);
                finish();
            }
        });


        Intent receive = getIntent();
        rollHistory.addAll((ArrayList<BEDiceRoll>) receive.getSerializableExtra("rolls"));

        rollAdapter = new RollAdapter(this, R.layout.cell, rollHistory);
        lv_History.setAdapter(rollAdapter);

    }

    class RollAdapter extends ArrayAdapter {
        ArrayList<BEDiceRoll> history;
        SimpleDateFormat format = new SimpleDateFormat("kk:mm:ss", Locale.getDefault());

        private final int[] colours = {
                Color.parseColor("#E8F5E9"),
                Color.parseColor("#FAFAFA")
        };

        public RollAdapter(Context context, int textViewResourceId,
                           ArrayList<BEDiceRoll> rollHistory) {
            super(context, textViewResourceId, rollHistory);
            this.history = rollHistory;
        }


        private ImageView rollDiceConverter(int num) {

            ImageView dice = new ImageView(HistoryActivity.this);
            switch (num) {
                case 1:
                    dice.setImageResource(R.drawable.one);
                    break;
                case 2:
                    dice.setImageResource(R.drawable.two);
                    break;
                case 3:
                    dice.setImageResource(R.drawable.three);
                    break;
                case 4:
                    dice.setImageResource(R.drawable.four);
                    break;
                case 5:
                    dice.setImageResource(R.drawable.five);
                    break;
                case 6:
                    dice.setImageResource(R.drawable.six);
                    break;
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0,10,25,10);
            dice.setLayoutParams(params);
            dice.getLayoutParams().height = 110;
            dice.getLayoutParams().width = 110;

            return dice;
        }

        public void createNewLine(BEDiceRoll roll, LinearLayout layout) {
            for (int i = 0; i < roll.getM_roll().size(); i++) {
                ImageView iv = rollDiceConverter(roll.getM_roll().get(i));
                iv.setTag("iv_" + i);
                layout.addView(iv);
            }
            Log.d("qwe", roll.getM_roll().size() + "");
        }



        @Override
        public View getView(int position, View view, ViewGroup parent) {

            if (view == null) {
                LayoutInflater li = (LayoutInflater) getContext().getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                view = li.inflate(R.layout.cell, null);

            } else
                Log.d("LIST", "Position: " + position + " View Reused");
            BEDiceRoll dice = history.get(position);

            TextView date = view.findViewById(R.id.date);
            LinearLayout linearLayout = view.findViewById(R.id.linearLine);

            /*date.setGravity(Gravity.CENTER_VERTICAL);
            linearLayout.setGravity(Gravity.CENTER_VERTICAL);*/

            view.setBackgroundColor(colours[position % colours.length]);

            createNewLine(dice,linearLayout);

            Log.d("asd", dice.getM_date());
            date.setText(dice.getM_date());

            return view;
        }


    }
}

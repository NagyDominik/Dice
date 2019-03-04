package com.example.dice;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dice.Model.BEDiceRoll;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class HistoryActivity extends AppCompatActivity {

    Button btn_Back;
    Button btn_Clear;
    ListView lv_History;

    RollAdapter rollAdapter;
    ArrayList<BEDiceRoll> rollHistory= new ArrayList<>();
    ArrayList<ImageView> rollInPics = new ArrayList<>();

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

        Intent receive = getIntent();
        rollHistory.addAll((ArrayList<BEDiceRoll>) receive.getSerializableExtra("rolls"));
        rollInPics = rollDiceConverter(rollHistory);

        rollAdapter = new RollAdapter(this, R.layout.cell, rollHistory);
        lv_History.setAdapter(rollAdapter);

    }

    private ArrayList<ImageView> rollDiceConverter(ArrayList<BEDiceRoll> list ) {
        ArrayList<BEDiceRoll> diceList = rollHistory;
        ArrayList<ImageView> diceInPics = new ArrayList<>();
        ImageView dice = new  ImageView(this);
        rollHistory.get(1).getM_roll().size(); //5

        for (int i = 0; i < diceList.size(); i++) {
             ArrayList<Integer> diceInNumbers = diceList.get(i).getM_roll();
             Log.d( "elso", String.valueOf(diceList.get(i).getM_roll().size()));

             for (int j = 0; j < diceInNumbers.size(); j++){
                 int currentNumber = diceInNumbers.get(j);
                 switch (currentNumber) {
                     case 1:dice.setImageResource(R.drawable.one);
                         diceInPics.add(dice);
                         break;
                     case 2: dice.setImageResource(R.drawable.two);
                         diceInPics.add(dice);
                         break;
                     case 3: dice.setImageResource(R.drawable.three);
                         diceInPics.add(dice);
                         break;
                     case 4: dice.setImageResource(R.drawable.four);
                         diceInPics.add(dice);
                         break;
                     case 5: dice.setImageResource(R.drawable.five);
                         diceInPics.add(dice);
                         break;
                     case 6: dice.setImageResource(R.drawable.six);
                         diceInPics.add(dice);
                         break;
                 }
            }
        }
        return diceInPics;
    }

    class RollAdapter extends ArrayAdapter{
        ArrayList<BEDiceRoll> history;
        ArrayList<ImageView> diceList;

        public RollAdapter(Context context, int textViewResourceId,
                           ArrayList<BEDiceRoll> rollHistory) {
            super(context, textViewResourceId, rollHistory);
            this.history = rollHistory;
        }
        @Override
        public View getView(int position, View view, ViewGroup parent) {

            if (view == null) {
                LayoutInflater li = (LayoutInflater) getContext().getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                view = li.inflate(R.layout.cell, null);

            }
            else
                Log.d("LIST", "Position: " + position + " View Reused");


            BEDiceRoll dice = history.get(position);

            TextView date = (TextView) view.findViewById(R.id.date);
            ImageView roll= ( ImageView ) view.findViewById(R.id.rolls);

            date.setText(dice.getM_date());
            // roll.setImageResource();

            return view;
        }


    }
}

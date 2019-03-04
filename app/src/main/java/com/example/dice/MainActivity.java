package com.example.dice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import com.example.dice.Model.BEDiceRoll;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btnRoll, btnHistory;
    LinearLayout ll_Layout;
    NumberPicker numPicker;
    ArrayList<BEDiceRoll> history = new ArrayList<>();
    SimpleDateFormat format = new SimpleDateFormat("kk:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRoll = findViewById(R.id.btnRoll);
        btnHistory = findViewById(R.id.btn_History);
        ll_Layout = findViewById(R.id.ll_layout);
        numPicker = findViewById(R.id.numPicker);
        numPicker.setMinValue(1);
        numPicker.setMaxValue(6);

        if (savedInstanceState != null && savedInstanceState.containsKey("NUMPICKER_VAL")) {
            numPicker.setValue(savedInstanceState.getInt("NUMPICKER_VAL"));
            createDice(numPicker.getValue());
            Log.d("DEBUGLOG","value of count restored");
        }
        else {
            Log.d("DEBUGLOG","value of history cannot be initialized");
        }

        setupListeners();
    }

    @Override
    protected void onSaveInstanceState(Bundle state)
    {
        super.onSaveInstanceState(state);
        state.putInt("NUMPICKER_VAL", numPicker.getValue());
        Log.d("DEBUGLOG","Backup...");
    }

    private void setupListeners() {
        btnRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<ImageView> list = getDiceList();
                history.add(rollDice(list));
            }
        });

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(MainActivity.this, HistoryActivity.class);
                Log.d("DEBUGLOG", "Detail activity will be started");
                x.putExtra("rolls", history);
                startActivityForResult(x,1);

                Log.d("DEBUGLOG", "Detail activity is started");
            }
        });

        numPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                createDice(newVal);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 1){
            if(resultCode == 2){
                history.clear();
            }
        }
    }

    private ArrayList<ImageView> getDiceList() {
        ArrayList<ImageView> diceList = new ArrayList<>();
        for (int i = 0; i < ll_Layout.getChildCount(); i++) {
            if (ll_Layout.getChildAt(i) instanceof LinearLayout) {
                LinearLayout child_l = (LinearLayout) ll_Layout.getChildAt(i);
                for (int j = 0; j < child_l.getChildCount(); j++) {
                    if (child_l.getChildAt(j) instanceof ImageView) {
                        diceList.add((ImageView) child_l.getChildAt(j));
                    }
                }
            }
        }
        return diceList;
    }

    private BEDiceRoll rollDice(ArrayList<ImageView> diceList) {
        BEDiceRoll roll = new BEDiceRoll();
        roll.setM_date(format.format(Calendar.getInstance().getTime()));
        Log.d("DEBUGLOG", roll.getM_date());
        for (int i = 0; i < diceList.size(); i++) {
            Random rnd = new Random();
            ImageView current = diceList.get(i);
            int currentRnd = rnd.nextInt(6) + 1;
            roll.addM_roll(currentRnd);
            switch (currentRnd) {
                case 1: current.setImageResource(R.drawable.one);
                    break;
                case 2: current.setImageResource(R.drawable.two);
                    break;
                case 3: current.setImageResource(R.drawable.three);
                    break;
                case 4: current.setImageResource(R.drawable.four);
                    break;
                case 5: current.setImageResource(R.drawable.five);
                    break;
                case 6: current.setImageResource(R.drawable.six);
                    break;
            }
        }
        return roll;
    }

    private void createDice(int num) {
        ll_Layout.removeAllViews();
        int currentLayoutNum = 0;
        int count = 0;
        LinearLayout currentLayout = new LinearLayout(this);

        for (int i = 0; i < num; i++) {
            if (i % 3 == 0) {
                if (currentLayoutNum < (i / 3)) {
                    currentLayoutNum = (i / 3);
                }
                LinearLayout layoutNew = new LinearLayout(this);
                layoutNew.setOrientation(LinearLayout.HORIZONTAL);
                layoutNew.setTag("LinearLayout_" + currentLayoutNum++);
                layoutNew.setGravity(Gravity.CENTER);
                currentLayout = layoutNew;
                ll_Layout.addView(currentLayout);
                Log.d("DEBUG", "LAYOUT CREATED");
            }
            ImageView iv = new ImageView(this);
            iv.setImageResource(R.drawable.one);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            iv.setLayoutParams(params);
            iv.setPadding(20,20,20,20);
            iv.getLayoutParams().height = 345;
            iv.getLayoutParams().width = 345;
            iv.setTag("ImageView_" + count++);
            currentLayout.addView(iv);
        }
    }

}

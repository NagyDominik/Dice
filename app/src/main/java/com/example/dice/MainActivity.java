package com.example.dice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btnRoll;
    LinearLayout ll_Layout;
    NumberPicker numPicker;
    ArrayList<String> history = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRoll = findViewById(R.id.btnRoll);
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
                String log = "";
                Random rnd = new Random();
                int rnd_num = rnd.nextInt(6) + 1;
                log += "Dice: " + rnd_num + ", ";
                rnd_num = rnd.nextInt(6) + 1;
                log += rnd_num;

            }
        });

        numPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                createDice(newVal);
            }
        });
    }

    private void rollDice(int num, ImageView iv) {
        switch (num) {
            case 1: iv.setImageResource(R.drawable.one);
                break;
            case 2: iv.setImageResource(R.drawable.two);
                break;
            case 3: iv.setImageResource(R.drawable.three);
                break;
            case 4: iv.setImageResource(R.drawable.four);
                break;
            case 5: iv.setImageResource(R.drawable.five);
                break;
            case 6: iv.setImageResource(R.drawable.six);
                break;
        }
    }

    private void createDice(int num) {
        ll_Layout.removeAllViews();
        int currentLayoutNum = 0;
        LinearLayout currentLayout = new LinearLayout(this);
        for (int i = 0; i < num; i++) {
            if (i % 3 == 0) {
                if (currentLayoutNum < (i / 3)) {
                    currentLayoutNum = (i / 3);
                }
                LinearLayout layoutNew = new LinearLayout(this);
                layoutNew.setOrientation(LinearLayout.HORIZONTAL);
                layoutNew.setTag("LinearLayout_" + currentLayoutNum);
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
            iv.setTag("ImageView_" + i);
            currentLayout.addView(iv);
        }
    }

}

package com.example.dice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btnRoll, btnClear;
    ImageView ivDice1, ivDice2;
    LinearLayout lLayout;
    ListView lvLog;
    ArrayAdapter adapter;
    ArrayList<String> history = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRoll = findViewById(R.id.btnRoll);
        btnClear = findViewById(R.id.btnClear);
        ivDice1 = findViewById(R.id.ivDice1);
        ivDice2 = findViewById(R.id.ivDice2);
        lvLog = findViewById(R.id.lvLog);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, history);
        lvLog.setAdapter(adapter);

        if (savedInstanceState != null && savedInstanceState.containsKey("HISTORY")) {
            history.addAll(savedInstanceState.getStringArrayList("HISTORY"));
            lvLog.setSelectionFromTop(savedInstanceState.getInt("SCROLL_POS"), 0);
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
        state.putStringArrayList("HISTORY", history);
        state.putInt("SCROLL_POS", lvLog.getFirstVisiblePosition());
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
                rollDice(rnd_num, ivDice1);
                rnd_num = rnd.nextInt(6) + 1;
                log += rnd_num;
                rollDice(rnd_num, ivDice2);
                history.add(0, log);
                adapter.notifyDataSetChanged();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                history.clear();
                adapter.clear();
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

    private TextView createTextView(String txt) {
        LayoutParams lparams = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        TextView tv = new TextView(this);
        tv.setLayoutParams(lparams);
        tv.setText(txt);
        tv.setTextSize(18);
        return tv;
    }

}

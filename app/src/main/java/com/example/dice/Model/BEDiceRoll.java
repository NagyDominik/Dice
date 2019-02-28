package com.example.dice.Model;

import java.io.Serializable;

public class BEDiceRoll implements Serializable {

    private String m_date;
    private int[] m_rolls;

    public BEDiceRoll(String date) {
        this.m_date = date;
    }

    public BEDiceRoll() {
    }

    public int[] getM_roll() {
        return m_rolls;
    }

    public String getM_date() {
        return m_date;
    }
    
}

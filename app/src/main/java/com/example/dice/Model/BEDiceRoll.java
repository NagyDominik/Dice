package com.example.dice.Model;

import java.io.Serializable;

public class BEDiceRoll implements Serializable {

    private int m_roll;
    private String m_date;

    public BEDiceRoll(int roll, String date) {
        this.m_roll = roll;
        this.m_date = date;
    }

    public BEDiceRoll() {
    }

    public int getM_roll() {
        return m_roll;
    }

    public String getM_date() {
        return m_date;
    }
    
}

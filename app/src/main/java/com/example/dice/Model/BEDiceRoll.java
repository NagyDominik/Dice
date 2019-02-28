package com.example.dice.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BEDiceRoll implements Serializable {

    private String m_date;
    private ArrayList<Integer> m_rolls = new ArrayList<>();

    public BEDiceRoll(String date) {
        this.m_date = date;
    }

    public BEDiceRoll() {
    }

    public void setM_date(String m_date) {
        this.m_date = m_date;
    }

    public void setM_rolls(List m_rolls) {
        this.m_rolls.clear();
        this.m_rolls.addAll(m_rolls);
    }

    public void addM_roll(int roll) {
        this.getM_roll().add(roll);
    }

    public void clearM_rolls() {
        this.m_rolls.clear();
    }

    public ArrayList<Integer> getM_roll() {
        return m_rolls;
    }

    public String getM_date() {
        return m_date;
    }
    
}

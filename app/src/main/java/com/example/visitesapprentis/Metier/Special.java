package com.example.visitesapprentis.Metier;

public class Special extends Visite{

    private String Special;

    public Special(String conclusion, String special) {
        super(conclusion);
        Special = special;
    }

    public String getSpecial() {
        return Special;
    }

    public void setSpecial(String special) {
        Special = special;
    }
}

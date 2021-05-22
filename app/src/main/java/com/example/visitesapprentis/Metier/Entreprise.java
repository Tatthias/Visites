package com.example.visitesapprentis.Metier;

public class Entreprise {

    private String nomEnt;
    private String adresseEnt;
    private String telEnt;

    public Entreprise(String nomEnt, String adresseEnt, String telEnt) {
        this.nomEnt = nomEnt;
        this.adresseEnt = adresseEnt;
        this.telEnt = telEnt;
    }

    public String getNomEnt() {
        return nomEnt;
    }

    public void setNomEnt(String nomEnt) {
        this.nomEnt = nomEnt;
    }

    public String getAdresseEnt() {
        return adresseEnt;
    }

    public void setAdresseEnt(String adresseEnt) {
        this.adresseEnt = adresseEnt;
    }

    public String getTelEnt() {
        return telEnt;
    }

    public void setTelEnt(String telEnt) {
        this.telEnt = telEnt;
    }
}

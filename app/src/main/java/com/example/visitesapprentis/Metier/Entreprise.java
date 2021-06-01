package com.example.visitesapprentis.Metier;

public class Entreprise {

    private String nomEnt;
    private String adresseEnt;
    private String cpEnt;
    private String villeEnt;
    private String telEnt;

    public Entreprise(String nomEnt, String adresseEnt, String cpEnt, String villeEnt, String telEnt) {
        this.nomEnt = nomEnt;
        this.adresseEnt = adresseEnt;
        this.cpEnt = cpEnt;
        this.villeEnt = villeEnt;
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

    public String getCpEnt() {
        return cpEnt;
    }

    public void setCpEnt(String cpEnt) {
        this.cpEnt = cpEnt;
    }

    public String getVilleEnt() {
        return villeEnt;
    }

    public void setVilleEnt(String villeEnt) {
        this.villeEnt = villeEnt;
    }

    @Override
    public String toString() {
        return nomEnt;
    }
}

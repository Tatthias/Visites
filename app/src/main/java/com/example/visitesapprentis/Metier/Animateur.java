package com.example.visitesapprentis.Metier;

public class Animateur {

    private String nomAni;
    private String adresseAni;
    private String mailAni;
    private String telAni;

    public Animateur(String nomAni, String adresseAni, String mailAni, String telAni) {
        this.nomAni = nomAni;
        this.adresseAni = adresseAni;
        this.mailAni = mailAni;
        this.telAni = telAni;
    }

    public String getNomAni() {
        return nomAni;
    }

    public void setNomAni(String nomAni) {
        this.nomAni = nomAni;
    }

    public String getAdresseAni() {
        return adresseAni;
    }

    public void setAdresseAni(String adresseAni) {
        this.adresseAni = adresseAni;
    }

    public String getMailAni() {
        return mailAni;
    }

    public void setMailAni(String mailAni) {
        this.mailAni = mailAni;
    }

    public String getTelAni() {
        return telAni;
    }

    public void setTelAni(String telAni) {
        this.telAni = telAni;
    }
}

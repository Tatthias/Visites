package com.example.visitesapprentis.Metier;

public class MaitreStage {

    private String nomMai;
    private String prenomMai;
    private String addresseMai;
    private String telMai;
    private String mailMai;

    public MaitreStage(String nomMai, String prenomMai, String addresseMai, String telMai, String mailMai) {
        this.nomMai = nomMai;
        this.prenomMai = prenomMai;
        this.addresseMai = addresseMai;
        this.telMai = telMai;
        this.mailMai = mailMai;
    }

    public String getNomMai() {
        return nomMai;
    }

    public void setNomMai(String nomMai) {
        this.nomMai = nomMai;
    }

    public String getPrenomMai() {
        return prenomMai;
    }

    public void setPrenomMai(String prenomMai) {
        this.prenomMai = prenomMai;
    }

    public String getAddresseMai() {
        return addresseMai;
    }

    public void setAddresseMai(String addresseMai) {
        this.addresseMai = addresseMai;
    }

    public String getTelMai() {
        return telMai;
    }

    public void setTelMai(String telMai) {
        this.telMai = telMai;
    }

    public String getMailMai() {
        return mailMai;
    }

    public void setMailMai(String mailMai) {
        this.mailMai = mailMai;
    }
}

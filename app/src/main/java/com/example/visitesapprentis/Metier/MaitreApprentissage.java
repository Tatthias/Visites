package com.example.visitesapprentis.Metier;

public class MaitreApprentissage {

    private int idMai;
    private String nomMai;
    private String prenomMai;
    private String addresseMai;
    private String cpMai;
    private String villeMai;
    private String telMai;
    private String mailMai;
    private Entreprise uneEnt;

    public MaitreApprentissage(int idMai, String nomMai, String prenomMai, String addresseMai, String cpMai, String villeMai, String telMai, String mailMai, Entreprise uneEnt) {
        this.idMai = idMai;
        this.nomMai = nomMai;
        this.prenomMai = prenomMai;
        this.addresseMai = addresseMai;
        this.cpMai = cpMai;
        this.villeMai = villeMai;
        this.telMai = telMai;
        this.mailMai = mailMai;
        this.uneEnt = uneEnt;
    }

    public int getIdMai() {
        return idMai;
    }

    public void setIdMai(int idMai) {
        this.idMai = idMai;
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

    public String getCpMai() {
        return cpMai;
    }

    public void setCpMai(String cpMai) {
        this.cpMai = cpMai;
    }

    public String getVilleMai() {
        return villeMai;
    }

    public void setVilleMai(String villeMai) {
        this.villeMai = villeMai;
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

    public Entreprise getUneEnt() {
        return uneEnt;
    }

    public void setUneEnt(Entreprise uneEnt) {
        this.uneEnt = uneEnt;
    }

    @Override
    public String toString() {
        return nomMai + " " + prenomMai + " " + uneEnt;
    }
}

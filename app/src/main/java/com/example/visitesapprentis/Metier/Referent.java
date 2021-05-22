package com.example.visitesapprentis.Metier;

public class Referent {

    private String nomRef;
    private String prenomRef;
    private String addresseRef;
    private String telRef;

    public Referent(String nomRef, String prenomRef, String addresseRef, String telRef) {
        this.nomRef = nomRef;
        this.prenomRef = prenomRef;
        this.addresseRef = addresseRef;
        this.telRef = telRef;
    }

    public String getNomRef() {
        return nomRef;
    }

    public void setNomRef(String nomRef) {
        this.nomRef = nomRef;
    }

    public String getPrenomRef() {
        return prenomRef;
    }

    public void setPrenomRef(String prenomRef) {
        this.prenomRef = prenomRef;
    }

    public String getAddresseRef() {
        return addresseRef;
    }

    public void setAddresseRef(String addresseRef) {
        this.addresseRef = addresseRef;
    }

    public String getTelRef() {
        return telRef;
    }

    public void setTelRef(String telRef) {
        this.telRef = telRef;
    }
}

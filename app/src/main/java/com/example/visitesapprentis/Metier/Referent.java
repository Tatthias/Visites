package com.example.visitesapprentis.Metier;

public class Referent {

    private int idRef;
    private String nomRef;
    private String prenomRef;
    private String addresseRef;
    private String telRef;

    public Referent(int idRef, String nomRef, String prenomRef, String addresseRef, String telRef) {
        this.idRef = idRef;
        this.nomRef = nomRef;
        this.prenomRef = prenomRef;
        this.addresseRef = addresseRef;
        this.telRef = telRef;
    }

    public int getIdRef() {
        return idRef;
    }

    public void setIdRef(int idReferent) {
        this.idRef = idReferent;
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

    public String toString() {
        return nomRef+ " "+prenomRef;
    }


}

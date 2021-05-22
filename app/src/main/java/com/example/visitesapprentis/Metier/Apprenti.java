package com.example.visitesapprentis.Metier;

import java.util.Date;

public class Apprenti {

    private int idApp;
    private String nomApp;
    private String prenomApp;
    private String adresseApp;
    private String villeApp;
    private String cpApp;
    private String telApp;
    private Date dateDebutApp;
    private String classeApp;
    private String mailApp;

    public Apprenti(int id, String nomApp, String prenomApp, String adresseApp, String villeApp, String cpApp, String telApp, Date dateDebutApp, String classeApp, String mailApp) {
        this.idApp = id;
        this.nomApp = nomApp;
        this.prenomApp = prenomApp;
        this.adresseApp = adresseApp;
        this.villeApp = villeApp;
        this.cpApp = cpApp;
        this.telApp = telApp;
        this.dateDebutApp = dateDebutApp;
        this.classeApp = classeApp;
        this.mailApp = mailApp;
    }

    public int getIdApp() {
        return idApp;
    }

    public void setIdApp(int idApprenti) {
        this.idApp = idApprenti;
    }

    public String getNomApp() {
        return nomApp;
    }

    public void setNomApp(String nomApp) {
        this.nomApp = nomApp;
    }

    public String getPrenomApp() {
        return prenomApp;
    }

    public void setPrenomApp(String prenomApp) {
        this.prenomApp = prenomApp;
    }

    public String getAdresseApp() {
        return adresseApp;
    }

    public void setAdresseApp(String adresseApp) {
        this.adresseApp = adresseApp;
    }

    public String getVilleApp() {
        return villeApp;
    }

    public void setVilleApp(String villeApp) {
        this.villeApp = villeApp;
    }

    public String getCpApp() {
        return cpApp;
    }

    public void setCpApp(String cpApp) {
        this.cpApp = cpApp;
    }

    public void setClasseApp(String classeApp) {
        this.classeApp = classeApp;
    }

    public String getTelApp() {
        return telApp;
    }

    public void setTelApp(String telApp) {
        this.telApp = telApp;
    }

    public Date getDateDebutApp() {
        return dateDebutApp;
    }

    public void setDateDebutApp(Date dateDebutApp) {
        this.dateDebutApp = dateDebutApp;
    }

    public String getClasseApp() {
        return classeApp;
    }

    public void setLaClasseApp(String laClasseApp) {
        this.classeApp = laClasseApp;
    }

    public String getMailApp() {
        return mailApp;
    }

    public void setMailApp(String mailApp) {
        this.mailApp = mailApp;
    }

    public String toString(){
        return nomApp + " "+ prenomApp;
    }

}



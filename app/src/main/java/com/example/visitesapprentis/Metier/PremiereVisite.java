package com.example.visitesapprentis.Metier;

public class PremiereVisite extends Visite {

    private String presentCFA;
    private String presentFor;
    private String valideAde;
    private String attenteEnt;
    private String livretApp;
    private String pronote;
    private String planif;
    private String travail;
    private String comportement;
    private String assiduite;
    private String motivation;
    private String implication;
    private String autres;
    private String travailent;
    private String suiviCo;
    private String suiviRes;

    public PremiereVisite(String conclusion, String presentCFA, String presentFor, String valideAde, String attenteEnt, String livretApp, String pronote, String planif, String travail, String comportement, String assiduite, String motivation, String implication, String autres, String travailent, String suiviCo, String suiviRes) {
        super(conclusion);
        this.presentCFA = presentCFA;
        this.presentFor = presentFor;
        this.valideAde = valideAde;
        this.attenteEnt = attenteEnt;
        this.livretApp = livretApp;
        this.pronote = pronote;
        this.planif = planif;
        this.travail = travail;
        this.comportement = comportement;
        this.assiduite = assiduite;
        this.motivation = motivation;
        this.implication = implication;
        this.autres = autres;
        this.travailent = travailent;
        this.suiviCo = suiviCo;
        this.suiviRes = suiviRes;
    }

    public String getPresentCFA() {
        return presentCFA;
    }

    public void setPresentCFA(String presentCFA) {
        this.presentCFA = presentCFA;
    }

    public String getPresentFor() {
        return presentFor;
    }

    public void setPresentFor(String presentFor) {
        this.presentFor = presentFor;
    }

    public String getValideAde() {
        return valideAde;
    }

    public void setValideAde(String valideAde) {
        this.valideAde = valideAde;
    }

    public String getAttenteEnt() {
        return attenteEnt;
    }

    public void setAttenteEnt(String attenteEnt) {
        this.attenteEnt = attenteEnt;
    }

    public String getLivretApp() {
        return livretApp;
    }

    public void setLivretApp(String livretApp) {
        this.livretApp = livretApp;
    }

    public String getPronote() {
        return pronote;
    }

    public void setPronote(String pronote) {
        this.pronote = pronote;
    }

    public String getPlanif() {
        return planif;
    }

    public void setPlanif(String planif) {
        this.planif = planif;
    }

    public String getTravail() {
        return travail;
    }

    public void setTravail(String travail) {
        this.travail = travail;
    }

    public String getComportement() {
        return comportement;
    }

    public void setComportement(String comportement) {
        this.comportement = comportement;
    }

    public String getAssiduite() {
        return assiduite;
    }

    public void setAssiduite(String assiduite) {
        this.assiduite = assiduite;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public String getImplication() {
        return implication;
    }

    public void setImplication(String implication) {
        this.implication = implication;
    }

    public String getAutres() {
        return autres;
    }

    public void setAutres(String autres) {
        this.autres = autres;
    }

    public String getTravailent() {
        return travailent;
    }

    public void setTravailent(String travailent) {
        this.travailent = travailent;
    }

    public String getSuiviCo() {
        return suiviCo;
    }

    public void setSuiviCo(String suiviCo) {
        this.suiviCo = suiviCo;
    }

    public String getSuiviRes() {
        return suiviRes;
    }

    public void setSuiviRes(String suiviRes) {
        this.suiviRes = suiviRes;
    }
}

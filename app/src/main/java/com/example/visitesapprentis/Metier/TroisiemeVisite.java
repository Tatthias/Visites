package com.example.visitesapprentis.Metier;

public class TroisiemeVisite extends Visite{

    private String bilanFor;
    private String projetsApp;
    private String projetsEnt;

    public TroisiemeVisite(String conclusion, String bilanFor, String projetsApp, String projetsEnt) {
        super(conclusion);
        this.bilanFor = bilanFor;
        this.projetsApp = projetsApp;
        this.projetsEnt = projetsEnt;
    }

    public String getBilanFor() {
        return bilanFor;
    }

    public void setBilanFor(String bilanFor) {
        this.bilanFor = bilanFor;
    }

    public String getProjetsApp() {
        return projetsApp;
    }

    public void setProjetsApp(String projetsApp) {
        this.projetsApp = projetsApp;
    }

    public String getProjetsEnt() {
        return projetsEnt;
    }

    public void setProjetsEnt(String projetsEnt) {
        this.projetsEnt = projetsEnt;
    }
}

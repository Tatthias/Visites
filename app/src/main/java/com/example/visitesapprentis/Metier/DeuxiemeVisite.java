package com.example.visitesapprentis.Metier;

public class DeuxiemeVisite extends Visite{

    private String evolutionCompo;
    private String adequationFor;
    private String implicationTache;
    private String difficultes;
    private String pointsAmelio;
    private String mobiliserMAdocSuivi;
    private String enjeux;

    public DeuxiemeVisite(String conclusion, String evolutionCompo, String adequationFor, String implicationTache, String difficultes, String pointsAmelio, String mobiliserMAdocSuivi, String enjeux) {
        super(conclusion);
        this.evolutionCompo = evolutionCompo;
        this.adequationFor = adequationFor;
        this.implicationTache = implicationTache;
        this.difficultes = difficultes;
        this.pointsAmelio = pointsAmelio;
        this.mobiliserMAdocSuivi = mobiliserMAdocSuivi;
        this.enjeux = enjeux;
    }

    public String getEvolutionCompo() {
        return evolutionCompo;
    }

    public void setEvolutionCompo(String evolutionCompo) {
        this.evolutionCompo = evolutionCompo;
    }

    public String getAdequationFor() {
        return adequationFor;
    }

    public void setAdequationFor(String adequationFor) {
        this.adequationFor = adequationFor;
    }

    public String getImplicationTache() {
        return implicationTache;
    }

    public void setImplicationTache(String implicationTache) {
        this.implicationTache = implicationTache;
    }

    public String getDifficultes() {
        return difficultes;
    }

    public void setDifficultes(String difficultes) {
        this.difficultes = difficultes;
    }

    public String getPointsAmelio() {
        return pointsAmelio;
    }

    public void setPointsAmelio(String pointsAmelio) {
        this.pointsAmelio = pointsAmelio;
    }

    public String getMobiliserMAdocSuivi() {
        return mobiliserMAdocSuivi;
    }

    public void setMobiliserMAdocSuivi(String mobiliserMAdocSuivi) {
        this.mobiliserMAdocSuivi = mobiliserMAdocSuivi;
    }

    public String getEnjeux() {
        return enjeux;
    }

    public void setEnjeux(String enjeux) {
        this.enjeux = enjeux;
    }
}

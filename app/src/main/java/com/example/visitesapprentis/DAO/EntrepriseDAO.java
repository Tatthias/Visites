package com.example.visitesapprentis.DAO;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.visitesapprentis.Metier.Entreprise;

import java.util.ArrayList;
import java.util.List;

public class EntrepriseDAO extends DAO<Entreprise>{

    private SQLiteVisitesApprentis dbVisiteEntreprise;
    private SQLiteDatabase db;

    //déclaration des outils nécessaire à la base
    private static final String TABLE_ENTREPRISE = "ENTREPRISE";
    private static final String COL_NOMENTREPRISE = "nomEnt";
    private static final String COL_ADRESSEENTREPRISE = "adresseEnt";
    private static final String COL_CPENTREPRISE = "cpEnt";
    private static final String COL_VILLEENTREPRISE = "villeEnt";
    private static final String COL_TELENTREPRISE = "telEnt";

    public EntrepriseDAO(Context context) {
        dbVisiteEntreprise = new SQLiteVisitesApprentis(context);
    }

    public void open() {
        db = dbVisiteEntreprise.getWritableDatabase();
    }

    public void close() {
        db.close();
    }

    @Override
    public void insert(Entreprise obj) {
        ContentValues valeur = new ContentValues();
        valeur.put(COL_NOMENTREPRISE, obj.getNomEnt());
        valeur.put(COL_ADRESSEENTREPRISE, obj.getAdresseEnt());
        valeur.put(COL_CPENTREPRISE, obj.getCpEnt());
        valeur.put(COL_VILLEENTREPRISE, obj.getVilleEnt());
        valeur.put(COL_TELENTREPRISE, obj.getTelEnt());

        db.insert(TABLE_ENTREPRISE, null, valeur);
    }

    @Override
    public void update(Entreprise obj) {
        ContentValues valeur = new ContentValues();
        valeur.put(COL_NOMENTREPRISE, obj.getNomEnt());
        valeur.put(COL_ADRESSEENTREPRISE, obj.getAdresseEnt());
        valeur.put(COL_CPENTREPRISE, obj.getCpEnt());
        valeur.put(COL_VILLEENTREPRISE, obj.getVilleEnt());
        valeur.put(COL_TELENTREPRISE, obj.getTelEnt());

        db.update(TABLE_ENTREPRISE, valeur, COL_NOMENTREPRISE + "=" + obj.getNomEnt(), null);
    }

    @Override
    public void delete(Entreprise obj) {
        db.delete(TABLE_ENTREPRISE, COL_NOMENTREPRISE + "=" + obj.getNomEnt(), null);
    }

    public List<Entreprise> read(){
        List<Entreprise> lesEntreprises = new ArrayList<>();
        String nom;
        String adresse;
        String cp;
        String ville;
        String tel;

        Cursor curseur = db.query(TABLE_ENTREPRISE, null, null, null, null, null, null);
        curseur.moveToFirst();

        for(int i = 0; i < curseur.getCount(); i++){
            nom = curseur.getString(0);
            adresse = curseur.getString(1);
            cp = curseur.getString(2);
            ville = curseur.getString(3);
            tel = curseur.getString(4);

            Entreprise uneEnt = new Entreprise(nom, adresse, cp, ville, tel);
            lesEntreprises.add(uneEnt);
            curseur.moveToNext();
        }

        curseur.close();
        close();
        return lesEntreprises;
    }
}

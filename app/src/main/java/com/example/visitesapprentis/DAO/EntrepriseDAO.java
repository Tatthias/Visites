package com.example.visitesapprentis.DAO;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.visitesapprentis.Metier.Apprenti;
import com.example.visitesapprentis.Metier.Entreprise;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EntrepriseDAO extends DAO<Entreprise>{

    private SQLiteVisitesApprentis dbVisiteEntreprise;
    private SQLiteDatabase db;

    //déclaration des outils nécessaire à la base
    private static final String TABLE_ENTREPRISE = "ENTREPRISE";
    private static final String COL_IDENTREPRISE = "idEnt";
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
        valeur.put(COL_IDENTREPRISE, obj.getIdEnt());
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
        valeur.put(COL_IDENTREPRISE, obj.getIdEnt());
        valeur.put(COL_NOMENTREPRISE, obj.getNomEnt());
        valeur.put(COL_ADRESSEENTREPRISE, obj.getAdresseEnt());
        valeur.put(COL_CPENTREPRISE, obj.getCpEnt());
        valeur.put(COL_VILLEENTREPRISE, obj.getVilleEnt());
        valeur.put(COL_TELENTREPRISE, obj.getTelEnt());

        Log.d("idEntDAO", String.valueOf(obj.getIdEnt()));
        db.update(TABLE_ENTREPRISE, valeur, COL_IDENTREPRISE + "=" +obj.getIdEnt(), null);
    }

    @Override
    public void delete(Entreprise obj) {
        db.delete(TABLE_ENTREPRISE, COL_NOMENTREPRISE + "=" + obj.getNomEnt(), null);
    }

    public Entreprise readPosition(int position) {
        Entreprise uneEnt;
        int id;
        String nom;
        String adresse;
        String ville;
        String cp;
        String tel;

        Cursor curseur = db.query(TABLE_ENTREPRISE, null, null, null, null, null, null);

        curseur.moveToFirst();
        curseur.moveToPosition(position);
        id = curseur.getInt(0);
        nom = curseur.getString(1);
        adresse = curseur.getString(2);
        cp = curseur.getString(3);
        ville = curseur.getString(4);
        tel = curseur.getString(5);

        uneEnt = new Entreprise(id, nom, adresse, cp, ville,tel);
        return uneEnt;
    }

    public List<Entreprise> read(){
        List<Entreprise> lesEntreprises = new ArrayList<>();
        int id;
        String nom;
        String adresse;
        String cp;
        String ville;
        String tel;

        Cursor curseur = db.query(TABLE_ENTREPRISE, null, null, null, null, null, null);
        curseur.moveToFirst();
        for (int i = 0; i < curseur.getCount(); i++){
            id = curseur.getInt(0);
            nom = curseur.getString(1);
            adresse = curseur.getString(2);
            cp = curseur.getString(3);
            ville = curseur.getString(4);
            tel = curseur.getString(5);

            Entreprise uneEnt = new Entreprise(id, nom, adresse, cp, ville, tel);
            lesEntreprises.add(uneEnt);
            curseur.moveToNext();
        }

        curseur.close();
        close();
        return lesEntreprises;
    }
}

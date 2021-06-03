package com.example.visitesapprentis.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.visitesapprentis.Metier.Entreprise;
import com.example.visitesapprentis.Metier.MaitreApprentissage;

import java.util.ArrayList;
import java.util.List;

public class MaitreAppDAO extends DAO<MaitreApprentissage>{

    private SQLiteVisitesApprentis dbVisiteApprenti;
    private SQLiteDatabase db;

    private static final String TABLE_MAITREAPP = "MAITREAPPRENTISSAGE";
    private static final String COL_ID_MAITREAPP = "idMai";
    private static final String COL_NOM_MAITREAPP = "nomMai";
    private static final String COL_PRENOM_MAITREAPP = "prenomMai";
    private static final String COL_ADRESSE_MAITREAPP = "addresseMai";
    private static final String COL_VILLE_MAITREAPP = "villeMai";
    private static final String COL_CP_MAITREAPP = "cpMai";
    private static final String COL_TEL_MAITREAPP = "telMai";
    private static final String COL_MAIL_MAITREAPP = "mailMai";
    private static final String COL_ID_ENTREPRISE_MAITREAPP = "idEnt";

    private static final String TABLE_ENTREPRISE = "ENTREPRISE";
    private static final String COL_IDENTREPRISE = "idEnt";
    private static final String COL_NOMENTREPRISE = "nomEnt";
    private static final String COL_ADRESSEENTREPRISE = "adresseEnt";
    private static final String COL_CPENTREPRISE = "cpEnt";
    private static final String COL_VILLEENTREPRISE = "villeEnt";
    private static final String COL_TELENTREPRISE = "telEnt";

    public MaitreAppDAO(Context context){dbVisiteApprenti = new SQLiteVisitesApprentis(context); }

    public void open() { db = dbVisiteApprenti.getWritableDatabase(); }

    public void close() {db.close(); }

    @Override
    public void insert(MaitreApprentissage obj) {
        ContentValues valeur = new ContentValues();
        valeur.put(COL_ID_MAITREAPP, obj.getIdMai());
        valeur.put(COL_NOM_MAITREAPP, obj.getNomMai());
        valeur.put(COL_PRENOM_MAITREAPP, obj.getPrenomMai());
        valeur.put(COL_ADRESSE_MAITREAPP, obj.getAddresseMai());
        valeur.put(COL_CP_MAITREAPP, obj.getCpMai());
        valeur.put(COL_VILLE_MAITREAPP, obj.getVilleMai());
        valeur.put(COL_TEL_MAITREAPP, obj.getTelMai());
        valeur.put(COL_MAIL_MAITREAPP, obj.getMailMai());
        valeur.put(COL_ID_ENTREPRISE_MAITREAPP, obj.getUneEnt().getIdEnt());

        db.insert(TABLE_MAITREAPP, null, valeur);
    }

    @Override
    public void update(MaitreApprentissage obj) {
        ContentValues valeur = new ContentValues();
        valeur.put(COL_ID_MAITREAPP, obj.getIdMai());
        valeur.put(COL_NOM_MAITREAPP, obj.getNomMai());
        valeur.put(COL_PRENOM_MAITREAPP, obj.getPrenomMai());
        valeur.put(COL_ADRESSE_MAITREAPP, obj.getAddresseMai());
        valeur.put(COL_CP_MAITREAPP, obj.getCpMai());
        valeur.put(COL_VILLE_MAITREAPP, obj.getVilleMai());
        valeur.put(COL_TEL_MAITREAPP, obj.getTelMai());
        valeur.put(COL_MAIL_MAITREAPP, obj.getMailMai());
        valeur.put(COL_ID_ENTREPRISE_MAITREAPP, obj.getUneEnt().getIdEnt());

        Log.d("idModifMai", String.valueOf(obj.getIdMai()));
        db.update(TABLE_MAITREAPP, valeur, COL_ID_MAITREAPP + "=" +obj.getIdMai(), null);
    }

    @Override
    public void delete(MaitreApprentissage obj) {
        Log.d("DeleteId",String.valueOf(obj.getIdMai()));
        db.delete(TABLE_MAITREAPP, COL_ID_MAITREAPP + "=" +obj.getIdMai(), null);
    }

    public List<MaitreApprentissage> read(){
        List<MaitreApprentissage> lesMaitres = new ArrayList<>();
        int idMai;
        String nom;
        String prenom;
        String adresse;
        String ville;
        String cp;
        String tel;
        String mail;
        int maiEnt;

        int idEnt;
        String nomEnt;
        String adresseEnt;
        String cpEnt;
        String villeEnt;
        String telEnt;


        Cursor curseur = db.query(TABLE_MAITREAPP, null, null, null, null, null, null);
        curseur.moveToFirst();
        for(int i = 0; i < curseur.getCount(); i++){
            idMai = curseur.getInt(0);
            nom = curseur.getString(1);
            prenom = curseur.getString(2);
            adresse = curseur.getString(3);
            ville = curseur.getString(4);
            cp = curseur.getString(5);
            tel = curseur.getString(6);
            mail = curseur.getString(7);
            maiEnt = curseur.getInt(8);
            Cursor curseurEnt = db.query(TABLE_ENTREPRISE, null, null, null, COL_IDENTREPRISE+ ", " +COL_NOMENTREPRISE + ", " +
                    COL_ADRESSEENTREPRISE + ", " + COL_CPENTREPRISE + ", " + COL_VILLEENTREPRISE + ", " + COL_TELENTREPRISE,
                    COL_IDENTREPRISE + "=" + maiEnt, null);
            curseurEnt.moveToFirst();
            for (int y = 0; y < curseurEnt.getCount(); y++){
                idEnt =  curseurEnt.getInt(0);
                nomEnt = curseurEnt.getString(1);
                adresseEnt = curseurEnt.getString(2);
                cpEnt = curseurEnt.getString(3);
                villeEnt = curseurEnt.getString(4);
                telEnt = curseurEnt.getString(5);

                Entreprise uneEnt = new Entreprise(idEnt, nomEnt, adresseEnt, cpEnt, villeEnt, telEnt);
                MaitreApprentissage unMai = new MaitreApprentissage(idMai, nom, prenom, adresse, cp, ville, tel, mail, uneEnt);
                lesMaitres.add(unMai);
            }
            curseurEnt.close();
            curseur.moveToNext();
        }
        curseur.close();
        close();
        return lesMaitres;
    }
}

package com.example.visitesapprentis.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.visitesapprentis.Metier.Referent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ReferentDAO {
    private SQLiteVisitesApprentis dbVisiteApprenti;
    private SQLiteDatabase db;

    //déclaration des outils nécessaire à la base
    private static final String TABLE_REFERENT = "REFERENT";
    private static final String COL_ID_REF = "idRef";
    private static final String COL_NOM_REF = "nomRef";
    private static final String COL_PRENOM_REF = "prenomRef";
    private static final String COL_ADDRESSE_REF = "addresseRef";
    private static final String COL_TEL_REF = "telRef";



    public ReferentDAO(Context context) {
        dbVisiteApprenti = new SQLiteVisitesApprentis(context);
    }

    public void open() {
        db = dbVisiteApprenti.getWritableDatabase();
    }

    public void close() {
        db.close();
    }

    public void insert(Referent Ref) {
        ContentValues valeur = new ContentValues();
        valeur.put(COL_ID_REF, Ref.getIdRef());
        valeur.put(COL_NOM_REF, Ref.getNomRef());
        valeur.put(COL_PRENOM_REF, Ref.getPrenomRef());
        valeur.put(COL_ADDRESSE_REF, Ref.getAddresseRef());
        valeur.put(COL_TEL_REF, Ref.getTelRef());

        db.insert(TABLE_REFERENT, null, valeur);
    }
    //insertion de l'apprenti dans la base

    public void update(Referent Ref) {
        ContentValues valeur = new ContentValues();
        valeur.put(COL_ID_REF, Ref.getIdRef());
        valeur.put(COL_NOM_REF, Ref.getNomRef());
        valeur.put(COL_PRENOM_REF, Ref.getPrenomRef());
        valeur.put(COL_ADDRESSE_REF, Ref.getAddresseRef());
        valeur.put(COL_TEL_REF, Ref.getTelRef());

        Log.d("idModif", String.valueOf(Ref.getIdRef()));

        db.update(TABLE_REFERENT, valeur, COL_ID_REF + "=" +Ref.getIdRef(), null);
    }
    //modification de l'apprenti

    public  void delete (Referent obj){
        db.delete(TABLE_REFERENT, COL_ID_REF + "=" +obj.getIdRef(),null);
        Log.d("DeleteId",String.valueOf(obj.getIdRef()));
    }
    //suppression de l'apprenti en fonction de son numero

    public Referent read(long id) {
        String nom;
        String prenom;
        String adresse;
        String tel;
        int idRef;
        Referent unRef;
        unRef = new Referent(0,null,null,null,null);


        Cursor curseurQuery = db.query(TABLE_REFERENT, null, COL_ID_REF+"="+id, null, null, null, null);
        curseurQuery.moveToFirst();
        Log.d("CurseurReadId",String.valueOf(curseurQuery.getCount()));
        if(!curseurQuery.isAfterLast())
        {
            idRef = curseurQuery.getInt(0);
            nom = curseurQuery.getString(1);
            prenom = curseurQuery.getString(2);
            adresse = curseurQuery.getString(3);
            tel = curseurQuery.getString(4);

            unRef = new Referent(idRef, nom, prenom, adresse, tel);
        }
        curseurQuery.close();
        return unRef;
    }
    //recherche le numéro de l'apprenti dans la base et la retourne

    public Referent readPosition(int id) {
        Referent unRef;
        String nom;
        String prenom;
        String adresse;
        String tel;

        int idRef;

        Cursor curseur = db.query(TABLE_REFERENT, null, null, null, null, null, null);

        curseur.moveToFirst();
        curseur.moveToPosition(id);
        idRef = curseur.getInt(0);
        nom = curseur.getString(1);
        prenom = curseur.getString(2);
        adresse = curseur.getString(3);
        tel = curseur.getString(4);


        unRef = new Referent(idRef, nom, prenom, adresse, tel);
        return unRef;
    }

    public List<Referent> read() {
        List<Referent> desReferents = new ArrayList<>();
        String nom;
        String prenom;
        String adresse;
        int idRef;
        String tel;

        Cursor curseur = db.query(TABLE_REFERENT, null, null, null, null, null, null);
        curseur.moveToFirst();
        for (int i = 0; i < curseur.getCount(); i++) {
            idRef = curseur.getInt(0);
            nom = curseur.getString(1);
            prenom = curseur.getString(2);
            adresse = curseur.getString(3);
            tel = curseur.getString(4);

            Referent unRef = new Referent(idRef, nom, prenom, adresse, tel);
            desReferents.add(unRef);
            curseur.moveToNext();
        }
        curseur.close();
        close();
        return desReferents;
    }

}

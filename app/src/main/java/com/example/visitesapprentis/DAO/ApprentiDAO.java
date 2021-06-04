package com.example.visitesapprentis.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.example.visitesapprentis.Metier.Apprenti;
import com.example.visitesapprentis.Metier.Referent;

public class ApprentiDAO extends DAO<Apprenti> {

    private SQLiteVisitesApprentis dbVisiteApprenti;
    private SQLiteDatabase db;

    //déclaration des outils nécessaire à la base
    private static final String TABLE_APPRENTI = "APPRENTI";
    private static final String COL_ID_APPRENTI = "idApp";
    private static final String COL_NOMAPPRENTI = "nomApp";
    private static final String COL_PRENOMAPPRENTI = "prenomApp";
    private static final String COL_ADRESSEAPPRENTI = "addresseApp";
    private static final String COL_VILLEAPPRENTI = "villeApp";
    private static final String COL_CPAPPRENTI = "cpApp";
    private static final String COL_TELAPPRENTI = "telApp";
    private static final String COL_DATEDEBUTAPPRENTI = "dateDebutApp";
    private static final String COL_CLASSEAPPRENTI = "classeApp";
    private static final String COL_MAILAPPRENTI = "mailApp";
    private static final String COL_REFAPPRENTI = "idRefApp";

    private static final String TABLE_REFERENT = "REFERENT";
    private static final String COL_ID_REF = "idRef";
    private static final String COL_NOM_REF = "nomRef";
    private static final String COL_PRENOM_REF = "prenomRef";
    private static final String COL_ADDRESSE_REF = "addresseRef";
    private static final String COL_TEL_REF = "telRef";


    private String getDateTime() {

        SimpleDateFormat dateFormat = new SimpleDateFormat(

                "yyyy/MM/dd ", Locale.getDefault());

        Date date = new Date();

        return dateFormat.format(date);

    }

    public ApprentiDAO(Context context) {
        dbVisiteApprenti = new SQLiteVisitesApprentis(context);
    }

    public void open() {
        db = dbVisiteApprenti.getWritableDatabase();
    }

    public void close() {
        db.close();
    }

    public void insert(Apprenti app) {
        ContentValues valeur = new ContentValues();
        valeur.put(COL_ID_APPRENTI, app.getIdApp());
        valeur.put(COL_NOMAPPRENTI, app.getNomApp());
        valeur.put(COL_PRENOMAPPRENTI, app.getPrenomApp());
        valeur.put(COL_ADRESSEAPPRENTI, app.getAddresseApp());
        valeur.put(COL_VILLEAPPRENTI, app.getVilleApp());
        valeur.put(COL_CPAPPRENTI, app.getCpApp());
        valeur.put(COL_TELAPPRENTI, app.getTelApp());
        valeur.put(COL_DATEDEBUTAPPRENTI,getDateTime());
        valeur.put(COL_CLASSEAPPRENTI, app.getClasseApp());
        valeur.put(COL_MAILAPPRENTI, app.getMailApp());
        valeur.put(COL_REFAPPRENTI, app.getUnReferent().getIdRef());

        db.insert(TABLE_APPRENTI, null, valeur);
    }
    //insertion de l'apprenti dans la base

    public void update(Apprenti app) {
        ContentValues valeur = new ContentValues();
        valeur.put(COL_ID_APPRENTI, app.getIdApp());
        valeur.put(COL_NOMAPPRENTI, app.getNomApp());
        valeur.put(COL_PRENOMAPPRENTI, app.getPrenomApp());
        valeur.put(COL_ADRESSEAPPRENTI, app.getAddresseApp());
        valeur.put(COL_VILLEAPPRENTI, app.getVilleApp());
        valeur.put(COL_CPAPPRENTI, app.getCpApp());
        valeur.put(COL_TELAPPRENTI, app.getTelApp());
        valeur.put(COL_DATEDEBUTAPPRENTI, String.valueOf(app.getDateDebutApp()));
        valeur.put(COL_CLASSEAPPRENTI, app.getClasseApp());
        valeur.put(COL_MAILAPPRENTI, app.getMailApp());
        valeur.put(COL_REFAPPRENTI, app.getUnReferent().getIdRef());


        Log.d("idModif", String.valueOf(app.getIdApp()));
        db.update(TABLE_APPRENTI, valeur, COL_ID_APPRENTI + "=" +app.getIdApp(), null);
    }
    //modification de l'apprenti

    public  void delete (Apprenti obj){
        Log.d("DeleteId",String.valueOf(obj.getIdApp()));
        db.delete(TABLE_APPRENTI, COL_ID_APPRENTI + "=" +obj.getIdApp(),null);
    }
    //suppression de l'apprenti en fonction de son numero

    public Apprenti read(long id) {
        String nomTable = TABLE_APPRENTI;
        String nom;
        String prenom;
        String adresse;
        String ville;
        String cp;
        String tel;
        String date;
        String classe;
        String mail;
        int idApp;
        Apprenti unApp;
        int idRefApp;

        String nomRef;
        String prenomRef;
        String adresseRef;
        String telRef;
        int idRef;

        unApp = new Apprenti(0,null,null,null,null,null,null,null,null,null,null);


        Cursor curseurQuery = db.query(nomTable, null, COL_ID_APPRENTI+"="+id, null, null, null, null);
        curseurQuery.moveToFirst();
        Log.d("CurseurReadId",String.valueOf(curseurQuery.getCount()));
        if(!curseurQuery.isAfterLast())
        {
            idApp = curseurQuery.getInt(0);
            nom = curseurQuery.getString(1);
            prenom = curseurQuery.getString(2);
            adresse = curseurQuery.getString(3);
            ville = curseurQuery.getString(4);
            cp = curseurQuery.getString(5);
            tel = curseurQuery.getString(6);
            date = curseurQuery.getString(7);
            classe = curseurQuery.getString(8);
            mail = curseurQuery.getString(9);
            idRefApp = curseurQuery.getInt(10);

            Cursor curseurRef = db.query(TABLE_REFERENT, null, null, null, COL_ID_REF+ ", " +COL_NOM_REF + ", " +
                            COL_PRENOM_REF + ", " + COL_ADDRESSE_REF + ", " + COL_TEL_REF ,
                    COL_ID_REF + "=" + idRefApp, null);
            curseurRef.moveToFirst();
            idRef =  curseurRef.getInt(0);
            nomRef = curseurRef.getString(1);
            prenomRef = curseurRef.getString(2);
            adresseRef = curseurRef.getString(3);
            telRef = curseurRef.getString(4);

            Date uneDate = null;
            try {
                uneDate = new SimpleDateFormat("yyyy/MM/dd").parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Referent unRef = new Referent(idRef,nomRef,prenomRef,adresseRef,telRef);
            unApp = new Apprenti(idApp, nom, prenom, adresse, ville, cp, tel, uneDate, classe, mail,unRef);

            curseurRef.close();
        }
        curseurQuery.close();
        return unApp;
    }
    //recherche le numéro de l'apprenti dans la base et la retourne

    public Apprenti readPosition(int id) {
        Apprenti unApp;
        String nom;
        String prenom;
        String adresse;
        String ville;
        String cp;
        String tel;
        String date;
        String classe;
        String mail;
        int idApp;
        int idRefApp;

        String nomRef;
        String prenomRef;
        String adresseRef;
        String telRef;
        int idRef;

        Cursor curseur = db.query(TABLE_APPRENTI, null, null, null, null, null, null);

        curseur.moveToFirst();
        curseur.moveToPosition(id);
        idApp = curseur.getInt(0);
        nom = curseur.getString(1);
        prenom = curseur.getString(2);
        adresse = curseur.getString(3);
        ville = curseur.getString(4);
        cp = curseur.getString(5);
        tel = curseur.getString(6);
        date = curseur.getString(7);
        classe = curseur.getString(8);
        mail = curseur.getString(9);
        idRefApp = curseur.getInt(10);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date uneDate = null;
        try {
            uneDate = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Cursor curseurRef = db.query(TABLE_REFERENT, null, null, null, COL_ID_REF+ ", " +COL_NOM_REF + ", " +
                        COL_PRENOM_REF + ", " + COL_ADDRESSE_REF + ", " + COL_TEL_REF ,
                COL_ID_REF + "=" + idRefApp, null);
        curseurRef.moveToFirst();
        idRef =  curseurRef.getInt(0);
        nomRef = curseurRef.getString(1);
        prenomRef = curseurRef.getString(2);
        adresseRef = curseurRef.getString(3);
        telRef = curseurRef.getString(4);

        Referent unRef = new Referent(idRef,nomRef,prenomRef,adresseRef,telRef);
        unApp = new Apprenti(idApp, nom, prenom, adresse, ville, cp, tel, uneDate, classe, mail,unRef);
        return unApp;
    }

    public List<Apprenti> read() {
        List<Apprenti> desApprentis = new ArrayList<>();
        String nom;
        String prenom;
        String adresse;
        String ville;
        String cp;
        String tel;
        String date;
        String classe;
        String mail;
        int idApp;
        int idRefApp;
        Apprenti unApp;

        String nomRef;
        String prenomRef;
        String adresseRef;
        String telRef;
        int idRef;


        Cursor curseur = db.query(TABLE_APPRENTI, null, null, null, null, null, null);
        curseur.moveToFirst();
        for (int i = 0; i < curseur.getCount(); i++) {
            idApp = curseur.getInt(0);
            nom = curseur.getString(1);
            prenom = curseur.getString(2);
            adresse = curseur.getString(3);
            ville = curseur.getString(4);
            cp = curseur.getString(5);
            tel = curseur.getString(6);
            date = curseur.getString(7);
            classe = curseur.getString(8);
            mail = curseur.getString(9);
            idRefApp = curseur.getInt(10);
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            Date uneDate = null;
            try {
                uneDate = formatter.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Cursor curseurRef = db.query(TABLE_REFERENT, null, null, null, COL_ID_REF+ ", " +COL_NOM_REF + ", " +
                            COL_PRENOM_REF + ", " + COL_ADDRESSE_REF + ", " + COL_TEL_REF ,
                    COL_ID_REF + "=" + idRefApp, null);
            curseurRef.moveToFirst();
            idRef =  curseurRef.getInt(0);
            nomRef = curseurRef.getString(1);
            prenomRef = curseurRef.getString(2);
            adresseRef = curseurRef.getString(3);
            telRef = curseurRef.getString(4);

            Referent unRef = new Referent(idRef,nomRef,prenomRef,adresseRef,telRef);
            unApp = new Apprenti(idApp, nom, prenom, adresse, ville, cp, tel, uneDate, classe, mail,unRef);
            curseurRef.close();
            curseur.moveToNext();
        }
        curseur.close();
        close();
        return desApprentis;
    }

}

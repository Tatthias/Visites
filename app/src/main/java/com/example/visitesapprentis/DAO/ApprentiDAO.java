package com.example.visitesapprentis.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
        valeur.put(COL_CPAPPRENTI, app.getClasseApp());
        valeur.put(COL_TELAPPRENTI, app.getTelApp());
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        valeur.put(COL_DATEDEBUTAPPRENTI,getDateTime());
        valeur.put(COL_CLASSEAPPRENTI, app.getClasseApp());
        valeur.put(COL_MAILAPPRENTI, app.getMailApp());

        db.insert(TABLE_APPRENTI, null, valeur);
    }
    //insertion de l'apprenti dans la base

    public void update(Apprenti app) {
        ContentValues valeur = new ContentValues();
        String clauseWhere = new String();
        db.update(app.getNomApp(), valeur, clauseWhere, null);
    }
    //modification de l'apprenti

    public void delete(Apprenti app) {
        String clauseWhere = new String();
        db.delete(app.getNomApp(), clauseWhere, null);
    }
    //suppression de l'apprenti en fonction de son numero

    public Apprenti read(long id) {
        String nomTable = TABLE_APPRENTI;
        String selection = "COL_ID_APPRENTI = id";
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

        Cursor curseurQuery = db.query(nomTable, null, selection, null, null, null, null);
        curseurQuery.moveToFirst();
        idApp = curseurQuery.getInt(1);
        nom = curseurQuery.getString(2);
        prenom = curseurQuery.getString(3);
        adresse = curseurQuery.getString(4);
        ville = curseurQuery.getString(5);
        cp = curseurQuery.getString(6);
        tel = curseurQuery.getString(7);
        date = curseurQuery.getString(8);
        classe = curseurQuery.getString(9);
        mail = curseurQuery.getString(10);
        Date uneDate = null;
        try {
            uneDate = new SimpleDateFormat("dd-mm-yyyy").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Apprenti unApp = new Apprenti(idApp, nom, prenom, adresse, ville, cp, tel, uneDate, classe, mail);
        curseurQuery.close();
        return (unApp);
    }
    //recherche le numéro de l'apprenti dans la base et la retourne

    public List<Apprenti> read() {
        String nomTable = TABLE_APPRENTI;
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
        boolean verif;

        Cursor curseur = db.query(nomTable, null, null, null, null, null, null);
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
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            Date uneDate = null;
            try {
                uneDate = formatter.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Apprenti unApp = new Apprenti(idApp, nom, prenom, adresse, ville, cp, tel, uneDate, classe, mail);
            desApprentis.add(unApp);
            curseur.moveToNext();
        }
        curseur.close();
        close();
        return desApprentis;
    }
}

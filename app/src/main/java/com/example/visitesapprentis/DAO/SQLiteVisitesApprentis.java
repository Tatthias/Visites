package com.example.visitesapprentis.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteVisitesApprentis extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "dbVisiteApprenti";
    private Context context = null;

    public SQLiteVisitesApprentis(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        try{
            db.execSQL("DROP TABLE IF EXISTS Entreprise");
            db.execSQL("CREATE TABLE Entreprise (idEnt INTEGER PRIMARY KEY AUTOINCREMENT,nomEnt VARCHAR(100), adresseEnt VARCHAR(100), cpEnt VARCHAR(100), villeEnt VARCHAR(100), telEnt CHAR(10))");
            db.execSQL("INSERT INTO Entreprise VALUES (1, 'Sarl Cym Developpement', '2 rue de la fontaine d Adam', '86202', 'Loudun', '0000000000')");
            db.execSQL("INSERT INTO Entreprise VALUES (2, 'Officentrale', '51 rue Chrales Gounod', '86200', 'loudun', '1111111111')");

            db.execSQL("DROP TABLE IF EXISTS Referent");
            db.execSQL("CREATE TABLE Referent (idRef INTEGER PRIMARY KEY AUTOINCREMENT, nomRef VARCHAR(100),  prenomRef VARCHAR(100), addresseRef VARCHAR(100),  telRef VARCHAR(10))");
            db.execSQL("INSERT INTO Referent VALUES (1, 'Pierre', 'Paul', 'loudun', '1111111111')");
            db.execSQL("INSERT INTO Referent VALUES (2, 'Jack', 'Daniels', 'loudun', '1111111111')");

            db.execSQL("DROP TABLE IF EXISTS MaitreApprentissage");
            db.execSQL("CREATE TABLE MaitreApprentissage (idMai INTEGER PRIMARY KEY AUTOINCREMENT, nomMai VARCHAR(100),  prenomMai VARCHAR(100), addresseMai VARCHAR(100)," +
                    " cpMai VARCHAR(100), villeMai VARCHAR(100), telMai VARCHAR(10), mailMai" +
                    " VARCHAR(100), idEnt INTEGER, FOREIGN KEY (idEnt) REFERENCES Entreprise (idEnt))");
            db.execSQL("INSERT INTO MaitreApprentissage VALUES (1, 'Benzema', 'Karim', '2 rue du but', '12345', 'Madrid', '1111111111', 'mail@cmail.com', 1)");
            db.execSQL("INSERT INTO MaitreApprentissage VALUES (2, 'Deschamp', 'Didier', '2 rue du Seine', '95000', 'Paris', '2222222222', 'mailDD@cmail.com', 2)");

            db.execSQL("DROP TABLE IF EXISTS Apprenti");
            db.execSQL("CREATE TABLE Apprenti (idApp INTEGER PRIMARY KEY AUTOINCREMENT, nomApp VARCHAR(100), prenomApp VARCHAR(100), addresseApp VARCHAR(100), " +
                    "villeApp VARCHAR(100), cpApp VARCHAR(100), telApp CHAR(10), dateDebutApp DATE, classeApp VARCHAR(20), mailApp VARCHAR(100), idRefApp INTEGER, " +
                    "idMaiApp INTEGER, FOREIGN KEY (idRefApp) REFERENCES Referent (idRef), FOREIGN KEY (idMaiApp) REFERENCES MaitreApprentissage (idMai))");
            db.execSQL("INSERT INTO Apprenti VALUES (1, 'Ribotto', 'Lucas', '8 rue Angers', 'Angers', '49200', '0101010101', '2018/09/01', 'STS2', 'lucasribotto@gmail.com',1, 1)");
            db.execSQL("INSERT INTO Apprenti VALUES (2, 'Mouchard', 'Maxime', '8 rue Niort', 'Niort', '37500', '0202020202', '2018/09/01', 'STS2', 'maximemouchard@gmail.com',2, 2)");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){
        onCreate(db);
    }
}

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
            db.execSQL("DROP TABLE IF EXISTS Apprenti");
            db.execSQL("CREATE TABLE Apprenti (idApp INTEGER PRIMARY KEY AUTOINCREMENT, nomApp VARCHAR(100), prenomApp VARCHAR(100), addresseApp VARCHAR(100), " +
                    "villeApp VARCHAR(100), cpApp VARCHAR(100), telApp CHAR(10), dateDebutApp DATE, classeApp VARCHAR(20), mailApp VARCHAR(100))");
            db.execSQL("INSERT INTO Apprenti VALUES (1, 'Ribotto', 'Lucas', '8 rue Angers', 'Angers', '49200', '0101010101', '2018/09/01', 'STS2', 'lucasribotto@gmail.com')");
            db.execSQL("INSERT INTO Apprenti VALUES (2, 'Mouchard', 'Maxime', '8 rue Niort', 'Niort', '37500', '0202020202', '2018/09/01', 'STS2', 'maximemouchard@gmail.com')");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){
        onCreate(db);
    }
}

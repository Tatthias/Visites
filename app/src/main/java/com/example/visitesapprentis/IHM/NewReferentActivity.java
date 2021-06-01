package com.example.visitesapprentis.IHM;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.visitesapprentis.DAO.ApprentiDAO;
import com.example.visitesapprentis.DAO.ReferentDAO;
import com.example.visitesapprentis.Metier.Apprenti;
import com.example.visitesapprentis.Metier.Referent;
import com.example.visitesapprentis.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewReferentActivity extends AppCompatActivity {

    private Button bAjouter;
    private Button bRetour;

    private Referent unRef;
    private ReferentDAO referentDAO;

    private EditText editNomRef;
    private EditText editPrenomRef;
    private EditText editAdresseRef;
    private EditText editTelRef;

    private int idRef;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_referent);

        bAjouter = (Button) findViewById(R.id.bAjouterRef);
        bAjouter.setOnClickListener(ajouterListener);

        bRetour = (Button) findViewById(R.id.bRetourRef);
        bRetour.setOnClickListener(retourListener);

        editNomRef = (EditText) findViewById(R.id.editNomRef);
        editPrenomRef = (EditText) findViewById(R.id.editPrenomRef);
        editAdresseRef  = (EditText) findViewById(R.id.editAdresseRef);
        editTelRef  = (EditText) findViewById(R.id.editTelRef);


        referentDAO = new ReferentDAO(getApplicationContext());
        referentDAO.open();
        for (Referent unRef : referentDAO.read()) {
            idRef = unRef.getIdRef() + 1;
        }
        referentDAO.close();
    }

    private View.OnClickListener retourListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(NewReferentActivity.this, ListeReferentActivity.class);
            startActivityForResult(intent, 0);
        }
    };

    private final View.OnClickListener ajouterListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            String nomRef = editNomRef.getText().toString();
            String prenomRef = editPrenomRef.getText().toString();
            String addresseRef = editAdresseRef.getText().toString();
            String telRef = editTelRef.getText().toString();



            unRef = new Referent(idRef, nomRef, prenomRef, addresseRef, telRef);

            referentDAO.open();
            referentDAO.insert(unRef);
            Log.d(String.valueOf(idRef), "ajout: ");
            referentDAO.close();

            finish();
            Intent intent = new Intent(NewReferentActivity.this, ListeReferentActivity.class);
            startActivityForResult(intent, 0);
        }

    };

}

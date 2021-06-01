package com.example.visitesapprentis.IHM;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.visitesapprentis.DAO.ApprentiDAO;
import com.example.visitesapprentis.DAO.EntrepriseDAO;
import com.example.visitesapprentis.Metier.Apprenti;
import com.example.visitesapprentis.Metier.Entreprise;
import com.example.visitesapprentis.R;

import java.util.Date;

public class NewEntrepriseActivity extends AppCompatActivity {

    private Button bAjouter;
    private Button bRetour;

    private Entreprise uneEnt;
    private EntrepriseDAO entrepriseDAO;

    private EditText editNomEnt;
    private EditText editRueEnt;
    private EditText editVilleEnt;
    private EditText editCPEnt;
    private EditText editTelEnt;

    private int idEnt;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entreprise);

        bAjouter = (Button) findViewById(R.id.bAjouterNewEnt);
        bAjouter.setOnClickListener(ajouterListener);

        bRetour = (Button) findViewById(R.id.bRetourNewEnt);
        bRetour.setOnClickListener(retourListener);

        editNomEnt = (EditText) findViewById(R.id.editNomNewEnt);
        editRueEnt  = (EditText) findViewById(R.id.editCpNewEnt);
        editVilleEnt  = (EditText) findViewById(R.id.editVilleNewEnt);
        editCPEnt  = (EditText) findViewById(R.id.editVilleNewEnt);
        editTelEnt  = (EditText) findViewById(R.id.editTelNewEnt);

        entrepriseDAO = new EntrepriseDAO(getApplicationContext());
        entrepriseDAO.open();
        for (Entreprise uneEnt : entrepriseDAO.read()) {
            idEnt = uneEnt.getIdEnt() + 1;
        }
        Log.d("idNewEnt", String.valueOf(idEnt));
        entrepriseDAO.close();
    }

    private View.OnClickListener retourListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(NewEntrepriseActivity.this, ListeEntrepriseActivity.class);
            startActivityForResult(intent, 0);
        }
    };

    private final View.OnClickListener ajouterListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            String nom = editNomEnt.getText().toString();
            String rue = editRueEnt.getText().toString();
            String cp = editCPEnt.getText().toString();
            String ville = editVilleEnt.getText().toString();
            String tel = editTelEnt.getText().toString();

            uneEnt = new Entreprise(idEnt, nom, rue, cp, ville, tel);

            entrepriseDAO.open();
            entrepriseDAO.insert(uneEnt);
            entrepriseDAO.close();

            finish();
            Intent intent = new Intent(NewEntrepriseActivity.this, ListeEntrepriseActivity.class);
            startActivityForResult(intent, 0);
        }

    };
}

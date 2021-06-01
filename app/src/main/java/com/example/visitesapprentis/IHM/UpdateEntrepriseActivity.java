package com.example.visitesapprentis.IHM;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.visitesapprentis.DAO.ApprentiDAO;
import com.example.visitesapprentis.DAO.EntrepriseDAO;
import com.example.visitesapprentis.Metier.Apprenti;
import com.example.visitesapprentis.Metier.Entreprise;
import com.example.visitesapprentis.R;

import java.util.Date;

public class UpdateEntrepriseActivity extends AppCompatActivity {

    private int idAppSupp;

    private Button bRetour;
    private Button bModifier;

    private Entreprise uneEnt;
    private EntrepriseDAO entrepriseDAO;
    private int position;

    private EditText editNomEnt;
    private EditText editRueEnt;
    private EditText editVilleEnt;
    private EditText editCPEnt;
    private EditText editTelEnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_entreprise);

        bRetour = (Button) findViewById(R.id.bRetourUpdateEnt);
        bRetour.setOnClickListener(retourListener);

        bModifier = (Button) findViewById(R.id.bModifierEnt);
        bModifier.setOnClickListener(modifierListener);

        Bundle extra = getIntent().getExtras();
        if(extra.getInt("position")>= 0)
        {
            position = extra.getInt("position");
        }

        entrepriseDAO = new EntrepriseDAO(getApplicationContext());
        entrepriseDAO.open();
        uneEnt = entrepriseDAO.readPosition(position);

        editNomEnt = (EditText) findViewById(R.id.editNomUpdateEnt);
        editNomEnt.setText(uneEnt.getNomEnt());
        editRueEnt  = (EditText) findViewById(R.id.editRueUpdateEnt);
        editRueEnt.setText(uneEnt.getAdresseEnt());
        editVilleEnt  = (EditText) findViewById(R.id.editVilleUpdateEnt);
        editVilleEnt.setText(uneEnt.getVilleEnt());
        editCPEnt  = (EditText) findViewById(R.id.editCpUpdateEnt);
        editCPEnt.setText(uneEnt.getCpEnt());
        editTelEnt  = (EditText) findViewById(R.id.editTelUpdateEnt);
        editTelEnt.setText(uneEnt.getTelEnt());

        entrepriseDAO.close();
    }

    private final View.OnClickListener retourListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(UpdateEntrepriseActivity.this, ListeEntrepriseActivity.class);
            intent.putExtra("position",position);
            startActivityForResult(intent, 0);
        }
    };

    private final View.OnClickListener modifierListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            String nom = editNomEnt.getText().toString();
            String addresse = editRueEnt.getText().toString();
            String ville = editVilleEnt.getText().toString();
            String cp = editCPEnt.getText().toString();
            String tel = editTelEnt.getText().toString();

            entrepriseDAO = new EntrepriseDAO(getApplicationContext());

            uneEnt = new Entreprise(position+2, nom, addresse, ville, cp, tel);

            Log.d("unePosition", String.valueOf(position));
            entrepriseDAO.open();
            entrepriseDAO.update(uneEnt);
            entrepriseDAO.close();

            Toast.makeText(getApplicationContext(), "Entreprise modifié", Toast.LENGTH_LONG).show();

            finish();
            Intent intent = new Intent(UpdateEntrepriseActivity.this, ListeEntrepriseActivity.class);
            intent.putExtra("position",position);
            startActivityForResult(intent, 0);
        }
    };
}
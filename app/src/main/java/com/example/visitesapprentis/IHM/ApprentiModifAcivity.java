package com.example.visitesapprentis.IHM;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.visitesapprentis.DAO.ApprentiDAO;
import com.example.visitesapprentis.Metier.Apprenti;
import com.example.visitesapprentis.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ApprentiModifAcivity extends AppCompatActivity {

    private int idAppSupp;

    private Button bRetour;
    private Button bModifier;

    private Apprenti unApp;
    private ApprentiDAO apprentiDAO;
    private int position;

    private EditText editNomApp;
    private EditText editPrenomApp;
    private EditText editRueApp;
    private EditText editVilleApp;
    private EditText editCPApp;
    private EditText editTelApp;
    private EditText editClasseApp;

    private EditText editMailApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apprenti_modif);

        bRetour = (Button) findViewById(R.id.bRetourAppModif);
        bRetour.setOnClickListener(retourListener);

        bModifier = (Button) findViewById(R.id.bModifierAppModif);
        bModifier.setOnClickListener(modifierListener);

        Bundle extra = getIntent().getExtras();
        if(extra.getInt("position")>= 0)
        {
            position = extra.getInt("position");
            Log.d("Count2",String.valueOf(position));
        }

        apprentiDAO = new ApprentiDAO(getApplicationContext());
        apprentiDAO.open();
        unApp = apprentiDAO.readPosition(position);

        editNomApp = (EditText) findViewById(R.id.editNomAppModif);
        editNomApp.setText(unApp.getNomApp());
        editPrenomApp = (EditText) findViewById(R.id.editPrenomAppModif);
        editPrenomApp.setText(unApp.getPrenomApp());
        editRueApp  = (EditText) findViewById(R.id.editRueAppModif);
        editRueApp.setText(unApp.getAddresseApp());
        editVilleApp  = (EditText) findViewById(R.id.editVilleAppModif);
        editVilleApp.setText(unApp.getVilleApp());
        editCPApp  = (EditText) findViewById(R.id.editCPAppModif);
        editCPApp.setText(unApp.getCpApp());
        editTelApp  = (EditText) findViewById(R.id.editTelAppModif);
        editTelApp.setText(unApp.getTelApp());
        editClasseApp  = (EditText) findViewById(R.id.editClasseAppModif);
        editClasseApp.setText(unApp.getClasseApp());
        editMailApp  = (EditText) findViewById(R.id.editMailAppModif);
        editMailApp.setText(unApp.getMailApp());


        apprentiDAO.close();
    }

    private final View.OnClickListener retourListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ApprentiModifAcivity.this, VisitesActivity.class);
            intent.putExtra("position",position);
            startActivityForResult(intent, 0);
        }
    };

    private final View.OnClickListener modifierListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            String nomApp = editNomApp.getText().toString();
            String prenomApp = editPrenomApp.getText().toString();
            String addresseApp = editRueApp.getText().toString();
            String villeApp = editVilleApp.getText().toString();
            String cpApp = editCPApp.getText().toString();
            String telApp = editTelApp.getText().toString();
            String classeApp = editClasseApp.getText().toString();
            String mailApp = editMailApp.getText().toString();

            Date laDate = unApp.getDateDebutApp();

            apprentiDAO = new ApprentiDAO(getApplicationContext());

            unApp = new Apprenti(position, nomApp, prenomApp, addresseApp, villeApp, cpApp, telApp, laDate, classeApp, mailApp);

            apprentiDAO.open();
            apprentiDAO.update(unApp);
            apprentiDAO.close();

            Toast.makeText(getApplicationContext(), "Apprenti modifié", Toast.LENGTH_LONG).show();

            Log.d("Fils de pute", String.valueOf(position));
            Log.d("ttes", String.valueOf(unApp.getIdApp()));

            finish();
            Intent intent = new Intent(ApprentiModifAcivity.this, VisitesActivity.class);
            intent.putExtra("position",position);
            startActivityForResult(intent, 0);
        }
    };
}

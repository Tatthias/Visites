package com.example.visitesapprentis.IHM;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.visitesapprentis.DAO.ApprentiDAO;
import com.example.visitesapprentis.Metier.Apprenti;
import com.example.visitesapprentis.R;

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

        bModifier = (Button) findViewById(R.id.bModifierApp);

        Bundle extra = getIntent().getExtras();
        if(extra.getInt("position")>= 0)
        {
            position = extra.getInt("position");
            Log.d("Count2",String.valueOf(position));
        }

        Log.d("TEST", "L'id de l'apprenti: " + String.valueOf(idAppSupp));

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

    private View.OnClickListener retourListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ApprentiModifAcivity.this, VisitesActivity.class);
            startActivityForResult(intent, 0);
        }
    };
}

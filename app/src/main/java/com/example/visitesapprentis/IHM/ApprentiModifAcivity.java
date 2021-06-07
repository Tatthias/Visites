package com.example.visitesapprentis.IHM;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.visitesapprentis.DAO.ApprentiDAO;
import com.example.visitesapprentis.DAO.EntrepriseDAO;
import com.example.visitesapprentis.DAO.MaitreAppDAO;
import com.example.visitesapprentis.DAO.ReferentDAO;
import com.example.visitesapprentis.Metier.Apprenti;
import com.example.visitesapprentis.Metier.Entreprise;
import com.example.visitesapprentis.Metier.MaitreApprentissage;
import com.example.visitesapprentis.Metier.Referent;
import com.example.visitesapprentis.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApprentiModifAcivity extends AppCompatActivity {

    private int idAppSupp;

    private Button bRetour;
    private Button bModifier;

    private Apprenti unApp;
    private ApprentiDAO apprentiDAO;
    private MaitreAppDAO maitreAppDAO;
    private MaitreApprentissage unMai;
    private ReferentDAO referentDAO;
    private Referent unRef;
    private int position;

    ListView listViewRef;
    ListView listViewMai;

    private EditText editNomApp;
    private EditText editPrenomApp;
    private EditText editRueApp;
    private EditText editVilleApp;
    private EditText editCPApp;
    private EditText editTelApp;
    private EditText editClasseApp;
    private EditText editRefApp;
    private EditText editMaiApp;

    private EditText editMailApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apprenti_modif);

        bRetour = (Button) findViewById(R.id.bRetourAppModif);
        bRetour.setOnClickListener(retourListener);

        bModifier = (Button) findViewById(R.id.bModifierAppModif);
        bModifier.setOnClickListener(modifierListener);

        listViewRef = (ListView) findViewById(R.id.listeRefAppUp);
        listViewMai = (ListView) findViewById(R.id.listeMaiAppUp);

        Bundle extra = getIntent().getExtras();
        if(extra.getInt("position")>= 0)
        {
            position = extra.getInt("position");
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
        editRefApp = (EditText) findViewById(R.id.editTextRefApp);
        editRefApp.setText(unApp.getUnReferent().toString());
        editMaiApp = (EditText) findViewById(R.id.editTextMaiApp);
        editMaiApp.setText(unApp.getUnMaître().toString());

        List<Referent> lesReferents = new ArrayList<>();
        referentDAO = new ReferentDAO(getApplicationContext());
        referentDAO.open();
        lesReferents = referentDAO.read();
        referentDAO.close();

        listViewRef.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                unRef = (Referent) listViewRef.getItemAtPosition(position);
            }
        });

        ArrayAdapter<Referent> arrayAdapterRef = new ArrayAdapter<Referent>(this, android.R.layout.simple_list_item_single_choice, lesReferents);
        listViewRef.setAdapter(arrayAdapterRef);

        List<MaitreApprentissage> lesMaitres = new ArrayList<>();
        maitreAppDAO = new MaitreAppDAO(getApplicationContext());
        maitreAppDAO.open();
        lesMaitres = maitreAppDAO.read();
        maitreAppDAO.close();

        listViewMai.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                unMai = (MaitreApprentissage) listViewMai.getItemAtPosition(position);
            }
        });

        ArrayAdapter<MaitreApprentissage> arrayAdapterMai = new ArrayAdapter<MaitreApprentissage>(this, android.R.layout.simple_list_item_single_choice, lesMaitres);
        listViewMai.setAdapter(arrayAdapterMai);

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

            unApp = new Apprenti(position+1, nomApp, prenomApp, addresseApp, villeApp, cpApp, telApp, laDate, classeApp, mailApp, unRef, unMai);

            Log.d("unePosition", String.valueOf(position));
            apprentiDAO.open();
            apprentiDAO.update(unApp);
            apprentiDAO.close();

            Toast.makeText(getApplicationContext(), "Apprenti modifié", Toast.LENGTH_LONG).show();

            finish();
            Intent intent = new Intent(ApprentiModifAcivity.this, VisitesActivity.class);
            intent.putExtra("position",position);
            startActivityForResult(intent, 0);
        }
    };
}

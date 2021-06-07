package com.example.visitesapprentis.IHM;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.visitesapprentis.DAO.ApprentiDAO;
import com.example.visitesapprentis.DAO.MaitreAppDAO;
import com.example.visitesapprentis.DAO.ReferentDAO;
import com.example.visitesapprentis.Metier.Apprenti;
import com.example.visitesapprentis.Metier.MaitreApprentissage;
import com.example.visitesapprentis.Metier.Referent;
import com.example.visitesapprentis.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewApprentiActivity extends AppCompatActivity {

    private Button bAjouter;
    private Button bRetour;

    private Apprenti unApp;
    private ApprentiDAO apprentiDAO;
    private MaitreAppDAO maitreAppDAO;
    private MaitreApprentissage unMai;
    private ReferentDAO referentDAO;
    private Referent unRef;

    ListView listViewRef;
    ListView listViewMai;

    private EditText editNomApp;
    private EditText editPrenomApp;
    private EditText editRueApp;
    private EditText editVilleApp;
    private EditText editCPApp;
    private EditText editTelApp;
    private EditText editClasseApp;
    private EditText editDateDebutApp;
    private EditText editMailApp;

    private int idApp;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apprenti);

        bAjouter = (Button) findViewById(R.id.bModifierAppModif);
        bAjouter.setOnClickListener(ajouterListener);

        bRetour = (Button) findViewById(R.id.bRetourAppModif);
        bRetour.setOnClickListener(retourListener);

        listViewRef = (ListView) findViewById(R.id.listeRefAppNew);
        listViewMai = (ListView) findViewById(R.id.listeMaiAppNew);

        editNomApp = (EditText) findViewById(R.id.editNomApp);
        editPrenomApp = (EditText) findViewById(R.id.editPrenomApp);
        editRueApp  = (EditText) findViewById(R.id.editRueApp);
        editVilleApp  = (EditText) findViewById(R.id.editVilleApp);
        editCPApp  = (EditText) findViewById(R.id.editCPApp);
        editTelApp  = (EditText) findViewById(R.id.editTelApp);
        editClasseApp  = (EditText) findViewById(R.id.editClasseApp);
        editMailApp  = (EditText) findViewById(R.id.editMailApp);

        apprentiDAO = new ApprentiDAO(getApplicationContext());
        apprentiDAO.open();
        for (Apprenti unApp : apprentiDAO.read()) {
            idApp = unApp.getIdApp() + 1;
        }
        apprentiDAO.close();

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
    }

    private View.OnClickListener retourListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(NewApprentiActivity.this, ListeApprentiActivity.class);
            startActivityForResult(intent, 0);
        }
    };

    private final View.OnClickListener ajouterListener = new View.OnClickListener() {
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

            Date laDate = new Date(31/05/2021);

            unApp = new Apprenti(idApp, nomApp, prenomApp, addresseApp, villeApp, cpApp, telApp, laDate, classeApp, mailApp, unRef, unMai);

            apprentiDAO.open();
            apprentiDAO.insert(unApp);
            apprentiDAO.close();

            finish();
            Intent intent = new Intent(NewApprentiActivity.this, ListeApprentiActivity.class);
            startActivityForResult(intent, 0);
        }

    };

    public Date StringToDate(String s){

        Date result = null;
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            result  = dateFormat.parse(s);
        }

        catch(ParseException e){
            e.printStackTrace();

        }
        return result ;
    };
}

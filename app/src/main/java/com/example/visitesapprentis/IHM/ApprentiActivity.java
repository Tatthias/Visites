package com.example.visitesapprentis.IHM;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.visitesapprentis.DAO.ApprentiDAO;
import com.example.visitesapprentis.Metier.Apprenti;
import com.example.visitesapprentis.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ApprentiActivity extends AppCompatActivity {

    private Button bAjouter;
    private Button bRetour;

    private Apprenti unApp;
    private ApprentiDAO apprentiDAO;

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

        editNomApp = (EditText) findViewById(R.id.editNomApp);
        editPrenomApp = (EditText) findViewById(R.id.editPrenomApp);
        editRueApp  = (EditText) findViewById(R.id.editRueApp);
        editVilleApp  = (EditText) findViewById(R.id.editVilleApp);
        editCPApp  = (EditText) findViewById(R.id.editCPApp);
        editTelApp  = (EditText) findViewById(R.id.editTelApp);
        editDateDebutApp  = (EditText) findViewById(R.id.editDateDebutApp);
        editClasseApp  = (EditText) findViewById(R.id.editClasseApp);
        editMailApp  = (EditText) findViewById(R.id.editMailApp);

        apprentiDAO = new ApprentiDAO(getApplicationContext());
        apprentiDAO.open();


        for (Apprenti unApp : apprentiDAO.read()) {
            idApp = unApp.getIdApp() + 1;
        }
        apprentiDAO.close();
    }

    private View.OnClickListener retourListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ApprentiActivity.this, MainActivity.class);
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
            String dateDebutApp = editNomApp.getText().toString();
            String classeApp = editNomApp.getText().toString();
            String mailApp = editNomApp.getText().toString();

            Date ladate = StringToDate(dateDebutApp);

            unApp = new Apprenti(idApp, nomApp, prenomApp, addresseApp, villeApp, cpApp, telApp, ladate, classeApp, mailApp);

            apprentiDAO.open();
            apprentiDAO.insert(unApp);
            apprentiDAO.close();

            finish();
            Intent intent = new Intent(ApprentiActivity.this, MainActivity.class);
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

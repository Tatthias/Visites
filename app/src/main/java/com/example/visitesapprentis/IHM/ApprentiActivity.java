package com.example.visitesapprentis.IHM;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

        bAjouter = (Button) findViewById(R.id.bAjouterApp);
        bAjouter.setOnClickListener(ajouterListener);

        bRetour = (Button) findViewById(R.id.bRetourApp);
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

    }

    private View.OnClickListener retourListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ApprentiActivity.this, MainActivity.class);
            startActivityForResult(intent, 0);
        }
    };

    private View.OnClickListener ajouterListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            String nomApp = editNomApp.getText().toString();
            String prenomApp = editPrenomApp.getText().toString();
            String rueApp = editRueApp.getText().toString();
            String villeApp = editVilleApp.getText().toString();
            String cpApp = editCPApp.getText().toString();
            String telApp = editTelApp.getText().toString();
            String dateDebutApp = editNomApp.getText().toString();
            String classeApp = editNomApp.getText().toString();
            String mailApp = editNomApp.getText().toString();

            SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");
            Date uneDate = new Date(20-05-2000);
            try {
                uneDate = format.parse(dateDebutApp);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            ApprentiDAO apprentiDAO = new ApprentiDAO(getApplicationContext());
            apprentiDAO.open();
            for(Apprenti unApp : apprentiDAO.read()){
                idApp = unApp.getIdApp()+1;
            }

            unApp = new Apprenti(idApp, nomApp, prenomApp, rueApp, villeApp, cpApp, telApp, uneDate, classeApp, mailApp);

            apprentiDAO.insert(unApp);

            apprentiDAO.close();

            finish();
            startActivity(getIntent());
        }
    };
}

package com.example.visitesapprentis.IHM;

import android.content.DialogInterface;
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
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.visitesapprentis.DAO.EntrepriseDAO;
import com.example.visitesapprentis.DAO.MaitreAppDAO;
import com.example.visitesapprentis.Metier.Entreprise;
import com.example.visitesapprentis.Metier.MaitreApprentissage;
import com.example.visitesapprentis.R;

import java.util.ArrayList;
import java.util.List;

public class UpdateMaitreActivity extends AppCompatActivity {

    private Button bModifier;
    private Button bRetour;
    private Button bSupprimer;

    ListView listView;

    private MaitreApprentissage unMai;
    private MaitreAppDAO maitreAppDAO;
    private EntrepriseDAO entrepriseDAO;
    private Entreprise uneEnt;
    private int position;

    private EditText editNomMai;
    private EditText editPrenomMai;
    private EditText editRueMai;
    private EditText editCpMai;
    private EditText editVilleMai;
    private EditText editTelMai;
    private EditText editMailMai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_maitre);

        listView = (ListView) findViewById(R.id.listeEntMaiUp);

        Bundle extra = getIntent().getExtras();
        if(extra.getInt("position")>= 0)
        {
            position = extra.getInt("position");
        }

        maitreAppDAO = new MaitreAppDAO(getApplicationContext());
        maitreAppDAO.open();
        unMai = maitreAppDAO.readPosition(position);

        editNomMai = (EditText) findViewById(R.id.editNomMaiUp);
        editNomMai.setText(unMai.getNomMai());
        editPrenomMai = (EditText) findViewById(R.id.editPrenomMaiUp);
        editPrenomMai.setText(unMai.getPrenomMai());
        editRueMai = (EditText) findViewById(R.id.editRueMaiUp);
        editRueMai.setText(unMai.getAddresseMai());
        editCpMai = (EditText) findViewById(R.id.editCpMaiUp);
        editCpMai.setText(unMai.getCpMai());
        editVilleMai = (EditText) findViewById(R.id.editVilleMaiUp);
        editVilleMai.setText(unMai.getVilleMai());
        editTelMai = (EditText) findViewById(R.id.editTelMaiUp);
        editTelMai.setText(unMai.getTelMai());
        editMailMai = (EditText) findViewById(R.id.editMailMaiUp);
        editMailMai.setText(unMai.getMailMai());

        List<Entreprise> lesEntreprises = new ArrayList<>();
        entrepriseDAO = new EntrepriseDAO(getApplicationContext());
        entrepriseDAO.open();
        lesEntreprises = entrepriseDAO.read();
        entrepriseDAO.close();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                uneEnt = (Entreprise) listView.getItemAtPosition(position);
                for(int i = 0; i < listView.getChildCount(); i++){
                    if(position == i){
                        listView.getChildAt(i).setBackgroundColor(Color.parseColor("#93D152"));
                    }else{
                        listView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                    }
                }
            }
        });

        ArrayAdapter<Entreprise> arrayAdapter = new ArrayAdapter<Entreprise>(this, android.R.layout.simple_list_item_1, lesEntreprises);
        listView.setAdapter(arrayAdapter);

        maitreAppDAO.close();

        bModifier = (Button) findViewById(R.id.bAjouterUpMai);
        bModifier.setOnClickListener(modifierListener);

        bRetour = (Button) findViewById(R.id.bRetourUpMai);
        bRetour.setOnClickListener(retourListener);

        bSupprimer = (Button) findViewById(R.id.bSupprimerMai);
        bSupprimer.setOnClickListener(suppressionListener);
    }

    private final View.OnClickListener retourListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(UpdateMaitreActivity.this, ListeMaitreAppActivity.class);
            startActivityForResult(intent, 0);
        }
    };

    private final View.OnClickListener modifierListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            String nom = editNomMai.getText().toString();
            String prenom = editPrenomMai.getText().toString();
            String addresse = editRueMai.getText().toString();
            String ville = editVilleMai.getText().toString();
            String cp = editCpMai.getText().toString();
            String tel = editTelMai.getText().toString();
            String mail = editMailMai.getText().toString();

            maitreAppDAO = new MaitreAppDAO(getApplicationContext());

            unMai = new MaitreApprentissage(position+1, nom, prenom, addresse, cp, ville, tel, mail, uneEnt);

            maitreAppDAO.open();
            maitreAppDAO.update(unMai);
            maitreAppDAO.close();

            Toast.makeText(getApplicationContext(), "Maître d'apprentissage modifié", Toast.LENGTH_LONG).show();

            finish();
            Intent intent = new Intent(UpdateMaitreActivity.this, ListeMaitreAppActivity.class);
            intent.putExtra("position",position);
            startActivityForResult(intent, 0);
        }
    };

    private View.OnClickListener suppressionListener = new View.OnClickListener() {

        public void onClick(View v) {
            maitreAppDAO = new MaitreAppDAO(getApplicationContext());
            maitreAppDAO.open();
            unMai = maitreAppDAO.readPosition(position);
            AlertDialog.Builder builder = new AlertDialog.Builder(UpdateMaitreActivity.this);
            builder.setTitle(R.string.app_name);
            builder.setIcon(R.mipmap.ic_launcher);
            builder.setMessage("Voulez-vous vraiment supprimer le maître d'apprentissage ?")
                    .setCancelable(false)
                    .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            maitreAppDAO.delete(unMai);
                            maitreAppDAO.close();
                            finish();
                            Intent intent = new Intent(UpdateMaitreActivity.this, ListeMaitreAppActivity.class);
                            startActivityForResult(intent, 0);
                        }
                    })
                    .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();

        }
    };
}

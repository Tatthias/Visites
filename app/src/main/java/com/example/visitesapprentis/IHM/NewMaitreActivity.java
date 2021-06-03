package com.example.visitesapprentis.IHM;

import android.annotation.SuppressLint;
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

import com.example.visitesapprentis.DAO.EntrepriseDAO;
import com.example.visitesapprentis.DAO.MaitreAppDAO;
import com.example.visitesapprentis.Metier.Entreprise;
import com.example.visitesapprentis.Metier.MaitreApprentissage;
import com.example.visitesapprentis.R;

import java.util.ArrayList;
import java.util.List;

public class NewMaitreActivity extends AppCompatActivity {

    private Button bAjouter;
    private Button bRetour;

    ListView listView;

    private MaitreApprentissage unMai;
    private MaitreAppDAO maitreAppDAO;
    private EntrepriseDAO entrepriseDAO;
    private Entreprise uneEnt;
    private int idMai;

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
        setContentView(R.layout.activity_new_maitre);

        listView = (ListView) findViewById(R.id.listeEntMai);

        editNomMai = (EditText) findViewById(R.id.editNomMaiNew);
        editPrenomMai = (EditText) findViewById(R.id.editPrenomMaiNew);
        editRueMai = (EditText) findViewById(R.id.editRueMaiNew);
        editCpMai = (EditText) findViewById(R.id.editCpMaiNew);
        editVilleMai = (EditText) findViewById(R.id.editVilleMaiNew);
        editTelMai = (EditText) findViewById(R.id.editTelMaiNew);
        editMailMai = (EditText) findViewById(R.id.editMailMaiNew);

        maitreAppDAO = new MaitreAppDAO(getApplicationContext());
        maitreAppDAO.open();
        for (MaitreApprentissage unMai : maitreAppDAO.read()) {
            idMai = unMai.getIdMai() + 1;
        }
        maitreAppDAO.close();


        List<Entreprise> lesEntreprises = new ArrayList<>();
        EntrepriseDAO entrepriseDAO = new EntrepriseDAO(getApplicationContext());
        entrepriseDAO.open();
        lesEntreprises = entrepriseDAO.read();
        entrepriseDAO.close();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                uneEnt = (Entreprise) listView.getItemAtPosition(position);
                Log.d("Ent selectionné", String.valueOf(uneEnt));
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

        bAjouter = (Button) findViewById(R.id.bAjouterNewMai);
        bAjouter.setOnClickListener(ajouterListener);

        bRetour = (Button) findViewById(R.id.bRetourNewMai);
        bRetour.setOnClickListener(retourListener);
    }

    private final View.OnClickListener retourListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(NewMaitreActivity.this, ListeMaitreAppActivity.class);
            startActivityForResult(intent, 0);
        }
    };

    private final View.OnClickListener ajouterListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String nom = editNomMai.getText().toString();
            String prenom = editPrenomMai.getText().toString();
            String rue = editRueMai.getText().toString();
            String cp = editCpMai.getText().toString();
            String ville = editVilleMai.getText().toString();
            String tel = editTelMai.getText().toString();
            String mail = editMailMai.getText().toString();

            unMai = new MaitreApprentissage(idMai, nom, prenom, rue, cp, ville, tel, mail, uneEnt);

            maitreAppDAO.open();
            maitreAppDAO.insert(unMai);
            maitreAppDAO.close();

            finish();
            Intent intent = new Intent(NewMaitreActivity.this, ListeMaitreAppActivity.class);
            startActivityForResult(intent, 0);
        }

    };
}

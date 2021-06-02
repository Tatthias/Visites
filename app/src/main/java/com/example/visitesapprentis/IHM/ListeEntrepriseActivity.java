package com.example.visitesapprentis.IHM;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.visitesapprentis.DAO.EntrepriseDAO;
import com.example.visitesapprentis.Metier.Entreprise;
import com.example.visitesapprentis.R;

import java.util.ArrayList;
import java.util.List;

public class ListeEntrepriseActivity extends AppCompatActivity {


    private Button bAjouter;
    private Button bRetour;

    ListView listView;

    private EntrepriseDAO entrepriseDAO;
    private int idEnt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listeent);

        listView = (ListView) findViewById(R.id.listeEntreprise);

        entrepriseDAO = new EntrepriseDAO(getApplicationContext());
        entrepriseDAO.open();
        for(Entreprise uneEnt : entrepriseDAO.read()){
            idEnt = uneEnt.getIdEnt() + 1;
        }
        entrepriseDAO.close();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent intent = new Intent(ListeEntrepriseActivity.this, UpdateEntrepriseActivity.class);

                intent.putExtra("position",position);
                Log.d("idEnt",String.valueOf(position));
                startActivityForResult(intent,0);
            }

        });

        List<Entreprise> lesEntreprises = new ArrayList<>();
        EntrepriseDAO entrepriseDAO = new EntrepriseDAO(getApplicationContext());
        entrepriseDAO.open();
        lesEntreprises = entrepriseDAO.read();
        entrepriseDAO.close();

        ArrayAdapter<Entreprise> arrayAdapter = new ArrayAdapter<Entreprise>(this, android.R.layout.simple_list_item_1, lesEntreprises);
        listView.setAdapter(arrayAdapter);

        bAjouter = (Button) findViewById(R.id.bAjouterEnt);
        bAjouter.setOnClickListener(ajouterListener);

        bRetour = (Button) findViewById(R.id.bRetour);
        bRetour.setOnClickListener(retourListener);
    }

    private View.OnClickListener retourListener = new View.OnClickListener() {
        public void onClick(View view) {
            Intent intent = new Intent( ListeEntrepriseActivity.this, MainActivity.class);
            startActivityForResult(intent, 0);
        }
    };

    private View.OnClickListener ajouterListener = new View.OnClickListener() {
        public void onClick(View view) {
            Intent intent = new Intent(ListeEntrepriseActivity.this, NewEntrepriseActivity.class);
            startActivityForResult(intent, 0);
        }
    };
}

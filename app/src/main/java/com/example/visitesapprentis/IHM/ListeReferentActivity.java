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

import com.example.visitesapprentis.DAO.ApprentiDAO;
import com.example.visitesapprentis.DAO.EntrepriseDAO;
import com.example.visitesapprentis.DAO.ReferentDAO;
import com.example.visitesapprentis.Metier.Apprenti;
import com.example.visitesapprentis.Metier.Entreprise;
import com.example.visitesapprentis.Metier.Referent;
import com.example.visitesapprentis.R;

import java.util.ArrayList;
import java.util.List;

public class ListeReferentActivity extends AppCompatActivity {
    private Button bAjouter;
    private Button bRetour;


    ListView listView;

    private ReferentDAO referentDAO ;
    private String nomRef;
    private int idRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listeref);

        listView = (ListView) findViewById(R.id.listeReferent);
        referentDAO = new ReferentDAO(getApplicationContext());
        referentDAO.open();
        for (Referent unRef : referentDAO.read()) {
            idRef = unRef.getIdRef() + 1;
        }
        referentDAO.close();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent intent = new Intent(ListeReferentActivity.this, ModifReferentActivity.class);

                intent.putExtra("position",position);
                Log.d("idRef",String.valueOf(position));
                startActivityForResult(intent,0);
            }

        });
        List<Referent> lesReferents = new ArrayList<>();
        ReferentDAO referentDAO = new ReferentDAO(getApplicationContext());
        referentDAO.open();
        lesReferents = referentDAO.read();
        referentDAO.close();

        ArrayAdapter<Referent> arrayAdapter = new ArrayAdapter<Referent>(this, android.R.layout.simple_list_item_1, lesReferents);

        listView.setAdapter(arrayAdapter);

        bAjouter = (Button) findViewById(R.id.bAjouterRef);
        bAjouter.setOnClickListener(ajouterListener);

        bRetour = (Button) findViewById(R.id.bRetour);
        bRetour.setOnClickListener(retourListener);

    }

    private View.OnClickListener retourListener = new View.OnClickListener() {
        public void onClick(View view) {
            Intent intent = new Intent( ListeReferentActivity.this, MainActivity.class);
            startActivityForResult(intent, 0);
        }
    };

    private View.OnClickListener ajouterListener = new View.OnClickListener() {
        public void onClick(View view) {
            Intent intent = new Intent(ListeReferentActivity.this, NewReferentActivity.class);
            startActivityForResult(intent, 0);
        }
    };

}

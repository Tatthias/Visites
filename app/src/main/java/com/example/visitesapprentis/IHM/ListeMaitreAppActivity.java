package com.example.visitesapprentis.IHM;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.visitesapprentis.DAO.EntrepriseDAO;
import com.example.visitesapprentis.DAO.MaitreAppDAO;
import com.example.visitesapprentis.Metier.Entreprise;
import com.example.visitesapprentis.Metier.MaitreApprentissage;
import com.example.visitesapprentis.R;

import java.util.ArrayList;
import java.util.List;

public class ListeMaitreAppActivity extends AppCompatActivity {

    private Button bAjouter;
    private Button bRetour;

    ListView listView;

    private MaitreAppDAO maitreAppDAO;
    private int idMai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listemai);

        listView = (ListView) findViewById(R.id.listeMaitreApp);

        maitreAppDAO = new MaitreAppDAO(getApplicationContext());
        maitreAppDAO.open();
        for(MaitreApprentissage unMai : maitreAppDAO.read()){
            idMai = unMai.getIdMai() + 1;
        }
        maitreAppDAO.close();

        List<MaitreApprentissage> lesMaitres = new ArrayList<>();
        MaitreAppDAO maitreAppDAO = new MaitreAppDAO(getApplicationContext());
        maitreAppDAO.open();
        lesMaitres = maitreAppDAO.read();
        maitreAppDAO.close();

        ArrayAdapter<MaitreApprentissage> arrayAdapter = new ArrayAdapter<MaitreApprentissage>(this, android.R.layout.simple_list_item_1, lesMaitres);
        listView.setAdapter(arrayAdapter);

        bAjouter = (Button) findViewById(R.id.bAjouterMaiApp);
        bAjouter.setOnClickListener(ajouterListener);

        bRetour = (Button) findViewById(R.id.bRetourMaiApp);
        bRetour.setOnClickListener(retourListener);
    }

    private View.OnClickListener retourListener = new View.OnClickListener() {
        public void onClick(View view) {
            Intent intent = new Intent( ListeMaitreAppActivity.this, MainActivity.class);
            startActivityForResult(intent, 0);
        }
    };

    private View.OnClickListener ajouterListener = new View.OnClickListener() {
        public void onClick(View view) {
            Intent intent = new Intent(ListeMaitreAppActivity.this, NewMaitreActivity.class);
            startActivityForResult(intent, 0);
        }
    };
}

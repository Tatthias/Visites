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
import com.example.visitesapprentis.Metier.Apprenti;
import com.example.visitesapprentis.R;

import java.util.ArrayList;
import java.util.List;

public class ListeApprentiActivity extends AppCompatActivity {


    private Button bAjouter;
    private Button bRetour;

    ListView listView;

    private ApprentiDAO apprentiDAO;
    private int idApp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listeapp);

        listView = (ListView) findViewById(R.id.listeVisites);

        apprentiDAO = new ApprentiDAO(getApplicationContext());
        apprentiDAO.open();
        for (Apprenti unApp : apprentiDAO.read()) {
            idApp = unApp.getIdApp() + 1;
        }
        apprentiDAO.close();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                    Intent intent = new Intent(ListeApprentiActivity.this, VisitesActivity.class);

                    intent.putExtra("position",position);
                    Log.d("idApp",String.valueOf(position));
                    startActivityForResult(intent,0);
            }

        });
        List<Apprenti> lesApprentis = new ArrayList<>();
        ApprentiDAO apprentiDAO = new ApprentiDAO(getApplicationContext());
        apprentiDAO.open();
        lesApprentis = apprentiDAO.read();
        apprentiDAO.close();

        ArrayAdapter<Apprenti> arrayAdapter = new ArrayAdapter<Apprenti>(this, android.R.layout.simple_list_item_1, lesApprentis);

        listView.setAdapter(arrayAdapter);

        bAjouter = (Button) findViewById(R.id.bModifierApp);
        bAjouter.setOnClickListener(ajouterListener);

        bRetour = (Button) findViewById(R.id.bRetour);
        bRetour.setOnClickListener(retourListener);
    }

    private View.OnClickListener retourListener = new View.OnClickListener() {
        public void onClick(View view) {
            Intent intent = new Intent( ListeApprentiActivity.this, MainActivity.class);
            startActivityForResult(intent, 0);
        }
    };

    private View.OnClickListener ajouterListener = new View.OnClickListener() {
        public void onClick(View view) {
            Intent intent = new Intent(ListeApprentiActivity.this, ApprentiActivity.class);
            startActivityForResult(intent, 0);
        }
    };
}

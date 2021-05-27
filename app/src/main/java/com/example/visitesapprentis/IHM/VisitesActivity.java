package com.example.visitesapprentis.IHM;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

import com.example.visitesapprentis.DAO.ApprentiDAO;
import com.example.visitesapprentis.Metier.Apprenti;
import com.example.visitesapprentis.R;


public class VisitesActivity extends AppCompatActivity {


    private int idAppSupp;
    private Apprenti unApp;
    private ApprentiDAO apprentiDAO;
    private Button bSuppression;

    private Button bRetour;
    private Button bModifier;
    private Button bSupprimer;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visites);
        bSuppression= (Button) findViewById(R.id.bSupprimerApp);
        bSuppression.setOnClickListener(suppressionListener);

        Bundle extra = getIntent().getExtras();
        if(extra.getInt("id")>= 0)
        {
            idAppSupp = extra.getInt("id");
            Log.d("Count2",String.valueOf(idAppSupp));
        }

        bRetour = (Button) findViewById(R.id.bRetourApp2);
        bRetour.setOnClickListener(retourListener);

        bModifier = (Button) findViewById(R.id.bModifierApp);

        bSupprimer = (Button) findViewById(R.id.bSupprimerApp);
    }

    private View.OnClickListener retourListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(VisitesActivity.this, MainActivity.class);
            startActivityForResult(intent, 0);
        }
    };



        TextView prenom = (TextView)findViewById(R.id.TextViewPrenom);


    private final View.OnClickListener modifier = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
        }

    };
    private View.OnClickListener suppressionListener = new View.OnClickListener() {

        public void onClick(View v) {
            apprentiDAO = new ApprentiDAO(getApplicationContext());
            apprentiDAO.open();
            unApp = apprentiDAO.read(idAppSupp);

            apprentiDAO.delete(unApp);
            apprentiDAO.close();
            finish();
            Intent intent = new Intent(VisitesActivity.this, MainActivity.class);
            startActivityForResult(intent, 0);
        }
    };

}

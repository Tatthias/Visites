package com.example.visitesapprentis.IHM;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.visitesapprentis.DAO.ApprentiDAO;
import com.example.visitesapprentis.Metier.Apprenti;
import com.example.visitesapprentis.R;


public class VisitesActivity extends AppCompatActivity {


    private int position;
    private Apprenti unApp;
    private ApprentiDAO apprentiDAO;
    private Button bSuppression;

    private Button bRetour;
    private Button bModifier;
    private Button bSupprimer;

    private TextView textView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visites);

        bSuppression = (Button) findViewById(R.id.bSupprimerApp);
        bSuppression.setOnClickListener(suppressionListener);

        bRetour = (Button) findViewById(R.id.bRetourApp2);
        bRetour.setOnClickListener(retourListener);

        bModifier = (Button) findViewById(R.id.bModifierEnt);
        bModifier.setOnClickListener(modifierListener);

        textView = (TextView) findViewById(R.id.textView2);

        Bundle extra = getIntent().getExtras();
        if(extra.getInt("position")>= 0)
        {
            position = extra.getInt("position");
        }
        apprentiDAO = new ApprentiDAO(getApplicationContext());
        apprentiDAO.open();
        unApp = apprentiDAO.readPosition(position);
        textView.setText("Liste des visites de "+ unApp.getNomApp() + " " + unApp.getPrenomApp() + " :");
        apprentiDAO.close();
    }

    private View.OnClickListener retourListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(VisitesActivity.this, ListeApprentiActivity.class);
            startActivityForResult(intent, 0);
        }
    };

    private final View.OnClickListener modifierListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent( VisitesActivity.this, ApprentiModifAcivity.class);
            intent.putExtra("position",position);
            startActivityForResult(intent, 0);
        }
    };

    private View.OnClickListener suppressionListener = new View.OnClickListener() {

        public void onClick(View v) {
            apprentiDAO = new ApprentiDAO(getApplicationContext());
            apprentiDAO.open();
            unApp = apprentiDAO.readPosition(position);
            AlertDialog.Builder builder = new AlertDialog.Builder(VisitesActivity.this);
            builder.setTitle(R.string.app_name);
            builder.setIcon(R.mipmap.ic_launcher);
            builder.setMessage("Voulez-vous vraiment supprimer l'apprenti?")
                    .setCancelable(false)
                    .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            apprentiDAO.delete(unApp);
                            apprentiDAO.close();
                            finish();
                            Intent intent = new Intent(VisitesActivity.this, ListeApprentiActivity.class);
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

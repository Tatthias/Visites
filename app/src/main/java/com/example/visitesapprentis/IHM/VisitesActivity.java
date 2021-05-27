package com.example.visitesapprentis.IHM;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.visitesapprentis.R;

public class VisitesActivity extends AppCompatActivity {

    private Button bRetour;
    private Button bModifier;
    private Button bSupprimer;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visites);

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

    private final View.OnClickListener modifier = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent()
        }
    }
}

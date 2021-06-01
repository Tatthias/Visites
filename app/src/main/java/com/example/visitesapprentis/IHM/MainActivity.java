package com.example.visitesapprentis.IHM;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.visitesapprentis.R;

public class MainActivity extends AppCompatActivity {

    private Button bApprenti;
    private Button bEntreprise;
    private Button bReferent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bApprenti = (Button) findViewById(R.id.buttonApprenti);
        bApprenti.setOnClickListener(apprentiListener);

        bEntreprise = (Button) findViewById(R.id.buttonEntreprise);
        bEntreprise.setOnClickListener(entrepriseListener);

        bReferent = (Button) findViewById(R.id.buttonReferent);
        bReferent.setOnClickListener(referentListener);
    }

    private View.OnClickListener apprentiListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, ListeApprentiActivity.class);
            startActivityForResult(intent, 0);
        }
    };

    private View.OnClickListener entrepriseListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, ListeEntrepriseActivity.class);
            startActivityForResult(intent, 0);
        }
    };
    private View.OnClickListener referentListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, ListeReferentActivity.class);
            startActivityForResult(intent, 0);
        }
    };
}

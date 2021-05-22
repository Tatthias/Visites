package com.example.visitesapprentis.IHM;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.visitesapprentis.R;

public class ApprentiActivity extends AppCompatActivity {

    private Button bAjouter;
    private Button bModifier;
    private Button bSupprimer;
    private Button bRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apprenti);

        bAjouter = (Button) findViewById(R.id.bAjouterApp);

        bModifier = (Button) findViewById(R.id.bModifierApp);

        bSupprimer = (Button) findViewById(R.id.bSupprimerApp);

        bRetour = (Button) findViewById(R.id.bRetourApp);
        bRetour.setOnClickListener(retourListener);
    }

    private View.OnClickListener retourListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ApprentiActivity.this, MainActivity.class);
            startActivityForResult(intent, 0);
        }
    };
}

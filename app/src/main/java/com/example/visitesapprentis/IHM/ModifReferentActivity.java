package com.example.visitesapprentis.IHM;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.visitesapprentis.DAO.ApprentiDAO;
import com.example.visitesapprentis.DAO.ReferentDAO;
import com.example.visitesapprentis.Metier.Apprenti;
import com.example.visitesapprentis.Metier.Referent;
import com.example.visitesapprentis.R;

import java.util.Date;

public class ModifReferentActivity extends AppCompatActivity {

    private int idAppSupp;

    private Button bRetour;
    private Button bModifier;

    private Referent unRef;
    private ReferentDAO referentDAO;
    private int position;

    private EditText editNomRef;
    private EditText editPrenomRef;
    private EditText editAdresseRef;
    private EditText editTelRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_referent_modif);

        bRetour = (Button) findViewById(R.id.bRetourRefModif);
        bRetour.setOnClickListener(retourListener);

        bModifier = (Button) findViewById(R.id.bModifierRefModif);
        bModifier.setOnClickListener(modifierListener);

        Bundle extra = getIntent().getExtras();
        if(extra.getInt("position")>= 0)
        {
            position = extra.getInt("position");
        }

        referentDAO = new ReferentDAO(getApplicationContext());
        referentDAO.open();
        unRef = referentDAO.readPosition(position);

        editNomRef = (EditText) findViewById(R.id.editNomRefModif);
        editNomRef.setText(unRef.getNomRef());
        editPrenomRef = (EditText) findViewById(R.id.editPrenomRefModif);
        editPrenomRef.setText(unRef.getPrenomRef());
        editAdresseRef  = (EditText) findViewById(R.id.editAdresseRefModif);
        editAdresseRef.setText(unRef.getAddresseRef());
        editTelRef  = (EditText) findViewById(R.id.editTelRefModif);
        editTelRef.setText(unRef.getTelRef());

        referentDAO.close();
    }

    private final View.OnClickListener retourListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ModifReferentActivity.this, ListeReferentActivity.class);
            startActivityForResult(intent, 0);
        }
    };

    private final View.OnClickListener modifierListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            String nomRef = editNomRef.getText().toString();
            String prenomRef = editPrenomRef.getText().toString();
            String addresseRef = editAdresseRef.getText().toString();
            String telRef = editTelRef.getText().toString();


            referentDAO = new ReferentDAO(getApplicationContext());

            unRef = new Referent(position+1, nomRef, prenomRef, addresseRef, telRef);

            Log.d("unePosition", String.valueOf(position));
            referentDAO.open();
            referentDAO.update(unRef);
            referentDAO.close();

            Toast.makeText(getApplicationContext(), "Référent modifié", Toast.LENGTH_LONG).show();

            finish();
            Intent intent = new Intent(ModifReferentActivity.this, ListeReferentActivity.class);
            startActivityForResult(intent, 0);
        }
    };
}

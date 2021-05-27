package com.example.visitesapprentis.IHM;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.visitesapprentis.DAO.ApprentiDAO;
import com.example.visitesapprentis.Metier.Apprenti;
import com.example.visitesapprentis.R;



public class VisitesActivity extends AppCompatActivity {


    private int idAppSupp;
    private Apprenti unApp;
    private ApprentiDAO apprentiDAO;
    private Button bSuppression;

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



        TextView prenom = (TextView)findViewById(R.id.TextViewPrenom);

    }
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

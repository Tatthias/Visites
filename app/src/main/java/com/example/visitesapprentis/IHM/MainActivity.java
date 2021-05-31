package com.example.visitesapprentis.IHM;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.visitesapprentis.R;

public class MainActivity extends AppCompatActivity {

    private Button bApprenti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bApprenti = (Button) findViewById(R.id.buttonApprenti);
        bApprenti.setOnClickListener(apprentiListener);
    }

    private View.OnClickListener apprentiListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, ListeApprentiActivity.class);
            startActivityForResult(intent, 0);
        }
    };
}

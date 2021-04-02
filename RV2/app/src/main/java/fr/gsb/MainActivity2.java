package fr.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.URLEncoder;

public class MainActivity2 extends AppCompatActivity {

    TextView tvVis;
    Button bConsulter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvVis = (TextView) findViewById(R.id.tvVis);

        Bundle paquet = this.getIntent().getExtras();
        String prenom = paquet.getString("prenom");
        String nom = paquet.getString("nom");

        tvVis.setText(prenom+" "+nom);

        bConsulter = (Button) findViewById(R.id.bConsulter);
        bConsulter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Consulter(v);
            }
        });
    }

    public void retourner(View vue){

        Intent intentionRetourner = new Intent(this, MainActivity.class);
        startActivity(intentionRetourner);
    }

    public void Consulter(View vue){

        Intent intentionEnvoyer = new Intent(this, RechercheRvActivity.class);
        startActivity(intentionEnvoyer);
    }
}
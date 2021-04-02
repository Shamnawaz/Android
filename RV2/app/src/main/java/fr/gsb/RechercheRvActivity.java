package fr.gsb;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.os.Bundle;
import android.widget.Spinner;

public class RechercheRvActivity extends AppCompatActivity {

    Spinner spinner;
    Spinner spinnera;
    Button bRapport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche_rv);

        spinner = (Spinner) findViewById(R.id.s1);
        spinnera = (Spinner) findViewById(R.id.s2);

        List ListMois = new ArrayList<Integer>();
        for (int i = 1; i < 13; i++) {

            ListMois.add(i);

        }

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(
                this,
                android.R.layout.simple_spinner_item,
                ListMois
        );

        List ListAnnee = new ArrayList<Integer>();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        for (int y = year; y > year - 4; y--) {

            ListAnnee.add(y);
        }

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                ListAnnee
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinnera.setAdapter(adapter2);

        bRapport = (Button) findViewById(R.id.bRapport);
        bRapport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Rapport(v);

            }
        });

    }


    public void retourner(View vue) {

        Intent intentionRetourner = new Intent(this, MainActivity2.class);
        startActivity(intentionRetourner);
    }

    public void Rapport(View vue) {

        Bundle paquet = new Bundle();
        paquet.putString("mois", spinner.getSelectedItem().toString());
        paquet.putString("Annee", spinnera.getSelectedItem().toString());

        Intent intentionEnvoyer = new Intent(this, ListeRvActivity.class);
        intentionEnvoyer.putExtras(paquet);

        startActivity(intentionEnvoyer);
    }
}


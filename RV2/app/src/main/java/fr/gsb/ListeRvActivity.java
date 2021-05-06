package fr.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.gsb.entites.Visiteur;
import fr.gsb.technique.Session;

public class ListeRvActivity extends AppCompatActivity {

    private static final String TAG = "GSB MAIN ACTIVITY";
    TextView tvTest;
    ListView lvRapport;

    List<String[]> rapports = new ArrayList<>();
    List<String> lrap = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_rv);

        tvTest = (TextView) findViewById(R.id.tvTest);


        Bundle paquet = this.getIntent().getExtras();
        String mois = paquet.getString("mois");
        String annee = paquet.getString("Annee");







        //tvTest.setText(Session.getSession().getVisiteur().getMatricule());

        String url = String.format("http://192.168.104.222:5000/rapports/%s/%s/%s", "a131", mois, annee );

        Response.Listener<String> ecouteurReponse = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response != null){

                    try {

                        JSONArray lRapports = new JSONArray(response);

                        for(int i = 0; i < lRapports.length(); i++) {
                            JSONObject objet = lRapports.getJSONObject(i);
                            System.out.println(objet.get("rap_num").toString());
                            String[] unRapport = new String[]{objet.get("rap_bilan").toString(),
                                    objet.get("pra_cp").toString(),
                                    objet.get("rap_num").toString(),
                                    objet.get("pra_nom").toString(),
                                    objet.get("rap_date_visite").toString(),
                                    objet.get("pra_prenom").toString(),
                                    objet.get("pra_ville").toString()};
                            rapports.add(unRapport);

                            rapports.add(unRapport);
                            lrap.add("Rapport "+objet.get("rap_num").toString());



                        }
                        lvRapport = (ListView) findViewById(R.id.lvRapport);
                        ArrayAdapter<String> adaptateur = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1 , lrap);

                        lvRapport.setAdapter(adaptateur);



                    }


                    catch (JSONException e) {
                        Log.e(TAG, "Erreur JSON : "+e.getMessage());
                    }

                    System.out.println(response);



                }

                else{

                    Toast.makeText(getApplicationContext(), "Prénom : null"+" || Nom :null", Toast.LENGTH_LONG).show();

                }

                Log.d(TAG, "Connexion Ok : "+response);
            }
        };




        Response.ErrorListener ecouteurErreur = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Erreur HTTP : "+error.getMessage());
            }
        };

        StringRequest requete = new StringRequest(
                Request.Method.GET ,
                url,
                ecouteurReponse,
                ecouteurErreur
        ) ;

        RequestQueue fileRequetes = Volley.newRequestQueue( this );
        fileRequetes.add(requete);


    }
}


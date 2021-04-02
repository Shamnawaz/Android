package fr.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.gsb.entites.Visiteur;
import fr.gsb.technique.Session;

public class ListeRvActivity extends AppCompatActivity {

    private static final String TAG = "GSB MAIN ACTIVITY";
    TextView tvTest;
    ListView lvRapport;

    String[] produits = {"Burger", "Baggel", "Hot Dog", "Pizza"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_rv);

        tvTest = (TextView) findViewById(R.id.tvTest);
        lvRapport = (ListView) findViewById(R.id.lvRapport);

        ArrayAdapter<String> adaptateur = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 , produits);

        lvRapport.setAdapter(adaptateur);

        lvRapport.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String produitsSelectionne = produits[ position ];
                tvTest.setText(produitsSelectionne);

            }
        });

        Bundle paquet = this.getIntent().getExtras();
        String mois = paquet.getString("mois");
        String annee = paquet.getString("Annee");




        //tvTest.setText(Session.getSession().getVisiteur().getMatricule());

        String url = String.format("http://192.168.104.222:5000/rapports/%s/%s/%s", "a131", mois, annee );

        Response.Listener<JSONArray> ecouteurReponse = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                try {

                    String[] rapports = new String[7];
                    JSONArray lRapports = new JSONArray(rapports);

                    /*rapports = {response.getString("rap_num"), response.getString("rap_date_visite"),
                                         response.getString("rap_bilan"), response.getString("pra_nom"),
                                         response.getString("pra_prenom"),response.getString("pra_cp"),
                                         response.getString("pra_ville")};*/

                } catch (JSONException e) {
                    Log.e(TAG, "Erreur JSON : "+e.getMessage());
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

        JsonArrayRequest requete = new JsonArrayRequest(
                Request.Method.GET ,
                url,
                null,
                ecouteurReponse,
                ecouteurErreur
        ) ;

        RequestQueue fileRequetes = Volley.newRequestQueue( this );
        fileRequetes.add(requete);


    }
}


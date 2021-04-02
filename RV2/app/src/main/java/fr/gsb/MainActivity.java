package fr.gsb;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import fr.gsb.entites.Visiteur;
import fr.gsb.technique.Session;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;
import java.net.SocketOption;
import java.net.URLEncoder;

import fr.gsb.modeles.ModeleGsb;
import fr.gsb.technique.Session;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "GSB MAIN ACTIVITY";
    TextView tvidMatricule;
    TextView textIdMotDePasse;
    EditText etMatricule;
    EditText etMdp;
    Button bValider;
    Button bAnnuler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bAnnuler = (Button) findViewById(R.id.bAnnuler);
        bAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                annuler(v);
            }
        });

        bValider = (Button) findViewById(R.id.bValider);
        bValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                seConnecter(v);

            }
        });
        Log.d(TAG, "Création de l'activité principale.");
    }


    public void seConnecter(View unView) {

        etMatricule = (EditText) findViewById(R.id.etMatricule);
        etMdp = (EditText) findViewById(R.id.etMdp);
        bValider = (Button) findViewById(R.id.bValider);
        /*bValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {*/

                Visiteur vis = ModeleGsb.getInstance().seConnecter(etMatricule.getText().toString(), etMdp.getText().toString());
                if (vis != null) {


                    bValider.setEnabled(false);
                    bAnnuler.setEnabled(false);
                    Toast.makeText(getApplicationContext(), "Prénom :"+vis.getPrenom()+" || Nom :"+vis.getNom(), Toast.LENGTH_LONG).show();
                    Log.d(TAG, "Connexion Ok :"+vis.getPrenom()+" Nom : "+vis.getNom());
                    String prenom = vis.getPrenom();
                    String nom = vis.getNom();

                    Bundle paquet = new Bundle();
                    paquet.putString("prenom", prenom);
                    paquet.putString("nom", nom);

                    Intent intentionEnvoyer = new Intent(this, MainActivity2.class);
                    intentionEnvoyer.putExtras(paquet);

                    startActivity(intentionEnvoyer);
                    //Session.getSession().ouvrir(vis);






                }
                else {

                    Toast.makeText(getApplicationContext(), "Erreur de la connexion. Veuillez recommencez...", Toast.LENGTH_LONG).show();
                    Log.d(TAG, "Connexion Nok.");
                }

                String matricule = null;
                String mdp = null;

                try {
                     matricule = URLEncoder.encode(etMatricule.getText().toString(), "UTF-8");
                     mdp = URLEncoder.encode(etMdp.getText().toString(), "UTF-8");


                }
                catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String url = String.format("http://192.168.104.222:5000/visiteurs/%s/%s", matricule, mdp);

                Response.Listener<JSONObject> ecouteurReponse = new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                        try {

                            Visiteur unVisiteur = new Visiteur();
                            unVisiteur.setMatricule(response.getString("matricule" ) );
                            unVisiteur.setMdp(response.getString("mdp"));
                            Log.i(TAG, String.valueOf(unVisiteur));
                            Session.getSession().ouvrir(unVisiteur);

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

                JsonObjectRequest requete = new JsonObjectRequest(
                        Request.Method.GET ,
                        url,
                        null,
                        ecouteurReponse,
                        ecouteurErreur
                ) ;

                RequestQueue fileRequetes = Volley.newRequestQueue( this );
                fileRequetes.add(requete);


            }







    public void annuler(View unView){

        bAnnuler = (Button) findViewById(R.id.bAnnuler);
        bAnnuler.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                etMatricule = (EditText) findViewById(R.id.etMatricule);
                etMdp = (EditText) findViewById(R.id.etMdp);
                etMatricule.setText("");
                etMdp.setText("");
                Log.d(TAG, "Initialisation des champs.");



            }
        });




        }}

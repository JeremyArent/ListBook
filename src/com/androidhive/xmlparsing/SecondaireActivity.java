package com.androidhive.xmlparsing;

import java.util.ArrayList;
import java.util.HashMap;

import com.androidhive.xmlparsing.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class SecondaireActivity  extends Activity {
	
	// Récupération des valeur du fichier XML
	static final String NOM = "nom";
	static final String PRIX = "prix";
	static final String AUT = "auteur";
	static final String EDIT = "editeur";
	static final String RESUM = "resume";

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_list_item);
        
        Intent in = getIntent();
        
        // récupération des valeurs
        String nom = in.getStringExtra(NOM);
        String prix = in.getStringExtra(PRIX);
        String auteur = in.getStringExtra(AUT);
        String editeur = in.getStringExtra(EDIT);
        String resume = in.getStringExtra(RESUM);

        // affichage des valeurs à l'écran
        TextView labelNom = (TextView) findViewById(R.id.nom_label);
        TextView labelPrix = (TextView) findViewById(R.id.prix_label);
        TextView labelAuteur = (TextView) findViewById(R.id.auteur_label);
        TextView labelEditeur = (TextView) findViewById(R.id.editeur_label);
        TextView labelResume = (TextView) findViewById(R.id.resume_label);
        
        labelNom.setText(nom);
        labelPrix.setText(prix);
        labelAuteur.setText(auteur);
        labelEditeur.setText(editeur);
        labelResume.setText(resume);
        
        final Button btnEnvoie = (Button) findViewById(R.id.btnEnvoie);
        btnEnvoie.setOnClickListener(new OnClickListener() {
        
        public void onClick(View v) {
        	Intent intent = new Intent(SecondaireActivity.this, EnvoieSMS.class);
        	startActivity(intent);
        	}
        });
        
    }
}

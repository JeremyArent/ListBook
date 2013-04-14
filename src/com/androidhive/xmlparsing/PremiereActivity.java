// Jérémy Arent 

package com.androidhive.xmlparsing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.androidhive.xmlparsing.R;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class PremiereActivity extends ListActivity {

	// Site URL
	static final String URL = "http://enfant-a-vendre.com/livre.xml";
	
	// Récupération des valeur du fichier XML
	
	static final String LIVRE = "livre"; //clé principal
	static final String ID = "id";
	static final String NOM = "nom";
	static final String PRIX = "prix";
	static final String AUT = "auteur";
	static final String RESUM = "resume";
	static final String STOCK = "stock";
	static final String EDIT = "editeur";
	static final String REM = "remarque";
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ArrayList<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();

		XMLFunctions parser = new XMLFunctions();
		//String xml = parser.getXmlFromUrl(URL);
		
		
		
		String xml="";
        try {
        BufferedReader in = new BufferedReader(new InputStreamReader(this.getAssets().open("livre.xml")));
        xml=in.readLine();
        String strLine;
        while ((strLine = in.readLine()) != null) {
    		xml=xml+strLine+"\n";    		
    	}    	
        in.close();     
        }
         catch (Exception e){
        	 Toast.makeText(PremiereActivity.this, "Problème lecture fichier XML", Toast.LENGTH_LONG).show();  
         	finish();
         }
		
		Document doc = parser.getDomElement(xml);

		NodeList nl = doc.getElementsByTagName(LIVRE);
		
		// looping through all item nodes <item>
		
		for (int i = 0; i < nl.getLength(); i++) {
			
			// création HashMap
			
			HashMap<String, String> map = new HashMap<String, String>();
			Element e = (Element) nl.item(i);
			// ajout des valeurs
			map.put(ID, parser.getValue(e, ID));
			map.put(NOM, parser.getValue(e, NOM));
			map.put(PRIX, parser.getValue(e, PRIX) + "€");
			map.put(AUT, "Auteur : " + parser.getValue(e, AUT));
			map.put(EDIT, "Editeur : " + parser.getValue(e, EDIT));
			map.put(RESUM, parser.getValue(e, RESUM));
			map.put(STOCK, "Stock :" + parser.getValue(e, STOCK));
			map.put(REM, "Remarques :" + parser.getValue(e, REM));

			// adding HashList to ArrayList
			menuItems.add(map);
		}

		// Adding menuItems to ListView
		ListAdapter adapter = new SimpleAdapter(this, menuItems,
				R.layout.list_item,
				new String[] { NOM, AUT, EDIT, PRIX, RESUM, STOCK, REM }, new int[] {
						R.id.nom, R.id.auteur, R.id.editeur, R.id.prix, R.id.resume, R.id.stock, R.id.remarque });

		setListAdapter(adapter);

		// selecting single ListView item
		ListView lv = getListView();

		lv.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				// getting values from selected ListItem
				String nom = ((TextView) view.findViewById(R.id.nom)).getText().toString();
				String prix = ((TextView) view.findViewById(R.id.prix)).getText().toString();
				String auteur = ((TextView) view.findViewById(R.id.auteur)).getText().toString();
				String editeur = ((TextView) view.findViewById(R.id.editeur)).getText().toString();
				String resume = ((TextView) view.findViewById(R.id.resume)).getText().toString();
				String stock = ((TextView) view.findViewById(R.id.stock)).getText().toString();
				String remarque = ((TextView) view.findViewById(R.id.remarque)).getText().toString();
				
				
				Intent in = new Intent(getApplicationContext(), SecondaireActivity.class);
				in.putExtra(NOM, nom);
				in.putExtra(PRIX, prix);
				in.putExtra(AUT, auteur);
				in.putExtra(EDIT, editeur);
				in.putExtra(RESUM, resume);
				in.putExtra(STOCK, stock);
				in.putExtra(REM, remarque);
				startActivity(in);

			}
		});

	}
}
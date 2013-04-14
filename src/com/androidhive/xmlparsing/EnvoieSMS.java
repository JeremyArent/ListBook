package com.androidhive.xmlparsing;
 
import android.app.Activity;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
 
public class EnvoieSMS extends Activity {
 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.envoiesms);
        //On r�cup�re le bouton cr�� en XML gr�ce � son id
        Button btnEnvoie = (Button)findViewById(R.id.envoyer);
        //On r�cup�re les deux EditText correspondant aux champs pour entrer le num�ro et le message
        final EditText numero =(EditText)findViewById(R.id.numero);
        final EditText message = (EditText)findViewById(R.id.message);
        //On affecte un �couteur d'�v�nement au bouton
        btnEnvoie.setOnClickListener(new OnClickListener() {
 
			@SuppressWarnings("deprecation")
			public void onClick(View v) {
				//On r�cup�re ce qui a �t� entr� dans les EditText
				String num = numero.getText().toString();
				String msg = message.getText().toString();
				//Si le num�ro est sup�rieur � 4 caract�res et que le message n'est pas vide on lance la proc�dure d'envoi
				if(num.length()>= 4 && msg.length() > 0){
					//Gr�ce � l'objet de gestion de SMS (SmsManager) que l'on r�cup�re via la m�thode static getDefault()
					//On envoie le SMS � l'aide de la m�thode sendTextMessage
					SmsManager.getDefault().sendTextMessage(num, null, msg, null, null);
					//On efface les deux EditText
					numero.setText("");
					message.setText("");
				}else{
					//On affiche un petit message d'erreur dans un Toast
					Toast.makeText(EnvoieSMS.this, "Enter le numero et/ou le message", Toast.LENGTH_SHORT).show();
				}
 
			}
		});
    }
}
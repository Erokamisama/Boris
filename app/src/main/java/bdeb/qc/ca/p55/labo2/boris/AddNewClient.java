package bdeb.qc.ca.p55.labo2.boris;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;

import ca.qc.bdeb.p55.labo2.Client.Client;

public class AddNewClient extends AppCompatActivity {
    private Button boutonValider;
    private EditText firstName;
    private EditText lastName;
    private EditText age;
    private EditText adress;
    private EditText city;
    private EditText mail;
    private RadioButton male;
    private RadioButton female;
    private RadioGroup gender;


    static final String SAISIE_NOMFAMILLE = "NOM";
    static final String SAISIE_PRENOM = "PRENOM";
    static final String SAISIE_VILLE ="VILLE";
    static final String SAISIE_AGE = "AGE";
    static final String SAISIE_ADRESSE = "ADRESSE";
    static final String SAISIE_MAIL = "MAIL";

    public static final String RETOUR_CLIENT = "RETOUR_CLIENT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_customer);


        linkXMLandJava();
        gender.check(male.getId());



        boutonValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCustomer();
            }
        });



    }

    private void linkXMLandJava() {
        boutonValider = (Button) findViewById(R.id.activity_add_new_customer_BtnValidate);
        firstName = (EditText) findViewById(R.id.activity_add_new_customer_textField_FirstName);
        lastName = (EditText) findViewById(R.id.activity_add_new_customer_TextFieldLastName);
        age = (EditText) findViewById(R.id.activity_add_new_customer_TxtFieldAge);
        city = (EditText) findViewById(R.id.activity_add_new_customer_TxtFieldCity);
        adress = (EditText) findViewById(R.id.activity_add_new_customer_TxtFieldAdress);
        mail = (EditText) findViewById(R.id.activity_add_new_customer_TxtFieldMail);
        male = (RadioButton) findViewById(R.id.activity_add_new_customer_OptMale);
        female = (RadioButton) findViewById(R.id.activity_add_new_customer_OptFemale);
        gender = (RadioGroup) findViewById(R.id.radioGroup);
    }

    private void saveCustomer() {
        Client client = new Client();
        int idBoutonchecked;
      idBoutonchecked = gender.getCheckedRadioButtonId();

        if(idBoutonchecked == male.getId()){
            client.setGender("M");
        }
        else{
            client.setGender("F");
        }

        client.setAge(Integer.parseInt(age.getText().toString()));

        client.setAdress(adress.getText().toString());

        client.setCity(city.getText().toString());

        client.setMail(mail.getText().toString());

        client.setFirstName(firstName.getText().toString());

        client.setLastName(lastName.getText().toString());

        retournerClient(client);









    }

    private void retournerClient(Client client) {
    //renvoie les donnees dans l autre activity pour ajouter dans la liste.
        Intent resultIntent = new Intent();

        resultIntent.putExtra(RETOUR_CLIENT, client);
        setResult(AppCompatActivity.RESULT_OK, resultIntent);
        finish();



    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(SAISIE_ADRESSE, adress.getText().toString());
        outState.putString(SAISIE_AGE, age.getText().toString());
        outState.putString(SAISIE_MAIL, mail.getText().toString());
        outState.putString(SAISIE_VILLE, city.getText().toString());
        outState.putString(SAISIE_PRENOM, firstName.getText().toString());
        outState.putString(SAISIE_NOMFAMILLE, lastName.getText().toString());

        super.onSaveInstanceState(outState);
    }
}

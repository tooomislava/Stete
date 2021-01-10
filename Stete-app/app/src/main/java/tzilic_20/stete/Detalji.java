package tzilic_20.stete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.ParseException;

import tzilic_20.stete.model.Odgovor;
import tzilic_20.stete.model.Steta;

public class Detalji extends AppCompatActivity implements RestSucelje {

    private Steta steta;
    private EditText vlasnik;
    private EditText geografskaSirina;
    private EditText geografskaDuzina;
    private EditText iznosStete;
    private Button nazad, dodaj, promjeni, obrisi;
    private RESTTask restTask;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalji);
        vlasnik = findViewById(R.id.vlasnik);
        geografskaSirina = findViewById(R.id.geografskaSirina);
        geografskaDuzina = findViewById(R.id.geografskaDuzina);
        iznosStete = findViewById(R.id.iznosStete);
        nazad = findViewById(R.id.nazad);
        dodaj = findViewById(R.id.dodaj);
        promjeni = findViewById(R.id.promjeni);
        obrisi = findViewById(R.id.obrisi);

        Intent i = getIntent();
        boolean noviRok = i.getBooleanExtra("novaSteta",false);
        if(!noviRok){
            steta = (Steta)i.getSerializableExtra("steta");
            vlasnik.setText(steta.getVlasnik());
            geografskaSirina.setText(String.valueOf(steta.getGeografskaSirina()));
            geografskaDuzina.setText(String.valueOf(steta.getGeografskaDuzina()));
            iznosStete.setText(String.valueOf(steta.getIznosStete()));
            dodaj.setVisibility(View.INVISIBLE);
        }else {
            promjeni.setVisibility(View.INVISIBLE);
            obrisi.setVisibility(View.INVISIBLE);
        }


        nazad.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                nazad(true);
            }
        });

        dodaj.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                try {
                    dodaj();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        promjeni.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                try {
                    promjeni();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        obrisi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                obrisi();
            }
        });

        restTask = new RESTTask(this);

         gson = new GsonBuilder().create();


    }

    private void obrisi() {
        restTask.execute(getString(R.string.RESTAPI) + "prijava/" + steta.getSifra(),"DELETE",null);
    }

    // nakon dodavanja ParseException, otići mišem na crveno gore kod promjeni/dodaj i odabrati Surround with try/catch.
    private void promjeni() throws ParseException {
        steta.setVlasnik(vlasnik.getText().toString());
        steta.setGeografskaSirina(Float.parseFloat(String.valueOf(geografskaSirina.getText())));
        steta.setGeografskaDuzina(Float.parseFloat(String.valueOf(geografskaDuzina.getText())));
        steta.setIznosStete(Float.parseFloat(String.valueOf(iznosStete.getText())));
        restTask.execute(getString(R.string.RESTAPI) + "prijava/" + steta.getSifra(),"PUT",gson.toJson(steta));
    }

    private void dodaj() throws ParseException {
        steta = new Steta();
        steta.setVlasnik(vlasnik.getText().toString());
        steta.setGeografskaSirina(Float.parseFloat(String.valueOf(geografskaSirina.getText())));
        steta.setGeografskaDuzina(Float.parseFloat(String.valueOf(geografskaDuzina.getText())));
        steta.setIznosStete(Float.parseFloat(String.valueOf(iznosStete.getText())));
        restTask.execute(getString(R.string.RESTAPI) + "prijava" ,"POST",gson.toJson(steta));
    }

    void nazad(boolean ok){
        setResult(ok ? MainActivity.OK : MainActivity.GRESKA, null);
        finish();
    }


    @Override
    public void zavrseno(Odgovor odgovor) {
            nazad(!odgovor.getPoruka().isGreska());
    }
}

package tzilic_20.stete.model;

import java.io.Serializable;
import java.util.Date;

public class Steta implements Serializable {

    private int sifra;
    private String vlasnik;
    private float geografskaSirina;
    private float geografskaDuzina;
    private float iznosStete;

    public Steta() {
    }

    public Steta(int sifra, Date datum, String kolegij, int brojPrijavljenih) {
        this.sifra = sifra;
        this.vlasnik = vlasnik;
        this.geografskaSirina = geografskaSirina;
        this.geografskaDuzina = geografskaDuzina;
        this.iznosStete = iznosStete;
    }

    public int getSifra() {
        return sifra;
    }

    public void setSifra(int sifra) {
        this.sifra = sifra;
    }

    public String getVlasnik() {
        return vlasnik;
    }

    public void setVlasnik(String vlasnik) {
        this.vlasnik = vlasnik;
    }

    public float getGeografskaSirina() {
        return geografskaSirina;
    }

    public void setGeografskaSirina(float geografskaSirina) {
        this.geografskaSirina = geografskaSirina;
    }

    public float getGeografskaDuzina() {
        return geografskaDuzina;
    }

    public void setGeografskaDuzina(float geografskaDuzina) {
        this.geografskaDuzina = geografskaDuzina;
    }

    public float getIznosStete() {
        return iznosStete;
    }

    public void setIznosStete(float iznosStete) {
        this.iznosStete = iznosStete;
    }
}

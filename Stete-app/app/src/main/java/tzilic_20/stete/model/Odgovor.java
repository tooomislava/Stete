package tzilic_20.stete.model;

import java.util.List;

public class Odgovor {

    private Poruka poruka;
    private List<Steta> podaci;

    public Poruka getPoruka() {
        return poruka;
    }

    public void setPoruka(Poruka poruka) {
        this.poruka = poruka;
    }

    public List<Steta> getPodaci() {
        return podaci;
    }

    public void setPodaci(List<Steta> podaci) {
        this.podaci = podaci;
    }
}


package matopeli.maailmanOsat;

import matopeli.Maailma;
import matopeli.Vektori;

/**
 * Luokka joka antaa perustoiminnot syötäville. 
 * Pitää huolen syötävän eliniästä, pisteiden laskemisesta,
 * heilumisesta ja syötävän poistamisesta
 */
public abstract class Syotava extends Palikka{
    
    protected int elinika; //Syötävän elinikä pelitikeissä
    public boolean poista; //pitääkö syötävä poistaa seuravassa syötävien päivityksessa
    
    public Syotava(Vektori koordinaatit, int elinika) {
        super(koordinaatit);
        this.elinika = elinika;
        poista = false;
    }
    
    /**
     * Päivittää syotavan eliniän ja kutsuu heiluta metodin
     * 
     * @param maailma Kaikille yhteinen maailma
     */
    @Override
    public void paivita(Maailma maailma) {
        super.paivita(maailma);
        elinika--;
        if(this.elinika <= 1){
            poista = true;
        }
        heiluta();
    }

    /**
     * Lisää oikean määrän pisteitä ja merkkaa syötävän poistettavaksi
     * 
     * @param maailma kaikille yhteinen maailma
     */
    @Override
    protected void tormaa(Maailma maailma) {
        maailma.getPistelaskuri().lisaaPisteita(getPisteet());
        poista = true;
    }
    
    /**
     * Kääntää syötävän suuntaa suhteessa sen elinikään siten että se heiluu
     * ja mitä vähemmän sillä on elinikää jäljellä sitä nopeammin se heiluu
     */
    private void heiluta() {
        this.suunta = new Vektori(0, -1).kaanna(0.2*Math.sin(5*Math.sqrt(elinika)));
    }
    
    /**
     * @return Syötävän tyypin
     */
    public abstract byte getTyyppi();
    
    /**
     * @return Syömisestä saatavat pisteet
     */
    public abstract int getPisteet();

    
}

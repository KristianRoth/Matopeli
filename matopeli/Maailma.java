package matopeli;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import matopeli.maailmanOsat.MaailmanOsa;
import matopeli.maailmanOsat.Mato;
import matopeli.maailmanOsat.Paivitettava;
import matopeli.maailmanOsat.Piirrettava;
import matopeli.maailmanOsat.Pistelaskuri;
import matopeli.maailmanOsat.Reunat;
import matopeli.maailmanOsat.Syotavat;

/**
 * Luokka joka sisältää kaikki erilliset pelilogiikan luokat kuten näppäintenkuuntelija ja pisteittenlaskijan
 * Maailma myös sisältää kaikki Palikat madon, syötävät ja reunat
 * Maailma kutsuu kaikkien päivittettävien päivitä metodit
 * ja kaikkien piirettävien piirrä metodit
 */
public class Maailma {
    
    private ArrayList<MaailmanOsa> maailmanOsat;
    private Mato mato;
    private Syotavat syotavat;
    private Reunat reunat;
    private Pistelaskuri pistelaskuri;
    private NappaintenKuuntelija nappaintenKuuntelija;
    private boolean kaunnissa;
    
    public Maailma() {
        nappaintenKuuntelija = new NappaintenKuuntelija();
        aloitus();
        kaunnissa = false;
    }
    
    /**
     * Metodi jota kutsutaa aina uuden pelin alkaessa
     * alustaa kaikki muuttujat
     */
    private void aloitus(){
        kaunnissa = true;
        mato = new Mato();
        syotavat = new Syotavat();
        reunat = new Reunat();
        pistelaskuri = new Pistelaskuri();
        maailmanOsat = new ArrayList<>();
        maailmanOsat.add(reunat);
        maailmanOsat.add(mato);
        maailmanOsat.add(syotavat);
        maailmanOsat.add(pistelaskuri);
        
    }
    
    /**
     * Kutsuu kaikkien päivitettävien päivitä metodit
     */
    public void paivita() {
        for (MaailmanOsa maailmanOsa : maailmanOsat) {
            if(maailmanOsa instanceof Paivitettava){
                ((Paivitettava)maailmanOsa).paivita(this);
            }
        }
    }
    
    /**
     * Kutsuu kaikkien piirrettävien piirrä metodit
     * 
     * @param g Ikkunan grafiikka
     */
    public void piirra(Graphics2D g) {
        for (MaailmanOsa maailmanOsa : maailmanOsat) {
            if(maailmanOsa instanceof Piirrettava){
                ((Piirrettava)maailmanOsa).piirra(g);
            }
        }
    }
    
    
    
    /**
     * Pysäyttää pelin kunnes käyttäjä painaa ENTER ja aloittaa pelin sitten alusta
     */
    public void lopetus() {
        while(!nappaintenKuuntelija.painettu(KeyEvent.VK_ENTER)){
            synchronized(nappaintenKuuntelija){
                try {
                    nappaintenKuuntelija.wait();
                } catch (InterruptedException ex) {
                }
            }
        }
        aloitus();
    }
    
    /**
     * Aiheuttaa pelin loppumisen tämän kello syklin jälkeen
     */
    public void lopeta() {
        kaunnissa = false;
    }
    
    public boolean kaunnissa(){
        return kaunnissa;
    }

    public Mato getMato() {
        return mato;
    }

    public NappaintenKuuntelija getNappaintenKuuntelija() {
        return nappaintenKuuntelija;
    }

    public Pistelaskuri getPistelaskuri() {
        return pistelaskuri;
    }
    
    
}


package matopeli.maailmanOsat;

import java.awt.Graphics2D;
import java.util.ArrayList;
import matopeli.Maailma;
import matopeli.Vektori;

/**
 * Luokka joka sisältää suurimman osan madon logiikasta kuten sen kasvattamisen laihduttamisen
 * se myös sisältää listan kaikista madonosista ja kutsuu niiden päivitä ja piirrä metodit
 */
public class Mato implements Paivitettava, Piirrettava{
    private ArrayList<MadonOsa> osat;
    private boolean kasvata;
    
    
    /**
     * Alustaa muuttujat ja määrittää madon aloitus pisteen, nopeudun ja aloitus pituuden
     */
    public Mato() {
        osat = new ArrayList<>();
        osat.add(new Paa(new Vektori(400,400), new Vektori(1,3)));
        kasvata = false;
        this.lisaa();
        this.lisaa();
        this.lisaa();
    }
    /**
     * @return Madon pää
     */
    public Paa getPaa(){
        return (Paa)osat.get(0);
    }
    
    /**
     * Asettaa madon kasvatettavaksi seuraavalla kerralla kun päivitä kutsutaan
     */
    public void kasvata(){
        kasvata = true;
    }
    
    /**
     * Lisää madon perään yhden palasen
     */
    private void lisaa(){
        synchronized(osat){
            osat.add(new Hanta(osat.get(osat.size()-1)));
        }
    }
    
    /**
     * @return Madon kaikki osat
     */
    public ArrayList<MadonOsa> getOsat() {
        return osat;
    }
    
    /**
     * Lähettää lihavan osan liikkeelle madon päästä
     */
    public void lihota(){
        osat.get(0).laski = 1;
    }
    
    /**
     * Poistaa madon hännästä 1/3 madon pituudesta
     */
    public void laihduta() {
        int osia = osat.size();
        synchronized(osat){
            if(osia > 3){
                for(int i = osia-1; i > osia*2/3; i--){
                    osat.remove(i);
                }
            }
        }
        this.lisaa();
    }
    
    /**
     * Kutsuu päivitä metodin kaikkille madon osille päästä häntään
     * tarkastaa sitten jos matoa täytyy kasvattaa
     * 
     * @param maailma Kaikille yhteinen maailma
     */
    @Override
    public void paivita(Maailma maailma){
        synchronized(osat){
            for (MadonOsa m : osat) {
                m.paivita(maailma);
            }
        }
        if(kasvata){
            lisaa();
            lisaa();
            lisaa();
            kasvata = false;
        }
    }
    
    /**
     * Kutsuu piirrä metodin kaikille madonosille
     * 
     * @param g Ikkunan grafiikka
     */
    @Override
    public void piirra(Graphics2D g) {
        synchronized(osat){
            for (MadonOsa madonOsa : osat) {
                madonOsa.piirra(g);
            }
        }
    }
}

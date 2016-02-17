
package matopeli.maailmanOsat;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;
import matopeli.Ikkuna;
import matopeli.Maailma;
import matopeli.Vektori;

/**
 * Luokka joka sisältää listan kaikista syötävistä ja kutsuu niiden päivitä ja piirrä metodit
 * Huolehtii myös syötävien lisäämisen ja poistamisen
 */
public class Syotavat implements Paivitettava, Piirrettava{
    public static final byte OMENA = 1;
    public static final byte PILLERI = 2;
    
    private Random random;
    private ArrayList<Syotava> syotavat;
    
    
    public Syotavat() {
        syotavat = new ArrayList<>();
        random = new Random();
    }
    
    /**
     * Kutsuu kaikkien syötävien päivitä metodit
     * Kutsuu myös metodit jotka hoitaa syötävien poistamisen ja uusien lisäämisen
     * 
     * @param maailma maailma jossa kaikki syötävät sijaitsee
     */
    @Override
    public void paivita(Maailma maailma){
        synchronized(syotavat){
            for (Syotava syotava : syotavat) {
                syotava.paivita(maailma);
            }
        }
        poista();
        lisaaSyotavia(maailma);
    }
    
    /**
     * Lisää omenia jos niitä on alle 20 ja vain noin joka 100 peli ikki
     * Lisää pillereitä jos niitä ei ole ja vain noin joka 1000 pelitikki
     * 
     * @param maailma maailma jossa kaikki syötävät sijaitsee
     */
    private void lisaaSyotavia(Maailma maailma) {
        int omenienMaara = getOmenienMaara();
        int pillereidenMaara = syotavat.size() - omenienMaara;
        if(omenienMaara < 20){
            if(random.nextDouble()<0.01){
                lisaa(maailma, OMENA);
            }
        }
        if(pillereidenMaara < 1){
            if(random.nextDouble() < 0.001){
                lisaa(maailma, PILLERI);
            }
        }
    }
    
    /**
     * Lisää valitun syötävän satunnaiseen paikkaan
     * 
     * @param maailma maailma jossa kaikki syötävät sijaitsee
     * @param tyyppi Millainen syötävä lisätään
     */
    private void lisaa(Maailma maailma, byte tyyppi){
        switch(tyyppi){
            case OMENA:
                Omena o = (Omena)satunnainenPaikka(maailma, new Omena(new Vektori()));
                syotavat.add(o);
                break;
            case PILLERI:
                Pilleri p = (Pilleri)satunnainenPaikka(maailma, new Pilleri(new Vektori()));
                syotavat.add(p);
                break;
        }
    }
    
    /**
     * Palauttaa palikan satunnaisella paikalla joka on tarkistettu ettei se ole jonkun 
     * muun syötävän tai madon päällä
     * Luo uusia sattuman varaisia paikkoja kunnes sopiva löytyy
     * 
     * @param maailma maailma jossa kaikki syötävät sijaitsee
     * @param p Palikka jolle halutaan satunnaistaa paikka
     * @return Palikan sattumanvaraisella paikalla
     */
    private Palikka satunnainenPaikka(Maailma maailma, Palikka p){
        Boolean b1;
        do {
            b1 = false;
            p.koordinaatit = new Vektori(random.nextDouble()*(Ikkuna.LEVEYS-140)+70, random.nextDouble()*(Ikkuna.KORKEUS-140)+70);
            synchronized(syotavat){
                for (Syotava syotava : syotavat) {
                    if(Palikka.tormaa(p, syotava)){
                        b1 = true;
                        break;
                    }
                }
            }
            if(!b1){
                synchronized(maailma.getMato().getOsat()){
                    for (MadonOsa madonOsa : maailma.getMato().getOsat()) {
                        if(Palikka.tormaa(p, madonOsa)){
                            b1 = true;
                            break;
                        }
                    }
                }
            }
            
        } while (b1);
        return p;
    }
    
    /**
     * Poistaa kaikki poistettavaksi merkatut syötävät
     */
    private void poista(){
        ArrayList<Syotava> poistettavat = new ArrayList<>();
        synchronized(syotavat){
            for (Syotava syotava : syotavat) {
                if(syotava.poista){
                    poistettavat.add(syotava);
                }
            }
        }
        syotavat.removeAll(poistettavat);
    }
    
    /**
     * @return lista syötävistä
     */
    public ArrayList<Syotava> getSyotavat() {
        return syotavat;
    }
    
    /**
     * @return Omenien lukumäärä syötävistä
     */
    private int getOmenienMaara(){
        int maara = 0;
        synchronized(syotavat){
            for (Syotava syotava : syotavat) {
                if(syotava.getTyyppi() == OMENA){
                    maara++;
                }
            }
        }
        return maara;
    }
    
    /**
     * Kutsuu kaikkien syötävien piirä metodit
     * 
     * @param g Ikkunan grafiikka
     */
    @Override
    public void piirra(Graphics2D g) {
        synchronized(syotavat){
            for (Syotava syotava : syotavat) {
                syotava.piirra(g);
            }
        }
    }
}

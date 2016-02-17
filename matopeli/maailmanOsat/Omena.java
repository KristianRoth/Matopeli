
package matopeli.maailmanOsat;

import java.awt.Image;
import java.util.Random;
import matopeli.Maailma;
import matopeli.Vakioita;
import matopeli.Vektori;

/**
 * Luokka joka sisältää omenan logiikan
 */
public class Omena extends Syotava{
    
    /**
     * Luo uuden omenan annetuihin koordinaatteihin sattuman varaisella eliniällä
     * välillä [500, 4500]
     * 
     * @param koordinaatit omenan koordinaatit
     */
    public Omena(Vektori koordinaatit) {
        super(koordinaatit, new Random().nextInt(4000)+500);
    }
    
    /**
     * @return omenan tyypin
     */
    @Override
    public byte getTyyppi() {
        return Syotavat.OMENA;
    }
    
    /**
     * Lihottaa madon
     * 
     * @param maailma maailma missä tämä sijaitsee
     */
    @Override
    protected void tormaa(Maailma maailma) {
        super.tormaa(maailma);
        maailma.getMato().lihota();
    }
    
    /**
     * @return Omenan spraitin
     */
    @Override
    public Image getSpraitti() {
        return Vakioita.OMENASPRAITTI;
    }

    /**
     * @return Omenasta saatavat pisteet
     */
    @Override
    public int getPisteet() {
        return Vakioita.OMENAPISTEET;
    }

    /**
     * @return Omenan muuntamattomat rajat
     */
    @Override
    protected Vektori[] getRajat() {
        return Vakioita.OMENARAJAT;
    }
    
    /**
     * @return Omenan spraitin koko
     */
    @Override
    protected Vektori getSpraittiKoko() {
        return Vakioita.OMENASPRAITTIKOKO;
    }
    
    
    
    
}

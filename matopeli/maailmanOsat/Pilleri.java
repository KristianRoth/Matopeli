
package matopeli.maailmanOsat;

import java.awt.Image;
import java.util.Random;
import matopeli.Maailma;
import matopeli.Vakioita;
import matopeli.Vektori;

/**
 * Luokka joka sisältää pillerin logiikan
 */
public class Pilleri extends Syotava{
    
    /**
     * Luo uuden pillerin annettuihin koordinaatteihin 
     * ja antaa sille sattumanvaraisen eliniän väliltä [500, 2000]
     * 
     * @param koordinaatit pillerin koordinaatit
     */
    public Pilleri(Vektori koordinaatit) {
        super(koordinaatit, new Random().nextInt(1500)+500);
    }

    /**
     * @return Pillerin tyypin
     */
    @Override
    public byte getTyyppi() {
        return Syotavat.PILLERI;
    }
    
    /**
     * Laihduttaa madon
     * 
     * @param maailma maailma missä pilleri sijaitsee 
     */
    @Override
    protected void tormaa(Maailma maailma) {
        super.tormaa(maailma);
        maailma.getMato().laihduta();
    }
    
    /**
     * @return Pillerin spraitti
     */
    @Override
    public Image getSpraitti() {
        return Vakioita.PILLERISPRAITTI;
    }
    
    /**
     * @return Pilleristä saatavat pisteet
     */
    @Override
    public int getPisteet() {
        return Vakioita.PILLERIPISTEET;
    }

    /**
     * @return Pillerin muuntamattomat rajat
     */
    @Override
    protected Vektori[] getRajat() {
        return Vakioita.PILLERIRAJAT;
    }

    /**
     * @return Pillerin spraitin koko
     */
    @Override
    protected Vektori getSpraittiKoko() {
        return Vakioita.PILLERISPRAITTIKOKO;
    }
    
    
}

package matopeli.maailmanOsat;

import matopeli.maailmanOsat.Piirrettava;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Luokka joka hoitaa pisteiden laskemisen
 */
public class Pistelaskuri implements Piirrettava{
    private int pisteet;

    public Pistelaskuri() {
        pisteet = 0;
    }
    
    /**
     * @return tämän hetkinen pistetilanne
     */
    public int getPisteet() {
        return pisteet;
    }

    /**
     * Lisää pisteitä lisattavan verran
     * 
     * @param lisattava lisäyksen suuruus
     */
    public void lisaaPisteita(int lisattava){
        pisteet += lisattava;
        if(pisteet < 0){
            pisteet = 0;
        }
    }

    /**
     * Piirtää pistemäärän ikkunan vasempaan ylä kulmaan
     * 
     * @param g ikkunan grafiikka
     */
    @Override
    public void piirra(Graphics2D g) {
        g.setColor(Color.orange);
        g.drawString("Pisteet: "+this.getPisteet()+"p", 20, 20);
    }
}

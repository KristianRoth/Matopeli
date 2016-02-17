package matopeli.maailmanOsat;

import java.awt.Graphics2D;

/**
 * Rajapinta jota implementtaa kaikki jotka pitää piirtää näkyviin ikkunaan
 */
public interface Piirrettava extends MaailmanOsa{
    /**
     * Kutsutaan sen jälkeen kun päivittäminen on suoritettu
     * piirtää ikkunaan piirrettävän
     * 
     * @param g Ikkunan grafiikka
     */
    public abstract void piirra(Graphics2D g);
}
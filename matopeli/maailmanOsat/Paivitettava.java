
package matopeli.maailmanOsat;

import matopeli.Maailma;

/**
 * Rajapinta jota implementtaa kaikki osat joita pitää päivittää joka pelintikki
 */
public interface Paivitettava extends MaailmanOsa{
    /**
     * Metodi joka kutsutaan kerran joka pelintikki
     * 
     * @param maailma maailma missä päivitettävä sijaitsee
     */
    public abstract void paivita(Maailma maailma);
}

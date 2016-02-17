
package matopeli.maailmanOsat;

import matopeli.Maailma;
import matopeli.Vektori;

/**
 * Palikka luokan aliluokkka joka antaa madon osille yleisiä toimintoja kuten
 * 
 */
public abstract class MadonOsa extends Palikka{
    public static final int laskiLiike = 7; // määrittää lihavan kohdan liikkeen nopeuden matoa pitkin
    protected int laski; // määrittää osan lihavuuden tilan
    protected int tila; // määrittää sen minkä 3 spraitista palautetaan
    
    public MadonOsa(Vektori koordinaatit, Vektori suunta) {
        super(koordinaatit);
        this.suunta = suunta;
    }
    
    /**
     * Palauttaa madonosan pisteen joka on seuraavan osan aloitus piste
     * 
     * @return piste josta seuraava pala alkaa
     */
    public Vektori getPera(){
        return Vektori.plus(suunta.yksikköVektori().kerro(-12), super.getKoordinaatit());
    }
    
    /**
     * Palauttaa madon tilan joka kertoo kuinkamonentena madonosa on.
     * 
     * @return madon tila
     */
    protected int getTila(){
        return tila;
    }
    
    /**
     * Kutsuu super päivitän ja abstraktin liikuta metodin.
     * Ja kasvattaa laski muutujan arvoa
     * 
     * @param maailma kaikille yhteinen maailma
     */
    @Override
    public void paivita(Maailma maailma) {
        super.paivita(maailma);
        liikuta();
        if(this.laski != 0){
            laski++;
        }
    }
    
    /**
     * Metodi joka kutsutaan kun madonosa saa taaksensa uuden osan
     */
    protected abstract void eiViimeinen();
    
    /**
     * Kutsutaan päivittämisen yhteydessä sisältää osan liikkeen logiikan
     */
    protected abstract void liikuta();
}

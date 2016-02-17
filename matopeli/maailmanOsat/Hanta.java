
package matopeli.maailmanOsat;

import java.awt.Image;
import matopeli.Maailma;
import matopeli.Vakioita;
import matopeli.Vektori;

/**
 * Luokka joka määrittää Kaikkien muiden madonosien logiikan paitsi pään
 * 
 */
public class Hanta extends MadonOsa{
    private MadonOsa edellinen; //madossa edeltävä madonosa
    private boolean viimeinen; //Onko madon osa tällähetkellä viimeisenä madossa
    
    /**
     * Luo uuden madonosan edellisen perusteella
     * Suunta on sama kuin edellisellä ja koordinaatit edellisen getPera() metodin mukainen
     * kutsuu myös edellisen eiViimeinen() Metodia
     * 
     * @param edellinen Madossa enne oleva Madonosa
     */
    public Hanta(MadonOsa edellinen) {
        super(edellinen.getPera(), edellinen.getSuunta());
        this.edellinen = edellinen;
        this.edellinen.eiViimeinen();
        this.viimeinen = true;
        this.tila = edellinen.getTila()+1;
    }
    
    /**
     * metodi joka kutsutaan kun tämä ei enää ole viimeinen
     */
    @Override
    public void eiViimeinen(){
        viimeinen = false;
    }
    
    /**
     * Palauttaa oikean spraitin
     * jos viimeinen niin hännänspraitin muuten tilasta ja lihavuudesta riippuvan keskispraitin
     * 
     * @return Tällä hetkellä tarvittava spraitti
     */
    @Override
    public Image getSpraitti() {
        if(viimeinen){
            return Vakioita.HANTASPRAITIT[6];
        }
        if(laski != 0){
            return Vakioita.HANTASPRAITIT[tila%3+3];
        }
        return Vakioita.HANTASPRAITIT[tila%3];
    }
    
    /**
     * Päivittää kaikki madonhännän ominaisuudet
     * 
     * @param maailma kaikille yhteinen maailma
     */
    @Override
    public void paivita(Maailma maailma) {
        super.paivita(maailma);
        hoidaLihavuus(maailma);
    }
    
    
    /**
     * Lopettaa pelin
     * 
     * @param maailma kaikille yhteinen maailma
     */
    @Override
    protected void tormaa(Maailma maailma) {
        maailma.lopeta();
    }
    
    /**
     * Liikuttaa madon osan edellisen perään ja kääntää sitä siten että 
     * sen suunta on ennen liikutusta olevasta perän pisteestä kohti uutta koordinaattia
     */
    @Override
    protected void liikuta() {
        Vektori apu = this.getPera();
        koordinaatit = edellinen.getPera();
        this.suunta = Vektori.plus(apu.kerro(-1), koordinaatit);
    }
    
    /**
     * Liikuttaa lihavaa kohtaa pitkin matoa
     * 
     * @param maailma kaikille yhteinen maailma
     */
    private void hoidaLihavuus(Maailma maailma) {
        if(edellinen.laski == MadonOsa.laskiLiike){
            edellinen.laski = 0;
            if(this.viimeinen){
                maailma.getMato().kasvata();
            }else{
                this.laski = 1;
            }
        }
    }

    /**
     * @return muuntamattomat törmäysrajat
     */
    @Override
    protected Vektori[] getRajat() {
        if(viimeinen){
            return Vakioita.HANTARAJAT;
        }
        return Vakioita.KESKIRAJAT;
    }

    /**
     * @return Tämänhetkisen spraitin koko
     */
    @Override
    protected Vektori getSpraittiKoko() {
        if(viimeinen){
            return Vakioita.HANTASPRAITTIKOKO;
        }
        if(laski != 0){
            return Vakioita.LASKIKESKISPRAITTIKOKO;
        }
        return Vakioita.KESKISPRAITTIKOKO;
    }
}

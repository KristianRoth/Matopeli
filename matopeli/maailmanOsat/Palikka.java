package matopeli.maailmanOsat;

import java.awt.Graphics2D;
import java.awt.Image;
import matopeli.Maailma;
import matopeli.Vektori;

/**
 * Perus maailman osa jota kaikki muut maailman osat extendaa
 * Antaa perus toimintoja kuten paikan, suunnan, törmäyksen testauksen ja piirtämisen
 */
public abstract class Palikka implements Paivitettava, Piirrettava{
    protected Vektori koordinaatit;
    protected Vektori suunta;
    
    public Palikka(Vektori koordinaatit) {
        this.koordinaatit = koordinaatit;
        this.suunta = new Vektori(0, -1);
    }
    
    /**
     * Laskee kulman palikan suunnan ja ylös vektorien välille
     * 
     * @return Kulma suunnan ja ylös vektorin välillä [0, 2PI]
     */
    private double getKulma(){
        Vektori ylos = new Vektori(0, -1);
        double kulma = Vektori.vektorienVälinenKulma(this.suunta, ylos);
        if(this.suunta.i < 0){
            return -kulma + 2*Math.PI;
        }else{
            return kulma;
        }    
    }
    
    /**
     * @return Palikan koordinaatti vektori
     */
    public Vektori getKoordinaatit() {
        return koordinaatit;
    }
    
    /**
     * @return Palikan suunta vektori
     */
    public Vektori getSuunta() {
        return suunta;
    }
    
    /**
     * Tarkistaa törmääkö kaksi palikkaa ottamalla molempien törmäys rajat
     * ja palauttaa true jos yksikin a:n rajoista leikkaa jonkin b:n rajoista
     * 
     * @param a ensimmäinen tarkistettava palikka
     * @param b toinen tarkistettava palikka
     * @return true jos palikat a ja b törmää muuten false
     */
    protected static boolean tormaa(Palikka a, Palikka b){
        Vektori[] aRajat = a.getTormausRajat();
        Vektori[] bRajat = b.getTormausRajat();
        Vektori aa;
        Vektori bb;
        for (int i = 0; i < aRajat.length; i++) {
            for (int j = 0; j < bRajat.length; j++) {
                aa = Vektori.plus(aRajat[i].kerro(-1),aRajat[(i+1)%aRajat.length]);
                bb = Vektori.plus(bRajat[j].kerro(-1),bRajat[(j+1)%bRajat.length]);
                if(Vektori.leikkaa(aa, Vektori.plus(a.koordinaatit, aRajat[i]), bb, Vektori.plus(b.koordinaatit, bRajat[j]))){
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Kääntää palikan törmäysrajat palikan suunnan mukaiseksi
     * 
     * @return Palikan törmäysrajat korjattuna palikan suunan suhteen
     */
    public Vektori[] getTormausRajat(){
        Vektori[] rajat = getRajat();
        Vektori[] korjatutRajat = new Vektori[rajat.length];
        double kulma = Vektori.vektorienVälinenKulma(suunta, new Vektori(0,1));
        if(suunta.i>0){
            kulma = -kulma;
        }
        for (int i = 0; i < rajat.length; i++) {
            korjatutRajat[i] = rajat[i].kaanna(kulma);
        }
        return korjatutRajat;
    }
    
    /**
     * Kutsuu abstraktia metodia tormaa jos madon pää törmää tämän palikan kanssa
     * 
     * @param maailma kaikille yhteinen maailma
     */
    @Override
    public void paivita(Maailma maailma){
        if(Palikka.tormaa(maailma.getMato().getPaa(), this)){
            tormaa(maailma);
        }
    }
    
    /**
     * Kääntää palikan spraitin ja piirtää sen oikeaan paikkaan
     * 
     * @param g Ikkunan grafiikka
     */
    @Override
    public void piirra(Graphics2D g) {
        g.rotate(this.getKulma(), this.koordinaatit.i, this.koordinaatit.j);
        g.drawImage(this.getSpraitti(), (int)(this.getKoordinaatit().i-this.getSpraittiKoko().i/2), (int)(this.getKoordinaatit().j-this.getSpraittiKoko().j/2), null);
        g.rotate(-this.getKulma(), this.koordinaatit.i, this.koordinaatit.j);
    }
    
    /**
     * Abstrakti metodi joka palauttaa palikan törmäyksen rajat normaalimuodossa
     * eli siten että niitä ei ole käännetty tämänhetkiseen suunan mukaiseksi
     * 
     * @return törmäysrajat normaalimuodossa
     */
    protected abstract Vektori[] getRajat();
    
    /**
     * Hakee palikan sen hetkisen spraitin mikä halutaan piirtää
     * 
     * @return Palikan spraitin
     */
    protected abstract Image getSpraitti();
    
    /**
     * Hakee palikan sen hetkisen spraitin koon
     * 
     * @return Vektori listan jossa on spraitin koko {leveys, korkeus}
     */
    protected abstract Vektori getSpraittiKoko();
    
    /**
     * Metodi joka kutsutaan aina kun tämä palikka törmää madon pään kanssa
     * 
     * @param maailma kaikille yhteinen maailma
     */
    protected abstract void tormaa(Maailma maailma);
}

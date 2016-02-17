
package matopeli;

import java.awt.Image;
import javax.swing.ImageIcon;
import matopeli.maailmanOsat.Palikka;

/**
 * Yleisiä vakioita pelin käyttetäväksi
 * Sisältää kaikki kuvat ja törmäysrajat
 */
public class Vakioita {
    /**
     * Kaikkien palikkojen Spraitit
     */
    public static final Image PAASPRAITTI =                 kuva("/resurssit/paa.png");
    public static final Image[] HANTASPRAITIT = {           kuva("/resurssit/keski_1.png"),
                                                            kuva("/resurssit/keski_2.png"),
                                                            kuva("/resurssit/keski_3.png"),
                                                            kuva("/resurssit/laskikeski_1.png"),
                                                            kuva("/resurssit/laskikeski_2.png"),
                                                            kuva("/resurssit/laskikeski_3.png"),
                                                            kuva("/resurssit/hanta.png")};
    public static final Image OMENASPRAITTI =               kuva("/resurssit/omena.png");
    public static final Image PILLERISPRAITTI =             kuva("/resurssit/pilleri.png");
    public static final Image TAUSTASPRAITTI =              kuva("/resurssit/tausta2.png");
    public static final Image REUNASPRAITTI =               kuva("/resurssit/reuna.png");
    
    /**
     * Kaikkien palikoiden spraittien koot
     */
    public static final Vektori PAASPRAITTIKOKO =           kuvaKoko(PAASPRAITTI);
    public static final Vektori KESKISPRAITTIKOKO =         kuvaKoko(HANTASPRAITIT[0]);
    public static final Vektori LASKIKESKISPRAITTIKOKO =    kuvaKoko(HANTASPRAITIT[3]);
    public static final Vektori HANTASPRAITTIKOKO =         kuvaKoko(HANTASPRAITIT[6]);
    public static final Vektori OMENASPRAITTIKOKO =         kuvaKoko(OMENASPRAITTI);
    public static final Vektori PILLERISPRAITTIKOKO =       kuvaKoko(PILLERISPRAITTI);
    
    /**
     * Kaikkien palikoiden törmäysrajat
     */
    public static final Vektori[] PAARAJAT = {      new Vektori(-10, 12),
                                                    new Vektori(-10, 24),
                                                    new Vektori(0, 37),
                                                    new Vektori(10, 24),
                                                    new Vektori(10, 12)};
    public static final Vektori[] KESKIRAJAT = {    new Vektori(-10, 0),
                                                    new Vektori(-10, -13),
                                                    new Vektori(10, -13),
                                                    new Vektori(10, 0)};
    public static final Vektori[] HANTARAJAT = {    new Vektori(-10, 0),
                                                    new Vektori(-10, -8),
                                                    new Vektori(0, -35),
                                                    new Vektori(10, -8),
                                                    new Vektori(10, 0)};
    public static final Vektori[] OMENARAJAT = {    new Vektori(-10, 10),
                                                    new Vektori(-10, -10),
                                                    new Vektori(10, -10),
                                                    new Vektori(10, 10)};
    public static final Vektori[] PILLERIRAJAT = {  new Vektori(-5, 9),
                                                    new Vektori(-5, -9),
                                                    new Vektori(5, -9),
                                                    new Vektori(5, 9)};
    public static final Vektori[] REUNARAJAT = {    new Vektori(-50, -50),
                                                    new Vektori(-50, -Ikkuna.KORKEUS+50),
                                                    new Vektori(-Ikkuna.LEVEYS+50, -Ikkuna.KORKEUS+50),
                                                    new Vektori(-Ikkuna.LEVEYS+50, -50)};
    
    /**
     * Syötävien pistemäärät
     */
    public static final int OMENAPISTEET =      100;
    public static final int PILLERIPISTEET =    -2000;
    
    /**
     * Pelin alkaessa näytettävä ohje
     */
    public static final String[] ohje = {   "Paina A ja D ohjataksesi matoa\n",
                                            "Syö omenia kasvaaksesi ja kerätäksesi pisteitä\n",
                                            "Syö pilleri aina kun tuntuu että olet liian pitkä\n",
                                            "Omenasta saa "+OMENAPISTEET+"p ja pilleristä menettää "+PILLERIPISTEET+"p",
                                            "Paina ENTER aloittaaksesi"};
    
    
    /**
     * Hakee kuvan resurseista ja palauttaa sen 
     * 
     * @param polku URL joka osittaa kuvaan
     * @return polussa oleva kuva
     */
    private static Image kuva(String polku) {
        ImageIcon g = new ImageIcon(Palikka.class.getResource(polku));
        return g.getImage();
    }
    
    /**
     * Laskee kuvan koon ja palauttaa sen Vektorina
     * 
     * @param kuva Kuva jonka koko halutaan
     * @return Kuvan koko vektorissa i = leveys j = korkeus
     */
    private static Vektori kuvaKoko(Image kuva){
        ImageIcon ikoni = new ImageIcon(kuva);
        return new Vektori(ikoni.getIconWidth(), ikoni.getIconHeight());
    }
}

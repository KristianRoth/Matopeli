package matopeli.maailmanOsat;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import matopeli.Ikkuna;
import matopeli.Maailma;
import matopeli.Vakioita;
import matopeli.Vektori;

/**
 * Luokka joka sisältää logiikan pelin reunoja varten
 */
public class Reunat extends Palikka{
    private Image spraitti;
    
    public Reunat() {
        super(new Vektori());
        teeSpraitti();
    }
    
    /**
     * Luo spraitin joka sopii ikkunaan sisältää reunojen ja taustan spraitit
     */
    private void teeSpraitti() {
        Image kuva = new BufferedImage(Ikkuna.LEVEYS, Ikkuna.KORKEUS, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D)kuva.getGraphics();
        for (int i = 0; i < Ikkuna.LEVEYS; i += 50) {
            for (int j = 0; j < Ikkuna.KORKEUS; j += 50) {
                g.drawImage(Vakioita.REUNASPRAITTI, i, j, null);
            }
        }
        g.fillRect(50, 50, Ikkuna.LEVEYS-100, Ikkuna.KORKEUS-100);
        g.setComposite(AlphaComposite.SrcOut);
        g.fillRect(50, 50, Ikkuna.LEVEYS-100, Ikkuna.KORKEUS-100);
        
        spraitti = new BufferedImage(Ikkuna.LEVEYS, Ikkuna.KORKEUS, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gg = (Graphics2D)spraitti.getGraphics();
        for (int i = 0; i < Ikkuna.LEVEYS; i+=100) {
            for (int j = 0; j < Ikkuna.KORKEUS; j+=100) {
                gg.drawImage(Vakioita.TAUSTASPRAITTI, i, j, null);
            }
        }
        gg.drawImage(kuva, 0, 0, null);
    }

    /**
     * Piirtää taustan overridaa palikan piirra koska 
     * tausta ei liiku/käänny
     * 
     * @param g Ikkunan grafiikka
     */
    @Override
    public void piirra(Graphics2D g) {
        g.drawImage(getSpraitti(), 0, 0, null);
    }
    
    /**
     * @return taustan spraitin
     */
    @Override
    public Image getSpraitti() {
        return spraitti;
    }
    
    /**
     * Kutsuu super.paivita()
     * 
     * @param maailma maailma missä reunat ovat
     */
    @Override
    public void paivita(Maailma maailma) {
        super.paivita(maailma);
    }
    
    /**
     * Lopettaa pelin
     * 
     * @param maailma maailma missä reunat ovat
     */
    @Override
    protected void tormaa(Maailma maailma) {
        maailma.lopeta();
    }

    /**
     * @return reunojen rajat
     */
    @Override
    protected Vektori[] getRajat() {
        return Vakioita.REUNARAJAT;
    }
    
    
    @Override
    protected Vektori getSpraittiKoko() {
        return new Vektori();
    }

    
    
}

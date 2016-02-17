
package matopeli;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;


public class Kangas extends JPanel{
    
    private Maailma maailma;
    
    public Kangas(Maailma ma) {
        this.maailma = ma;
    }
    
    /**
     * paintComponent kutsutaan kun ikkunaa päivitetään ja se piirtää kaiken ruudulle
     * 
     * @param gg Ikkunan grafiikka
     */
    @Override
    public void paintComponent(Graphics gg){
        super.paintComponent(gg);
        Graphics2D g = (Graphics2D) gg;
        
        maailma.piirra(g);
        if(!maailma.kaunnissa()){
            piirraOhje(g);
        }
    }
    
    /**
     * Kirjoittaa ruudulle ohjeen
     * 
     * @param g Ikkunan grafiikka
     */
    private void piirraOhje(Graphics2D g) {
        for (int i = 0; i < Vakioita.ohje.length; i++) {
            g.drawString(Vakioita.ohje[i], 100, 100+20*i);
        }
        
    }
}

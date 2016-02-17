
package matopeli;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ikkuna extends JFrame{
    private Maailma maailma;
    public static final int LEVEYS = 1500;
    public static final int KORKEUS = 1000;
    
    /**
     * Luo ikkunan
     * 
     * @param maailma maailma missä kaikki piirrettävät ovat
     */
    public Ikkuna(Maailma maailma ){
        this.maailma = maailma;
        setSize(LEVEYS+8, KORKEUS+28);
        setResizable(false);
        JPanel kangas = new Kangas(maailma);
        add(kangas);
        addKeyListener(maailma.getNappaintenKuuntelija());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    /**
     * Piirtää ikkunan uudelleen
     */
    public void piirra(){
        repaint();
    }

    public Maailma getMaailma() {
        return maailma;
    }
}

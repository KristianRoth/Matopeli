package matopeli.maailmanOsat;

import java.awt.Image;
import java.awt.event.KeyEvent;
import matopeli.Maailma;
import matopeli.Vakioita;
import matopeli.Vektori;

/**
 * Luokka joka hoitaa madon pään logiikan ja madonliikumisen
 */
public class Paa extends MadonOsa{
    
    public Paa(Vektori koordinaatit, Vektori suunta) {
        super(koordinaatit, suunta);
    }
    
    /**
     * @return seuraavan osan koordinaatit
     */
    @Override
    public Vektori getPera() {
        return this.koordinaatit;
    }
    
    /**
     * @return pään spraitti
     */
    @Override
    public Image getSpraitti() {
        return Vakioita.PAASPRAITTI;
    }
    
    /**
     * Kutsuu super.paivita() ja lukee käyttäjän inputin ja kääntää päätä sen mukaisesti
     * 
     * @param maailma kaikille yhteinen maailma
     */
    @Override
    public void paivita(Maailma maailma) {
        if(maailma.getNappaintenKuuntelija().painettu(KeyEvent.VK_A)){
            this.suunta = this.suunta.kaanna(-0.08);
        }
        if(maailma.getNappaintenKuuntelija().painettu(KeyEvent.VK_D)){
            this.suunta = this.suunta.kaanna(0.08);
        }
        super.paivita(maailma);
        
    }
    
    /** 
     * Mitään ei tapahdu
     * 
     * @param maailma kaikille yhteinen maailma
     */
    @Override
    protected void tormaa(Maailma maailma) {
    }
    
    
    /**
     * @return muuntamattomat törmäysrajat
     */
    @Override
    protected Vektori[] getRajat() {
        return Vakioita.PAARAJAT;
    }
    
    /**
     * @return pään spraitin koko
     */
    @Override
    protected Vektori getSpraittiKoko() {
        return Vakioita.PAASPRAITTIKOKO;
    }
    
    /**
     * Mitään ei tapahdu
     */
    @Override
    public void eiViimeinen() {
    }
    
    /**
     * Liikuttaa päätä suunnan verran eteenpäin
     */
    @Override
    public void liikuta() {
        this.koordinaatit = Vektori.plus(koordinaatit, suunta);
    }
    
}

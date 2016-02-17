package matopeli;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

/**
 * Luokka joka sisältää käyttäjän näppäinten tunnistamisen
 */
public class NappaintenKuuntelija implements KeyListener{
    /**
     * HashMappi joka sisältää tarvittavien näppäinten tilan true painettu false ei painettu
     */
    public HashMap nappaimet;

    public NappaintenKuuntelija() {
        nappaimet = new HashMap();
        nappaimet.put(KeyEvent.VK_A, false);
        nappaimet.put(KeyEvent.VK_D, false);
        nappaimet.put(KeyEvent.VK_W, false);
        nappaimet.put(KeyEvent.VK_ENTER, false);
        
        
    }
    
    /**
     * Palautta onko näppäin painettu
     * 
     * @param näppäin näppäin jonka halutaan tietää onko painettu
     * @return true jos näppäin on painettu false muuten
     */
    public boolean painettu(int näppäin){
        return (boolean)nappaimet.get(näppäin);
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    /**
     * Asettaa painetun näppäimen arvoksi true
     * ja kutsuu notify() metodin
     * 
     * @param e keyeventti joka sisältää painetun näppäimen
     */
    @Override
    public void keyPressed(KeyEvent e) {
        synchronized(this){
            this.notify();
        }
        if(nappaimet.containsKey(e.getKeyCode())){
            nappaimet.put(e.getKeyCode(), true);
            
        }
        
    }
    
    /**
     * Asettaa nostetun näppäimen arvoksi false
     * 
     * @param e keyeventti joka sisältää nostetun näppäimen
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if(nappaimet.containsKey(e.getKeyCode())){
            nappaimet.put(e.getKeyCode(), false);
        }
    }
    
    
    
}

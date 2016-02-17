package matopeli;

/**
 * Luokka joka huolehtii tehtävien järjestyksestä ja ajoittamisesta
 */
public class Moottori implements Runnable{
    private Ikkuna ikkuna;
    private Maailma maailma;
    
    public Moottori(Ikkuna i) {
        this.ikkuna = i;
        maailma = this.ikkuna.getMaailma();
    }
    
    /**
     * Kutsuu metodeja piirra piirrä odota järjestyksessä kunnes peli on määrätty lopetettavaksi 
     */
    @Override
    public void run() {
        long time;
        while(true){
            aloitus();
            while(maailma.kaunnissa()){
                time = System.nanoTime();
                päivitä();
                piirra();
                odota((System.nanoTime()-time)/1000000);
            }
        }
    }
    
    /**
     * Kutsuu maailman piirra metodin
     */
    private void päivitä() {
        maailma.paivita();
    }
    
    /**
     * Kutsuu ikkunan uudelleen piitämisen
     */
    private void piirra() {
        ikkuna.piirra();
    }
    
    /**
     * Odottaa 1/60 sekunnin ottaa huomioon jo päivittämiseen kuluneen ajan
     * 
     * @param time päivittämiseen ja piirtämiseen kulunut aika
     */
    private void odota(long time) {
        time = 1000/60-time;
        if(time < 0){
            time = 0;
        }
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            
        }
    }
    
    /**
     * Piirtää ikkunan kerran ja kutsuu sitten maailman metodin joka aloittaa pelin uudestaan
     */
    private void aloitus() {
        piirra();
        maailma.lopetus();
    }
}


package matopeli;


public class Matopeli {
    public static void main(String[] args) {
        
        Maailma ma = new Maailma(); //luodaan maailma mikä sisältää kaikki pelin logiikan ja osat
        Ikkuna i = new Ikkuna(ma); //luodaan käyttäjälle näkyvä ikkuna
        Moottori m = new Moottori(i); //luodaan pelimoottori joka tahdistaa maailman päivittämisen ja ikkunan piirtämisen
        m.run(); //Käynnistetään peli
    }
}

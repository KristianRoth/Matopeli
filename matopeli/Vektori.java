
package matopeli;


/**
 * Kaksi uloitteinen vektori jolla on tärkeitä vektoreita koskevia metodeja
 */
public class Vektori {
    public double i;
    public double j;

    /**
     * Luo uuden vektorin annetuilla parametreillä
     * 
     * @param i i
     * @param j j
     */
    public Vektori(double i, double j) {
        this.i = i;
        this.j = j;
    }
    
    /**
     * Luo uuden 0 vektorin
     */
    public Vektori() {
        this.i = 0;
        this.j = 0;
    }
    
    /**
     * Kertoo vektorin vakiolla eli kasvattaa vektorin pituutta a:n verran
     * 
     * @param a kerroin
     * @return kerrottu vektori
     */
    public Vektori kerro(double a){
        return new Vektori(this.i*a, this.j*a);
    } 
    
    /**
     * @return vektorin pituus
     */
    public double pituus(){
        return Math.sqrt(Math.pow(this.i,2)+Math.pow(this.j,2));
    }
    
    /**
     * Palauttaa uuden vektorin joka on parametreiksi annettujen vektorien summa
     * 
     * @param a summan ensimmäinen vektori
     * @param b summan toinen vektori
     * @return uusi vektori a + b
     */
    public static Vektori plus(Vektori a, Vektori b){
        return new Vektori(a.i+b.i, a.j+b.j);
    }
    
    /**
     * Palauttaa kahden vektorin välisen pistetulon
     * 
     * @param a pistetulon ensimmäinen vektori
     * @param b pistetulon toinen vektori
     * @return pistetulo a:n ja b:n välillä
     */
    public static double pistetulo(Vektori a, Vektori b){
        return a.i*b.i + a.j*b.j;
    }
    
    /**
     * Palauttaa vektorien välisen pienemmän kulman välillä [-PI, PI]
     * 
     * @param a ensimmäinen vektori
     * @param b toinen vektori
     * @return a:n ja b:n välisen pienemmän kulman suuruus radiaaneissa
     */
    public static double vektorienVälinenKulma(Vektori a, Vektori b){
        double pistetulo = Vektori.pistetulo(a,b);
        double pituuksienTulo = a.pituus()*b.pituus();
        if(pituuksienTulo != 0){
            return Math.acos(pistetulo/pituuksienTulo);
        }
        return 0;
    }
    
    /**
     * @return vektorin suuntaisen vektorin jonka pituus on 1
     */
    public Vektori yksikköVektori(){
        if (this.pituus() == 0){
            return new Vektori(0,0);
        }
        return new Vektori(i/this.pituus(), j/this.pituus());
    }
    
    /**
     * Kääntää vektoria annettujen asteiden määrän positiivinen myötäpäivään negatiivinen vastapäivään
     * 
     * @param astetta kääntämisen suuruus radiaaneissa
     * @return käännetty vektori
     */
     public Vektori kaanna(double astetta){
        if(astetta == 0){
            return this;
        }
        Vektori apu = new Vektori(-Math.signum(astetta)*j,Math.signum(astetta)*i);
        apu = apu.yksikköVektori();
        apu = Vektori.plus(apu, this.yksikköVektori().kerro(Math.signum(astetta)/Math.tan(astetta)));
        return apu.yksikköVektori().kerro(this.pituus());
        
    }
    
    public static double ristiTulo(Vektori a, Vektori b){
        
        return a.i*b.j-a.j*b.i;
    }
    /**
     * Laskee leikkaako kaksi vektoria
     * Lähde: http://stackoverflow.com/a/565282
     * 
     * @param a ensimmäinen vektori
     * @param aLahto a vektorin lähtöpiste
     * @param b toinen vektori 
     * @param bLahto b vektorin lähtöpiste
     * @return true jos vektorit leikkaa muuten false
     */
    public static boolean leikkaa(Vektori a, Vektori aLahto, Vektori b, Vektori bLahto){
        double ab = Vektori.ristiTulo(a, b);
        if(ab == 0){
            return false;
        }
        Vektori apu = Vektori.plus(bLahto, aLahto.kerro(-1));
        double t = Vektori.ristiTulo(apu, b)/ab;
        double u = Vektori.ristiTulo(apu, a)/ab;
        if(u>0 && t>0 && u<1 && t<1){
            return true;
        }
        return false;
    }
}

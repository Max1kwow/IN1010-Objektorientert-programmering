abstract class Resept {

    public final int id;
    private static int teller = 0;
    protected Legemiddel legemiddel;
    protected Lege utskrivendeLege;
    protected Pasient pasient; 
    protected int reit; 


    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasient = pasient;
        this.reit = reit;

        id = teller;
        teller++;
    }


    public int hentId(){
        return id;
    }


    public Legemiddel hentLegemiddel(){
        return legemiddel; 
    }

    public Lege hentLege(){
        return utskrivendeLege; 
    }

    public Pasient hentPasientId(){
        return pasient;
    }

    public int hentReit(){
        return reit;
    }


    public boolean bruk(){
        if (reit > 0){
            reit--;
            return true;
        } else {
            return false;
        }

    }

    abstract public String farge(); 

    abstract public int prisAaBetale(); 

    abstract public String type();

    @Override
    public String toString() {
        String utskrift = "\n***";
        utskrift += "\nPasient id: " + pasient;
        utskrift += legemiddel;
        utskrift += "\nUtskrivende lege: " + utskrivendeLege;
        utskrift += "\nAntall ganger som er igjen paa resepten: " + reit;
        return utskrift;
    }


}

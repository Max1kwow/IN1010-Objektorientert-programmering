abstract class Resept {

    public final int id;
    private static int teller = 0;
    protected Legemiddel legemiddel;
    protected Lege utskrivendeLege;
    protected int pasientId;
    protected int reit; //En resept har et antall ganger som er igjen på resepten,  hvis reit = 0, er resepten ugyldig

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasientId = pasientId;
        this.reit = reit;

        id = teller;
        teller++;
    }


    public int hentId(){
        return id;
    }


    public Legemiddel hentLegemiddel(){
        return legemiddel; //henter tilhørende Legemiddel
    }

    public Lege hentLege(){
        return utskrivendeLege; //henter utskrivende Lege
    }

    public int hentPasientId(){
        return pasientId;
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

    abstract public String farge(); //Returnerer reseptens farge, enten hvit eller blaa

    abstract public int prisAaBetale(); //Returnerer prisen pasienten må betale.

    @Override
    public String toString() {
        String utskrift = "\n***";
        utskrift += "\nPasient id: " + pasientId;
        utskrift += legemiddel;
        utskrift += "\nUtskrivende lege: " + utskrivendeLege;
        utskrift += "\nAntall ganger som er igjen på resepten: " + reit;
        return utskrift;
    }


}

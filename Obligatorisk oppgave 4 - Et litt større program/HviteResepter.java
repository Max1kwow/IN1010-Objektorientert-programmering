class HviteResepter extends Resept{

    public String type;

    public HviteResepter(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        super(legemiddel, utskrivendeLege, pasient, reit);
        type = "hvit";
    }

    @Override
    public String farge() {
        return "Hvite resept";
    }

    @Override
    public int prisAaBetale() {
        return legemiddel.hentPris();
    }

    public String type(){
        return type;
    }

    @Override
    public String toString() {
        return super.toString() + "\n***" + "\nResept farge: " + farge() + "\nPris: " + prisAaBetale();
    }
}

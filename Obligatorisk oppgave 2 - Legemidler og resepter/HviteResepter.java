class HviteResepter extends Resept{

    public HviteResepter(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }

    @Override
    public String farge() {
        return "Hvite resept";
    }

    @Override
    public int prisAaBetale() {
        return legemiddel.hentPris();
    }

    @Override
    public String toString() {
        return super.toString() + "\n***" + "\nResept farge: " + farge() + "\nPris: " + prisAaBetale();
    }
}

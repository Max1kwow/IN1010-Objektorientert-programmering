class BlaaResept extends Resept{

    public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }

    @Override
    public String farge() {
        return "Blå resept";
    }

    @Override
    public int prisAaBetale() {

        if (legemiddel.hentPris() > 0){
            int sum = (int) Math.round(legemiddel.hentPris() / 4);
            return sum;
        } else {
            return 0;
        }

    }

    @Override
    public String toString() {

        return super.toString() + "\n***" + "\nResept farge: " + farge() + "\nPris: " + prisAaBetale();
    }
}

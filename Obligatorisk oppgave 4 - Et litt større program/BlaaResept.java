class BlaaResept extends Resept{
    
    public String type;

    public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        super(legemiddel, utskrivendeLege, pasient, reit);
        type = "blaa";
    }

    @Override
    public String farge() {
        return "Blaa resept";
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

    public String type(){
        return type;
    }

    @Override
    public String toString() {

        return super.toString() + "\n***" + "\nResept farge: " + farge() + "\nPris: " + prisAaBetale();
    }
}

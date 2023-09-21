class PResept extends HviteResepter{

    public PResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }

    @Override
    public int prisAaBetale(){

        if (legemiddel.hentPris() > 108){
            int sum = legemiddel.hentPris() - 108;
            return sum;
        } else {
            return 0;
        }

    }


    @Override
    public String toString() {
        return super.toString();
    }





}




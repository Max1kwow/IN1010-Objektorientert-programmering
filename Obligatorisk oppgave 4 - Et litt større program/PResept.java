class PResept extends HviteResepter{

    public String type;

    public PResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        super(legemiddel, utskrivendeLege, pasient, reit);
        type = "p";
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

    public String type(){
        return type;
    }

    @Override
    public String toString() {
        return super.toString();
    }





}




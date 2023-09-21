class MilResept extends HviteResepter{

    public MilResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId){
        super(legemiddel, utskrivendeLege, pasientId, 3); //hvis vi vil kontrollere instansvariabel og at bruker skal ikke kunne endre den da skriver vi den direkte her

    }

    @Override
    public int prisAaBetale() {
        return 0; //prisen er 0 fordi at det er 100% rabatt
    }


    @Override
    public String toString() {
        return super.toString();
    }



}

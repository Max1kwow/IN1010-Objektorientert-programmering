class MilResept extends HviteResepter{

    public String type;

    public MilResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient){
        super(legemiddel, utskrivendeLege, pasient, 3); 
        type = "militaer";

    }

    @Override
    public int prisAaBetale() {
        return 0; 
    }

    public String type(){
        return type;
    }

    @Override
    public String toString() {
        return super.toString();
    }



}

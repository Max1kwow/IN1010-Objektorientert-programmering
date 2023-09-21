class Vanlig extends Legemiddel{

    public String type;

    public Vanlig(String navn, int pris, double virkestoff){
        super(navn, pris, virkestoff);
        type = "Vanlig";
    }


    public String type(){
        return type;
    }

    public int hentStyrke(){
        return 0;
    }

}

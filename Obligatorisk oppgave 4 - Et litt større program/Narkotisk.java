class Narkotisk extends Legemiddel{

    protected int narkotiskStyrken;
    public String type;

    public Narkotisk(String navn, int pris, double virkestoff, int styrken){
        super(navn, pris, virkestoff);
        narkotiskStyrken = styrken;
        type = "Narkotisk";
    }

    public int hentStyrke(){
        return narkotiskStyrken;
    }

    public String type(){
        return type;
    }

  

    @Override
    public String toString() {
        return super.toString() + "\n***" + "\nNarkotisk styrken: " + narkotiskStyrken;
    }

}

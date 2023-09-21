class Narkotisk extends Legemiddel{

    private int narkotiskStyrken;

    public Narkotisk(String navn, int pris, double virkestoff, int styrken){
        super(navn, pris, virkestoff);
        narkotiskStyrken = styrken;
    }

    public int hentStyrke(){
        return narkotiskStyrken;
    }


    @Override
    public String toString() {
        return super.toString() + "\n***" + "\nNarkotisk styrken: " + narkotiskStyrken;
    }

}

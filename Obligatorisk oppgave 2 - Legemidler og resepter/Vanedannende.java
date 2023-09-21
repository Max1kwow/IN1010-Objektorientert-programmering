class Vanedannende extends Legemiddel{

    private int vanedannendeStyrken;

    public Vanedannende(String navn, int pris, double virkestoff, int styrken){
        super(navn, pris, virkestoff);
        vanedannendeStyrken = styrken;
    }

    @Override
    public String toString() {
        return super.toString() + "\nVanedannende styrken: " + vanedannendeStyrken;
    }

}

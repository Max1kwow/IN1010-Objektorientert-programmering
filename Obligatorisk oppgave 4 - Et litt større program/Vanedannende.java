class Vanedannende extends Legemiddel{

    protected int vanedannendeStyrken;
    public String type;

    public Vanedannende(String navn, int pris, double virkestoff, int styrken){
        super(navn, pris, virkestoff);
        vanedannendeStyrken = styrken;
        type = "Vanedannende";
    }

    public String type(){
        return type;
    }

    
    public int hentStyrke(){
        return vanedannendeStyrken;
    }



    @Override
    public String toString() {
        return super.toString() + "\nVanedannende styrken: " + vanedannendeStyrken;
    }

}

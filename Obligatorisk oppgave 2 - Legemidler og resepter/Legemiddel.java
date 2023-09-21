abstract class Legemiddel {

    public final String navn;
    private int pris;
    public double virkestoff;
    public final int id;
    private static int teller = 0;

    public Legemiddel(String navn, int pris, double virkestoff){
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;

        id = teller;
        teller++;
    }

    public int hentPris(){
        return pris;
    }

    public void settNyPris(int nyPris){
        pris = nyPris;
    }


    @Override
    public String toString() {
        String utskrift = "\n***";
        utskrift += "\n*Legemiddel: " + navn;
        utskrift += "\nPris: " + pris + "kr";
        utskrift += "\nVirkestoff: " + virkestoff;
        utskrift += "\nid: " + id;
        return utskrift;
    }
}

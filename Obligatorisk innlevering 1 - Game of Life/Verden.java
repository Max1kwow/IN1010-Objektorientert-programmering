public class Verden {

    public int antRader;
    public int antKolonner;
    public Rutenett rutenett;
    public int genNr = 0;

    public Verden(int antRader, int antKolonner){
        this.antRader = antRader;
        this.antKolonner = antKolonner;
        rutenett = new Rutenett(antRader, antKolonner);
        rutenett.fyllMedTilfeldigeCeller();
        rutenett.kobleAlleCeller();
    }

    public void tegn(){
        rutenett.tegnRutenett();
        System.out.println("\nGenerasjon nr: " + genNr);
        System.out.println("Det er " +  rutenett.antallLevende() + " levende celler.");
    }

    public void oppdatering(){
        for (int i=0; i < antRader; i++){
            for (int j=0; j < antKolonner; j++){
                rutenett.hentCelle(i,j).oppdaterStatus();

            }
        }
        genNr++;
    }
}
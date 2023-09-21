class Pasient {
    protected String navn, foedselsNum;
    protected int unikId;
    protected static int teller = 0;

    protected Koe<Resept> pasientResepter = new Koe<>(); 

    public int antNarkotiskeResepter;

    public Pasient(String navn, String foedselsNum){
        this.navn = navn;
        this.foedselsNum = foedselsNum;
        unikId = teller;
        teller++;
    }

    public int hentID(){
        return unikId;
    }

    public String hentNavn(){
        return navn;
    }

    public Koe<Resept> hentPasientResepter(){
        return pasientResepter;
    }

    public int antNarkotiskeResepter(){
        for (Resept resept : pasientResepter){
            if (resept.legemiddel instanceof Narkotisk){
                antNarkotiskeResepter ++;
            }
        }
        return antNarkotiskeResepter;
    }



    @Override 
    public String toString(){
        return "Pasienter navn: " + navn + "\n" + "Foedselsnummer: " + foedselsNum;
    }
    

}

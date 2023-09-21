class Lege implements Comparable<Lege>{

    IndeksertListe<Resept> utskrevendeResepter = new IndeksertListe<>();

    private String navn;
    public int antNarkotiskeResepter;

    public Lege(String navn){
        this.navn = navn;
    }

    public String hentKontrollKode(){
        return " ";
    }

    public String hentNavn(){
        return navn;
    }

    @Override
    public String toString() {
        return "Leges navn: " + hentNavn();
    }

    @Override
    public int compareTo(Lege annenLege) {
        return navn.compareTo(annenLege.navn);
    }

    public IndeksertListe<Resept> skrivUtAlleResepter(){
        return utskrevendeResepter;
    }

    public int antNarkotiskeResepter(){
        for (Resept resept : utskrevendeResepter){
            if (resept.legemiddel instanceof Narkotisk){
                antNarkotiskeResepter ++;
            }
        }
        return antNarkotiskeResepter;
    }



    public HviteResepter skrivHvitResept (Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {

        if (legemiddel instanceof Narkotisk){
            throw new UlovligUtskrift(this, legemiddel);
        } else{
            HviteResepter hviteResepter = new HviteResepter(legemiddel, this, pasient, reit);
            utskrevendeResepter.leggTil(hviteResepter);
            pasient.pasientResepter.leggTil(hviteResepter);
            return hviteResepter;
        }

    }


    public MilResept skrivMilResept (Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift {

        if (legemiddel instanceof Narkotisk){
            throw new UlovligUtskrift(this, legemiddel);
        } else{
            MilResept milResept = new MilResept(legemiddel, this, pasient);
            utskrevendeResepter.leggTil(milResept);
            pasient.pasientResepter.leggTil(milResept);
            return milResept;
        }

    }


    public PResept skrivPResept (Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {

        if (legemiddel instanceof Narkotisk){
            throw new UlovligUtskrift(this, legemiddel);
        } else{
            PResept pResept = new PResept(legemiddel, this, pasient, reit);
            utskrevendeResepter.leggTil(pResept);
            pasient.pasientResepter.leggTil(pResept);
            return pResept;
        }

    }


    public BlaaResept skrivBlaaResept (Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk && (this instanceof Spesialist) == false){
            throw new UlovligUtskrift(this, legemiddel);
        } else{
            BlaaResept blaaResept = new BlaaResept(legemiddel, this, pasient, reit);
            utskrevendeResepter.leggTil(blaaResept);
            pasient.pasientResepter.leggTil(blaaResept);
            return blaaResept;
        }

    }
    

}

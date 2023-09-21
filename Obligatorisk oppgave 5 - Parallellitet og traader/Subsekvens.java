//Mønstrene vi er på jakt etter i immunrepertoarene er altså korte, like substrenger eller subsekvenser.
//En subsekvens er en String av en gitt lengde, typisk 3 eller 4 (en konstant i programmet ditt som du i din besvarelse kan sette til 3), f.eks. «ABC»

class Subsekvens {
    public final String subsekvens;
    private int antall = 1; //et heltall som angir antall forekomster av denne subsekvensen hos flere personer

    public Subsekvens(String subsekvens){
        this.subsekvens = subsekvens;
    }

    public int hentAntallForekomster(){
        return antall;
    }

    public void okeAntallForekomster(int tall){
        antall = antall + tall;
    }

    @Override
    public String toString() {
        return "(" + subsekvens + "," + antall + ")";
    }
}
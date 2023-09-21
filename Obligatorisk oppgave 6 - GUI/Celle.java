
//Klassen beskriver en celle under simuleringen. En celle skal ha en variabel som beskriver status (levende/død).
//NB! Statusvariabelen skal være av typen boolean

public class Celle {

    Celle[] naboer = new Celle[8]; //8 naboer av denne cellen som er rundt
    public boolean levende; //false - celle er død/ true - levende
    public int antNaboer = 0;
    public int antLevendeNaboer = 0;

    public Celle(){
        levende = false;
    }

    public void settDoed(){
        levende = false;
    }

    public void settLevende(){
        levende = true;
    }

    public boolean erLevende(){
        //hvis cellen er levende kommer true hvis den er dø kommer false
        return levende;
    }


    //er en tegnrepresentasjon av cellens status til bruk i tegning av brettet
    public char hentStatusTegn(){
        if (levende){
            return 'O';
        } else {
            return '.';
        }
    }

    public void leggTilNabo(Celle nabo){
        if(antNaboer < naboer.length){
            naboer[antNaboer] = nabo;
            antNaboer++;
        }
    }


    public void tellLevendeNaboer(){
        this.antLevendeNaboer = 0; //for å telle fra 0 hver gang (global variabel)
        for(int i = 0; i < naboer.length; i++){
            if(naboer[i] != null && naboer[i].erLevende()){ //hvis første elem er ikke null og den første elem er levende
                //System.out.println(naboer[i].erLevende());
                this.antLevendeNaboer++;
            }
        }
    }



    public void oppdaterStatus(){
        tellLevendeNaboer(); //returnerer antLevendeNaboer

        // == for å sjekke om de er like
        if (this.levende == true){ //på dette linje så sjekker vi om cellen som vi er på er levende eller dø
            if(antLevendeNaboer < 2 || antLevendeNaboer > 3){
                settDoed();
            } else {
                settLevende();
            }
        } else { //if levende == false betyr død celle
            if (antLevendeNaboer == 3){
                settLevende();
            } else {
                settDoed();
            }
        }
    }
}
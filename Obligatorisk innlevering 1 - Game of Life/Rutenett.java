
//Denne klassen beskriver et todimensjonalt brett som inneholder celler. Rutenett skal holde
//styr på hvilke celler som skal endre status og oppdatere disse for hver generasjon.

public class Rutenett {

    public Celle[][] rutene;
    public int antRader;
    public int antKolonner;

                    //her lagger vi selve tabel (rutenett)
    public Rutenett(int antRader, int antKolonner){
        this.antRader = antRader;
        this.antKolonner = antKolonner;
        rutene = new Celle[antRader][antKolonner];
    }


    //mens her så lager nyCelle på bestemt plass i tabellen
    public void lagCelle(int rad, int kol){
        Celle nyCelle = new Celle();
        rutene[rad][kol] = nyCelle; //legger ny celle inn i arrayen på en plass

        //Hver celle i rutenettet skal ha ⅓ sjanse for å være levende når den legges inn i rutenettet
        if(Math.random()<=0.3333){
            nyCelle.settLevende();
        }
    }

    public void fyllMedTilfeldigeCeller(){

        //for hver eneste første for løkke den inderste for løkke kjøres max antall ganger
        //etter den andre er ferdig å kjøre kommer kode tilbake til første for løkke og endrer i
        //så etterpå kjøres andre før løkke igjen fra starten til slutten
        for(int i=0; i < rutene.length; i++){
            for (int j=0; j < rutene[i].length; j++){
                lagCelle(i,j);
            }
        }
    }


    public Celle hentCelle(int rad, int kol){
        if(rad >= antRader || kol >= antKolonner || rad < 0 || kol < 0){ //>= pga indeks
            return null;
        } else {
            return rutene[rad][kol];
        }
    }



    public void tegnRutenett(){

        for(int i = 0; i<10; i++){
            System.out.println();
        }

        for(int i=0; i < rutene.length; i++){
            System.out.println(); //for linje skift, gå til neste linje linje som er nede
            for (int j=0; j < rutene[i].length; j++){
                System.out.print(rutene[i][j].hentStatusTegn());

            }
        }
    }



    //For å bestemme hvilke celler som skal være levende og døde i neste generasjon,
    //trenger vi å vite statusen til hver celles nabo
    public void settNaboer(int rad, int kol){

        //sjekker alle naboCeller som er rundt vår egen celle og legger de til naboliste
        if(hentCelle(rad-1,kol-1) != null){
            hentCelle(rad, kol).leggTilNabo(hentCelle(rad-1, kol-1));
        }
        if (hentCelle(rad-1,kol) != null){
            hentCelle(rad, kol).leggTilNabo(hentCelle(rad-1, kol));
        }
        if (hentCelle(rad-1,kol+1) != null){
            hentCelle(rad, kol).leggTilNabo(hentCelle(rad-1, kol+1));
        }
        if (hentCelle(rad,kol-1) != null){
            hentCelle(rad, kol).leggTilNabo(hentCelle(rad, kol-1));
        }
        if (hentCelle(rad,kol+1) != null){
            hentCelle(rad, kol).leggTilNabo(hentCelle(rad, kol+1));
        }
        if (hentCelle(rad+1,kol-1) != null){
            hentCelle(rad, kol).leggTilNabo(hentCelle(rad+1, kol-1));
        }
        if (hentCelle(rad+1,kol) != null){
            hentCelle(rad, kol).leggTilNabo(hentCelle(rad+1, kol));
        }
        if (hentCelle(rad+1,kol+1) != null){
            hentCelle(rad, kol).leggTilNabo(hentCelle(rad+1, kol+1));
        }


    }



    public void kobleAlleCeller(){
        for(int i=0; i < rutene.length; i++){
            for (int j=0; j < rutene[i].length; j++){
                settNaboer(i,j);
            }
        }
    }



    public int antallLevende(){

        int antLevendeCeller = 0;

        for (int i=0; i < rutene.length; i++){
            for (int j=0; j < rutene[i].length; j++){
                if(rutene[i][j].erLevende()){
                    antLevendeCeller++;
                }
            }
        }
        return antLevendeCeller;

    }


}




/* vanskelig

    public void settNaboer(int rad, int kol){
        for (int r = -1; r < 2; r++){
            for (int k = -1; k < 2; k++){
                if (r != 0 || k != 0){
                    Celle celle_som_sjekkes = hentCelle(rad+r, kol+k);
                    if (celle_som_sjekkes != null){
                        hentCelle(rad,kol).leggTilNabo(celle_som_sjekkes);
                    }
                }
            }
        }
      }
         */
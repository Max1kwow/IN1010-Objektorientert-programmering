import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Labyrint {
    private Rute[][] rutene;
    private int rader, kolonner;

    public Labyrint(String fil){
        try{
            Scanner sc = new Scanner(new File(fil));
            if (sc.hasNextInt()){
                rader = sc.nextInt(); //får første tall som representerer antall rader fra første linje i filen
                kolonner = sc.nextInt(); //får andre tall som representerer antall kolonner fra første linje i filen
                sc.nextLine();
                rutene = new Rute[rader][kolonner];
            } else {
                System.out.println("Feil filformat");
                System.exit(1);
            }


            for (int rad = 0; rad < rader; rad++){ //går gjennom hver rad i filen
                String linje = sc.nextLine();
                char[] splitLinje = linje.toCharArray(); //splitter hver element på linjen
                for (int element = 0; element < kolonner; element++){ //går gjennom hver element (#/.) i filen
                    if (splitLinje[element] == '#'){
                        rutene[rad][element] = new SortRute(rad, element, this);
                    }
                    else if (splitLinje[element] == '.'){
                        if (rad == 0 || rad == rader-1 || element == 0 || element == kolonner-1){ //sjekker om posisjon er en åpning
                            rutene[rad][element] = new Aapning(rad, element, this);
                        }else {
                            rutene[rad][element] = new HviteRute(rad, element, this);
                        }

                    }else{
                        System.out.println("Feil format");
                        System.exit(1);
                    }
                }
            }


        } catch (FileNotFoundException e){
            System.out.println("Fil finnes ikke");
            System.exit(1);
            }


        System.out.println(this);
        hentNaboInfo();

    }


    public void hentNaboInfo(){
        for (int rad = 0; rad < rader; rad++){
            for (int kol = 0; kol < kolonner; kol++){
                Rute naavarendeRute = rutene[rad][kol];
                if(kol+1 < kolonner){ //sjekker nabo til høyre
                    naavarendeRute.settNabo(rutene[rad][kol+1]); //legger til nabo liste
                }
                if (kol-1 >= 0){ //sjekker nabo til venstre
                    naavarendeRute.settNabo(rutene[rad][kol-1]);
                }
                if (rad-1 >= 0){ //sjekker nabo til nord
                    naavarendeRute.settNabo(rutene[rad-1][kol]);
                }
                if (rad+1 < rader){ //sjekker nabo til sør
                    naavarendeRute.settNabo(rutene[rad+1][kol]);
                }
            }
        }
    }


    public void finnUtveiFra(int rad, int kol){
        System.out.println("Aapninger: ");
        rutene[rad][kol].finn(null);
    }




    @Override
    public String toString() {
        String str = "";
        for (int rad = 0; rad < rader; rad++){
            for (int kol = 0; kol < kolonner; kol++){
                str += rutene[rad][kol].toString();
            }
            str += "\n";
        }
        return str;
    }

}

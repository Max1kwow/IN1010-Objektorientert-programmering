import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Oblig5Del2A {
    public static void main(String[] args) {
        //Del1
//        String mappeNavn1 = "TestDataLike";
//        String mappeNavn2 = "TestDataLitenLike";
        SubsekvensRegister subReg = new SubsekvensRegister();
//        subReg.lesFraMetaData(mappeNavn1);
//        subReg.lesFraMetaData(mappeNavn2);
//        subReg.combinerHashMap();

        //Del 2
        if (args.length > 1) { //sjekker om 2 mappe navn gitt som parametre
            ArrayList<String> mapper = new ArrayList<>(); //arrayList for å holde info om alle mapper
            mapper.add(args[0]); //mappenavn1 fra bruker
            mapper.add(args[1]); //mappenavn2 fra bruker

            Monitor1 monitor1 = new Monitor1(subReg);

            ArrayList<Thread> threadsArrayList = new ArrayList<>(); //arrayList for å lagre referanse til tråder. Brukes for join() senere

            //går gjennom mappe navner for å ikke skrive samme kode flere ganger
            for (String mappeNavn : mapper){
                try {
                    Scanner sc = new Scanner(new File( mappeNavn + "/metadata.csv")); //koden ser sånn ut f.eks: "TestDataLike/metadata.csv/fil1,2,3"...

                    //for hver linje i filen lager ny tråde objekt med bestemt filNavn som utforer sin run() metoden
                    while (sc.hasNextLine()){
                        String[] linje = sc.nextLine().split(","); //her lagrer vi filNavner (fil1.csv, fil2.csv, osv.)
                        String veiTilFil = mappeNavn + "/" + linje[0]; //koden ser sånn ut f.eks: "TestDataLike/fil1.csv" osv.

                        LeseTrad leseTrad = new LeseTrad(veiTilFil, monitor1); //lager Runnable objekt som tar filNavn(veien til fil) og referanse til monitor Objekt
                        Thread thread = new Thread(leseTrad); //opretter tråede objekt
                        threadsArrayList.add(thread); //lagrer referanse til tråde
                        thread.start();
                    }
                } catch (FileNotFoundException e){
                    System.out.println("Metadata file not found!");
                    System.exit(0);
                }
            }

            //går gjennom hver tråd objekt og kaller på join() for at trådene skal vente på hverandre
            //det gjør vi for at main() tråden kjøres på slutten og vi får riktig antall HashMaper i hashMap arrayList
            for(Thread traade : threadsArrayList){
                try {
                    traade.join();
                } catch (InterruptedException e){
                    System.out.println("Kunne ikke vente for tråder");
                }
            }
            System.out.println(monitor1.hentAntallHashMaper());
            monitor1.combinerHashMap();
            System.out.println(monitor1.hentAntallHashMaper());
        }else{
            System.out.println("Du må skrive to mappe navn");
        }
    }
}
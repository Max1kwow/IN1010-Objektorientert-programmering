import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

//Et immunrepertoar består av mange immunreseptorer som er proteiner som gjenkjenner viruset.
//Immunrepertoaret til én person er representert av én fil, og hver immunreseptor
//er representert som en sekvens av (store) bokstaver, én sekvens per linje i filen. Vi ønsker å analysere og finne mønstre i disse sekvensene.
class SubsekvensRegister {
    ArrayList<HashMap<String, Subsekvens>> hashBeholder = new ArrayList<>();
    public void leggTil_HashMapMedSubsekvenser(HashMap<String, Subsekvens> hashMap){
        hashBeholder.add(hashMap);
    }

    public HashMap<String,Subsekvens> taUt_HashMapMedSubsekvenser(int indeks){
        return hashBeholder.get(indeks);
    }

    public int hentAntallHashMaper(){
        return hashBeholder.size();
    }



    public void lesFraMetaData(String mappeNavn){
        try {
            Scanner sc = new Scanner(new File( mappeNavn + "/metadata.csv")); //koden ser sånn ut f.eks: "TestDataLike/metadata.csv/fil1,2,3"...
            while (sc.hasNextLine()){
                String[] linje = sc.nextLine().split(","); //her lagrer vi filNavner (fil1.csv, fil2.csv, osv.)
                String veiTilFil = mappeNavn + "/" + linje[0]; //koden ser sånn ut f.eks: "TestDataLike/fil1.csv" osv.
                HashMap<String, Subsekvens> tmpHashMap = lesFil(veiTilFil); //bruker lesfil metoden med fil1,2,3.csv osv. som parameter (veien til filer hvor subsekvensene skal leses fra)
                hashBeholder.add(tmpHashMap);
            }
        } catch (FileNotFoundException e){
            System.out.println("Metadata file not found!");
        }
    }



    public void combinerHashMap(){
        while (hashBeholder.size() > 1){
            HashMap<String, Subsekvens> valgt0 = hashBeholder.remove(0);
            HashMap<String, Subsekvens> valgt1 = hashBeholder.remove(0);
            HashMap<String, Subsekvens> combinertHashMap = slaaHashMaperSammen(valgt0, valgt1);
            hashBeholder.add(combinertHashMap);
        }

        int flestForekomster = 0;
        Subsekvens storstSub = null;
        for (Subsekvens sub : hashBeholder.get(0).values()){
            if (sub.hentAntallForekomster() > flestForekomster){
                flestForekomster = sub.hentAntallForekomster();
                storstSub = sub;
            }
        }
        System.out.println(storstSub);
    }



    //Metoden leser én og én linje, finner subsekvensene i linjen og legger disse inn i HashMap-en.
    //Metoden skal returnere en referanse til den nye HashMap-en.
    public static HashMap<String,Subsekvens> lesFil(String fil){

        HashMap<String,Subsekvens> current_HashMap = new HashMap<>();

        try {
            Scanner sc = new Scanner(new File(fil));

            while (sc.hasNext()){
                String linje = sc.nextLine();

                if (linje.length() < 3 ){
                    System.exit(1);
                } else {
                    for(int i = 0; i < linje.length()-2; i++){ // -2 fordi at vi kan ikke lese/bruke de 2 siste bokstavene
                        String subsekvens = linje.substring(i, i+3); //lagrer subsekvenser/3 bokstaver: i, i+3 fordi at vi skal lagre hver 3 påfølgende bokstaver i variabel

                        //hvis vi finner ikke en subsekvens som finnes i HashMapen vår, så lager vi ny Subsekvens objekt
                        //som skal ha anatall forekomster er lik 1
                        //om vi finner en subsekvens som finnes i HashMapen fra før så gjør vi ingenting
                        if (!current_HashMap.containsKey(subsekvens)){
                            Subsekvens nySubsekvensObj = new Subsekvens(subsekvens);
                            current_HashMap.put(subsekvens, nySubsekvensObj);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found!");
            System.exit(1);
        }
        return current_HashMap; //returnerer HashMap av subsekvensene til en person (antall av hver subsekvens kan være kun 1, fordi vi øker ikke)
    }



    //slår sammen to HashMap-er som tar vare på Subsekvens-objekter og returnerer referanse til en HashMap som er en sammenslåing (fletting) av de to parametrene,
    //parametrene til metoden er referanser til de to HashMap-ene som skal slås sammen
    public static HashMap<String,Subsekvens> slaaHashMaperSammen(HashMap<String,Subsekvens> hashMap1, HashMap<String,Subsekvens> hashMap2){

        HashMap<String,Subsekvens> hoved_HashMap = new HashMap<>();

        //går gjennom den første HashMap og lagrer referanse til alle subsekvens objekter i ny HashMap
        for (Subsekvens subObjekt : hashMap1.values()){
            String subsekvens = subObjekt.subsekvens;
            hoved_HashMap.put(subsekvens, subObjekt);
        }

        //går gjennom andre HashMap sin subsekvens objekter og sjekker om subsekvens (String) eksisterer som et key
        //i den ny HashMap. Hvis key eksisterer da kan vi øke antall forekomster i objekt av den key i ny HashMap med
        //antall fra den andre HashMap sin objekt av denne key (legge sammen antall forekomster/antall av subsekvens fra de to HashMappene)
        //Hvis key eksisterer ikke i den ny HashMap da kan vi direkte lagre referanse av den subsekvens objekt i ny HashMap
        for (Subsekvens subObjekt : hashMap2.values()){
            if (hoved_HashMap.containsKey(subObjekt.subsekvens)){
                Subsekvens subobjkInyHashmap = hoved_HashMap.get(subObjekt.subsekvens);
                subobjkInyHashmap.okeAntallForekomster(subObjekt.hentAntallForekomster());
            }
            else {
                hoved_HashMap.put(subObjekt.subsekvens,subObjekt);
            }
        }

        return hoved_HashMap; //består av de to HashMapene
    }

}

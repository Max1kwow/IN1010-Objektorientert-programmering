import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;

class Legesystem {
    Koe<Legemiddel> legemiddelListe = new Koe<>();
    IndeksertListe<Resept> reseptListe = new IndeksertListe<>();
    Prioritetskoe<Lege> legeListe = new Prioritetskoe<>();
    IndeksertListe<Pasient> pasientListe = new IndeksertListe<>();

    static int totAntVanedannendeResepter; 
    static int totAntNarkotiskeResepter; 

    public void lesFraFil(String filNavn){
        try{
            Scanner sc = new Scanner(new File(filNavn));
            String klassNavn = null;
            while (sc.hasNextLine()){
                String linje = sc.nextLine();
                String[] split = linje.trim().split(" ");
                if (split[0].equals("#")){
                    klassNavn = split[1];
                } else {
                    if (klassNavn.equals("Pasienter")){
                        String[] split_pasient = linje.trim().split(",");
                        String pasient_navn = split_pasient[0];
                        String fnr = split_pasient[1];
                        Pasient pasient = new Pasient(pasient_navn, fnr);
                        pasientListe.leggTil(pasient);

                    } else if (klassNavn.equals("Legemidler")){
                        String[] split_legemidler = linje.trim().split(",");
                        String legemiddel_navn = split_legemidler[0];
                        String type = split_legemidler[1];
                        int pris = Integer.parseInt(split_legemidler[2]);
                        Double virkestoff = Double.parseDouble(split_legemidler[3]);

                        if(type.equals("vanlig")){
                            Vanlig vanlig = new Vanlig(legemiddel_navn, pris, virkestoff);
                            legemiddelListe.leggTil(vanlig);
                        } else if (type.equals("narkotisk")){
                            int styrken = Integer.parseInt(split_legemidler[4]);
                            Narkotisk narkotisk = new Narkotisk(legemiddel_navn, pris, virkestoff, styrken);
                            legemiddelListe.leggTil(narkotisk);
                        } else if (type.equals("vanedannende")){
                            int styrken = Integer.parseInt(split_legemidler[4]);
                            Vanedannende vanedannende = new Vanedannende(legemiddel_navn, pris, virkestoff, styrken);
                            legemiddelListe.leggTil(vanedannende);
                        }
                    }
                    else if (klassNavn.equals("Leger")){
                        String[] split_leger = linje.trim().split(",");
                        String legeNavn = split_leger[0];
                        String kontrollid = split_leger[1];

                        if(kontrollid.equals("0")){
                            Lege vanligLege = new Lege(legeNavn);
                            legeListe.leggTil(vanligLege);
                        } else{
                            Spesialist spesialistLege = new Spesialist(legeNavn, kontrollid);
                            legeListe.leggTil(spesialistLege);
                        }
                    }
                    else if (klassNavn.equals("Resepter")){
                        String[] split_resepter = linje.trim().split(",");
                        int legemiddelNummer = Integer.parseInt(split_resepter[0]);
                        String legeNavn = split_resepter[1];
                        int pasientId = Integer.parseInt(split_resepter[2]);
                        String type = split_resepter[3];

                        Legemiddel spesefikkLegemiddel = null;
                        Lege spesefikkLege = null;
                        Pasient spesefikkPasient = null;

                        for (Legemiddel legemiddel : legemiddelListe){
                            if (legemiddel.hentID() == legemiddelNummer){
                                spesefikkLegemiddel = legemiddel;
                            }
                        }
                        for (Lege lege : legeListe){
                            if (lege.hentNavn().equals(legeNavn)){
                                spesefikkLege = lege;
                            }
                        }
                        for (Pasient pasient : pasientListe){
                            if (pasient.hentID() == pasientId){
                                spesefikkPasient = pasient;
                            }
                        }

                        //vi treng aa finne riktig legemiddelNummer,legeNavn,pasientID objekter
                        //og bruke dem som parametre for aa lage resept objekt (som kombinerer alt sammen)

                        //sjekker at fant alle noedvendige objekter (de er ikke null)
                        //hvis i hvert fall en er null saa kan vi ikke resept
                        if(spesefikkLegemiddel != null && spesefikkLege != null && spesefikkPasient != null){
                            //naa sjekker vi alle typer:

                            if (type.equals("blaa")){
                                int reit = Integer.parseInt(split_resepter[4]);
                                BlaaResept blaaResept = spesefikkLege.skrivBlaaResept(spesefikkLegemiddel, spesefikkPasient, reit);
                                reseptListe.leggTil(blaaResept);
                            }
                            else if (type.equals("hvit")){
                                int reit = Integer.parseInt(split_resepter[4]);
                                HviteResepter hvitResepter = spesefikkLege.skrivHvitResept(spesefikkLegemiddel, spesefikkPasient, reit);
                                reseptListe.leggTil(hvitResepter);
                            }
                            else if (type.equals("militaer")){
                                MilResept milResept = spesefikkLege.skrivMilResept(spesefikkLegemiddel, spesefikkPasient);
                                reseptListe.leggTil(milResept);
                            }
                            else if (type.equals("p")){
                                int reit = Integer.parseInt(split_resepter[4]);
                                PResept pResept = spesefikkLege.skrivPResept(spesefikkLegemiddel, spesefikkPasient, reit);
                                reseptListe.leggTil(pResept);
                            }
                        }
                    }     
                }
            }
        }catch (FileNotFoundException e){
            System.out.println("Feil filnavn");
        } catch (UlovligUtskrift e) {
            throw new RuntimeException(e);
        }
    }

    public void printAlleLegemidler(){
        for (Legemiddel legemiddel : legemiddelListe){
            System.out.println(legemiddel);
            System.out.println("..............");
        }
    }

    public void printAlleResepter(){
        for (Resept resept : reseptListe){
            System.out.println(resept);
            System.out.println("..............");
        }
    }

    public void printAlleLeger(){
        for (Lege lege : legeListe){
            System.out.println(lege);
        }
    }

    public void printAllePasienter(){
        for (Pasient pasient : pasientListe){
            System.out.println(pasient);
        }
    }

    public void leggNyData(String input, Scanner sc){
        if (input.equals("lege")){
            try{
                System.out.print("Skriv legens informasjon i format: navn,kontrollid(0 hvis vanlig lege) ");
                String info = sc.nextLine();
                String[] linjeInput = info.trim().split(",");
                Lege nyLege = null;
                if (linjeInput[1].equals("0")){
                    nyLege = new Lege("Dr. " + info);
                } else {
                    nyLege = new Spesialist("Dr. " + linjeInput[0], linjeInput[1]);
                }
                legeListe.leggTil(nyLege);
            } catch(Exception e){
                System.out.println("\nNoe gikk galt, husk riktig format!\nProev igjen!");
            }
        } //skriver man 0 blir det godkjenningsfritak, burde staa ingenting

        else if (input.equals("legemiddel")){
            try{
                System.out.print("Skriv legemiddel sin info i format: navn,type,pris,virkestoff,(styrke) ");
                String info = sc.nextLine();
                String[] split_legemidler = info.trim().split(",");
                String legemiddel_navn = split_legemidler[0];
                String type = split_legemidler[1];
                int pris = Integer.parseInt(split_legemidler[2]);
                Double virkestoff = Double.parseDouble(split_legemidler[3]);

                if(type.equals("vanlig")){
                    Vanlig vanlig = new Vanlig(legemiddel_navn, pris, virkestoff);
                    legemiddelListe.leggTil(vanlig);
                } else if (type.equals("narkotisk")){
                    int styrken = Integer.parseInt(split_legemidler[4]);
                    Narkotisk narkotisk = new Narkotisk(legemiddel_navn, pris, virkestoff, styrken);
                    legemiddelListe.leggTil(narkotisk);
                } else if (type.equals("vanedannende")){
                    int styrken = Integer.parseInt(split_legemidler[4]);
                    Vanedannende vanedannende = new Vanedannende(legemiddel_navn, pris, virkestoff, styrken);
                    legemiddelListe.leggTil(vanedannende);
                }
            } catch(Exception e){
                System.out.println("\nNoe gikk galt, husk riktig format!\nProev igjen!");
            }
        }

        else if (input.equals("pasient")){
            try{
                System.out.print("Skriv pasient i format: navn, foedselsnummer: ");
                String info = sc.nextLine();
                String[] split_pasienter = info.trim().split(",");
                String pasient_navn = split_pasienter[0];
                String foedselsnummer = split_pasienter[1];
                //Pasient nyPasient = null;
                Pasient nyPasient = new Pasient(pasient_navn, foedselsnummer);
                pasientListe.leggTil(nyPasient);
            } catch(Exception e){
                System.out.println("\nNoe gikk galt. Husk navn og foedselsnummer!\nProev igjen!");
            }
        }


        //Resepter skal opprettes via en Lege sin skrivResept().
        //Skal ikke vaere tillat aa lage en resept uten en gyldig lege
        else if (input.equals("resept")){
            try {
                //Legemiddel legemiddel, Pasient pasient, int reit
                System.out.print("Skriv hva slags resept vil du faa, i format: legemiddel,pasient,lege: ");
                String info = sc.nextLine();
                String[] split_resept = info.trim().split(",");
                String legemiddelForResept = split_resept[0];
                String pasientForResept = split_resept[1];
                String legeForResept = split_resept[2];


                System.out.println("Velg hvilket resept vil du velge mellom: hvit,mil,blaa,p");
                info = sc.nextLine();

                Legemiddel spesefikkLegemiddel = null;
                Lege spesefikkLege = null;
                Pasient spesefikkPasient = null;

                for (Legemiddel legemiddel : legemiddelListe){
                    if (legemiddel.hentNavn().equals(legemiddelForResept)){
                        spesefikkLegemiddel = legemiddel;
                    }
                }

                for (Lege lege : legeListe){
                    if (lege.hentNavn().equals(legeForResept)){
                        spesefikkLege = lege;
                    }
                }

                for (Pasient pasient : pasientListe){
                    if (pasient.hentNavn().equals(pasientForResept)){
                        spesefikkPasient = pasient;
                    }
                }

                if(spesefikkLegemiddel != null && spesefikkLege != null && spesefikkPasient != null){
                    if(info.equals("hvit") || info.equals("blaa") || info.equals("p")){
                        System.out.print("Skriv reit: ");
                        try{
                            int reit = Integer.parseInt(sc.nextLine());
                            if (info.equals("hvit")){
                                HviteResepter hvitResept = spesefikkLege.skrivHvitResept(spesefikkLegemiddel, spesefikkPasient, reit);
                                reseptListe.leggTil(hvitResept);
                            } else if (info.equals("blaa")){
                                BlaaResept blaaResept = spesefikkLege.skrivBlaaResept(spesefikkLegemiddel, spesefikkPasient, reit);
                                reseptListe.leggTil(blaaResept);
                            } else {
                                PResept pResept = spesefikkLege.skrivPResept(spesefikkLegemiddel, spesefikkPasient, reit);
                                reseptListe.leggTil(pResept);
                            }

                        } catch (NumberFormatException el){
                            System.out.println("Du maa skrive tall for reit!");
                        } catch (UlovligUtskrift e){
                            System.out.println("Den lege kan ikke skrive den ut!");
                        }

                    }else if(info.equals("mil")) {
                        MilResept milResept = spesefikkLege.skrivMilResept(spesefikkLegemiddel, spesefikkPasient);
                        reseptListe.leggTil(milResept);
                    } else {
                        System.out.println("Den type finnes ikke");
                    }
                }else{
                    System.out.println("Enten lege, legemiddel eller pasient finnes ikke");
                }


            } catch (Exception e){
                System.out.println("\nNoe gikk galt. \nProev igjen!");
            }
        }
}





//MAIN klassen med meny for bruker
    public static void main(String[] args){
        Legesystem legeSystem = new Legesystem();
        legeSystem.lesFraFil("legedata.txt");
        
        Scanner sc = new Scanner(System.in);
        String menyText = "\n\nHei, her er dine valgmuligheter:\n" +
                "1: Se liste over leger, pasienter, legemidler, respter.\n" +
                "2: Opprette og legge til nye elementer i systemet.\n" +
                "3: Bruke en gitt resept fra listen til en pasient.\n" +
                "4: Skrive ut forskjellige former for statistikk.\n" +
                "5: Skrive alle data til fil\n" +
                "OBS! Svarformatet maa oppgis som et tall, for eksempel: 1\n" +
                "Hvis du vil avslutte, skriv stopp";
        System.out.println(menyText);
        String input = sc.nextLine();

        while (!input.equals("stopp")){
            if (input.equals("1")){
                System.out.println("-----------------Alle leger:------------------");
                legeSystem.printAlleLeger();
                System.out.println("\n---------------Alle pasienter:----------------");
                legeSystem.printAllePasienter();
                System.out.println("\n---------------Alle legemidler:---------------");
                legeSystem.printAlleLegemidler();
                System.out.println("\n----------------Alle resepter:----------------");
                legeSystem.printAlleResepter();

            }
        
            else if (input.equals("2")){
                System.out.println("Hva slags element vil du legge til: lege, legemiddel, pasient eller resept?");
                String data_type = sc.nextLine();
                legeSystem.leggNyData(data_type.toLowerCase(), sc);
            }
        
            else if (input.equals("3")){
                //bruker en resept fra Pasienten sin reseptListe, her blir reit --
                System.out.println("Hvilken pasient vil du se resepter for?");
                int i = 0;
                //printer ut informasjon om alle pasienter som er i pasientListe
                for (Pasient p : legeSystem.pasientListe){
                    System.out.println(i + ": " + p.hentNavn());
                    i++;
                }
                System.out.print("> ");
                String svar = sc.nextLine(); // henter tall som tilhoerer pasient indeks i pasientListe
                //lagrer peker til valgt pasient objekt
                Pasient pasientPeker = legeSystem.pasientListe.hent(Integer.parseInt(svar));

                System.out.println("Hvilken resept vil du bruke?");

                i = 0;
                //gaar gjennom alle resepter i reseptListe for valgt pasient og printer informasjon ut
                for (Resept resept : pasientPeker.hentPasientResepter()){
                    System.out.println(i + ": " + resept.hentLegemiddel().hentNavn() + " ("+ resept.hentReit() +" reit)");
                    i++;
                }
                System.out.print("> ");
                svar = sc.nextLine(); // bruker velger tall som tilhoerer til en bestemt resept for valgt pasient

                Resept valgtPasResept = null; // uten den faar feil
                int j = 0; // bruker for aa oeke den opp til naar den == til valgt resept nummer oeverst
                for(Resept r : pasientPeker.hentPasientResepter()){
                    if (j == Integer.parseInt(svar)){ // naar vi kom til riktig valgt resept i reseptListe for bestemt pasient
                        valgtPasResept = r; //lagrer vi resept i valgt resepter for aa bruke reit senere
                    }
                    j++;
                }

                if(valgtPasResept.bruk() == true){
                    System.out.println("Brukte resept paa " + valgtPasResept.hentLegemiddel().hentNavn() + ". Antall gjenvaerende reit: " + valgtPasResept.hentReit());
                } else {
                    System.out.println("Det var ikke mulig aa bruke resept (pga 0 reit) ");
                }
            }
            
            else if (input.equals("4")){
                String menyText2 = "\n\nHer er alternativene dine for statistikk utskrift:\n" +
                        "1: Totalt antall utskrevne resepter paa vanedannende legemidler.\n" +
                        "2: Totalt antall utskrevne resepter paa narkotiske legemidler.\n" +
                        "3: Mulig misbruk av narkotika.\n" +
                        "OBS! Svarformatet maa oppgis som et tall, for eksempel: 1";
                System.out.println(menyText2);

                String input2 = sc.nextLine();

                if (input2.equals("1")){
                    for (Lege l : legeSystem.legeListe){
                        for (Resept r : l.utskrevendeResepter){
                            if (r.legemiddel instanceof Vanedannende){
                                totAntVanedannendeResepter ++;
                            }
                        }
                    }
                    System.out.println("\nTotalt antall utskrevne resepter paa vanedannende legemidler er --> " + totAntVanedannendeResepter);
                }
                if (input2.equals("2")){
                    for (Lege l : legeSystem.legeListe){
                        for (Resept r : l.utskrevendeResepter){
                            if (r.legemiddel instanceof Narkotisk){
                                totAntNarkotiskeResepter ++;
                            }
                        }
                    }
                    System.out.println("Totalt antall utskrevne resepter paa narkotiske legemidler er --> " + totAntNarkotiskeResepter);  
                }
                if (input2.equals("3")){
                    System.out.println("\nLeger som har skrevet ut narkotiske resepter: ");
                    for (Lege lege : legeSystem.legeListe){
                        System.out.println(lege.hentNavn() + ", " + lege.antNarkotiskeResepter());
                    }
                    System.out.println("\nPasienter som har tilgang til narkotiske resepter: ");
                    for (Pasient pasient : legeSystem.pasientListe){
                        System.out.println(pasient.hentNavn() + ", " + pasient.antNarkotiskeResepter());
                    }
                }
            }

            
            else if (input.equals("5")){
                PrintWriter tilFil = null;
                try{
                    tilFil = new PrintWriter("legesystemfil.txt");
                }
                catch(Exception e){
                    System.out.println("En feil oppstod.");
                }

                    tilFil.println("# Pasienter (navn, fnr)");
                    for (Pasient pasient : legeSystem.pasientListe){
                        tilFil.println(pasient.hentNavn() + ", " + pasient.foedselsNum);
                    }
                    tilFil.println("# Legemidler (navn,type,pris,virkestoff,[styrke])");
                    for (Legemiddel legemiddel : legeSystem.legemiddelListe){
                        if (legemiddel instanceof Narkotisk || legemiddel instanceof Vanedannende){
                            tilFil.write(legemiddel.hentNavn() + ", " + legemiddel.type() + ", " + legemiddel.hentPris() + ", " + legemiddel.virkestoff + ", " + legemiddel.hentStyrke() + "\n");
                        }
                        else{
                            tilFil.write(legemiddel.hentNavn() + ", " + legemiddel.type() + ", " + legemiddel.hentPris() + ", " + legemiddel.virkestoff + "\n"); 
                        }
                    }
                    tilFil.println("# Leger (navn,kontrollid / 0 hvis vanlig lege)");
                    for (Lege lege : legeSystem.legeListe){
                        if (lege instanceof Spesialist){
                            tilFil.write(lege.hentNavn() + ", " + lege.hentKontrollKode() + "\n"); 
                        }
                        else{
                            tilFil.write(lege.hentNavn() + ", " + lege.hentKontrollKode() + "\n"); 
                        }
                    }
                    tilFil.println("# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])");
                    for (Resept resept : legeSystem.reseptListe){
                        if (resept instanceof HviteResepter){
                            tilFil.write(resept.hentId() + ", " + resept.utskrivendeLege.hentNavn() + ", " + resept.pasient.hentID() + ", " + resept.type() + ", " + resept.reit + "\n");
                        }
                        else if (resept instanceof PResept){
                            tilFil.write(resept.hentId() + ", " + resept.utskrivendeLege.hentNavn() + ", " + resept.pasient.hentID() + ", " + resept.type() + ", " + resept.reit + "\n");
                        }
                        else if (resept instanceof BlaaResept){
                            tilFil.write(resept.hentId() + ", " + resept.utskrivendeLege.hentNavn() + ", " + resept.pasient.hentID() + ", " + resept.type() + ", " + resept.reit + "\n");
                        }
                        else {
                            tilFil.write(resept.hentId() + ", " + resept.utskrivendeLege.hentNavn() + ", " + resept.pasient.hentID() + ", " + resept.type() + "\n"); 
                        }
                    }
                    tilFil.close();

            }
            System.out.println(menyText);
            input = sc.nextLine();
        }

        }

    }


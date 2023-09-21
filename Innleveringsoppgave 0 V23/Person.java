public class Person {

    private Bil3 personSinBil; // definerer variabel bilperson som har bil3 objekt typen

    public Person(Bil3 personSinBil){
        this.personSinBil = personSinBil;
    }

    public void skrivUt(){                               //MÅ REPETERE, HAR GLEMT AT personSinBil variabel av Bil3 type har tilgang til metoder i Bil3 klassen!
        System.out.println("Bilnummeret til bilen som personen eier er: " + personSinBil.hentNummer());
    }
}


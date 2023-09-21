public class Bil2 {

    private String bilNummer;

    //konstruktør
    public Bil2(String bilNummer){
        this.bilNummer = bilNummer;
    }

    public void skrivUt(){
        System.out.println("Bilnummeret: " + bilNummer);
    }
}

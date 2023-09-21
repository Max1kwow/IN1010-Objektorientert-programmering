public class Bil3 {

    private String bilNummer;

    public Bil3(String bilNummer){
        this.bilNummer = bilNummer;
    }

    public void skrivUt(){
        System.out.println("Bilnummeret: " + bilNummer);
    }

    public String hentNummer(){
        return bilNummer;
    }
}

import java.util.ArrayList;

abstract class Rute {
    protected int radnummer, kolonnenummer;
    private Labyrint labyrint;
    protected ArrayList<Rute> naboListe;

    public Rute(int radnummer, int kolonnenummer, Labyrint labyrint){
        this.radnummer = radnummer;
        this.kolonnenummer = kolonnenummer;
        this.labyrint = labyrint;
        naboListe = new ArrayList<>();
    }

    public void settNabo(Rute naboElement){
        naboListe.add(naboElement);
    }

    abstract public void finn(Rute fra);

}

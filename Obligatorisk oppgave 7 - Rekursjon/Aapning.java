class Aapning extends HviteRute{
    public Aapning(int radnummer, int kolonnenummer, Labyrint labyrint){
        super(radnummer, kolonnenummer, labyrint);
    }

    @Override
    public String toString() {
        return ".";
    }

    @Override
    public void finn(Rute fra){
        //System.out.println("Fant åpning, på radnummer: " + radnummer + " og kolonnenummer: " + kolonnenummer);
        System.out.println("(" + radnummer + "," + kolonnenummer + ")");
    }
}

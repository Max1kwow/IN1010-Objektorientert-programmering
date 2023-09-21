class SortRute extends Rute {

    public SortRute(int radnummer, int kolonnenummer, Labyrint labyrint){
        super(radnummer, kolonnenummer, labyrint);
    }

    @Override
    public String toString() {
        return "#";
    }

    @Override
    public void finn(Rute fra){
        if (fra == null){
            System.out.println("Kan ikke starte i sort rute!");
        }
    }
}

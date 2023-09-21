class HviteRute extends Rute{
    public HviteRute(int radnummer, int kolonnenummer, Labyrint labyrint){
        super(radnummer, kolonnenummer, labyrint);
    }

    @Override
    public String toString() {
        return ".";
    }

    @Override
    public void finn(Rute fra){
        //System.out.println(this.radnummer + " " + this.kolonnenummer);
        if (fra == null){
            for (Rute rute : naboListe){
                rute.finn(this);
            }
        } else{
            for (Rute rute : naboListe){
                if (rute != fra){
                    rute.finn(this);
                }
            }
        }
    }

}

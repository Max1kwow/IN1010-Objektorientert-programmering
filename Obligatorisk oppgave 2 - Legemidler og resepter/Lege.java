class Lege {
    private String navn;

    public Lege(String navn){
        this.navn = navn;
    }

    public String hentNavn(){
        return navn;
    }

    @Override
    public String toString() {
        return "Leges navn: " + hentNavn();
    }
}

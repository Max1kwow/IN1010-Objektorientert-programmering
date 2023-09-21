class Spesialist extends Lege implements godkjenningsfritak{
    private String kontrollkode;

    public Spesialist(String navn, String kontrollkode){
        super(navn);
        this.kontrollkode = kontrollkode;
    }

    @Override
    public String hentKontrollKode() {
        return kontrollkode;
    }

    @Override
    public String toString() {
        return super.toString() + "\nGodkjenningsfritak: " + hentKontrollKode();
    }
}

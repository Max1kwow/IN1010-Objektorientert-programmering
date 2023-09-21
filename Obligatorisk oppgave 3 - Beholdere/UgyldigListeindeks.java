class UgyldigListeindeks extends RuntimeException {
    //alt med pos og indekser treng UgyldigListeindeks
    UgyldigListeindeks (int indeks) {
        super("Ugyldig indeks: "+indeks);
    }
}

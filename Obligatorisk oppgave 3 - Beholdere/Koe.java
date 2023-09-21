//skal fungere som en kø. En type lister der vi alltid setter inn nye elementer
//bakerst og henter dem ut fra starten(hode) (first in, first out)
class Koe<E> extends Lenkeliste<E> {

    public Koe(){
        super();
    }

    @Override
    public void leggTil(E x) {
        super.leggTil(x);
    }

    @Override
    public E hent() {
        return super.hent();
    }
}

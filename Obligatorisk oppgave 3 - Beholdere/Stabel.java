//En stabel er en liste som fungerer litt spesielt: et siste elementet som er lagt inn, er alltid det som hentes ut først.
//Både fjerner og legger til på slutten
class Stabel<E> extends Lenkeliste<E>{
    public Stabel(){
        super();
    }

    //nye elementer legges først i listen
    @Override
    public void leggTil(E x) {
        Node nyNode = new Node(x);
        if (startNode == null){
            startNode = nyNode;
            stoerrelse++;
        }
        else {
            Node peker = startNode;
            startNode = nyNode;
            nyNode.nesteNode = peker; //vi endre peker fra startNode til Node2 før var den på node1
            stoerrelse++;
        }
    }
}

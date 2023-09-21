class Stabel<E> extends Lenkeliste<E>{

    public Stabel(){
        super();
    }

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
            nyNode.nesteNode = peker; 
            stoerrelse++;
        }
    }
}

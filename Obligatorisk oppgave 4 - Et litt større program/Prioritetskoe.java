class Prioritetskoe<E extends Comparable<E>> extends Lenkeliste<E>{

   
    @Override
    public void leggTil(E x) {
        Node nyNode = new Node(x);
        if(startNode == null){
            startNode = nyNode;
            stoerrelse++;
        } else{ 
            if (startNode.data.compareTo(x) > 0){
                Node peker = startNode;
                startNode = nyNode;
                nyNode.nesteNode = peker;
                stoerrelse++;
            } else {
               
                Node peker = startNode;
                while (peker.nesteNode != null && peker.nesteNode.data.compareTo(x) < 0){ 
                    peker = peker.nesteNode;
                }
                nyNode.nesteNode = peker.nesteNode;
                peker.nesteNode = nyNode;
                stoerrelse++;
            }
        }
    }
}

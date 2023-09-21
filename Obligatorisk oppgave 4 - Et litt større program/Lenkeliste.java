import java.util.Iterator;

abstract class Lenkeliste<E> implements Liste<E> {

    public Node startNode; 
    public int stoerrelse;

    protected class Node{ 
        Node nesteNode;
        E data;

        public Node(E data){
            this.data = data;
        }
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
            while (peker.nesteNode != null){ 
                peker = peker.nesteNode; 
            }
            peker.nesteNode = nyNode; 
            stoerrelse++; 

        }
    }



    @Override
    public E hent() {
        if (startNode == null){ 
            return null;
        }
        else {
            return startNode.data; 
        }
    }


    @Override
    public E fjern() throws UgyldigListeindeks{
        if (stoerrelse() == 0){
            System.out.println("Det finnes ingen elementer i Lenkelisten.");
            throw new UgyldigListeindeks(0);
        }
        else{
            E forsteNodeData = startNode.data;
            startNode = startNode.nesteNode; 
            stoerrelse--;
            return forsteNodeData; 
        }

    }



    @Override
    public int stoerrelse() {
        return stoerrelse;
    }


    @Override
    public String toString() {
        String listeInfo = "";

        if (startNode == null){
            listeInfo = "Lenkeliste er tom (ingen elementer i Lenkeliste)";
        } else {
            Node peker = startNode;
            while (peker != null){
                listeInfo += " " + peker.data.toString(); 
                peker = peker.nesteNode;
            }
        }
        return listeInfo;
    }



    @Override
    public Iterator<E> iterator() {
        return new LenkelisteIterator();
    }


    class LenkelisteIterator implements Iterator<E>{

        Node peker = startNode;

        @Override
        public boolean hasNext() { 
            if (peker != null){
                return true;
            } else {
                return false;
            }
        }

        @Override
        public E next() { 
            if(hasNext()){
                E nodeSinData = peker.data;
                peker = peker.nesteNode;
                return nodeSinData;
            } else {
                return null;
            }
        }

    }
}

import java.util.Iterator;

//Nye elementer settes inn på slutten av listen og tas ut fra starten
//slik at det elementet som ble satt inn først, er det første som blir tatt ut.
abstract class Lenkeliste<E> implements Liste<E> {
    public Node startNode; //skriver her pga det er global variabel
    public int stoerrelse;

    protected class Node{ //Node er indre klasse (kan tenkes som Lenkeliste sin "metoden")
        Node nesteNode; //Vi kommer til å ha mange Noder og da treng vi mange nesteNode pekere som lenker nodene sammen
        E data;
        public Node(E data){
            this.data = data;
        }
    }


    @Override
    public void leggTil(E x) { //skal legge inn et nytt element, det skal legges sist i listen

        Node nyNode = new Node(x);

        if (startNode == null){ //hvis listen er tom
            startNode = nyNode;
            stoerrelse++;
        }
        else { //hvis listen ikke er tom
            Node peker = startNode;
            while (peker.nesteNode != null){ //vi treng å finne Node som peker på null
                peker = peker.nesteNode; //bruker peker for å gå til neste node hver gang, så lenge vi finner ikke en node som skal peke på null og da kan vi legge til nyNode
            }
            peker.nesteNode = nyNode; //legger en node til på slutten til lenkelisten
            stoerrelse++; //+1 node på slutten
        }
    }


    @Override
    public E hent() { //skal returnere det første elementet i listen, men ikke fjernes fra listen
        if (startNode == null){ //hvis listen er tom
            return null;
        }
        else {
            return startNode.data; //hvis første element i Lenketliste ikke er null, da henter vi den data
        }
    }


    @Override
    public E fjern() throws UgyldigListeindeks{ //skal fjerne det første elementet i listen og returnere det
        if (stoerrelse() == 0){
            System.out.println("Det finnes ingen elementer i Lenketlisten.");
            throw new UgyldigListeindeks(0);
        }
        else{
            E forsteNodeData = startNode.data;//vi må bruke forsteNodeData for å lagre startNode sin data for å returnere data på slutten
            startNode = startNode.nesteNode; //her kommer vi til node 2 også mister vi første peker (fjerner node 1)
            stoerrelse--;
            return forsteNodeData; //returnerer data fra første node
        }
    }


    @Override
    public int stoerrelse() { //skal returnere hvor mange elementer det er i listen
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
                listeInfo += " " + peker.data.toString(); //for å endre T til String
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
            return peker != null;
        }
        
        @Override
        public E next() {
            E data = peker.data;
            peker = peker.nesteNode;
            return data;
        }
    }
}

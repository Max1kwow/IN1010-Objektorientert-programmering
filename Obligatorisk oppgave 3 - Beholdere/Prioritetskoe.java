
//Klasseparameteren E må implementere Comparable så vi får en mulighet for å sammenligne objkter og sortere etter
class Prioritetskoe<E extends Comparable<E>> extends Lenkeliste<E>{

    //vi ønsker at listen skal være sortert og krever derfor at elementer som settes inn, skal være sammenlignbare.
    // Kall på leggTil(E x) skal altså sette inn elementer i sortert rekkefølge (fra minst til størst, der minst ligger først i listen).


    //Hvis ikke, leter vi oss frem til første element i listen som er større enn det vi skal sette inn.
    //• Flytter elementet og alle de resterende ett hakk lenger ut.
    //• Setter inn det nye elementet på rett plass.
    //• Hvis alle elementene i listen er mindre eller lik det nye elementet, må det nye elementet settes bakerst.
    @Override
    public void leggTil(E x) {
        Node nyNode = new Node(x);
        if(startNode == null){
            startNode = nyNode;
            stoerrelse++;
        } else{  //25          //50
            if (startNode.data.compareTo(x) > 0){
                Node peker = startNode;
                startNode = nyNode;
                nyNode.nesteNode = peker;
                stoerrelse++;
            } else {
                //a er mindre enn b   hvis   a.compareTo(b)   returnerer < 0
                //a er større enn b   hvis   a.compareTo(b)   returnerer > 0
                //a er lik        b   hvis   a.compareTo(b)   returnerer 0
                Node peker = startNode;

                //finne hvor peker skal stoppe
                while (peker.nesteNode != null && peker.nesteNode.data.compareTo(x) < 0){
                    peker = peker.nesteNode;
                }
                //bytt pekkere
                nyNode.nesteNode = peker.nesteNode;
                peker.nesteNode = nyNode;
                stoerrelse++;
            }
        }
    }
}

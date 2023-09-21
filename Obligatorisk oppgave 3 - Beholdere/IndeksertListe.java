class IndeksertListe<E> extends Lenkeliste<E>{
    public IndeksertListe(){
        super();
    }


    //skal sette inn x i listen i posisjon pos der 0<=pos<=stoerrelse().
    //Dette betyr at alle elementene lenger ut i listen forskyves og heretter får en høyere indeks.
    public void leggTil(int pos, E x) throws UgyldigListeindeks{     //alt med pos og indekser treng UgyldigListeindeks
        if (0 > pos || pos > stoerrelse()){
            throw new UgyldigListeindeks(pos);
        }
        Node nyNode = new Node(x);
        if (stoerrelse == 0){
            startNode = nyNode;
        } else { //hvis vi har flere enn 0 noder
            if (pos == 0){
                Node peker = startNode; //lagrer node1 for å ikke miste peker
                startNode = nyNode; //legger til nyNode på starten
                nyNode.nesteNode = peker; //setter peker fra nyNode til Node1
            } else {
                Node peker = startNode;
                for (int i = 0; i < pos - 1; i++){ //for å stoppe rett før riktig posisjon
                    peker = peker.nesteNode;
                }
                nyNode.nesteNode = peker.nesteNode;
                peker.nesteNode = nyNode; //hvis posisjon er 4 vi kommer til å sette nyNode mellome indeks 3 og 4

            }
        }
        stoerrelse++;
    }


    //skal erstatte elementet i posisjon pos med x. Lovlig pos er 0<=pos<stoerrelse().
    //pos kan ikke være lik stoerrelse fordi at antall indekser lik størrelsen - 1
    public void sett (int pos, E x) throws UgyldigListeindeks{
        if (pos >= 0 && pos < stoerrelse()){ //hvis gyldig indeks
            Node peker = startNode;
            for (int i = 0; i < pos; i++){
                peker = peker.nesteNode;
            }
            peker.data = x; //peker peker på riktig Node objekt, bytter data i bestemt node
        }else{
            throw new UgyldigListeindeks(pos);
        }
    }


    //skal hente elementet i gitt posisjon der
    //0<=pos<stoerrelse(). Elementet skal bli stående i listen.
    public E hent(int pos) throws UgyldigListeindeks{
        if (pos >= 0 && pos < stoerrelse()){
            Node peker = startNode;
            for (int i = 0; i < pos; i++){
                peker = peker.nesteNode;
            }
            return peker.data; //peker peker på riktig Node objekt, returnerer bestemt Node sin data
        }else{
            throw new UgyldigListeindeks(pos);
        }
    }


    //skal fjerne elementet i posisjon pos (der 0<=pos<stoerrelse()) og returnere det.
    //Det innebærer at alle elementene lenger ut i listen vil få en lavere indeks.
    public E fjern (int pos){
        if (pos >= 0 && pos < stoerrelse()){ //hvis gyldig indeks
            if (pos == 0){
                E startNodeSinData = startNode.data; //først lager vi startNode data for å returnerer den på linje 93
                startNode = startNode.nesteNode; //her kommer vi til node 2 også mister vi første peker (fjerner node 1)
                stoerrelse--;
                return startNodeSinData; //returnerer den fjernet node sin data
            } else {
                Node peker = startNode;
                for (int i = 0; i < pos - 1; i++){
                    peker = peker.nesteNode;
                }
                E pekerNodeSinData = peker.nesteNode.data; //lagrer pekerNodeSinData før vi fjerner den(den node som skal fjernes)
                peker.nesteNode = peker.nesteNode.nesteNode; //endrer peker slik at posisjon node skal fjernes
                stoerrelse--;
                return pekerNodeSinData; //returnerer data av node som ble fjernet
            }
        }else{
            throw new UgyldigListeindeks(pos);
        }
    }
}




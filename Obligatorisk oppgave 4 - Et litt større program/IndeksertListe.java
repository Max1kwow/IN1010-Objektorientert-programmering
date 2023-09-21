class IndeksertListe<E> extends Lenkeliste<E>{

    public IndeksertListe(){
        super();
    }


    public void leggTil(int pos, E x) throws UgyldigListeindeks{     
        if (0 > pos || pos > stoerrelse()){
            throw new UgyldigListeindeks(pos);
        }
        Node nyNode = new Node(x);
        if (stoerrelse == 0){
            startNode = nyNode;
        } else { //hvis vi har flere enn 0 noder
            if (pos == 0){
                Node peker = startNode; 
                startNode = nyNode; 
                nyNode.nesteNode = peker; 
            } else {
                Node peker = startNode;
                for (int i = 0; i < pos - 1; i++){ 
                    peker = peker.nesteNode;
                }
            
                nyNode.nesteNode = peker.nesteNode; 
                peker.nesteNode = nyNode;          

            }
        }
        stoerrelse++;
    }



    public void sett (int pos, E x) throws UgyldigListeindeks{
        if (pos >= 0 && pos < stoerrelse()){ 
            Node peker = startNode;
            for (int i = 0; i < pos; i++){
                peker = peker.nesteNode;
            }
            peker.data = x; 
        }else{
            throw new UgyldigListeindeks(pos);
        }
    }



    public E hent(int pos) throws UgyldigListeindeks{
        if (pos >= 0 && pos < stoerrelse()){
            Node peker = startNode;
            for (int i = 0; i < pos; i++){
                peker = peker.nesteNode;
            }
            return peker.data; 
        }else{
            throw new UgyldigListeindeks(pos);
        }
    }




    public E fjern (int pos){
        if (pos >= 0 && pos < stoerrelse()){ 
            if (pos == 0){
                E startNodeSinData = startNode.data;
                startNode = startNode.nesteNode; 
                stoerrelse--;
                return startNodeSinData; 
            } else {
                Node peker = startNode;
                for (int i = 0; i < pos - 1; i++){
                    peker = peker.nesteNode;
                }
                E pekerNodeSinData = peker.nesteNode.data; 
                peker.nesteNode = peker.nesteNode.nesteNode; 
                stoerrelse--;
                return pekerNodeSinData; 
            }

        }else{
            throw new UgyldigListeindeks(pos);
        }
    }



}

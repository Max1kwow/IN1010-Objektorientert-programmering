import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//bygg en monitor rundt SubsekvensRegisteret du skrev i oppgave 2,
//slik at bare én tråd kan sette inn en HashMap om gangen.
class Monitor1 {
    protected SubsekvensRegister subRegisterObjekt;
    Lock laas = new ReentrantLock();
    public Monitor1(SubsekvensRegister subRegisterObjekt){
        this.subRegisterObjekt = subRegisterObjekt;
    }


    //bygg en monitor slik at bare en tråd kan sette inn en HashMap om gangen
    public void leggTil_HashMapMedSubsekvenser(HashMap<String, Subsekvens> hashMap){
        //kritisk region:
        laas.lock();

        try {
            subRegisterObjekt.leggTil_HashMapMedSubsekvenser(hashMap);
        } finally {
            laas.unlock();
        }
    }


    public HashMap<String,Subsekvens> taUt_HashMapMedSubsekvenser(int indeks){
        //kritisk region:
        laas.lock();

        try {
            return subRegisterObjekt.taUt_HashMapMedSubsekvenser(indeks);
        } finally {
            laas.unlock();
        }
    }

    public int hentAntallHashMaper(){
        return subRegisterObjekt.hentAntallHashMaper();
    }

    public static HashMap<String,Subsekvens> lesFil(String fil){
        return SubsekvensRegister.lesFil(fil); 
    }

    public static HashMap<String,Subsekvens> slaaHashMaperSammen(HashMap<String,Subsekvens> hashMap1, HashMap<String,Subsekvens> hashMap2){
        return SubsekvensRegister.slaaHashMaperSammen(hashMap1, hashMap2);
    }

    public void combinerHashMap(){
        subRegisterObjekt.combinerHashMap();
    }

}

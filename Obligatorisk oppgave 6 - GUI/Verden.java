import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Verden implements Runnable{

    public int antRader;
    public int antKolonner;
    public Rutenett rutenett;
    public int genNr = 0;
    private Lock laas = new ReentrantLock();
    private Condition ventSignal = laas.newCondition(); //returnerer condition
    GameOfLifeView view;

    private boolean vent = true;

    public Verden(int antRader, int antKolonner, GameOfLifeView view){
        this.antRader = antRader;
        this.antKolonner = antKolonner;
        rutenett = new Rutenett(antRader, antKolonner);
        rutenett.fyllMedTilfeldigeCeller();
        rutenett.kobleAlleCeller();
        this.view = view;
    }

    public void tegn(){
        rutenett.tegnRutenett();
        System.out.println("\nGenerasjon nr: " + genNr);
        System.out.println("Det er " +  rutenett.antallLevende() + " levende celler.");
    }

    public void oppdatering(){
        for (int i=0; i < antRader; i++){
            for (int j=0; j < antKolonner; j++){
                rutenett.hentCelle(i,j).oppdaterStatus();
            }
        }
        genNr++;
    }

    public void start(){
        this.vent = false;
        laas.lock();
        try{
            ventSignal.signalAll();
        }finally {
            laas.unlock();
        }
    }

    public void stop(){this.vent = true;}

    @Override
    public void run() {

        while (true){
            laas.lock();
            try{
                while (this.vent){
                    ventSignal.await();
                }

                oppdatering(); //oppdaterer status til alle celler i rutenett


                for (int i=0; i < antRader; i++){
                    for (int j=0; j < antKolonner; j++){
                        Celle celle = rutenett.hentCelle(i, j);
                        boolean erLevende = celle.erLevende();
                        view.oppdaterStatus(i, j, erLevende);
                    }
                }

                view.oppdaterAntall(rutenett.antallLevende());

                try{
                    Thread.sleep(2000);
                } catch (InterruptedException e){
                    System.out.println("Exception");
                }
            } catch (InterruptedException e){
                System.out.println("InterruptedException");
            }finally {
                laas.unlock();
            }


        }



    }
}
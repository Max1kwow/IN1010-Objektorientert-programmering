import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class GameOfLifeControl{ //bindeledde mellom Gui og model

    private int rad, kol; //hvorfor trenger vi rad, kol her?
    Verden gameOfLifeModel; //Verden er vår "modell"
    GameOfLifeView gameOfLifeView;
    private Thread traad;

    public GameOfLifeControl(int rad, int kol){
        this.rad = rad;
        this.kol = kol;
        gameOfLifeView = new GameOfLifeView(rad, kol, this);
        gameOfLifeModel = new Verden(rad, kol, gameOfLifeView);

        // fiks ferdig GUI etter model er ferdig laget
        gameOfLifeView.lagTabell();
        gameOfLifeView.oppdaterAntall(gameOfLifeModel.rutenett.antallLevende());

        traad = new Thread(gameOfLifeModel);
        traad.start();
    }


    //kaller på hentCelle(rad, kol), rutenett sin metoden for å hente nødvendig celle
    public Celle hentCelle(int rad, int kol){
        return gameOfLifeModel.rutenett.hentCelle(rad, kol);
    }


    public void start(){
        gameOfLifeModel.start();
    }

    public void stop(){
        gameOfLifeModel.stop();
    }

}

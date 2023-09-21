import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GameOfLifeView {

    //kan være lurt å skrive instansvariablene her, for å ha tilgang til dem senere utenfor konstruktør
    private int antRad, antKol;
    private JLabel antallLevende_tekst;
    private JPanel mainPanel, topPanel, bottomPanel;
    private JButton buttonStartStop, buttonAvslutt;
    private int windowW, windowH;
    private GameOfLifeControl kontroller;

    JButton [][] jButtonsListe;

    public GameOfLifeView(int antRad, int antKol, GameOfLifeControl kontroller){
        this.antRad = antRad;
        this.antKol = antKol;
        jButtonsListe = new JButton[antRad][antKol];
        this.kontroller = kontroller;

        JFrame vindu = new JFrame("Game of life");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //lukke programmet
        this.windowW = 800;
        this.windowH = 600;
        vindu.setSize(windowW, windowH);




        //kobler knappene til en spesefik funksjon
        class ButtonAction implements ActionListener{
            JButton buttonStartStop;
            public ButtonAction(JButton buttonStartStop){
                this.buttonStartStop = buttonStartStop;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                if (this.buttonStartStop.getText().equals("Start")){
                    this.buttonStartStop.setText("Stop");
                    kontroller.start();
                }else {
                    this.buttonStartStop.setText("Start");
                    kontroller.stop();
                }

            }
        }


        //knappen avslutt, avslutter programmet
        class ButtonAvslutt implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        }

        //hoved panelet
        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new BorderLayout());
        vindu.add(this.mainPanel); //legger hoved panelet til vinduet (JFrame)


        // ------------------- Top panel -----------------------
        this.topPanel = new JPanel(); //top panel som har to knapper og tekst
        int topPanelH = 50;
        this.topPanel.setPreferredSize(new Dimension(windowW, topPanelH)); //endrer på størrelse til top panelet
        this.mainPanel.add(this.topPanel, BorderLayout.NORTH); //plaserer top panelet øverst oppe i mainPanel
        this.antallLevende_tekst = new JLabel("Antall levende celler: 0"); //bruker JLabel for å legge til tekst
        antallLevende_tekst.setFont(new Font("Arial", Font.BOLD, 15)); //endrer på knapper tekst størrelse her

        this.buttonStartStop = new JButton("Start");
        this.buttonStartStop.addActionListener(new ButtonAction(buttonStartStop)); //kobler Start knapp til en funksjon


        this.buttonAvslutt = new JButton("Avslutt");
        this.buttonAvslutt.addActionListener(new ButtonAvslutt()); //kobler Avslutt knapp til en funksjon

        //legger alle objektene (knappene) til top panelet
        this.topPanel.add(this.antallLevende_tekst);
        this.topPanel.add(this.buttonStartStop);
        this.topPanel.add(this.buttonAvslutt);


        // ------------------- Bottom panel -----------------------
        this.bottomPanel = new JPanel(); //lager bottom panelet
        this.bottomPanel.setPreferredSize(new Dimension(windowW, windowH-topPanelH)); //endrer på størrelse til bottom panelet
        this.mainPanel.add(this.bottomPanel, BorderLayout.CENTER); //plaserer bottom panelet nederst i mainPanel

        vindu.setLocationRelativeTo(null); //vinduet i sentrum
        vindu.setVisible(true);
    }



    public void lagTabell(){
        //deler bottom panel på rader og kolloner
        this.bottomPanel.setLayout(new GridLayout(this.antRad,this.antKol));
        for (int i=0; i < this.antRad; i++){
            for (int j=0; j < this.antKol; j++){

                boolean levende = kontroller.hentCelle(i, j).erLevende();
                String tegn = "";
                if(levende){
                    tegn = "O";
                }

                JButton knappen = new JButton(tegn); //vi kaller på JButtonKlassen som sjekker om denne bestemt Cellen som vi er på nå er levende eller ikke og lager tilsvarende visuell representasjon (0 eller .)
                //knappen.setMargin(new Insets(0,0,0,0)); //hvis man vil legge noe tekst til knappene
                //knappen.setFont(new Font("Arial", Font.PLAIN, 17)); //også for tekst i knappene?
                jButtonsListe[i][j] = knappen;
                this.bottomPanel.add(knappen);//legger knappene til bottomPanel
            }
        }
    }

    public void oppdaterStatus(int i, int j,  Boolean levende){
        if (levende){
            jButtonsListe[i][j].setText("O");
        } else {
            jButtonsListe[i][j].setText("");
        }
    }

    public void oppdaterAntall(int antall){
        this.antallLevende_tekst.setText("Antall levende celler: " + antall);
    }


}



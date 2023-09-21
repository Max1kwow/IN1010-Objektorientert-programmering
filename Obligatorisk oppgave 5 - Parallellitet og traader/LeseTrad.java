class LeseTrad implements Runnable{
    private String filNavnet;
    private Monitor1 monitor1;

    public LeseTrad(String filNavnet, Monitor1 monitor1){
        //initialiser Data
        this.filNavnet = filNavnet;
        this.monitor1 = monitor1;
    }

    @Override
    public void run() {
        //Tråd-koden som bruker Data
        try{
            monitor1.leggTil_HashMapMedSubsekvenser(Monitor1.lesFil(filNavnet));
        } catch (Exception e){
            System.out.println("Noe gikk feil med Tråd!");
        }
    }
}

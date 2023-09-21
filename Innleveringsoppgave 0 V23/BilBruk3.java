public class BilBruk3 {
    public static void main(String[] args) {
        Bil3 bil1 = new Bil3("NO12345");  //lager bil objekt
        Person pers1 = new Person(bil1);            //lager person objekt
        pers1.skrivUt();


        //Bil3 bil2 = new Bil3("NO54364");  //lager bil objekt
        //Person pers2 = new Person(bil2);  //lager person objekt
        //pers2.skrivUt();
    }
}

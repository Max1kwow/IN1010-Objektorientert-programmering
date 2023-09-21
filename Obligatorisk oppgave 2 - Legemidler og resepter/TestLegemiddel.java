public class TestLegemiddel {
    public static void main(String[] args) {
        Narkotisk narkotisk = new Narkotisk("Narkotisk medisin", 50, 125,500);
        Narkotisk narkotisk1 = new Narkotisk("Narkotisk medisin", 50, 125,500);
        Vanedannende vanedannende = new Vanedannende("Vanedannende medisin", 35, 230,750);
        Vanlig vanlig = new Vanlig("Vanlig medisin", 70, 90);

       //det printes ut samme id av samme objekt hver gang fordi at når vi skriver: System.out.println(narkotisk) 2 eller 10 ganger, objektet narkotisk printes bare ut,
        //det lages ikke 2 eller 10 forskjellige objekter narkotisk (derfor id blir samme) og når vi kaller på narkotisk1 eller vanedannende da økes id til 1 og 2 osv.
        System.out.println(narkotisk); //id 0
        System.out.println(narkotisk); //id 0
        System.out.println(narkotisk1); //id 1
        System.out.println(vanedannende); //id 2
        System.out.println(vanlig); //id 3
        System.out.println(vanlig); //id 3



        System.out.println("\n" + testLegemiddelId(narkotisk, 0));
        System.out.println("\n" + testPris(narkotisk, 50));





    }



    private static boolean testLegemiddelId(Legemiddel legemiddel, int forventetLegemiddelId) {
        return legemiddel.id == forventetLegemiddelId;
    }

    private static boolean testPris(Legemiddel legemiddel, int forventetLegemiddelPris) {
        return legemiddel.hentPris() == forventetLegemiddelPris;
    }

}

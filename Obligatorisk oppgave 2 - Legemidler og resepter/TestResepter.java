public class TestResepter {
    public static void main(String[] args) {
        Narkotisk narkotisk = new Narkotisk("Narkotisk medisin", 150, 300,500);
        Narkotisk narkotisk1 = new Narkotisk("Narkotisk medisin", 150, 300,500);
        Lege lege = new Lege("Thomas");

        MilResept milResept = new MilResept(narkotisk, lege, 1);
        MilResept milResept1 = new MilResept(narkotisk, lege, 1);
        PResept pResept = new PResept(narkotisk1, lege, 2, 3);
        BlaaResept blaaResept = new BlaaResept(narkotisk, lege, 3, 1);

        //System.out.println(milResept);
        //System.out.println(pResept);
        System.out.println(blaaResept);


        System.out.println("\n" + testResepterId(milResept1, 1));
        System.out.println("\n" + testPris(milResept1, 0));
    }



    private static boolean testResepterId(Resept milResept, int forventetReseptId) {
        return milResept.id == forventetReseptId;
    }

    private static boolean testPris(Resept milResept, int forventetReseptPris) {
        return milResept.prisAaBetale() == forventetReseptPris;
    }
}

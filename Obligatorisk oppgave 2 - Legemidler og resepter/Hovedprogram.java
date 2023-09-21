public class Hovedprogram {
    public static void main(String[] args) {
        Narkotisk narkotisk = new Narkotisk("Narkotisk medisin", 50, 125,500);
        Vanedannende vanedannende = new Vanedannende("Vanedannende medisin", 35, 230,750);
        Vanlig vanlig = new Vanlig("Vanlig medisin", 70, 90);

        Lege lege = new Lege("Thomas");
        Spesialist spesLege = new Spesialist("Peder", "GBD335");

        HviteResepter hviteResepter = new HviteResepter(narkotisk, spesLege, 5, 2);
        MilResept milResept = new MilResept(narkotisk, lege, 1);
        PResept pResept = new PResept(vanedannende, spesLege, 2, 3);
        BlaaResept blaaResept = new BlaaResept(vanlig, lege, 3, 3);


        System.out.println(narkotisk);
        System.out.println("--------------");

        System.out.println(vanedannende);
        System.out.println("--------------");

        System.out.println(vanlig);
        System.out.println("--------------");

        System.out.println(lege);
        System.out.println("--------------");

        System.out.println(spesLege);
        System.out.println("--------------");


        System.out.println(hviteResepter);
        System.out.println("--------------");


        System.out.println(milResept);
        System.out.println("--------------");

        System.out.println(pResept);
        System.out.println("--------------");

        System.out.println(blaaResept);
        System.out.println("--------------");

    }
}

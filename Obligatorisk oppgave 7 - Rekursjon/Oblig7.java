import java.util.Scanner;

public class Oblig7 {
    public static void main(String[] args) {
        String sparsmal = "Skriv inn koordinater <rad> <kolonne> ('-1' for aa avslutte): ";
        String filnavn = args[0];
        Labyrint labyrint = new Labyrint(filnavn);

        System.out.println(sparsmal);
        Scanner sc = new Scanner(System.in);
        int inputRad = sc.nextInt();
        int inputKol = sc.nextInt();

        while (inputRad != -1){
            labyrint.finnUtveiFra(inputRad, inputKol);

            System.out.println(sparsmal);
            inputRad = sc.nextInt();
            if (inputRad != -1){
                inputKol = sc.nextInt();
            }
        }
    }
}

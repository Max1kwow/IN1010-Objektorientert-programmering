import java.util.Scanner;

public class GameOfLife {
    public static void main(String[] args) {

        Verden verden = new Verden(25,25);
        verden.tegn();
        verden.oppdatering();

        Scanner system = new Scanner(System.in);
        System.out.println("Press enter to continue, or q to exit!");
        String input = system.nextLine();

        while (!input.equals("q")){
            verden.tegn();
            verden.oppdatering();
            input = system.nextLine();
        }

    }
}

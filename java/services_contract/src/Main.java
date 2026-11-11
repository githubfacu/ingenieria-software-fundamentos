import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        final int YEAR = 2026;
        ServicesContract servicesContract = new ServicesContract("USantaTecla", YEAR);

        Menu optionsMenu = new Menu(servicesContract, scanner);

        boolean exitMenu = false;

        while (!exitMenu){
            exitMenu = !optionsMenu.showOptions();
        }

        scanner.close();
    }
}
import java.util.Scanner;

public class Menu {

    private final String[] options = {
            "1. Mostrar contrato",
            "2. Mostrar coste anual",
            "3. Cancelar contrato",
            "4. Ampliar contrato",
            "5. Desplazar contrato",
            "0. Salir"
    };
    private ServicesContract servicesContract;
    private Scanner scanner;

    public Menu(ServicesContract servicesContract, Scanner scanner) {
        this.servicesContract = servicesContract;
        this.scanner = scanner;
    }

    private int readInt(String msg){
        System.out.println(msg);
        return scanner.nextInt();
    }

    private double readDouble(String msg){
        System.out.println(msg);
        return scanner.nextDouble();
    }

    private Date readDate(){
        int day = readInt("Introduce dia");
        int month = readInt("Introduce mes");
        return new Date(day, month, servicesContract.getYear());
    }

    private void execChosenOption(int chosenOption){
        Date date;
        switch (chosenOption){
            case 1:
                servicesContract.writeln();
                break;

            case 2:
                double cost = servicesContract.getCost();
                System.out.println("Coste anual del contrato: " + cost);
                break;

            case 3:
                date = readDate();
                servicesContract.cancel(date);
                break;

            case 4:
                date = readDate();
                double scale = readDouble("Introduce escala");
                servicesContract.enlarge(date, scale);
                break;

            case 5:
                date = readDate();
                double shift = readDouble("Introduce longitud desplazamiento");
                servicesContract.shift(date, shift);
                break;

            default:
                System.out.println("Opción no válida");
        }
    }

    public boolean showOptions(){
        for (String option : options) {
            System.out.println(option);
        }
        int chosenOption = readInt("Selecciona opción");
        if (chosenOption == 0) {
            return false; // salir
        }
        execChosenOption(chosenOption);
        return true;
    }

}

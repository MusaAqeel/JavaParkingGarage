// Musa
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ParkingGarage parkingGarage = new ParkingGarage("parkedCars.txt");

        while (true) {
            System.out.println("Welcome to the Parking Garage");
            System.out.println("1. Park a car");
            System.out.println("2. Exit a car");
            System.out.println("3. View parked cars");
            System.out.println("4. View statistics of parking garage");
            System.out.println("5. Get empty spots in the parking garage");
            System.out.println("6. Sort cars by make");
            System.out.println("7. Save current state of parking garage");
            System.out.println("8. Exit program");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    parkingGarage.parkCar();
                    break;
                case 2:
                    parkingGarage.exitCar();
                    break;
                case 3:
                    parkingGarage.viewCars();
                    break;
                case 4:
                    parkingGarage.getStatistics();
                    break;
                case 5:
                    parkingGarage.getEmptySpots();
                    break;
                case 6:
                    parkingGarage.sortCars();
                    break;
                case 7:
                    parkingGarage.writeToFile();
                    break;
                case 8:
                    System.out.println("Exiting program...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}

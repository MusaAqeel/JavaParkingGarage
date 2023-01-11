import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class ParkingGarage {
    private Car[] westSpots;
    private Car[] eastSpots;
    private LinkedList<Car> westEntrance;
    private LinkedList<Car> eastEntrance;
    private String fileName;

    public ParkingGarage(String fileName) {
        westSpots = new Car[4];
        eastSpots = new Car[4];
        westEntrance = new LinkedList<>();
        eastEntrance = new LinkedList<>();
        this.fileName = fileName;
        readFromFile();
    }

    private void readFromFile() {
        try {
            Scanner fileScanner = new Scanner(new File(fileName));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] carInfo = line.split(",");
                Car car = new Car(carInfo[0], carInfo[1], carInfo[2], carInfo[3], carInfo[4], Integer.parseInt(carInfo[5]));
                if (car.getEntrance().equals("west")) {
                    westSpots[car.getSpotNumber()] = car;
                } else {
                    eastSpots[car.getSpotNumber()] = car;
                }
            }
            fileScanner.close();
        } catch (IOException e) {
            System.out.println("Error reading from file");
        }
    }

    public void parkCar() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter car make: ");
        String make = sc.nextLine();
        System.out.print("Enter car model: ");
        String model = sc.nextLine();
        System.out.print("Enter car color: ");
        String color = sc.nextLine();
        System.out.print("Enter car plate number: ");
        String plateNumber = sc.nextLine();
        System.out.print("Enter entrance (west or east): ");
        String entrance = sc.nextLine();
        System.out.print("Enter spot number (1-4): ");
        int spotNumber = sc.nextInt();
        if (entrance.equalsIgnoreCase("west")) {
            if (westSpots[spotNumber - 1] == null) {
                westSpots[spotNumber - 1] = new Car(make, model, color, plateNumber, entrance, spotNumber);
                westEntrance.addLast(westSpots[spotNumber - 1]);
                System.out.println("Car parked successfully in the west entrance spot " + spotNumber);
                updateFile();
            } else {
                System.out.println("Spot already occupied. Please choose a different spot.");
            }
        } else if (entrance.equalsIgnoreCase("east")) {
            if (eastSpots[spotNumber - 1] == null) {
                eastSpots[spotNumber - 1] = new Car(make, model, color, plateNumber, entrance, spotNumber);
                eastEntrance.addLast(eastSpots[spotNumber - 1]);
                System.out.println("Car parked successfully in the east entrance spot " + spotNumber);
                updateFile();
            } else {
                System.out.println("Spot already occupied. Please choose a different spot.");
            }
        } else {
            System.out.println("Invalid entrance. Please enter 'west' or 'east'.");
        }
    }

    public void exitCar() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter entrance (west or east): ");
        String entrance = sc.nextLine();
        System.out.print("Enter spot number (1-4): ");
        int spotNumber = sc.nextInt();
        if (entrance.equalsIgnoreCase("west")) {
            if (westSpots[spotNumber - 1] != null) {
                westSpots[spotNumber - 1] = null;
                westEntrance.remove(westEntrance.get(spotNumber - 1));
                System.out.println("Car successfully removed from west entrance spot " + spotNumber);
                updateFile();
            } else {
                System.out.println("Spot already empty. Please choose a different spot.");
            }
        } else if (entrance.equalsIgnoreCase("east")) {
            if (eastSpots[spotNumber - 1] != null) {
                eastSpots[spotNumber - 1] = null;
                eastEntrance.remove(eastEntrance.get(spotNumber - 1));
                System.out.println("Car successfully removed from east entrance spot " + spotNumber);
                updateFile();
            } else {
                System.out.println("Spot already empty. Please choose a different spot.");
            }
        } else {
            System.out.println("Invalid entrance. Please enter 'west' or 'east'.");
        }
    }

    public void displayParkingStatus() {
        System.out.println("West entrance:");
        for (int i = 0; i < westSpots.length; i++) {
            if (westSpots[i] != null) {
                System.out.println("Spot " + (i + 1) + ": Occupied by a " + westSpots[i].getColor() + " " + westSpots[i].getMake() + " " + westSpots[i].getModel() + " with plate number " + westSpots[i].getPlateNumber());
            } else {
                System.out.println("Spot " + (i + 1) + ": Empty");
            }
        }
        System.out.println("East entrance:");
        for (int i = 0; i < eastSpots.length; i++) {
            if (eastSpots[i] != null) {
                System.out.println("Spot " + (i + 1) + ": Occupied by a " + eastSpots[i].getColor() + " " + eastSpots[i].getMake() + " " + eastSpots[i].getModel() + " with plate number " + eastSpots[i].getPlateNumber());
            } else {
                System.out.println("Spot " + (i + 1) + ": Empty");
            }
        }
    }

    public void updateFile() {
        try {
            FileWriter writer = new FileWriter(fileName);
            for (int i = 0; i < westSpots.length; i++) {
                if (westSpots[i] != null) {
                    writer.write(westSpots[i].toString() + "\n");
                }
            }
            for (int i = 0; i < eastSpots.length; i++) {
                if (eastSpots[i] != null) {
                    writer.write(eastSpots[i].toString() + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void sortCars() {
        Arrays.sort(westSpots, new CarComparator());
        Arrays.sort(eastSpots, new CarComparator());
    }

    public void viewCars() {
        System.out.println("Cars parked at West entrance: ");
        for (int i = 0; i < westSpots.length; i++) {
            if (westSpots[i] != null) {
                System.out.println("Spot " + (i + 1) + ": " + westSpots[i].getMake() + " " + westSpots[i].getModel() + " (" + westSpots[i].getPlateNumber() + ")");
            }
        }
        System.out.println("Cars parked at East entrance: ");
        for (int i = 0; i < eastSpots.length; i++) {
            if (eastSpots[i] != null) {
                System.out.println("Spot " + (i + 1) + ": " + eastSpots[i].getMake() + " " + eastSpots[i].getModel() + " (" + eastSpots[i].getPlateNumber() + ")");
            }
        }
    }


    class CarComparator implements Comparator<Car> {
        @Override
        public int compare(Car car1, Car car2) {
            return car1.getMake().compareTo(car2.getMake());
        }
    }


    public void findCar(String plateNumber) {
        for (int i = 0; i < westSpots.length; i++) {
            if (westSpots[i] != null && westSpots[i].getPlateNumber().equalsIgnoreCase(plateNumber)) {
                System.out.println("Car found at spot " + (i + 1) + " at the west entrance");
                return;
            }
        }
        for (int i = 0; i < eastSpots.length; i++) {
            if (eastSpots[i] != null && eastSpots[i].getPlateNumber().equalsIgnoreCase(plateNumber)) {
                System.out.println("Car found at spot " + (i + 1) + " at the east entrance");
                return;
            }
        }
        System.out.println("Car not found");
    }

    public void getStatistics() {
        int westOccupied = 0;
        int eastOccupied = 0;
        for (int i = 0; i < westSpots.length; i++) {
            if (westSpots[i] != null) {
                westOccupied++;
            }
        }
        for (int i = 0; i < eastSpots.length; i++) {
            if (eastSpots[i] != null) {
                eastOccupied++;
            }
        }
        System.out.println("West Entrance: " + westOccupied + " spots occupied out of " + westSpots.length);
        System.out.println("East Entrance: " + eastOccupied + " spots occupied out of " + eastSpots.length);
    }


    public void writeToFile() {
        try {
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");
            for (int i = 0; i < westSpots.length; i++) {
                if (westSpots[i] != null) {
                    writer.println("west," + westSpots[i].getPlateNumber() + "," + westSpots[i].getMake() + "," + westSpots[i].getModel() + "," + westSpots[i].getColor() + "," + (i + 1));
                }
            }
            for (int i = 0; i < eastSpots.length; i++) {
                if (eastSpots[i] != null) {
                    writer.println("east," + eastSpots[i].getPlateNumber() + "," + eastSpots[i].getMake() + "," + eastSpots[i].getModel() + "," + eastSpots[i].getColor() + "," + (i + 1));
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getEmptySpots() {
        System.out.println("Empty spots at West entrance: ");
        for (int i = 0; i < westSpots.length; i++) {
            if (westSpots[i] == null) {
                System.out.print((i + 1) + ", ");
            }
        }
        System.out.println("\nEmpty spots at East entrance: ");
        for (int i = 0; i < eastSpots.length; i++) {
            if (eastSpots[i] == null) {
                System.out.print((i + 1) + ", ");
            }
        }
        System.out.println();
    }
}





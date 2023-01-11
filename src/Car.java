public class Car {
    private String make;
    private String model;
    private String color;
    private String plateNumber;
    private String entrance;
    private int spotNumber;

    public Car(String make, String model, String color, String plateNumber, String entrance, int spotNumber) {
        this.make = make;
        this.model = model;
        this.color = color;
        this.plateNumber = plateNumber;
        this.entrance = entrance;
        this.spotNumber = spotNumber;
    }

    // getters and setters for the fields
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }
    public String getEntrance() {
        return entrance;
    }

    public void setEntrance(String entrance) {
        this.entrance = entrance;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(int spotNumber) {
        this.spotNumber = spotNumber;
    }
}

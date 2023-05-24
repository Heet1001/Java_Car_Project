package vehiclepackage;
import javax.swing.*;

public class Car extends Vehicle {
    public static final double MPG = 20.0;
    public static final int MAX_DRIVING_HOURS = 8;
    public static final int AVG_SPEED = 60;
    private boolean isRental;

    // Constructor for the car itself
    public Car(String carType, boolean isRental) {
        super(carType, null);
        this.isRental = isRental;
        if(carType.equals("Sedan")) {
            capacity = 5;
        } else if(carType.equals("Van")) {
            capacity = 8;
        } else{ // if coupe
            capacity = 2;
        }
    }

    // Constructor for the button
    public Car(String carType, ImageIcon icon) {
        super(carType, icon);
    }

    public boolean isRental() {
        return isRental;
    }
}

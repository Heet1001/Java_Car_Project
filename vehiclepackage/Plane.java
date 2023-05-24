package vehiclepackage;
import javax.swing.*;

public class Plane extends Vehicle {
    private double ticketCost;

    public Plane(String brand, ImageIcon icon, double pricePerMile, int distance) {
        super(brand, icon);
        this.ticketCost = 50 + (pricePerMile*distance);
        speed = 450;
    }

    public double getTotalCost(int numPeople){
        return numPeople*ticketCost;
    }
}

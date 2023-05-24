package vehiclepackage;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;

public abstract class Vehicle extends JButton {
    protected int speed; // in miles per hour
    protected int capacity;

    public Vehicle(String brand, ImageIcon icon) {
        super(brand, icon);
        setPreferredSize(new DimensionUIResource(350, 250));
    }

    public int getCapacity() {
        return capacity;
    }

    public double getTime(int distance) {
        return (double)distance/speed;
    }
}

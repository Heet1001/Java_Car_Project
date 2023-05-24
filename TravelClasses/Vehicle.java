package TravelClasses;

public abstract class Vehicle {
    private String brand;
    private int speed;
    private int capacity;
    private float gasTank;

    public abstract int calculateSpeed();
    public abstract float getGas(float gasPrice);

    public int getCapacity() {
        return capacity;
    }
}

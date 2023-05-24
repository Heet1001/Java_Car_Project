package TravelClasses;

public class Car extends Vehicle {
    private String carType;
    private int mpg; 

    public Car(String carType, int mpg)
    {
        this.carType=carType;
        this.mpg=mpg;
    }

    public void hotelAndCityStops()
    {
        
    }
    public float estimatePrice()
    {

    }

    @Override
    public int calculateSpeed() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getGas(float gasPrice) {
        // TODO Auto-generated method stub
        return 0;
    }
}

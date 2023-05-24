package TravelClasses;

public class State {
    private String name;
    // Hotel
    private float averageGasPrice;
    private City capital;
    private int distanceFromHome;

    public State(String name, String capitalName, int distance, float avgGasPrice) {
        this.name = name;
        capital = new City(capitalName);
        distanceFromHome = distance;
        averageGasPrice = avgGasPrice;
    }

}

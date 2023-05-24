package guipackage;
import java.awt.*;           
import javax.swing.*;
import java.lang.Math;
import travelpackage.Airport;
import travelpackage.Garage;
import travelpackage.State;
import vehiclepackage.*;


public class FinalPanel extends JPanel {
    private final double HOTEL_FEE = 60.0;
    private final double FOOD_COST = 12.0*2; // $12 a person, eats twice a day
    private JPanel helperPanel = new JPanel();
    private JPanel costInfo = new JPanel();
    private int totalDistance = 0;
    private double totalTripCost = 0;
    private double totalTime = 0;
    private String stateName = "";

    public FinalPanel(State selectedState, int groupSize, double groupWallet, JPanel infoPanel, MainFrame mainFrame){

        totalDistance = selectedState.getDistance();
        stateName = selectedState.getText();
        if(infoPanel instanceof Garage) {
            Garage garage = (Garage)infoPanel;
            double totalHotelCost = 0;
            double totalGasCost = 0;
            double foodCost = 0;
            double rentalFee = 0;
            int totalStops=0;
            totalGasCost = (totalDistance/Car.MPG) * Garage.AVG_GAS_PRICE * garage.getTotalCars();
            totalTime = (double) totalDistance/Car.AVG_SPEED;
            totalStops = (int) totalTime / Car.MAX_DRIVING_HOURS;
            totalHotelCost = HOTEL_FEE * totalStops * (int)Math.ceil((double)groupSize/2); // two people to a room
            foodCost = FOOD_COST*groupSize*totalStops;

            setUpPanel();
            totalTripCost = (double) totalGasCost + totalHotelCost + foodCost + garage.getTotalHourlyRentalFees();
            totalTripCost = truncate(totalTripCost);
            rentalFee = truncate(garage.getTotalHourlyRentalFees() * totalTime);
            totalTime = truncate(totalTime);
            totalHotelCost = truncate(totalHotelCost);
            
            JLabel totalCostPrompt = new JLabel("The total trip cost for your trip from Texas to "+stateName+" is: $"+ totalTripCost);
            totalCostPrompt.setFont(new Font("Arial", Font.PLAIN, 20));
            costInfo.add(totalCostPrompt);
            JLabel totalTimePrompt = new JLabel("The total time spent driving will be: "+totalTime+" hours");
            totalTimePrompt.setFont(new Font("Arial", Font.PLAIN, 20));
            costInfo.add(totalTimePrompt);
            JLabel totalHotelCostPrompt = new JLabel("Total hotel cost will be: $"+totalHotelCost);
            totalHotelCostPrompt.setFont(new Font("Arial", Font.PLAIN, 20));
            costInfo.add(totalHotelCostPrompt);
            JLabel foodCostPrompt = new JLabel("Total food cost is: $"+foodCost);
            foodCostPrompt.setFont(new Font("Arial", Font.PLAIN, 20));
            costInfo.add(foodCostPrompt);
            JLabel totalGasPrompt = new JLabel("Total money spent on gas: $"+totalGasCost);
            totalGasPrompt.setFont(new Font("Arial", Font.PLAIN, 20));
            costInfo.add(totalGasPrompt);
            JLabel rentalFeeCostPrompt = new JLabel("Total cost for rental car: $"+rentalFee);
            rentalFeeCostPrompt.setFont(new Font("Arial", Font.PLAIN, 20));
            costInfo.add(rentalFeeCostPrompt);
            add(helperPanel,BorderLayout.NORTH);
            add(costInfo,BorderLayout.CENTER);
        }

        if(infoPanel instanceof Airport) {
            Plane plane = ((Airport)infoPanel).getSelectedPlane();
            totalTripCost = plane.getTotalCost(groupSize);
            totalTime = plane.getTime(totalDistance);
            totalTripCost = (double) Math.round(totalTripCost*100)/100;
            totalTime = (double) Math.round(totalTime*100)/100;

            setUpPanel();
            
            JLabel totalCostPrompt = new JLabel("The total trip cost for your trip from Texas to "+ stateName +" is: $"+ totalTripCost);
            totalCostPrompt.setFont(new Font("Arial", Font.PLAIN, 20));
            costInfo.add(totalCostPrompt);
            JLabel totalTimePrompt = new JLabel("The total time spent in the trip will be: "+totalTime+" hours");
            totalTimePrompt.setFont(new Font("Arial", Font.PLAIN, 20));
            costInfo.add(totalTimePrompt);
            add(helperPanel,BorderLayout.NORTH);
            add(costInfo,BorderLayout.CENTER);
        }
        
        
        if(groupWallet < totalTripCost){
            mainFrame.resetFrame(this);
            JOptionPane.showMessageDialog(helperPanel,
                "You dont have enough money",
                "Insane error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    

    private double truncate(double numberToTruncate) {
        return (double) Math.round(numberToTruncate*100)/100; // (x*100)/100 is to get only two decimal places
    }
    
    private void setUpPanel() {
        ImageIcon thankYou = new ImageIcon("./Pictures/thankyou.png");
        JLabel imgLbl = new JLabel(thankYou);
        helperPanel.add(imgLbl);
        costInfo.setLayout(new BoxLayout(costInfo, BoxLayout.Y_AXIS));
    }
}
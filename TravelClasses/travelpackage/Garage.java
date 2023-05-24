package travelpackage;

import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vehiclepackage.*;

public class Garage extends JPanel {
    public final static double AVG_GAS_PRICE = 4.00;
    private final double RENTAL_FEE = 68.0/24;
    private ArrayList<Car> cars = new ArrayList<Car>();
    private boolean finished = false;
    private int totalCapacity;

    public Garage(int distance, int groupSize) {
        int[] numOfCars = {0, 0, 0};
        JButton[] remove = new JButton[3];
        String[] carTypes = {"Sedan", "Van", "Coupe"};
        JPanel[] panelsForCars = new JPanel[3];
        JPanel labelHolder = new JPanel();
        JLabel[] numOfCarsLabel = new JLabel[3];
        JPanel allButtons = new JPanel();
        JLabel carSelectPrompt = new JLabel("Please add the cars you currently own:");
        carSelectPrompt.setFont(new Font("Arial", Font.PLAIN, 30));
        
        for(int i = 0; i < carTypes.length; i++) {
            numOfCarsLabel[i] = new JLabel("0");
            labelHolder.add(numOfCarsLabel[i]);
            ImageIcon icon = new ImageIcon("./Pictures/" + carTypes[i] + ".png");
            Car baseCarButtons = new Car(carTypes[i], icon);
            baseCarButtons.addActionListener(new ActionListener() {
                @Override  
                public void actionPerformed(ActionEvent e) {
                    String carType = ((JButton)e.getSource()).getText();
                    if(carType.equals("Sedan")) {
                        numOfCars[0]++;
                        numOfCarsLabel[0].setText(Integer.toString(numOfCars[0]));
                    } else if(carType.equals("Van")) {
                        numOfCars[1]++;
                        numOfCarsLabel[1].setText(Integer.toString(numOfCars[1]));
                    } else {
                        numOfCars[2]++;
                        numOfCarsLabel[2].setText(Integer.toString(numOfCars[2]));
                    }
                    
                }
            });

            remove[i] = new JButton("remove" + carTypes[i]);
            remove[i].setPreferredSize(new DimensionUIResource(150, 100));
            remove[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    String carType = ((JButton)e.getSource()).getText();
                    if(carType.contains("Sedan") && numOfCars[0] > 0) {
                        numOfCars[0]--;
                        numOfCarsLabel[0].setText(Integer.toString(numOfCars[0]));
                    } else if(carType.contains("Van") && numOfCars[1] > 0) {
                        numOfCars[1]--;
                        numOfCarsLabel[1].setText(Integer.toString(numOfCars[0]));
                    } else if (numOfCars[2] > 0) {
                        numOfCars[2]--;
                        numOfCarsLabel[2].setText(Integer.toString(numOfCars[0]));
                    }
                }
            });

            panelsForCars[i] = new JPanel();
            panelsForCars[i].setLayout(new BoxLayout(panelsForCars[i], BoxLayout.Y_AXIS));
            panelsForCars[i].add(baseCarButtons);
            panelsForCars[i].add(remove[i]);
            
            allButtons.add(panelsForCars[i]);
        }

        JButton confirm = new JButton("Confirm");
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                addCars(numOfCars);
                totalCapacity = calculateSeat();
                if(totalCapacity < groupSize) {
                    int howManySedansToAdd = (int) Math.ceil((double)(groupSize-totalCapacity)/5);
                    JOptionPane.showMessageDialog(panelsForCars[0],
                        "You dont have enough space for all of your people, you will need to rent " + howManySedansToAdd + " more Sedans\nRenting more cars for you",
                        "Insane error",
                        JOptionPane.ERROR_MESSAGE);

                    addCars(howManySedansToAdd);
                }
                finished = true;
            }
        });

        add(carSelectPrompt);
        add(allButtons,BorderLayout.CENTER);
        add(labelHolder,BorderLayout.PAGE_END);
        add(confirm);
    }

    private void addCars(int numbOfCars) {
        for(int i = 0; i < numbOfCars; i++) {
            cars.add(new Car("Sedan", true));
        }
    }

    private void addCars(int[] numbOfCars){
        Car carToAdd = new Car("", false);
        for(int i = 0; i < numbOfCars.length; i++) {
            for(int j = 0; j < numbOfCars[i]; j++) {
                switch(j){
                    case 0:
                        carToAdd = new Car("Sedan", false);
                        break;
                    case 1:
                        carToAdd = new Car("Van", false);
                        break;
                    case 2:
                        carToAdd = new Car("Coupe", false);
                        break;
                }
                cars.add(carToAdd);
            }
        }
    }

    private int calculateSeat() {
        int allSeats = 0;
        for(int i = 0; i < cars.size(); i++) {
            allSeats += cars.get(i).getCapacity();
        }
        return allSeats;
    }

    public boolean isFinished(){
        return finished;
    }

    public double getTotalHourlyRentalFees(){
        double allRentalFeesPerHour = 0;
        for(int i = 0; i < cars.size(); i++)
        {
            if(cars.get(i).isRental()){
                allRentalFeesPerHour += RENTAL_FEE;
            }
        }
        return allRentalFeesPerHour;
    }

    public int getTotalCars() {
        return cars.size();
    }
}
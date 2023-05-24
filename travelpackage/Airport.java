package travelpackage;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import vehiclepackage.*;

public class Airport extends JPanel {
    private Plane selectedPlane;
    
    public Airport(int distance)
    {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        String[] planeBrands = {"American", "Spirit", "United", "Delta"};
        double[] planeCost = {0.7, 0.5, 0.8, 1.1}; // per mile

        JPanel airlineSelection1 = new JPanel();
        JPanel airlineSelection2 = new JPanel();
        JPanel textHelperPanel = new JPanel();
        JLabel airlineSelect = new JLabel("Select which airline you would like to use: ",SwingConstants.CENTER);
        textHelperPanel.add(airlineSelect);
        airlineSelect.setFont(new Font("Arial", Font.ITALIC, 30));

        for(int i = 0; i < planeBrands.length; i++) {
            ImageIcon icon = new ImageIcon("./Pictures/" + planeBrands[i] + ".png");
            Plane plane = new Plane(planeBrands[i], icon, planeCost[i], distance);
            plane.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int result = JOptionPane.showConfirmDialog(null, "Are you sure?");
                    switch (result) {
                        case JOptionPane.YES_OPTION:
                            selectedPlane = (Plane) e.getSource();
                            break;
                        default:
                            JOptionPane.getRootFrame().dispose();
                    }
                }
            });

            if(i < 2) {          
                airlineSelection1.add(plane);
            } else {
                airlineSelection2.add(plane);
            }
        }
        add(textHelperPanel,BorderLayout.CENTER);
        add(airlineSelection1);
        add(airlineSelection2);
    }

    public Plane getSelectedPlane() {
        return selectedPlane;
    }

}

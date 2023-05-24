package guipackage;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;

public class TravelModePanel extends JPanel {
    private String selectedModeOfTravel = "";
    private JPanel buttonPanel;
       
    public TravelModePanel(){
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        JPanel textHelperPanel = new JPanel();
        JLabel pickTravelPrompt = new JLabel("Choose the type of travel:", SwingConstants.CENTER);
        textHelperPanel.add(pickTravelPrompt);
        pickTravelPrompt.setFont(new Font("Arial", Font.PLAIN, 30));
        JButton airButton = new JButton("By Air");
        airButton.addActionListener(new ButtonClicked());
        airButton.setPreferredSize(new DimensionUIResource(300, 200));
        JButton carButton = new JButton("By Road");
        carButton.addActionListener(new ButtonClicked());
        carButton.setPreferredSize(new DimensionUIResource(300, 200));

        add(textHelperPanel,BorderLayout.CENTER);
        buttonPanel = new JPanel();
        ImageIcon carAndPlane = new ImageIcon("./Pictures/pickTravel.png");
        JLabel cpImg = new JLabel(carAndPlane);
        add(cpImg);
        buttonPanel.add(airButton,BorderLayout.AFTER_LAST_LINE);
        buttonPanel.add(carButton,BorderLayout.AFTER_LINE_ENDS);
        add(buttonPanel);
        
    }

    public String getTravelMode(){
        return selectedModeOfTravel;
    }

    private class ButtonClicked implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            selectedModeOfTravel = ((JButton) e.getSource()).getText();
        }
    }
}
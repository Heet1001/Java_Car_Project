package travelpackage;

import javax.swing.*;
import java.awt.event.*;
import java.awt.GridLayout;

import guipackage.*;

public class State extends JButton {
    private static JPanel stateSelection = new JPanel(new GridLayout(10, 5));
    private static State selectedState;
    private int distanceFromHome;

    public State(String name, int distance, MainFrame mainFrame, JPanel nextPanel) {
        super(name);
        distanceFromHome = distance;
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "Are you sure?");
                switch (result) {
                    case JOptionPane.YES_OPTION:
                        selectedState = (State) e.getSource();
                        mainFrame.resetFrame(nextPanel);
                        break;
                    default:
                        JOptionPane.getRootFrame().dispose();
                }
            }
        });

        stateSelection.add(this);
    }

    public int getDistance() {
        return distanceFromHome;
    }

    public static State getSelectedState() {
        return selectedState;
    }

    public static JPanel getJPanel() {
        return stateSelection;
    }
}